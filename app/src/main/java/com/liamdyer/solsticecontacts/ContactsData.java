package com.liamdyer.solsticecontacts;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Represents the data retrieved from the json endpoint.
 */
public class ContactsData {
    Contact contacts[];

    public ContactsData(String jsonData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        try {
            this.contacts = mapper.readValue(jsonData, Contact[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
