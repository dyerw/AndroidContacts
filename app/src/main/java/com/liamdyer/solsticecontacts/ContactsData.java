package com.liamdyer.solsticecontacts;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

/**
 * Represents the data retrieved from the json endpoint.
 */
public class ContactsData {
    static final String jsonEndpoint = "https://solstice.applauncher.com/external/contacts.json";
    Contact[] contacts;

    public ContactsData() {
        // Create a JSON mapper and map JSON from the endpoint into
        // an array of Contact objects
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.contacts = mapper.readValue(new URL(jsonEndpoint), Contact[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
