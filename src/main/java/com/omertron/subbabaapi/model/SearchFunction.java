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
