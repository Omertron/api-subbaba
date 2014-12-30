/*
 *      Copyright (c) 2004-2015 Stuart Boston
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
package com.omertron.subbabaapi.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.omertron.subbabaapi.model.AbstractJsonMapping;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaResult;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class SubBabaWrapper extends AbstractJsonMapping {

    // Object properties
    private String searchTerm = "";
    private int totalResults = 0;
    private int id = 0;
    private String imdbId = "";
    @JsonProperty("results")
    private List<SubBabaResult> results = Collections.emptyList();
    @JsonProperty("content")
    private SubBabaContent content = null;

    //<editor-fold defaultstate="collapsed" desc="Setter Methods">
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = StringUtils.trimToEmpty(searchTerm);
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = StringUtils.trimToEmpty(imdbId);
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
}
