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

import com.moviejukebox.subbaba.model.SubBabaContent;
import com.moviejukebox.subbaba.model.SubBabaFileInfo;
import com.moviejukebox.subbaba.model.SubBabaMovie;
import com.moviejukebox.subbaba.model.SubBabaSearchResults;
import com.moviejukebox.subbaba.tools.FilteringLayout;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.text.AbstractDocument.Content;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class SubBaba {

    private static final Logger LOGGER = Logger.getLogger(SubBaba.class);
    private String apiKey = null;
    // We have to use a driver so we can use the Friendly name coder to stop the replacement of "_" with "__"
    private XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
    public static final String SEARCH_NAME = "search";
    public static final String SEARCH_IMDB = "imdb";
    public static final String SEARCH_CONTENT = "get_content";
    public static final String TYPE_ALL = "all";
    public static final String TYPE_POSTERS = "1";
    public static final String TYPE_DVD_COVERS = "2";
    public static final String TYPE_CD_COVERS = "3";

    public SubBaba(String apiKey) {
        if (StringUtils.isBlank(apiKey)) {
            return;
        }

        this.apiKey = apiKey;
        FilteringLayout.addReplacementString(apiKey);

        initalise();
    }

    /**
     * Output the API version information to the debug log
     */
    public static void showVersion() {
        String apiTitle = SubBaba.class.getPackage().getSpecificationTitle();

        if (StringUtils.isNotBlank(apiTitle)) {
            String apiVersion = SubBaba.class.getPackage().getSpecificationVersion();
            String apiRevision = SubBaba.class.getPackage().getImplementationVersion();
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
     * Configure the Xstream interface
     */
    private void initalise() {
        xstream.processAnnotations(Content.class);
        xstream.processAnnotations(SubBabaFileInfo.class);
        xstream.processAnnotations(SubBabaMovie.class);

        xstream.alias("movie", SubBabaMovie.class);
        xstream.alias("content", SubBabaContent.class);
        xstream.addImplicitCollection(SubBabaMovie.class, "content");
        xstream.alias("fileInfo", SubBabaFileInfo.class);
        xstream.alias("OpenSearchDescription", SubBabaSearchResults.class);
        xstream.addImplicitCollection(SubBabaSearchResults.class, "movies");
    }

    /**
     * Generate the URL with just the term and mode.
     *
     * @param searchTerm
     * @param searchMode
     * @return
     */
    public URL generateUrl(String searchTerm, String searchMode) {
        return generateUrl(searchTerm, searchMode, null);
    }

    /**
     * Generate the full API URL
     *
     * @param searchTerm
     * @param searchMode
     * @param searchType
     * @return
     */
    public URL generateUrl(String searchTerm, String searchMode, String searchType) {
        StringBuilder stringUrl = new StringBuilder("http://www.sub-baba.com/api/");
        stringUrl.append(apiKey);
        stringUrl.append("/xml/");
        stringUrl.append(searchMode).append("/");

        try {
            stringUrl.append(URLEncoder.encode(searchTerm.toLowerCase(), "UTF-8"));
        } catch (UnsupportedEncodingException ignore) {
            // Failed to encode the string, so try it un-encoded
            stringUrl.append(searchTerm);
        }

        if (searchType != null) {
            stringUrl.append("/").append(searchType);
        }

        URL searchUrl;
        try {
            searchUrl = new URL(stringUrl.toString());
        } catch (MalformedURLException ignore) {
            searchUrl = null;
        }

        LOGGER.info("URL: " + searchUrl);
        return searchUrl;
    }

    /**
     * Use the English name of a movie to search for posters
     *
     * @param movieName
     * @param searchType
     * @return
     */
    public SubBabaMovie searchByEnglishName(String movieName, String searchType) {
        URL searchUrl = generateUrl(movieName, SEARCH_NAME, searchType);

        SubBabaSearchResults results = (SubBabaSearchResults) xstream.fromXML(searchUrl);

        if (results.getMovies().size() > 0) {
            return results.getMovies().get(0);
        }

        return null;
    }

    /**
     * Use the IMDb ID to get the movie information
     *
     * @param imdbId
     * @param searchType
     * @return
     */
    public SubBabaMovie searchByImdbId(String imdbId, String searchType) {
        URL searchUrl = generateUrl(imdbId, SEARCH_IMDB, searchType);

        SubBabaSearchResults results = (SubBabaSearchResults) xstream.fromXML(searchUrl);

        if (results.getMovies().size() > 0) {
            return results.getMovies().get(0);
        }

        return null;
    }

    /**
     * Get the specific content associated with an ID
     *
     * @param contentId
     * @return
     */
    public SubBabaContent fetchInfoByContentId(String contentId) {
        URL searchUrl = generateUrl(contentId, SEARCH_CONTENT);

        SubBabaSearchResults results = (SubBabaSearchResults) xstream.fromXML(searchUrl);

        return results.getContent();
    }
    /**
     * Used for testing
     *
     * @param sbm
     * @return
     */
//    public String convertToXml(Object obj) {
//        return xstream.toXML(obj);
//    }
}
