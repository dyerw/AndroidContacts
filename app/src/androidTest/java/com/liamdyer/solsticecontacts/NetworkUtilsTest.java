package com.liamdyer.solsticecontacts;

import java.io.IOException;

import android.test.AndroidTestCase;

/**
 * Tests the methods for the network utility class.
 */
public class NetworkUtilsTest extends AndroidTestCase {
    /**
     * Tests to make sure we're getting the proper json
     * string from the server. We test the length since it's
     * highly unlikely we will get a string that is incorrect
     * but of the same length.
     */
    public void testGetJson() throws IOException {
        String data = NetworkUtils.getJson("https://solstice.applauncher.com/external/contacts.json");
        // Remove whitespace for character counting
        data = data.replaceAll("\\s+","");
        assertEquals(6876, data.length());
    }
}
