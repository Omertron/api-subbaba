/*
 *      Copyright (c) 2004-2013 Stuart Boston
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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubBabaFileInfo implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(SubBabaFileInfo.class);
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
        LOG.trace(sb.toString());
    }
}
