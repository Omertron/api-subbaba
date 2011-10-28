/*
 *      Copyright (c) 2004-2011 YAMJ Members
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

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.moviejukebox.subbaba.model.SubBabaContent;
import com.moviejukebox.subbaba.model.SubBabaFileInfo;
import com.moviejukebox.subbaba.model.SubBabaMovie;
import com.moviejukebox.subbaba.model.SubBabaSearchResults;

public class SubBabaTest {

    private static String apikey = "";
    private SubBaba subbaba;

    @Before
    public void setUp() throws Exception {
        subbaba = new SubBaba(apikey);
    }

    @Test
    public void testSearchbyEnglishName() {
        SubBabaMovie sbm = subbaba.searchByEnglishName("Alice In Wonderland", SubBaba.TYPE_POSTERS);
        assertTrue(sbm != null);
        assertTrue(sbm.getId() == 2606);
    }
    
    @Test
    public void testSearchByImdbId() {
        SubBabaMovie sbm = subbaba.searchByImdbId("tt1014759", SubBaba.TYPE_POSTERS);
        assertTrue(sbm != null);
        assertTrue(sbm.getId() == 2606);
    }
    
    @Test
    public void testFetchInfoByContentId() {
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
            
            sbContent.setDownloads("" + (looper * 2));
            sbContent.setFileInfo(sbFileInfo);
            sbContent.setId(looper * 1000);
            sbContent.setPoints("" + looper);
            sbContent.setRating("" + (looper * 3));
            sbContent.setType("poster");
            sbContent.setUploaderName("Unknown");
            sbContent.setUrl("http://google.com");
            sbContent.setVotes("" + looper);
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
