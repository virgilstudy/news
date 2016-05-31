package org.news.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.news.bean.NewsContent;
import org.news.bean.NewsItem;

import java.sql.SQLException;

/**
 * Created by virgil on 2016/5/31.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "virgilNews.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<NewsContent, Integer> newsContentDao = null;
    private Dao<NewsItem, Integer> newsItemDao = null;
    private RuntimeExceptionDao<NewsItem, Integer> newsItemRuntimeDao = null;
    private RuntimeExceptionDao<NewsContent, Integer> newsContentRuntimeDao = null;
    private static DataBaseHelper helper = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new DataBaseHelper(context);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, NewsItem.class);
            TableUtils.createTable(connectionSource, NewsContent.class);
            newsItemDao = getNewsItemDao();
            newsContentDao = getNewsContentDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, NewsItem.class, true);
            TableUtils.dropTable(connectionSource, NewsContent.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<NewsContent, Integer> getNewsContentDao() throws SQLException {
        if (newsContentDao == null) {
            newsContentDao = getDao(NewsContent.class);
        }
        return newsContentDao;
    }

    public Dao<NewsItem, Integer> getNewsItemDao() throws SQLException {
        if (newsItemDao == null) {
            newsItemDao = getDao(NewsItem.class);
        }
        return newsItemDao;
    }

    public RuntimeExceptionDao<NewsItem, Integer> getNewsItemRuntimeDao() {
        if (newsItemRuntimeDao == null) {
            newsItemRuntimeDao = getRuntimeExceptionDao(NewsItem.class);
        }
        return newsItemRuntimeDao;
    }

    public RuntimeExceptionDao<NewsContent, Integer> getNewsContentRuntimeDao() {
        if (newsContentRuntimeDao == null) {
            newsContentRuntimeDao = getRuntimeExceptionDao(NewsContent.class);
        }
        return newsContentRuntimeDao;
    }

}
