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
package com.moviejukebox.subbaba;

import com.moviejukebox.subbaba.model.SearchType;
import com.moviejukebox.subbaba.model.SubBabaContent;
import com.moviejukebox.subbaba.model.SubBabaMovie;
import com.moviejukebox.subbaba.tools.ApiBuilder;
import com.moviejukebox.subbaba.tools.FilteringLayout;
import com.moviejukebox.subbaba.wrapper.SubBabaWrapper;
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
     * Output the API version information to the debug log
     */
    public static void showVersion() {
        String apiTitle = SubBabaApi.class.getPackage().getSpecificationTitle();

        if (StringUtils.isNotBlank(apiTitle)) {
            String apiVersion = SubBabaApi.class.getPackage().getSpecificationVersion();
            String apiRevision = SubBabaApi.class.getPackage().getImplementationVersion();
            StringBuilder sv = new StringBuilder();
            sv.append(apiTitle).append(" ");
            sv.append(apiVersion).append(" r");
            sv.append(apiRevision);
            LOGGER.debug(sv.toString());
        } else {
            LOGGER.debug("API-SubBaba version/revision information not available");
        }
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
}
