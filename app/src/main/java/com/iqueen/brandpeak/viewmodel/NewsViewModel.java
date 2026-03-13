package com.iqueen.brandpeak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.iqueen.brandpeak.api.common.common.Resource;
import com.iqueen.brandpeak.items.NewsItem;
import com.iqueen.brandpeak.repository.NewsRepository;
import com.iqueen.brandpeak.utils.AbsentLiveData;
import com.iqueen.brandpeak.utils.Constant;
import com.iqueen.brandpeak.utils.PrefManager;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    NewsRepository newsRepository;

    public LiveData<Resource<List<NewsItem>>> result;
    public MutableLiveData<TmpDataHolder> newsObj = new MutableLiveData<>();

    PrefManager prefManager;

    public NewsViewModel(@NonNull Application application) {
        super(application);

        newsRepository = new NewsRepository(application);
        prefManager = new PrefManager(application);

        result = Transformations.switchMap(newsObj, obj->{
            if (obj==null){
                return AbsentLiveData.create();
            }
            return newsRepository.getNews(prefManager.getString(Constant.api_key), obj.page);
        });
    }

    public void setNewsObj(String page){
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.page = page;
        newsObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<NewsItem>>> getNews(){
        return result;
    }

    class TmpDataHolder {
        public String page = "";
    }
}
