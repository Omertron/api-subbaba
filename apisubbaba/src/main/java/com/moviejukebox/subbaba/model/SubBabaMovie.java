/*
 *      Copyright (c) 2004-2011 YAMJ Members
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

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SubBabaMovie {
    @XStreamAlias("original_name")
    private String originalName = "";
    private String type = "";
    private int id = 0;
    @XStreamAlias("imdb_id")
    private String imdbId = "";
    private List<SubBabaContent> content = new ArrayList<SubBabaContent>();

    public void addContent(SubBabaContent contentRecord) {
        this.content.add(contentRecord);
    }
    
    public List<SubBabaContent> getContent() {
        return content;
    }

    public void setContent(List<SubBabaContent> content) {
        this.content = content;
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

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[SubBabaMovie=[originalName=");
        builder.append(originalName);
        builder.append("][type=");
        builder.append(type);
        builder.append("][id=");
        builder.append(id);
        builder.append("][imdbId=");
        builder.append(imdbId);
        builder.append("][content=");
        builder.append(content);
        builder.append("]]");
        return builder.toString();
    }
}
