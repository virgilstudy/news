package org.news.bean;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by virgil on 2016/5/31.
 */
public class NewsItem {
    private int id;
    @DatabaseField
    private int type;
    @DatabaseField
    private String title;
    @DatabaseField(id = true)
    private String url;
    @DatabaseField(canBeNull = true)
    private String date;
    @DatabaseField
    private String content;
    @DatabaseField
    private int pageNumber;
    @DatabaseField
    private String source;
    @DatabaseField(canBeNull = false)
    private Date updatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
