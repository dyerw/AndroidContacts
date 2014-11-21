package com.liamdyer.solsticecontacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Contains utility functions for making network calls
 */
public class NetworkUtils {
    /**
     * Gets JSON data from a static URL endpoint
     * @return the string containing all the json data
     */
    static public String getJson(String jsonURL) throws IOException {
        InputStream is = null;
        String json = "";

        try {
            // Create an HTTP connection to the endpoint
            URL url = new URL(jsonURL);
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

    /**
     * Reads an InputStream and converts it to a String.
     */
    static private String readIt(InputStream stream) throws IOException {
        Reader isReader = new InputStreamReader(stream, "UTF-8");
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder out = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            line += "\n";
            out.append(line);
        }
        reader.close();

        return out.toString();
    }
}
