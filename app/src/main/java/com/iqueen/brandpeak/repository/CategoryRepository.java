package com.iqueen.brandpeak.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.iqueen.brandpeak.Config;
import com.iqueen.brandpeak.api.ApiClient;
import com.iqueen.brandpeak.api.ApiResponse;
import com.iqueen.brandpeak.api.common.NetworkBoundResource;
import com.iqueen.brandpeak.api.common.common.Resource;
import com.iqueen.brandpeak.database.AppDatabase;
import com.iqueen.brandpeak.database.CategoryDao;
import com.iqueen.brandpeak.database.CustomCategoryDao;
import com.iqueen.brandpeak.items.BusinessCategoryItem;
import com.iqueen.brandpeak.items.BusinessSubCategoryItem;
import com.iqueen.brandpeak.items.CategoryItem;
import com.iqueen.brandpeak.items.CustomCategory;
import com.iqueen.brandpeak.items.CustomModel;
import com.iqueen.brandpeak.utils.Util;

import java.util.List;

public class CategoryRepository {

    public AppDatabase db;
    public CategoryDao categoryDao;
    public CustomCategoryDao customCategoryDao;

    public CategoryRepository(Application application) {

        db = AppDatabase.getInstance(application);
        categoryDao = db.getCategoryDao();
        customCategoryDao = db.getCustomCategoryDao();

    }

    public LiveData<Resource<List<CategoryItem>>> getCategory(String apiKey, String page) {
        return new NetworkBoundResource<List<CategoryItem>, List<CategoryItem>>() {
            @Override
            protected void saveCallResult(@NonNull List<CategoryItem> item) {
                try {
                    db.runInTransaction(() -> {
                        categoryDao.deleteTable();
                        categoryDao.insert(item);
                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CategoryItem> data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<List<CategoryItem>> loadFromDb() {
                return categoryDao.getCategoryItems();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<CategoryItem>>> createCall() {
                return ApiClient.getApiService().getCategory(apiKey, page);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<CustomCategory>>> getCustomCategory(String apiKey, String page) {
        return new NetworkBoundResource<List<CustomCategory>, List<CustomCategory>>() {
            @Override
            protected void saveCallResult(@NonNull List<CustomCategory> item) {

                try {
                    db.runInTransaction(() -> {
                        customCategoryDao.deleteTable();
                        customCategoryDao.insertAll(item);
                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<CustomCategory> data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<List<CustomCategory>> loadFromDb() {
                return customCategoryDao.getCustomCategory();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<CustomCategory>>> createCall() {
                return ApiClient.getApiService().getCustomCategory(apiKey, page);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<BusinessCategoryItem>>> getBusinessCategories(String apiKey) {
        return new NetworkBoundResource<List<BusinessCategoryItem>, List<BusinessCategoryItem>>() {
            @Override
            protected void saveCallResult(@NonNull List<BusinessCategoryItem> item) {
                try {
                    db.runInTransaction(() -> {
                        categoryDao.deleteBusinessTable();
                        categoryDao.insertBusinessCategory(item);
                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<BusinessCategoryItem> data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<List<BusinessCategoryItem>> loadFromDb() {
                return categoryDao.getBusinessCategoryItems();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<BusinessCategoryItem>>> createCall() {
                return ApiClient.getApiService().getBusinessCategory(apiKey);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<BusinessSubCategoryItem>>> getBusinessSubCategory(String apiKey, String categoryId) {
        return new NetworkBoundResource<List<BusinessSubCategoryItem>, List<BusinessSubCategoryItem>>() {
            @Override
            protected void saveCallResult(@NonNull List<BusinessSubCategoryItem> item) {
                try {
                    db.runInTransaction(() -> {
                        categoryDao.deleteBusinessSubTable(categoryId);
                        for (BusinessSubCategoryItem item1 : item) {
                            categoryDao.insertBusinessSub(new BusinessSubCategoryItem(item1.businessCategoryId,
                                    categoryId, item1.businessCategoryName, item1.businessCategoryIcon));
                        }
                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<BusinessSubCategoryItem> data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<List<BusinessSubCategoryItem>> loadFromDb() {
                return categoryDao.getBusinessSubItems(categoryId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<BusinessSubCategoryItem>>> createCall() {
                return ApiClient.getApiService().getBusinessSubCategory(apiKey, categoryId);
            }
        }.asLiveData();
    }

    public LiveData<Resource<CustomModel>> getCustomModel(String apiKey) {
        return new NetworkBoundResource<CustomModel, CustomModel>() {
            @Override
            protected void saveCallResult(@NonNull CustomModel item) {
                try {
                    db.runInTransaction(() -> {
                        customCategoryDao.deleteCustomModel();
                        customCategoryDao.insertCustomModel(item);
                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable CustomModel data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<CustomModel> loadFromDb() {
                return customCategoryDao.getCustomModelItems();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CustomModel>> createCall() {
                return ApiClient.getApiService().getAllCustom(apiKey);
            }
        }.asLiveData();
    }
}
