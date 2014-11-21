package com.liamdyer.solsticecontacts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

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
     * Gets an input stream from a url
     * @param dataUrl the url to get the input stream from
     * @return an input stream of the data contained at the url
     */
    private static InputStream getInputStream(String dataUrl) throws Exception {
        // Create an HTTP connection to the endpoint
        URL url = new URL(dataUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        conn.connect();
        int response = conn.getResponseCode();

        return conn.getInputStream();
    }

    /**
     * Downloads an image and converts it to a bitmap
     * @param url the url to download the image from
     * @return a bitmap of the image
     */
    static public Bitmap downloadImage(String url) throws IOException {
        InputStream is = null;
        Bitmap bm = null;

        try {

            is = NetworkUtils.getInputStream(url);
            bm = BitmapFactory.decodeStream(is);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return bm;
    }

    /**
     * Gets JSON data from a static URL endpoint
     * @return the string containing all the json data
     */
    static public String getJson(String jsonURL) throws IOException {
        InputStream is = null;
        String json = "";

        try {
            is = NetworkUtils.getInputStream(jsonURL);

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
