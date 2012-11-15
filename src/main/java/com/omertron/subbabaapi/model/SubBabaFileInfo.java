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

public class SubBabaFileInfo implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SubBabaFileInfo.class);
    // Serial Version
    private static final long serialVersionUID = 1L;
    // Object properties
    private int width = 0;
    private int height = 0;
    private String fileSize = "";

    //<editor-fold defaultstate="collapsed" desc="Setter Methods">
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter Methods">
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFileSize() {
        return fileSize;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "SubBabaFileInfo{" + "width=" + width + ", height=" + height + ", fileSize=" + fileSize + '}';
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
        LOGGER.trace(sb.toString());
    }
}
