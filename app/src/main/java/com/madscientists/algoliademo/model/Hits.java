package com.madscientists.algoliademo.model;

/**
 * Created by madscientist on 7/10/17.
 */

public class Hits {

    /**
     * created_at : 2014-03-30T21:47:30.000Z
     * title : Why Sports Are A Sad and Dangerous Waste of Time
     * url : https://medium.com/sociology-of-sport/3530f0ee579a
     * author : joelrunyon
     * points : 15
     * story_text :
     * comment_text : null
     * num_comments : 16
     * story_id : null
     * story_title : null
     * story_url : null
     * parent_id : null
     * created_at_i : 1396216050
     * _tags : ["story","author_joelrunyon","story_7498083"]
     * objectID : 7498083
     * _highlightResult : {"title":{},"url":{"value":"https://medium.com/sociology-of-sport/3530f0ee579a","matchLevel":"none","matchedWords":[]},"author":{"value":"joelrunyon","matchLevel":"none","matchedWords":[]},"story_text":{"value":"","matchLevel":"none","matchedWords":[]}}
     */

    private String created_at;
    private String title;
    private String url;
    private String author;
    private int points;
    private String story_text;
    private Object comment_text;
    private int num_comments;
    private Object story_id;
    private Object story_title;
    private Object story_url;
    private Object parent_id;
    private int created_at_i;
    private String objectID;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStory_text() {
        return story_text;
    }

    public void setStory_text(String story_text) {
        this.story_text = story_text;
    }

    public Object getComment_text() {
        return comment_text;
    }

    public void setComment_text(Object comment_text) {
        this.comment_text = comment_text;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public Object getStory_id() {
        return story_id;
    }

    public void setStory_id(Object story_id) {
        this.story_id = story_id;
    }

    public Object getStory_title() {
        return story_title;
    }

    public void setStory_title(Object story_title) {
        this.story_title = story_title;
    }

    public Object getStory_url() {
        return story_url;
    }

    public void setStory_url(Object story_url) {
        this.story_url = story_url;
    }

    public Object getParent_id() {
        return parent_id;
    }

    public void setParent_id(Object parent_id) {
        this.parent_id = parent_id;
    }

    public int getCreated_at_i() {
        return created_at_i;
    }

    public void setCreated_at_i(int created_at_i) {
        this.created_at_i = created_at_i;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
