/*
 *      Copyright (c) 2004-2012 Stuart Boston
 *
 *      This software is licensed under a Creative Commons License
 *      See the LICENCE.txt file included in this package
 *
 *      For any reuse or distribution, you must make clear to others the
 *      license terms of this work.
 */
package com.omertron.subbabaapi;

import com.omertron.subbabaapi.model.SearchType;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaMovie;
import com.omertron.subbabaapi.tools.ApiBuilder;
import com.omertron.subbabaapi.tools.FilteringLayout;
import com.omertron.subbabaapi.tools.WebBrowser;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class SubBabaApi {

    private static final Logger LOGGER = Logger.getLogger(SubBabaApi.class);

    public SubBabaApi(String apiKey) {
        if (StringUtils.isBlank(apiKey)) {
            return;
        }

        FilteringLayout.addReplacementString(apiKey);
        ApiBuilder.setApiKey(apiKey);
    }

    /**
     * Use the English name of a movie to search for posters
     *
     * @param movieName
     * @param searchType
     * @return
     */
    public List<SubBabaMovie> searchByEnglishName(String movieName, SearchType searchType) {
        return ApiBuilder.searchByEnglishName(movieName, searchType);
    }

    /**
     * Use the IMDb ID to get the movie information
     *
     * @param imdbId
     * @param searchType
     * @return
     */
    public List<SubBabaMovie> searchByImdbId(String imdbId, SearchType searchType) {
        return ApiBuilder.searchByImdbId(imdbId, searchType);
    }

    /**
     * Get the specific content associated with an ID
     *
     * @param contentId
     * @return
     */
    public SubBabaContent fetchInfoByContentId(String contentId) {
        SubBabaContent sbc = ApiBuilder.fetchInfoByContentId(contentId);

        if (sbc != null) {
            LOGGER.info(sbc.toString());
        }
        return sbc;
    }

    /**
     * Set the web browser proxy information
     *
     * @param host
     * @param port
     * @param username
     * @param password
     */
    public void setProxy(String host, String port, String username, String password) {
        WebBrowser.setProxyHost(host);
        WebBrowser.setProxyPort(port);
        WebBrowser.setProxyUsername(username);
        WebBrowser.setProxyPassword(password);
    }

    /**
     * Set the web browser timeout settings
     *
     * @param webTimeoutConnect
     * @param webTimeoutRead
     */
    public void setTimeout(int webTimeoutConnect, int webTimeoutRead) {
        WebBrowser.setWebTimeoutConnect(webTimeoutConnect);
        WebBrowser.setWebTimeoutRead(webTimeoutRead);
    }
}
