/*
 *      Copyright (c) 2004-2012 Stuart Boston
 *
 *      This software is licensed under a Creative Commons License
 *      See the LICENCE.txt file included in this package
 *
 *      For any reuse or distribution, you must make clear to others the
 *      license terms of this work.
 */
package com.moviejukebox.subbaba.model;

public enum SearchType {

    ALL("all"),
    POSTERS("1"),
    DVD_COVERS("2"),
    CD_COVERS("3");
    private String type;

    private SearchType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Set the search type from a string.
     *
     * @param type
     * @return
     */
    public static SearchType fromString(String type) {
        if (type != null) {
            try {
                for (SearchType searchType : SearchType.values()) {
                    if (type.equalsIgnoreCase(searchType.type)) {
                        return searchType;
                    }
                }
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("SearchType '" + type + "' does not exist", ex);
            }
        }
        throw new IllegalArgumentException("SearchType is null");
    }
}
