package com.debajyotibasak.testnews.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.debajyotibasak.testnews.model.NewsArticle;

import java.util.List;

/**
 * Created by debajyotibasak on 12/03/18.
 */
@Dao
public interface NewsArticleDao {
    /**
     * Insert articles into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertArticles(List<NewsArticle> articles);

    /**
     * Get all the articles from database
     */
    @Query("SELECT * FROM news_article")
    LiveData<List<NewsArticle>> getNewsArticles();
}
