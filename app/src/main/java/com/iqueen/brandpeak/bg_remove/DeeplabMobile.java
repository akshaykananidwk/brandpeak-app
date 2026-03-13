package com.iqueen.brandpeak.bg_remove;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.Tensor;
import java.nio.MappedByteBuffer;

public class DeeplabMobile implements DeeplabInterface {
    private static final String MODEL_FILE = "selfie_multiclass.tflite";
    private Interpreter interpreter = null;
    private final int inputSize = 514;

    public int getInputSize() {
        return inputSize;
    }

    public boolean initialize(Context context) {
        try {
            MappedByteBuffer tfliteModel = FileUtil.loadMappedFile(context, MODEL_FILE);
            Interpreter.Options options = new Interpreter.Options();
            this.interpreter = new Interpreter(tfliteModel, options);
            return true;
        } catch (Exception e) {
            Log.e("Deeplab", "Error initializing LiteRT", e);
            return false;
        }
    }

    public boolean isInitialized() {
        return this.interpreter != null;
    }

    public Bitmap segment(Bitmap bitmap) {
        if (interpreter == null || bitmap == null) return null;

        try {
            int originalWidth = bitmap.getWidth();
            int originalHeight = bitmap.getHeight();

            // =========================
            // 1️⃣ Prepare Input
            // =========================
            Tensor inputTensor = interpreter.getInputTensor(0);
            int[] inputShape = inputTensor.shape(); // [1, H, W, 3]
            DataType inputType = inputTensor.dataType();

            int modelHeight = inputShape[1];
            int modelWidth = inputShape[2];

            ImageProcessor.Builder processorBuilder = new ImageProcessor.Builder()
                    .add(new ResizeOp(modelHeight, modelWidth, ResizeOp.ResizeMethod.BILINEAR));

            if (inputType == DataType.FLOAT32) {
                processorBuilder.add(new NormalizeOp(127.5f, 127.5f));
            }

            ImageProcessor imageProcessor = processorBuilder.build();

            TensorImage inputImage = new TensorImage(inputType);
            inputImage.load(bitmap);
            inputImage = imageProcessor.process(inputImage);

            // =========================
            // 2️⃣ Prepare Output
            // =========================
            int[] outputShape = interpreter.getOutputTensor(0).shape();
            int outputHeight = outputShape[1];
            int outputWidth = outputShape[2];

            boolean is4D = outputShape.length == 4;
            int classes = is4D ? outputShape[3] : 1;

            Bitmap maskBitmap = Bitmap.createBitmap(outputWidth, outputHeight, Bitmap.Config.ARGB_8888);
            int[] pixels = new int[outputWidth * outputHeight];

            if (is4D) {
                float[][][][] output = new float[1][outputHeight][outputWidth][classes];
                interpreter.run(inputImage.getBuffer(), output);

                for (int y = 0; y < outputHeight; y++) {
                    for (int x = 0; x < outputWidth; x++) {

                        float[] logits = output[0][y][x];

                        // ---- SOFTMAX ----
                        float maxLogit = Float.NEGATIVE_INFINITY;
                        for (int c = 0; c < classes; c++) {
                            if (logits[c] > maxLogit) maxLogit = logits[c];
                        }

                        float sum = 0f;
                        float[] probs = new float[classes];

                        for (int c = 0; c < classes; c++) {
                            probs[c] = (float) Math.exp(logits[c] - maxLogit);
                            sum += probs[c];
                        }

                        for (int c = 0; c < classes; c++) {
                            probs[c] /= sum;
                        }

                        // ---- ARGMAX ----
                        int maxIndex = 0;
                        float maxVal = probs[0];
                        for (int c = 1; c < classes; c++) {
                            if (probs[c] > maxVal) {
                                maxVal = probs[c];
                                maxIndex = c;
                            }
                        }

                        // For Pascal VOC DeepLab:
                        // class 15 = person
                        // If binary model (2 classes), class 1 = foreground

                        boolean isForeground;

                        if (classes == 21) {
                            isForeground = (maxIndex == 15); // person
                        } else if (classes == 2) {
                            isForeground = (maxIndex == 1);
                        } else {
                            isForeground = (maxIndex != 0); // fallback
                        }

                        int alpha = isForeground ? 255 : 0;
                        pixels[y * outputWidth + x] = (alpha << 24);
                    }
                }

            } else {
                // Binary probability mask [1,H,W,1] or [1,H,W]
                float[][][] output = new float[1][outputHeight][outputWidth];
                interpreter.run(inputImage.getBuffer(), output);

                for (int y = 0; y < outputHeight; y++) {
                    for (int x = 0; x < outputWidth; x++) {
                        float val = output[0][y][x];

                        // Apply sigmoid if logits
                        val = (float) (1.0 / (1.0 + Math.exp(-val)));

                        int alpha = val > 0.5f ? 255 : 0;
                        pixels[y * outputWidth + x] = (alpha << 24);
                    }
                }
            }

            maskBitmap.setPixels(pixels, 0, outputWidth, 0, 0, outputWidth, outputHeight);

            // Scale back to original image size
            return Bitmap.createScaledBitmap(maskBitmap, originalWidth, originalHeight, true);

        } catch (Exception e) {
            Log.e("Deeplab", "Segmentation error", e);
            return null;
        }
    }
}
