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
import java.util.List;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubBabaApiTest {

    private static final Logger LOG = LoggerFactory.getLogger(SubBabaApiTest.class);
    private static final String APIKEY = "9f0942674ca1387875c0e4cad608871d";
    private final SubBabaApi subbaba;
    private static final String NO_CONTENT_RETURNED = "No content returned";

    public SubBabaApiTest() throws SubBabaException {
        subbaba = new SubBabaApi(APIKEY);
    }

    @BeforeClass
    public static void setUpClass() {
        TestLogger.configure();
    }

    /**
     * Test of searchByEnglishName method, of class SubBabaApi.
     *
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    @Test
    public void testSearchByEnglishName() throws SubBabaException {
        LOG.info("searchByEnglishName");
        String movieName = "Alice in Wonderland";
        SearchType searchType = SearchType.ALL;
        List<SubBabaMovie> results = subbaba.searchByEnglishName(movieName, searchType);

        assertNotNull(NO_CONTENT_RETURNED, results);
        assertFalse("No movies returned", results.isEmpty());
    }

    /**
     * Test of searchByImdbId method, of class SubBabaApi.
     *
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    @Test
    public void testSearchByImdbId() throws SubBabaException {
        LOG.info("searchByImdbId");
        String imdbId = "tt1014759";
        SearchType searchType = SearchType.ALL;
        SubBabaMovie result = subbaba.searchByImdbId(imdbId, searchType);

        assertNotNull(NO_CONTENT_RETURNED, result);
    }

    /**
     * Test of fetchInfoByContentId method, of class SubBabaApi.
     *
     * @throws com.omertron.subbabaapi.SubBabaException
     */
    @Test
    public void testFetchInfoByContentId() throws SubBabaException {
        LOG.info("fetchInfoByContentId");
        String contentId = "5996";
        SubBabaContent result = subbaba.fetchInfoByContentId(contentId);

        assertNotNull(NO_CONTENT_RETURNED, result);
        assertEquals("Wrong content returned", "poster", result.getType());
    }
}
