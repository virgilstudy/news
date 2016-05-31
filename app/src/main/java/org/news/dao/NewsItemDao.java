package org.news.dao;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import org.news.bean.NewsItem;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by virgil on 2016/5/31.
 */
public class NewsItemDao {
    private RuntimeExceptionDao<NewsItem, Integer> mNewsItemDao;
    private DataBaseHelper mDataBaseHelper;

    public NewsItemDao(Context context) {
        mDataBaseHelper = DataBaseHelper.getHelper(context);
        this.mNewsItemDao = mDataBaseHelper.getNewsItemRuntimeDao();
    }

    public void createOrUpdate(NewsItem item) {
        mNewsItemDao.createOrUpdate(item);
    }

    public int deleteByTitle(String title) throws SQLException {
        DeleteBuilder<NewsItem, Integer> deleteBuilder = mNewsItemDao.deleteBuilder();
        deleteBuilder.where().eq("title", title);
        return deleteBuilder.delete();
    }

    public int deleteByUrl(String url) throws SQLException {
        DeleteBuilder<NewsItem, Integer> deleteBuilder = mNewsItemDao.deleteBuilder();
        deleteBuilder.where().eq("url", url);
        return deleteBuilder.delete();
    }

    public void deleteAll() {
        mNewsItemDao.delete(queryAll());
    }

    public List<NewsItem> queryAll() {
        return mNewsItemDao.queryForAll();
    }

    public NewsItem searchByTitle(String title) throws SQLException {
        List<NewsItem> list = mNewsItemDao.queryBuilder().where().eq("title", title).query();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NewsItem searchByUrl(String url) throws SQLException {
        List<NewsItem> list = mNewsItemDao.queryBuilder().where().eq("url", url).query();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List<NewsItem> searchByPageAndType(int page, int type) throws SQLException {
        List<NewsItem> list = mNewsItemDao.queryBuilder().where().eq("pageNumber", page).and().eq("type", type).query();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
}
