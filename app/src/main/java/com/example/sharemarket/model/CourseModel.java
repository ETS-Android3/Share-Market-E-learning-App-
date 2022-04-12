package com.example.sharemarket.model;

public class CourseModel {

    String coursename;
    String thumbnail;
    String id;
    String authorname;

    public CourseModel(String coursename, String thumbnail, String id, String authorname) {
        this.coursename = coursename;
        this.thumbnail = thumbnail;
        this.id = id;
        this.authorname = authorname;
    }

    public CourseModel() {
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorname() {return authorname;}

    public void setAuthorname(String authorname) {this.authorname =  authorname;}

}
