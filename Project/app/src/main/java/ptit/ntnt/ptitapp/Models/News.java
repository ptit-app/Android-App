package ptit.ntnt.ptitapp.Models;

import java.util.Date;

import ptit.ntnt.ptitapp.R;

/**
 * Created by datshiro on 11/03/2018.
 */

public class News {
    private String title;
    private String content;
    private User author;
    private int featureImageId;
    private Date createdAt;
    private Date modifiedAt;
    

    public News() {
    }

    public News(String title, String content) {
        this.title = title;
        this.content = content;
        this.featureImageId = R.drawable.user_avatar;
    }

    public News(String title, String content, User author, int featureImageId, Date createdAt, Date modifiedAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.featureImageId = featureImageId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public News(String title, String content, User author, int featureImageId) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.featureImageId = featureImageId;
        this.createdAt = new Date();
        this.modifiedAt = new Date();
    }

    void UpdateModifyTime(){
        this.modifiedAt = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        UpdateModifyTime();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        UpdateModifyTime();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        UpdateModifyTime();
    }

    public int getFeatureImageId() {
        return featureImageId;
    }

    public void setFeatureImageId(int featureImageId) {
        this.featureImageId = featureImageId;
        UpdateModifyTime();
    }
}
