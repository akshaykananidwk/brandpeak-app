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
import com.iqueen.brandpeak.database.HomeDao;
import com.iqueen.brandpeak.items.BusinessCategoryItem;
import com.iqueen.brandpeak.items.HomeItem;
import com.iqueen.brandpeak.items.PersonalItem;
import com.iqueen.brandpeak.utils.Util;

import java.util.List;

public class HomeRepository {

    AppDatabase db;
    HomeDao homeDao;

    public HomeRepository(Application application) {
        db = AppDatabase.getInstance(application);
        homeDao = db.getHomeDao();
    }


    public LiveData<Resource<HomeItem>> getHomeData(String apiKey) {
        return new NetworkBoundResource<HomeItem, HomeItem>() {
            @Override
            protected void saveCallResult(@NonNull HomeItem item) {

                try {
                    db.runInTransaction(() -> {
                        homeDao.deleteTable();
                        homeDao.insertAll(item);

                        homeDao.deleteBusinessCategory();
                        homeDao.insertBusinessCategory(item.businessCategoryList);

                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable HomeItem data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<HomeItem> loadFromDb() {
                return homeDao.getHomeItem();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<HomeItem>> createCall() {
                return ApiClient.getApiService().getHomeData(apiKey);
            }
        }.asLiveData();
    }

    public LiveData<List<BusinessCategoryItem>> getBusinessCategory() {
        return homeDao.getBusinessCategoryList();
    }

    public LiveData<Resource<List<PersonalItem>>> getPersonalItems(String apiKey) {
        return new NetworkBoundResource<List<PersonalItem>, List<PersonalItem>>() {
            @Override
            protected void saveCallResult(@NonNull List<PersonalItem> item) {
                try {
                    db.runInTransaction(() -> {
                        homeDao.deletePersonal();
                        homeDao.insertPersonal(item);
                    });
                } catch (Exception ex) {
                    Util.showErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<PersonalItem> data) {
                return Config.IS_CONNECTED;
            }

            @NonNull
            @Override
            protected LiveData<List<PersonalItem>> loadFromDb() {
                return homeDao.getPersonalList();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<PersonalItem>>> createCall() {
                return ApiClient.getApiService().getPersonal(apiKey);
            }
        }.asLiveData();
    }
}
