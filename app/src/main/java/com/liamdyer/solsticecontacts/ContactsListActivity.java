package com.liamdyer.solsticecontacts;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class ContactsListActivity extends ListActivity {
    static final String jsonEndpoint = "https://solstice.applauncher.com/external/contacts.json";
    ContactsData contactsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        // Populate the contacts list with data
        ProgressDialog dialog = new ProgressDialog(this);
        try {

            PopulateContactsTask task = new PopulateContactsTask(dialog);
            task.execute(jsonEndpoint);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * An asynchronous task that makes a network call to the contacts JSON
     * and then uses that data to populate the UI.
     */
    class PopulateContactsTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        /**
         *
         * @param dialog dialog object we use to update our progress to the user
         */
        PopulateContactsTask(ProgressDialog dialog) {
            this.dialog = dialog;
        }

        /**
         * Let's the user know we're downloading the contacts
         * from the endpoint.
         */
        @Override
        protected void onPreExecute() {
            dialog.setTitle("Retrieving Contacts...");
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
            populateContacts(json);
        }
    }

    /**
     * Given a json string representing a list of contacts populates
     * the list view in the activity with that data.
     * @param data a json string list of contacts
     */
    private void populateContacts(String data) {
        // Parse JSON into an object
        try {
            contactsData = new ContactsData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If we weren't able to parse the JSON, let the user
        // know the data was corrupt
        if (contactsData == null) {
            //TODO: Update UI w/ error
            return;
        }

        // Populate ListView
        ContactArrayAdapter mContactsAdapter = new ContactArrayAdapter(
                // The context of the current activity
                this,
                // The data to populate with
                contactsData.contacts);

        ListView listView = this.getListView();
        listView.setAdapter(mContactsAdapter);
    }

    /**
     * Upon a list item being clicked a new activity is launched to show
     * details for that contact.
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ContactDetailActivity.class);
        intent.putExtra("contact", contactsData.contacts[position]);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
