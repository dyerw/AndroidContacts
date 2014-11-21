package com.liamdyer.solsticecontacts;

/**
 * Collects all the detailed information about a contact.
 */
public class ContactDetail {
    int employeeId;
    boolean favorite;
    String largeImageURL;
    String email;
    String website;
    Address address;

    public class Address {
        String street;
        String city;
        String state;
        String country;
        String zip;
        float latitude;
        float longitude;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getCountry() {
            return country;
        }

        public String getZip() {
            return zip;
        }

        public float getLatitude() {
            return latitude;
        }

        public float getLongitude() {
            return longitude;
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public Address getAddress() {
        return address;
    }
}
