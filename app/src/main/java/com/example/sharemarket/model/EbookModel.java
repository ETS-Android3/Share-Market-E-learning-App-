package com.example.sharemarket.model;

public class EbookModel {

    String ebookname;
    String ebookimage;
    String authorname;
    String ebookurl;



    public EbookModel(String ebookname, String ebookimage,  String ebookurl, String authorname) {
        this.ebookname = ebookname;
        this.ebookimage = ebookimage;
        this.authorname = authorname;
        this.ebookurl = ebookurl;

    }

    public EbookModel() {

    }

    public String getEbookname() {
        return ebookname;
    }

    public void setEbookname(String ebookname) {
        this.ebookname = ebookname;
    }

    public String getEbookimage() {
        return ebookimage;
    }

    public void setEbookimage(String ebookimage) {
        this.ebookimage = ebookimage;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getEbookurl(){return ebookurl;}
    public void setEbookurl(String ebookurl) {this.ebookurl = ebookurl;}

}
