/*
 *      Copyright (c) 2004-2011 YAMJ Members
 *      http://code.google.com/p/moviejukebox/people/list 
 *  
 *      Web: http://code.google.com/p/moviejukebox/
 *  
 *      This software is licensed under a Creative Commons License
 *      See this page: http://code.google.com/p/moviejukebox/wiki/License
 *  
 *      For any reuse or distribution, you must make clear to others the 
 *      license terms of this work.  
 */
package com.moviejukebox.subbaba.model;

public class SubBabaContent {
    private int id = 0;
    private String type = "";
    private String votes = "";
    private String points = "";
    private String rating = "";
    private SubBabaFileInfo fileInfo;
    private String downloads = "";
    private String uploaderName = "";
    private String url = "";
    private String download = "";
    private String uploadDate = "";
    
    public int getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    public String getVotes() {
        return votes;
    }
    
    public String getPoints() {
        return points;
    }
    
    public String getRating() {
        return rating;
    }
    
    public SubBabaFileInfo getFileInfo() {
        return fileInfo;
    }
    
    public String getDownloads() {
        return downloads;
    }
    
    public String getUploaderName() {
        return uploaderName;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setVotes(String votes) {
        this.votes = votes;
    }
    
    public void setPoints(String points) {
        this.points = points;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public void setFileInfo(SubBabaFileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
    
    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }
    
    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Content=[id=");
        builder.append(id);
        builder.append("][type=");
        builder.append(type);
        builder.append("][votes=");
        builder.append(votes);
        builder.append("][points=");
        builder.append(points);
        builder.append("][rating=");
        builder.append(rating);
        builder.append("][fileInfo=");
        builder.append(fileInfo);
        builder.append("][downloads=");
        builder.append(downloads);
        builder.append("][uploaderName=");
        builder.append(uploaderName);
        builder.append("][url=");
        builder.append(url);
        builder.append("][download=");
        builder.append(download);
        builder.append("][uploadDate=");
        builder.append(uploadDate);
        builder.append("]]");
        return builder.toString();
    }
}
