package com.iqueen.brandpeak.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iqueen.brandpeak.items.BusinessCategoryItem;
import com.iqueen.brandpeak.items.CategoryItem;
import com.iqueen.brandpeak.items.CustomCategory;
import com.iqueen.brandpeak.items.CustomInModel;
import com.iqueen.brandpeak.items.EarningItem;
import com.iqueen.brandpeak.items.FeatureItem;
import com.iqueen.brandpeak.items.FestivalItem;
import com.iqueen.brandpeak.items.PostItem;
import com.iqueen.brandpeak.items.ProductCatItem;
import com.iqueen.brandpeak.items.ProductItem;
import com.iqueen.brandpeak.items.StickerCategory;
import com.iqueen.brandpeak.items.StickerItem;
import com.iqueen.brandpeak.items.StickerModel;
import com.iqueen.brandpeak.items.StoryItem;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverters implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<String> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<String> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromStickers(List<StickerItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StickerItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<StickerItem> toStickers(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StickerItem>>() {
        }.getType();
        List<StickerItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromEarning(List<EarningItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<EarningItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<EarningItem> toEarning(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<EarningItem>>() {
        }.getType();
        List<EarningItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromStickerCate(List<StickerCategory> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StickerCategory>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<StickerCategory> toStickerCate(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StickerCategory>>() {
        }.getType();
        List<StickerCategory> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromProductCat(List<ProductCatItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductCatItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<ProductCatItem> toProductCat(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductCatItem>>() {
        }.getType();
        List<ProductCatItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromProduct(List<ProductItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<ProductItem> toProduct(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductItem>>() {
        }.getType();
        List<ProductItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }


    @TypeConverter // note this annotation
    public String fromStickerModel(List<StickerModel> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StickerModel>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<StickerModel> toStickerModel(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StickerModel>>() {
        }.getType();
        List<StickerModel> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromStory(List<StoryItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StoryItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<StoryItem> toStory(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StoryItem>>() {
        }.getType();
        List<StoryItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromFestival(List<FestivalItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FestivalItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<FestivalItem> toFestival(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FestivalItem>>() {
        }.getType();
        List<FestivalItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }



    @TypeConverter // note this annotation
    public String fromBusinessCategory(List<BusinessCategoryItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<BusinessCategoryItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<BusinessCategoryItem> toBusinessCategory(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<BusinessCategoryItem>>() {
        }.getType();
        List<BusinessCategoryItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }


    @TypeConverter // note this annotation
    public String fromCategory(List<CategoryItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoryItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<CategoryItem> toCategory(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoryItem>>() {
        }.getType();
        List<CategoryItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }





    @TypeConverter // note this annotation
    public String fromPost(List<PostItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PostItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<PostItem> toPost(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PostItem>>() {
        }.getType();
        List<PostItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }


    @TypeConverter // note this annotation
    public String fromFeature(List<FeatureItem> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FeatureItem>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<FeatureItem> toFeature(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FeatureItem>>() {
        }.getType();
        List<FeatureItem> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromCustomModel(List<CustomCategory> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CustomCategory>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<CustomCategory> toCustomModel(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CustomCategory>>() {
        }.getType();
        List<CustomCategory> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromCustomInModel(List<CustomInModel> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CustomInModel>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<CustomInModel> toCustomInModel(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CustomInModel>>() {
        }.getType();
        List<CustomInModel> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

}
