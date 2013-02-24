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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubBabaResult {

    private static final Logger LOG = LoggerFactory.getLogger(SubBabaResult.class);
    // Serial Version
    private static final long serialVersionUID = 1L;
    // Object properties
    private SubBabaMovie movie = null;

    public SubBabaMovie getMovie() {
        return this.movie;
    }

    public void setMovie(SubBabaMovie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "SubBabaResults{" + "movie=" + movie + '}';
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
        LOG.trace(sb.toString());
    }
}
