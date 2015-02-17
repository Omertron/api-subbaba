/*
 *      Copyright (c) 2004-2015 Stuart Boston
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
import com.omertron.subbabaapi.SubBabaException;
import com.omertron.subbabaapi.enumerations.SearchFunction;
import com.omertron.subbabaapi.enumerations.SearchType;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaMovie;
import com.omertron.subbabaapi.model.SubBabaResult;
import com.omertron.subbabaapi.wrapper.SubBabaWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yamj.api.common.exception.ApiExceptionType;
import org.yamj.api.common.http.DigestedResponse;
import org.yamj.api.common.http.DigestedResponseReader;
import org.yamj.api.common.http.UserAgentSelector;

public final class ApiBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ApiBuilder.class);
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final Charset CHARSET = Charset.forName(DEFAULT_CHARSET);
    // API parts
    private static final String API_BASE = "http://www.sub-baba.com/api/";
    private static final String API_TYPE = "/json/";
    private static final String API_SLASH = "/";
    // Jackson JSON configuration
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static String apiKey;
    private static HttpClient httpClient;
    private static final int HTTP_STATUS_300 = 300;
    private static final int HTTP_STATUS_500 = 500;

    private ApiBuilder() {
        throw new UnsupportedOperationException("Class cannot be initialised");
    }

    public static void setApiKey(String newApiKey) {
        apiKey = newApiKey;
    }

    public static void setHttpClient(HttpClient httpClient) {
        ApiBuilder.httpClient = httpClient;
    }

    /**
     * Search by name
     *
     * @param query
     * @param searchType
     * @return
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    public static List<SubBabaMovie> searchByEnglishName(String query, SearchType searchType) throws SubBabaException {
        SubBabaWrapper sbw = getWrapper(SubBabaWrapper.class, SearchFunction.NAME, query, searchType);
        if (sbw == null) {
            return Collections.emptyList();
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
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    public static SubBabaContent fetchInfoByContentId(String query) throws SubBabaException {
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
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    public static SubBabaMovie searchByImdbId(String query, SearchType searchType) throws SubBabaException {
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
    private static URL buildUrl(SearchFunction function, String query, SearchType searchType) throws SubBabaException {
        StringBuilder sbURL = new StringBuilder(API_BASE);
        sbURL.append(apiKey);
        sbURL.append(API_TYPE);
        sbURL.append(function.getType()).append(API_SLASH);

        if (StringUtils.isNotBlank(query)) {
            try {
                sbURL.append(URLEncoder.encode(query.toLowerCase(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                LOG.trace("Failed to encode query: {}", query, ex);
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
            throw new SubBabaException(ApiExceptionType.INVALID_URL, "Failed to convert string to URL", sbURL.toString(), ex);
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
    private static <T> T getWrapper(Class<T> clazz, SearchFunction function, String query) throws SubBabaException {
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
    private static <T> T getWrapper(Class<T> clazz, SearchFunction function, String query, SearchType searchType) throws SubBabaException {
        URL url = buildUrl(function, query, searchType);
        try {
            final HttpGet httpGet = new HttpGet(url.toURI());
            httpGet.addHeader("accept", "application/json");
            httpGet.addHeader(HTTP.USER_AGENT, UserAgentSelector.randomUserAgent());

            final DigestedResponse response = DigestedResponseReader.requestContent(httpClient, httpGet, CHARSET);
            if (response.getStatusCode() >= HTTP_STATUS_500) {
                throw new SubBabaException(ApiExceptionType.HTTP_503_ERROR, response.getContent(), response.getStatusCode(), url);
            } else if (response.getStatusCode() >= HTTP_STATUS_300) {
                throw new SubBabaException(ApiExceptionType.HTTP_404_ERROR, response.getContent(), response.getStatusCode(), url);
            }

            Object wrapper = MAPPER.readValue(response.getContent(), clazz);
            return clazz.cast(wrapper);
        } catch (JsonParseException ex) {
            throw new SubBabaException(ApiExceptionType.MAPPING_FAILED, "Failed to parse object", url, ex);
        } catch (JsonMappingException ex) {
            throw new SubBabaException(ApiExceptionType.MAPPING_FAILED, "Failed to parse object", url, ex);
        } catch (IOException ex) {
            throw new SubBabaException(ApiExceptionType.CONNECTION_ERROR, "Error retrieving URL", url, ex);
        } catch (URISyntaxException ex) {
            throw new SubBabaException(ApiExceptionType.INVALID_URL, "Invalid URL", url, ex);
        }
    }
}
