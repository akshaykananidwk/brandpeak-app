package com.iqueen.brandpeak.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.iqueen.brandpeak.items.AppInfo;
import com.iqueen.brandpeak.items.AppVersion;
import com.iqueen.brandpeak.items.BusinessCategoryItem;
import com.iqueen.brandpeak.items.BusinessItem;
import com.iqueen.brandpeak.items.BusinessSubCategoryItem;
import com.iqueen.brandpeak.items.CategoryItem;
import com.iqueen.brandpeak.items.CustomCategory;
import com.iqueen.brandpeak.items.CustomModel;
import com.iqueen.brandpeak.items.DynamicFrameItem;
import com.iqueen.brandpeak.items.EarningItem;
import com.iqueen.brandpeak.items.FestivalItem;
import com.iqueen.brandpeak.items.FrameCategoryItem;
import com.iqueen.brandpeak.items.HomeItem;
import com.iqueen.brandpeak.items.ItemVcard;
import com.iqueen.brandpeak.items.LanguageItem;
import com.iqueen.brandpeak.items.MainStrModel;
import com.iqueen.brandpeak.items.NewsItem;
import com.iqueen.brandpeak.items.OfferItem;
import com.iqueen.brandpeak.items.PersonalItem;
import com.iqueen.brandpeak.items.PostItem;
import com.iqueen.brandpeak.items.ProductCatItem;
import com.iqueen.brandpeak.items.ProductItem;
import com.iqueen.brandpeak.items.ProductModel;
import com.iqueen.brandpeak.items.ReferDetail;
import com.iqueen.brandpeak.items.StickerCategory;
import com.iqueen.brandpeak.items.StickerItem;
import com.iqueen.brandpeak.items.StickerModel;
import com.iqueen.brandpeak.items.StoryItem;
import com.iqueen.brandpeak.items.SubjectItem;
import com.iqueen.brandpeak.items.SubsPlanItem;
import com.iqueen.brandpeak.items.UserFrame;
import com.iqueen.brandpeak.items.UserItem;
import com.iqueen.brandpeak.items.UserLogin;

@Database(entities = {StoryItem.class, FestivalItem.class, CategoryItem.class, PostItem.class,
        LanguageItem.class, UserItem.class,
        UserLogin.class, BusinessItem.class, SubsPlanItem.class,
        SubjectItem.class, NewsItem.class, AppVersion.class, AppInfo.class, CustomCategory.class, HomeItem.class,
        BusinessCategoryItem.class, CustomModel.class, UserFrame.class, ItemVcard.class,
        StickerItem.class, StickerCategory.class, StickerModel.class, MainStrModel.class, OfferItem.class,
        DynamicFrameItem.class, ProductCatItem.class, ProductItem.class, ProductModel.class, ReferDetail.class,
        EarningItem.class, BusinessSubCategoryItem.class, PersonalItem.class, FrameCategoryItem.class}, version = 39, exportSchema = false)
@TypeConverters({DataConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "festival_database";

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract StoryDao getStoryDao();

    public abstract FestivalDao getFestivalDao();

    public abstract CategoryDao getCategoryDao();

    public abstract PostDao getPostDao();

    public abstract LanguageDao getLanguageDao();

    public abstract UserDao getUserDao();

    public abstract BusinessDao getBusinessDao();

    public abstract SubsPlanDao getSubsPlanDao();

    public abstract NewsDao getNewsDao();

    public abstract UserLoginDao getUserLoginDao();

    public abstract CustomCategoryDao getCustomCategoryDao();

    public abstract HomeDao getHomeDao();

    public abstract VCardDao getVCardDao();

    public abstract FrameDao getFrameDao();

    public abstract ProductDao getProductDao();
}

