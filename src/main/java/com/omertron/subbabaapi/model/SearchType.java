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
