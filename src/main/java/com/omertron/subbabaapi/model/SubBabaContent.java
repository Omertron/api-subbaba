/*
 *      Copyright (c) 2004-2012 Stuart Boston
 *
 *      This software is licensed under a Creative Commons License
 *      See the LICENCE.txt file included in this package
 *
 *      For any reuse or distribution, you must make clear to others the
 *      license terms of this work.
 */
package com.omertron.subbabaapi.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.io.Serializable;
import org.apache.log4j.Logger;

public class SubBabaContent implements Serializable {

    private static final Logger logger = Logger.getLogger(SubBabaContent.class);
    // Serial Version
    private static final long serialVersionUID = 1L;
    // Object properties
    private int id = 0;
    private String type = "";
    private int votes = 0;
    private int points = 0;
    private int rating = 0;
    private SubBabaFileInfo fileInfo;
    private int downloads = 0;
    private String uploaderName = "";
    private String url = "";
    private String download = "";
    private String uploadDate = "";

    //<editor-fold defaultstate="collapsed" desc="Setter Methods">
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setFileInfo(SubBabaFileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter Methods">
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getVotes() {
        return votes;
    }

    public int getPoints() {
        return points;
    }

    public int getRating() {
        return rating;
    }

    public SubBabaFileInfo getFileInfo() {
        return fileInfo;
    }

    public int getDownloads() {
        return downloads;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public String getUrl() {
        return url;
    }

    public String getDownload() {
        return download;
    }

    public String getUploadDate() {
        return uploadDate;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "SubBabaContent{" + "type=" + type + ", votes=" + votes + ", points=" + points + ", rating=" + rating + ", fileInfo=" + fileInfo + ", downloads=" + downloads + ", uploaderName=" + uploaderName + ", url=" + url + ", download=" + download + ", uploadDate=" + uploadDate + '}';
    }

    /**
     * Handle unknown properties and print a message
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown property: '").append(key);
        sb.append("' value: '").append(value).append("'");
        logger.trace(sb.toString());
    }
}
