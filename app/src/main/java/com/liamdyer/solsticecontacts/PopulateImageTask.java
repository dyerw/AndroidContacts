package com.liamdyer.solsticecontacts;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * A task to asynchronously download the contact images to display to the user
 * given an image view
 */
public class PopulateImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public PopulateImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        try {
            return NetworkUtils.downloadImage(urls[0]);
        } catch (Exception e) {
            //TODO: populate w/ default image
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
