package com.liamdyer.solsticecontacts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ContactDetailActivity extends Activity {
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // Get the contact data
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");

        // Populate the contact info with data
        ProgressDialog dialog = new ProgressDialog(this);
        PopulateContactDetailTask task = new PopulateContactDetailTask(dialog);
        task.execute(contact.getDetailsURL());

    }

    /**
     * An async task that fetches the contact data and populates the UI.
     */
    class PopulateContactDetailTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        PopulateContactDetailTask(ProgressDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        protected void onPreExecute() {
            dialog.setTitle("Retrieving Contact...");
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            // We only need one url despite having varargs
            String url = urls[0];

            try {
                return NetworkUtils.getJson(url);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            dialog.dismiss();
            populateContact(json);
        }
    }

    private void populateContact(String jsonData) {
        // Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        ContactDetail contactDetail = null;
        try {
            contactDetail = mapper.readValue(jsonData, ContactDetail.class);
        } catch (Exception e) {
            // Corrupt JSON
            e.printStackTrace();
        }

        if (contactDetail == null) {
            //TODO: update UI w/ error
            return;
        }

        // Populate Top Row of info
        ImageView photoView = (ImageView) this.findViewById(R.id.lg_contact_pic);
        TextView nameView = (TextView) this.findViewById(R.id.contact_detail_name);
        TextView companyView = (TextView) this.findViewById(R.id.contact_detail_company);
        ImageView favView = (ImageView) this.findViewById(R.id.fav_star);

        nameView.setText(contact.getName());
        companyView.setText(contact.getCompany());
        new PopulateImageTask(photoView).execute(contactDetail.getLargeImageURL());

        // Sets the star if this contact is a favorite
        if (contactDetail.isFavorite()) {
            favView.setImageResource(R.drawable.star);
        } else {
            favView.setImageResource(R.drawable.no_star);
        }

        // Populate the rest of the data

        // Get all the views by their ID
        TextView addressView = (TextView) this.findViewById(R.id.contact_detail_address);
        TextView cityView = (TextView) this.findViewById(R.id.contact_detail_city);
        TextView birthdayView = (TextView) this.findViewById(R.id.contact_detail_birthday);
        TextView emailView = (TextView) this.findViewById(R.id.contact_detail_email);

        // Populate the address
        addressView.setText(contactDetail.getAddress().getStreet());
        cityView.setText(contactDetail.getAddress().getCity() + ", " + contactDetail.getAddress().getState());

        // Populate the email
        emailView.setText(contactDetail.getEmail());

        // Populate the birthday, formatting it from a Date
        DateFormat df = new SimpleDateFormat("MMMM d, y");
        birthdayView.setText(df.format(contact.getBirthdate()));

        // Populate only the numbers that exist
        if (contact.getPhone().hasNumber("home")) {
            addNumber("Home", contact.getPhone().getHome());
        }

        if (contact.getPhone().hasNumber("work")) {
            addNumber("Work", contact.getPhone().getWork());
        }

        if (contact.getPhone().hasNumber("mobile")) {
            addNumber("Mobile", contact.getPhone().getMobile());
        }
    }

    /**
     * Adds a phone number to the UI for a contact
     * @param name the name of the number
     * @param number the telephone number
     */
    private void addNumber(String name, String number) {
        LinearLayout layout = (LinearLayout) this.findViewById(R.id.contact_detail_phone_layout);
        TextView numView = new TextView(this);
        numView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        numView.setText(number + " " + name);
        layout.addView(numView);
    }
}
