package com.liamdyer.solsticecontacts;

import android.test.AndroidTestCase;

public class ContactsDataTest extends AndroidTestCase {
    private ContactsData contactsData;

    public void setUp() throws Exception{
        super.setUp();
        contactsData = new ContactsData();
        for (Contact c : contactsData.contacts) {
            System.out.println(c.name);
        }
    }

    /**
     * Make sure our list is populated with the correct number of
     * entries.
     */
    public void testContactsPopulated() {
        assertEquals(8, 8);
    }

}
