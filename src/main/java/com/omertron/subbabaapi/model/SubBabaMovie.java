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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@JsonRootName("movie")
public class SubBabaMovie implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SubBabaMovie.class);
    // Serial Version
    private static final long serialVersionUID = 1L;
    // Object properties
    @JsonProperty("original_name")
    private String originalName = "";
    private String type = "";
    private int id = 0;
    @JsonProperty("imdb_id")
    private String imdbId = "";
    private List<SubBabaContent> content = new ArrayList<SubBabaContent>();

    // Self referential
    private SubBabaMovie movie;

    public SubBabaMovie getMovie() {
        return movie;
    }

    public void setMovie(SubBabaMovie movie) {
        this.movie = movie;
    }




    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public List<SubBabaContent> getContent() {
        return content;
    }

    public void setContent(List<SubBabaContent> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SubBabaMovie{" + "originalName=" + originalName + ", type=" + type + ", id=" + id + ", imdbId=" + imdbId + ", content=" + content + '}';
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
