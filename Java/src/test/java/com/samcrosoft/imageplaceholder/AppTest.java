package com.samcrosoft.imageplaceholder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class AppTest {

    Object oApp;

    @Before
    public void setUp() throws Exception {
        this.oApp = new App();
    }


    @Test
    public void checkDefaultFontNotEmpty(){
        String sDefaultFontName = App.DEFAULT_FONT_NAME;
        String sEmpty = "";

        assertNotEquals("The Default Font Name Must No Be Empty",sDefaultFontName, sEmpty);
    }


    @After
    public void tearDown() throws Exception {

    }
}