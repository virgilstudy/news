package org.news.dao;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import org.news.bean.NewsContent;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by virgil on 2016/5/31.
 */
public class NewsContentDao {
    private RuntimeExceptionDao<NewsContent, Integer> mNewsContentDao;
    private DataBaseHelper mDataBaseHelper;

    public NewsContentDao(Context context) {
        mDataBaseHelper = DataBaseHelper.getHelper(context);
        this.mNewsContentDao = mDataBaseHelper.getNewsContentRuntimeDao();
    }

    public void createOrUpdate(NewsContent content) {
        mNewsContentDao.createOrUpdate(content);
    }

    public void create(NewsContent content) {
        mNewsContentDao.create(content);
    }

    public int deleteByTitle(String title) throws SQLException {
        DeleteBuilder<NewsContent, Integer> deleteBuilder = mNewsContentDao.deleteBuilder();
        deleteBuilder.where().eq("title", title);
        return deleteBuilder.delete();
    }

    public int deleteByUrl(String url) throws SQLException {
        DeleteBuilder<NewsContent, Integer> deleteBuilder = mNewsContentDao.deleteBuilder();
        deleteBuilder.where().eq("url", url);
        return deleteBuilder.delete();
    }

    public void deleteAll() {
        mNewsContentDao.delete(queryAll());
    }

    public NewsContent searchByTitle(String title) throws SQLException {
        List<NewsContent> list = mNewsContentDao.queryBuilder().where().eq("title", title).query();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List<NewsContent> queryAll() {
        List<NewsContent> list = mNewsContentDao.queryForAll();
        return list;
    }

    public NewsContent searchByUrl(String url) throws SQLException {
        List<NewsContent> list = mNewsContentDao.queryBuilder().where().eq("url", url).query();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
