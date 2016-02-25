/*
 *      Copyright (c) 2004-2016 Stuart Boston
 *
 *      This file is part of the SubBaba API.
 *
 *      The SubBaba API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      The SubBaba API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with the SubBaba API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.omertron.subbabaapi.model;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

public class SubBabaContent extends AbstractJsonMapping implements Serializable {

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
        this.type = StringUtils.trimToEmpty(type);
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
        this.uploaderName = StringUtils.trimToEmpty(uploaderName);
    }

    public void setUrl(String url) {
        this.url = StringUtils.trimToEmpty(url);
    }

    public void setDownload(String download) {
        this.download = StringUtils.trimToEmpty(download);
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = StringUtils.trimToEmpty(uploadDate);
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
}
