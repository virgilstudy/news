package org.news.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by virgil on 2016/5/31.
 */
public class NewsContent {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String date;
    @DatabaseField
    private String author;
    @DatabaseField
    private String source;
    @DatabaseField(canBeNull = false)
    private String url;
    @DatabaseField
    private String content;

    public String getFormatContent() {
        return content;
    }

    public String toString() {
        return "NewsContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
//                ", type=" + type +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", contents=" + content +
//                ", imgUrls=" + imgUrls +
                '}';
    }

    public String addContent(String content) {
        if (this.content == null) {
            this.content = "\t\t" + content;
            return this.content;
        }
        this.content += "\t\t" + content + "\n\n";
        return this.content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
