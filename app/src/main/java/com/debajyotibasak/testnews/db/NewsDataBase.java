package com.debajyotibasak.testnews.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.debajyotibasak.testnews.model.NewsArticle;

/**
 * Created by debajyotibasak on 12/03/18.
 */

@Database(entities = {NewsArticle.class}, version = 1, exportSchema = false)
public abstract class NewsDataBase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static volatile NewsDataBase sInstance;

    public static NewsDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), NewsDataBase.class, "news-db").build();
                }
            }
        }
        return sInstance;
    }

    public abstract NewsArticleDao newsArticleDao();
}
