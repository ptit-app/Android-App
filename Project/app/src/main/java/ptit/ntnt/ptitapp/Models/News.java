package ptit.ntnt.ptitapp.Models;

import java.util.Date;

import ptit.ntnt.ptitapp.R;

/**
 * Created by datshiro on 11/03/2018.
 */

public class News {
    private String id;
    private String title;
    private String content;
    private String authorId;
    private int featureImageId;
    private Date createdAt;
    private Date modifiedAt;
    

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId='" + authorId + '\'' +
                ", featureImageId=" + featureImageId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    void UpdateModifyTime(){
        this.modifiedAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return authorId;
    }

    public int getFeatureImageId() {
        return featureImageId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public News setId(String id) {
        this.id = id;
        UpdateModifyTime();
        return this;
    }

    public News setTitle(String title) {
        this.title = title;
        UpdateModifyTime();
        return this;
    }

    public News setContent(String content) {
        this.content = content;
        UpdateModifyTime();
        return this;
    }

    public News setAuthor(String author) {
        this.authorId = author;
        UpdateModifyTime();
        return this;
    }

    public News setFeatureImageId(int featureImageId) {
        this.featureImageId = featureImageId;
        UpdateModifyTime();
        return this;
    }

    public News setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        UpdateModifyTime();
        return this;
    }

    public News setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        UpdateModifyTime();
        return this;
    }
}
