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
package com.moviejukebox.subbaba.model;

import java.util.ArrayList;
import java.util.List;

public class SubBabaSearchResults {

    private String searchTerm = "";
    private int totalResults = 0;
    private int id = 0;
    private String imdbId = "";
    private List<SubBabaMovie> movies = new ArrayList<SubBabaMovie>();  // Used in searchByEnglishName & searchByImdbId methods
    private SubBabaContent content = new SubBabaContent();  // Used in the fetchInfoByContentId method

    public String getSearchTerm() {
        return searchTerm;
    }

    public List<SubBabaMovie> getMovies() {
        return movies;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void setMovies(List<SubBabaMovie> movies) {
        this.movies = movies;
    }

    public void addMovie(SubBabaMovie sbm) {
        this.movies.add(sbm);
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public SubBabaContent getContent() {
        return content;
    }

    public void setContent(SubBabaContent content) {
        this.content = content;
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
}
