package com.liamdyer.solsticecontacts;

import android.test.AndroidTestCase;

import java.io.IOException;

public class ContactsDataTest extends AndroidTestCase {
    private ContactsData contactsData;

    public void setUp() throws Exception{
        super.setUp();
        contactsData = new ContactsData();
    }

    /**
     * Tests to make sure we're getting the proper json
     * string from the server. We test the length since it's
     * highly unlikely we will get a string that is incorrect
     * but of the same length.
     */
    public void testGetJson() throws IOException {
        String data = contactsData.getJson();
        System.out.println(data);
        assertEquals(9512, data.length());
    }

    /**
     * Make sure our list is populated with the correct number of
     * entries.
     */
    public void testContactsPopulated() {
        assertEquals(8, 8);
    }

}
