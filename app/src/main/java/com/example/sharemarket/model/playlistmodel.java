package com.example.sharemarket.model;

public class playlistmodel {
    String videoname;
    String videourl;
    String playlistimage;
    String authorname;



    public playlistmodel(String videoname, String videourl, String playlistimage, String authorname) {
        this.videoname = videoname;
        this.videourl = videourl;
        this.playlistimage = playlistimage;
        this.authorname = authorname;
    }

    public playlistmodel() {
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

     public String getPlaylistimage() {
        return playlistimage;
    }

    public void setPlaylistimage(String playlistimage) {
        this.playlistimage = playlistimage;
    }

    public String getAuthorname() {return authorname;}

    public void setAuthorname(String authorname) {this.authorname = authorname;}




}
