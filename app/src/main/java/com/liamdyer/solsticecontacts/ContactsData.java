package com.liamdyer.solsticecontacts;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Represents the data retrieved from the json endpoint.
 */
public class ContactsData {
    static final String jsonEndpoint = "https://solstice.applauncher.com/external/contacts.json";
    Contact contacts[];

    public ContactsData() throws IOException {
        // Get the JSON data from the URL
        String data = this.getJson();

        // Create a JSON mapper and map JSON  into
        // an array of Contact objects
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.contacts = mapper.readValue(data, Contact[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO: Move all this to a utility class
    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        Reader isreader = new InputStreamReader(stream, "UTF-8");
        BufferedReader reader = new BufferedReader(isreader);
        StringBuilder out = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            line += "\n";
            out.append(line);
        }
        reader.close();

        return out.toString();
    }

    /**
     * Gets JSON data from a static URL endpoint
     * @return the string containing all the json data
     */
    public String getJson() throws IOException {
        InputStream is = null;
        String json = "";

        try {
            // Create an HTTP connection to the endpoint
            URL url = new URL(jsonEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            int response = conn.getResponseCode();

            is = conn.getInputStream();

            // Convert stream to string
            json = readIt(is);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return json;
    }
}
