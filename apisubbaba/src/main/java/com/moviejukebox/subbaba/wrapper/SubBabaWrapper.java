/*
 *      Copyright (c) 2004-2012 YAMJ Members
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
package com.moviejukebox.subbaba.wrapper;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moviejukebox.subbaba.model.SubBabaContent;
import com.moviejukebox.subbaba.model.SubBabaMovie;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class SubBabaWrapper {

    private static final Logger LOGGER = Logger.getLogger(SubBabaWrapper.class);
    // Object properties
    private String searchTerm = "";
    private int totalResults = 0;
    private int id = 0;
    private String imdbId = "";
    @JsonProperty("results")
    private List<SubBabaMovie> movies = new ArrayList<SubBabaMovie>();  // Used in searchByEnglishName & searchByImdbId methods
    private SubBabaContent content = new SubBabaContent();  // Used in the fetchInfoByContentId method

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
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

    public List<SubBabaMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<SubBabaMovie> movies) {
        this.movies = movies;
    }

    public SubBabaContent getContent() {
        return content;
    }

    public void setContent(SubBabaContent content) {
        this.content = content;
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
