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
        // Remove whitespace for character counting
        data = data.replaceAll("\\s+","");
        assertEquals(6876, data.length());
    }

    /**
     * Make sure our list is populated with the correct number of
     * entries. We expect there to be 20 contacts since that's
     * what's present in the JSON data.
     */
    public void testContactsPopulated() {
        assertEquals(contactsData.contacts.length, 20);
    }

}
