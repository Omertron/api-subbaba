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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@JsonRootName("movie")
public class SubBabaMovie extends AbstractJsonMapping implements Serializable {

    // Serial Version
    private static final long serialVersionUID = 1L;
    // Object properties
    @JsonProperty("original_name")
    private String originalName = "";
    private String type = "";
    private int id = 0;
    @JsonProperty("imdb_id")
    private String imdbId = "";
    private List<SubBabaContent> content = Collections.emptyList();

    //<editor-fold defaultstate="collapsed" desc="Setter Methods">
    public void setOriginalName(String originalName) {
        this.originalName = StringUtils.trimToEmpty(originalName);
    }

    public void setType(String type) {
        this.type = StringUtils.trimToEmpty(type);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = StringUtils.trimToEmpty(imdbId);
    }

    public void setContent(List<SubBabaContent> content) {
        this.content = content;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter Methods">
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public List<SubBabaContent> getContent() {
        return content;
    }
    //</editor-fold>
}
