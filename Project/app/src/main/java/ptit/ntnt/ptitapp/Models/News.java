package ptit.ntnt.ptitapp.Models;

import android.icu.lang.UProperty;

import java.text.SimpleDateFormat;
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
    private String createdAt;
    private String modifiedAt;
    private String description;

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId='" + authorId + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    void UpdateModifyTime(){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
        this.modifiedAt = formater.format(new Date());
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

    public String getAuthorId() {
        return authorId;
    }

    public News setAuthorId(String authorId) {
        this.authorId = authorId;
        this.UpdateModifyTime();
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public News setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public News setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
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


    public String getDescription() {
        return description;
    }

    public News setDescription(String description) {
        this.description = description;
        UpdateModifyTime();
        return this;
    }
}
