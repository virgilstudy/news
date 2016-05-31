package org.news.biz;

import android.content.Context;
import android.util.Log;

import org.news.bean.NewsItem;
import org.news.dao.NewsContentDao;
import org.news.dao.NewsItemDao;
import org.news.utils.SuesApiUtils;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by virgil on 2016/5/31.
 * 处理新闻的业务逻辑
 */
public class NewsItemBiz {
    //列表页相关
    private static final String BASE_TABLE_CLASS = "border1";
    private static final String COLUMN_TABLE_CLASS = "columnStyle";
    private static final String POST_TIME_CLASS = "posttime";
    private static final String NEWS_SOURCE_CLASS = "derivation";
    //新闻内容相关
    private static final String NEWS_TITLE_CLASS = "biaoti";
    private static final String NEWS_MATA_CLASS = "postmeta";
    private static final String NEWS_META_ITEM_CLASS = "meta_item";
    private static final String NEWS_ARTICLE_CLASS = "article";
    private static final int OUTOFTIME_MINUTE = 30;
    private List<NewsItem> mNewsItemCache;//newsItem缓存
    private Context mContext;
    private NewsItemDao newsItemDao;
    private NewsContentDao newsContentDao;

    public NewsItemBiz(Context context) {
        mContext = context;
        newsContentDao = new NewsContentDao(context);
        newsItemDao = new NewsItemDao(context);
    }

    public <T> int isOutOfTime(T t) {
        if (t instanceof NewsItem) {
            return ((NewsItem) t).getUpdatetime().compareTo(getUnOutOfTimeDate());
        }
        return -1;
    }

    public Date getUnOutOfTimeDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -OUTOFTIME_MINUTE);
        return calendar.getTime();
    }

    public List<NewsItem> getmNewsItemCache(int newsType, int currentPage, boolean isNeedRefresh) throws SQLException {
        if (mNewsItemCache == null || isNeedRefresh) {
            mNewsItemCache = newsItemDao.searchByPageAndType(currentPage, newsType);
        }
        return mNewsItemCache;
    }

    public void setmNewsItemCache(List<NewsItem> mNewsItemCache) {
        this.mNewsItemCache = mNewsItemCache;
    }

    public List<NewsItem> getNewsItem(int type, int page, boolean netAvailable) throws Exception {
        //当无网络时加载数据库中数据
        Log.i("ASDNET", "netAvailable:" + netAvailable);
        if (!netAvailable) {
            return getmNewsItemCache(type, page, false);
        }
        //有网络时查看数据是否过期,未过期则返回缓存数据
        if (getmNewsItemCache(type, page, false) != null && this.isOutOfTime(getmNewsItemCache(type, page, false).get(0)) > 0) {
            return getmNewsItemCache(type, page, true);
        }
        //若数据已过期，则重新获取
        String url = SuesApiUtils.getNewsUrl(type, page);
        

    }
}
