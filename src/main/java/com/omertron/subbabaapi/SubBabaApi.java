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
package com.omertron.subbabaapi;

import com.omertron.subbabaapi.enumerations.SearchType;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaMovie;
import com.omertron.subbabaapi.tools.ApiBuilder;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.yamj.api.common.exception.ApiExceptionType;
import org.yamj.api.common.http.SimpleHttpClientBuilder;

public class SubBabaApi {

    /**
     * Create a new instance of the API with the provided API key and a default HttpClient
     *
     * @param apiKey
     * @throws SubBabaException
     */
    public SubBabaApi(String apiKey) throws SubBabaException {
        // Use a default pooling client if one is not provided
        this(apiKey, new SimpleHttpClientBuilder().build());
    }

    /**
     * Create a new instance of the API with the provided API key and HttpClient
     *
     * @param apiKey
     * @param httpClient
     * @throws SubBabaException
     */
    public SubBabaApi(String apiKey, HttpClient httpClient) throws SubBabaException {
        if (StringUtils.isBlank(apiKey)) {
            throw new SubBabaException(ApiExceptionType.AUTH_FAILURE, "Invalid API Key");
        }

        if (httpClient == null) {
            throw new SubBabaException(ApiExceptionType.HTTP_CLIENT_MISSING, "Invalid HttpClient provided");
        }

        ApiBuilder.setApiKey(apiKey);
        ApiBuilder.setHttpClient(httpClient);
    }

    /**
     * Use the English name of a movie to search for posters
     *
     * @param movieName
     * @param searchType
     * @return
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    public List<SubBabaMovie> searchByEnglishName(String movieName, SearchType searchType) throws SubBabaException {
        return ApiBuilder.searchByEnglishName(movieName, searchType);
    }

    /**
     * Use the IMDb ID to get the movie information
     *
     * @param imdbId
     * @param searchType
     * @return
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    public SubBabaMovie searchByImdbId(String imdbId, SearchType searchType) throws SubBabaException {
        return ApiBuilder.searchByImdbId(imdbId, searchType);
    }

    /**
     * Get the specific content associated with an ID
     *
     * @param contentId
     * @return
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    public SubBabaContent fetchInfoByContentId(String contentId) throws SubBabaException {
        return ApiBuilder.fetchInfoByContentId(contentId);
    }
}
