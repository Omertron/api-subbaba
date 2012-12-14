/*
 *      Copyright (c) 2004-2012 Stuart Boston
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

import com.omertron.subbabaapi.model.SearchType;
import com.omertron.subbabaapi.model.SubBabaContent;
import com.omertron.subbabaapi.model.SubBabaMovie;
import com.omertron.subbabaapi.tools.FilteringLayout;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SubBabaApiTest {

    private static final Logger logger = Logger.getLogger(SubBabaApiTest.class);
    private static final String APIKEY = "9f0942674ca1387875c0e4cad608871d";
    private SubBabaApi subbaba;

    public SubBabaApiTest() {
        subbaba = new SubBabaApi(APIKEY);
        // Make sure the filter isn't applied to the test output
        FilteringLayout.addReplacementString("DO_NOT_MATCH");
    }

    @BeforeClass
    public static void setUpClass() {        // Set the logger level to TRACE
        Logger.getRootLogger().setLevel(Level.TRACE);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of searchByEnglishName method, of class SubBabaApi.
     */
    @Test
    public void testSearchByEnglishName() {
        logger.info("searchByEnglishName");
        String movieName = "Alice in Wonderland";
        SearchType searchType = SearchType.ALL;
        List<SubBabaMovie> results = subbaba.searchByEnglishName(movieName, searchType);

        assertNotNull("No content returned", results);
        assertTrue("No movies returned", results.size() > 0);
    }

    /**
     * Test of searchByImdbId method, of class SubBabaApi.
     */
    @Test
    public void testSearchByImdbId() {
        logger.info("searchByImdbId");
        String imdbId = "tt1014759";
        SearchType searchType = SearchType.ALL;
        SubBabaMovie result = subbaba.searchByImdbId(imdbId, searchType);

        assertNotNull("No content returned", result);
    }

    /**
     * Test of fetchInfoByContentId method, of class SubBabaApi.
     */
    @Test
    public void testFetchInfoByContentId() {
        logger.info("fetchInfoByContentId");
        String contentId = "5996";
        SubBabaContent result = subbaba.fetchInfoByContentId(contentId);

        assertNotNull("No content returned", result);
        assertTrue("Wrong content returned", result.getType().equalsIgnoreCase("poster"));
    }
}
