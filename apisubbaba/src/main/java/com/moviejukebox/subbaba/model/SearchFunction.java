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

public enum SearchFunction {
    NAME("search"),
    IMDB("imdb"),
    SUBBABA("get_content");

    private String type;

    private SearchFunction(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Set the search type from a string.
     *
     * @param function
     * @return
     */
    public static SearchFunction fromString(String function) {
        if (function != null) {
            try {
                for (SearchFunction searchFunction : SearchFunction.values()) {
                    if (function.equalsIgnoreCase(searchFunction.type)) {
                        return searchFunction;
                    }
                }
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("SearchFunction '" + function + "' does not exist", ex);
            }
        }
        throw new IllegalArgumentException("SearchFunction is null");
    }

}
