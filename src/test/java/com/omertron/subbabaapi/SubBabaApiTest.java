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

    private static final Logger LOGGER = Logger.getLogger(SubBabaApiTest.class);
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
        LOGGER.info("searchByEnglishName");
        String movieName = "Alice In Wonderland";
        SearchType searchType = SearchType.ALL;
        List<SubBabaMovie> result = subbaba.searchByEnglishName(movieName, searchType);

        assertNotNull("No content returned", result);
        assertTrue("No movies returned", result.size() > 0);
    }

    /**
     * Test of searchByImdbId method, of class SubBabaApi.
     */
    @Test
    public void testSearchByImdbId() {
        LOGGER.info("searchByImdbId");
        String imdbId = "tt1014759";
        SearchType searchType = SearchType.ALL;
        List<SubBabaMovie> result = subbaba.searchByImdbId(imdbId, searchType);

        assertNotNull("No content returned", result);
        assertTrue("No movies returned", result.size() > 0);
    }

    /**
     * Test of fetchInfoByContentId method, of class SubBabaApi.
     */
    @Test
    public void testFetchInfoByContentId() {
        LOGGER.info("fetchInfoByContentId");
        String contentId = "5996";
        SubBabaContent result = subbaba.fetchInfoByContentId(contentId);

        assertNotNull("No content returned", result);
        assertTrue("Wrong content returned", result.getType().equalsIgnoreCase("poster"));
    }
}
