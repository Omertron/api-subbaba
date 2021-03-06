/*
 *      Copyright (c) 2004-2016 Stuart Boston
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

public class SubBabaResult extends AbstractJsonMapping {

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
}
