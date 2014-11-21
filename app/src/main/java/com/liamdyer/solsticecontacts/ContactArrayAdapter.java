package com.liamdyer.solsticecontacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapts an array of contacts into a list view.
 */
public class ContactArrayAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final Contact[] contacts;

    public ContactArrayAdapter(Context context, Contact[] contacts) {
        super(context, R.layout.list_item_contact, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //TODO: Use ViewHolder for smoother scrolling
        View contactView = inflater.inflate(R.layout.list_item_contact, parent, false);
        ImageView picView = (ImageView) contactView.findViewById(R.id.list_item_contact_picture);
        TextView nameView = (TextView) contactView.findViewById(R.id.list_item_contact_name_textview);
        TextView phoneView = (TextView) contactView.findViewById(R.id.list_item_contact_phone_textview);

        Contact contact = contacts[position];
        nameView.setText(contact.getName());
        phoneView.setText(contact.getPhone().getHome());
        new PopulateImageTask(picView).execute(contact.getSmallImageURL());

        return contactView;
    }

    /**
     * A task to asynchronously download the contact images to display to the user
     */
    class PopulateImageTask extends AsyncTask<String, Void, Bitmap> {
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
}
