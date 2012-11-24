/*
 *      Copyright (c) 2004-2012 Stuart Boston
 *
 *      This software is licensed under a Creative Commons License
 *      See the LICENCE.txt file included in this package
 *
 *      For any reuse or distribution, you must make clear to others the
 *      license terms of this work.
 */
package com.omertron.subbabaapi.wrapper;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaResult;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

public class SubBabaWrapper {

    private static final Logger logger = Logger.getLogger(SubBabaWrapper.class);
    // Object properties
    private String searchTerm = "";
    private int totalResults = 0;
    private int id = 0;
    private String imdbId = "";
    @JsonProperty("results")
    private List<SubBabaResult> results = Collections.EMPTY_LIST;
    @JsonProperty("content")
    private SubBabaContent content = null;

    //<editor-fold defaultstate="collapsed" desc="Setter Methods">
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setResults(List<SubBabaResult> results) {
        this.results = results;
    }

    public void setContent(SubBabaContent content) {
        this.content = content;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter Methdods">
    public String getSearchTerm() {
        return searchTerm;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public List<SubBabaResult> getResults() {
        return results;
    }

    public SubBabaContent getContent() {
        return content;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "SubBabaWrapper{" + "searchTerm=" + searchTerm + ", totalResults=" + totalResults + ", id=" + id + ", imdbId=" + imdbId + ", results=" + results + ", content=" + content + '}';
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
