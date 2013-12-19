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
package com.omertron.subbabaapi.tools;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omertron.subbabaapi.enumerations.SearchFunction;
import com.omertron.subbabaapi.enumerations.SearchType;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaMovie;
import com.omertron.subbabaapi.model.SubBabaResult;
import com.omertron.subbabaapi.wrapper.SubBabaWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yamj.api.common.http.CommonHttpClient;

public final class ApiBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ApiBuilder.class);
    private static final String DEFAULT_CHARSET = "UTF-8";
    // API parts
    private static final String API_BASE = "http://www.sub-baba.com/api/";
    private static final String API_TYPE = "/json/";
    private static final String API_SLASH = "/";
    // Jackson JSON configuration
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static String apiKey;
    private static CommonHttpClient httpClient;

    private ApiBuilder() {
        throw new UnsupportedOperationException("Class cannot be initialised");
    }

    public static void setApiKey(String newApiKey) {
        apiKey = newApiKey;
    }

    public static void setHttpClient(CommonHttpClient httpClient) {
        ApiBuilder.httpClient = httpClient;
    }

    /**
     * Search by name
     *
     * @param query
     * @param searchType
     * @return
     */
    public static List<SubBabaMovie> searchByEnglishName(String query, SearchType searchType) {
        SubBabaWrapper sbw = getWrapper(SubBabaWrapper.class, SearchFunction.NAME, query, searchType);
        if (sbw == null) {
            return Collections.EMPTY_LIST;
        }

        List<SubBabaMovie> movies = new ArrayList<SubBabaMovie>();
        for (SubBabaResult result : sbw.getResults()) {
            movies.add(result.getMovie());
        }

        return movies;
    }

    /**
     * Get the single image information using the Sub-Baba ID
     *
     * @param query
     * @return
     */
    public static SubBabaContent fetchInfoByContentId(String query) {
        SubBabaWrapper sbw = getWrapper(SubBabaWrapper.class, SearchFunction.SUBBABA, query);

        if (sbw == null) {
            return null;
        } else {
            return sbw.getContent();
        }
    }

    /**
     * Search by IMDB ID
     *
     * @param query
     * @param searchType
     * @return
     */
    public static SubBabaMovie searchByImdbId(String query, SearchType searchType) {
        SubBabaWrapper sbw = getWrapper(SubBabaWrapper.class, SearchFunction.IMDB, query, searchType);
        if (sbw == null) {
            return new SubBabaMovie();
        }

        List<SubBabaResult> listResults = sbw.getResults();
        if (listResults == null || listResults.isEmpty()) {
            return new SubBabaMovie();
        } else {
            SubBabaResult sbResult = listResults.get(0);
            return sbResult.getMovie();
        }
    }

    /**
     * Create the URL from the parameters
     *
     * @param function
     * @param query
     * @param searchType
     * @return
     */
    private static URL buildUrl(SearchFunction function, String query, SearchType searchType) {
        StringBuilder sbURL = new StringBuilder(API_BASE);
        sbURL.append(apiKey);
        sbURL.append(API_TYPE);
        sbURL.append(function.getType()).append(API_SLASH);

        if (StringUtils.isNotBlank(query)) {
            try {
                sbURL.append(URLEncoder.encode(query.toLowerCase(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                LOG.trace("Failed to encode query: " + query, ex);
                // Failed to encode the string, so try it un-encoded
                sbURL.append(query);
            }
        }

        if (searchType != null) {
            sbURL.append(API_SLASH);
            sbURL.append(searchType.getType());
        }

        LOG.trace("URL: {}", sbURL.toString());
        try {
            return new URL(sbURL.toString());
        } catch (MalformedURLException ex) {
            LOG.trace("Failed to convert string to URL: {}", ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Get the wrapper
     *
     * @param <T>
     * @param clazz
     * @param function
     * @param query
     * @return
     */
    private static <T> T getWrapper(Class<T> clazz, SearchFunction function, String query) {
        return getWrapper(clazz, function, query, null);
    }

    /**
     * Get the wrapper based on the function
     *
     * @param <T>
     * @param clazz
     * @param function
     * @param query
     * @param searchType
     * @return
     */
    private static <T> T getWrapper(Class<T> clazz, SearchFunction function, String query, SearchType searchType) {
        try {
            String webPage = httpClient.requestContent(buildUrl(function, query, searchType), Charset.forName(DEFAULT_CHARSET));
            Object response = MAPPER.readValue(webPage, clazz);
            return clazz.cast(response);
        } catch (JsonParseException ex) {
            LOG.warn("JsonParseException: {}", ex.getMessage(), ex);
        } catch (JsonMappingException ex) {
            LOG.warn("JsonMappingException: {}", ex.getMessage(), ex);
        } catch (IOException ex) {
            LOG.warn("IOException: {}", ex.getMessage(), ex);
        }
        return null;
    }
}
