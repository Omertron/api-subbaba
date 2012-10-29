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
import com.moviejukebox.subbaba.model.SubBabaFileInfo;
import com.moviejukebox.subbaba.model.SubBabaMovie;
import com.moviejukebox.subbaba.model.SubBabaSearchResults;
import java.util.List;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SubBabaTest {

    private static final Logger LOGGER = Logger.getLogger(SubBabaTest.class);
    private static String apikey = "9f0942674ca1387875c0e4cad608871d";
    private SubBaba subbaba;

    @Before
    public void setUp() throws Exception {
        subbaba = new SubBaba(apikey);
    }

    @Test
    public void testSearchbyEnglishName() {
        LOGGER.info("SearchbyEnglishName");

        List<SubBabaMovie> sbMovies = subbaba.searchByEnglishName("Alice In Wonderland", SearchType.POSTERS);
        assertTrue(sbMovies != null);
        assertTrue(sbMovies.get(0).getId() == 2606);
    }

    @Test
    public void testSearchByImdbId() {
        LOGGER.info("SearchByImdbId");

        SubBabaMovie sbm = subbaba.searchByImdbId("tt1014759", SearchType.POSTERS);
        assertTrue(sbm != null);
        assertTrue(sbm.getId() == 2606);
    }

    @Test
    public void testFetchInfoByContentId() {
        LOGGER.info("FetchInfoByContentId");

        SubBabaContent sbc = subbaba.fetchInfoByContentId("5997");
        assertTrue(sbc.getUrl().length() > 0);
    }

//    @Test
    public void testCreation() {

        SubBabaMovie sbm = new SubBabaMovie();
        sbm.setOriginalName("Test Movie Name");
        sbm.setType("Type");
        sbm.setId(9999);
        sbm.setImdbId("tt123456");

        for (int looper = 1; looper <= 3; looper++) {
            SubBabaContent sbContent = new SubBabaContent();
            SubBabaFileInfo sbFileInfo = new SubBabaFileInfo();

            sbFileInfo.setFileSize(looper + "00Kb");
            sbFileInfo.setHeight(720);
            sbFileInfo.setWidth(1280);

            sbContent.setDownloads(String.valueOf(looper * 2));
            sbContent.setFileInfo(sbFileInfo);
            sbContent.setId(looper * 1000);
            sbContent.setPoints(String.valueOf(looper));
            sbContent.setRating(String.valueOf(looper * 3));
            sbContent.setType("poster");
            sbContent.setUploaderName("Unknown");
            sbContent.setUrl("http://google.com");
            sbContent.setVotes(String.valueOf(looper));
            sbm.addContent(sbContent);
        }

        SubBabaSearchResults results = new SubBabaSearchResults();
        results.setSearchTerm("Hello");
        results.setTotalResults(3);
        results.addMovie(sbm);

//        String xml = subbaba.convertToXml(results);
//        System.out.println(xml);
    }
}
