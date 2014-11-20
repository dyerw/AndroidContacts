package com.liamdyer.solsticecontacts;

import java.util.Date;

/**
 * Represents the attributes of a single contact.
 */
public class Contact {
    String name, company, detailsURL, smallImageURL;
    Date birthdate;
    Phone phone;

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Phone getPhone() {
        return phone;
    }

//    /**
//     *
//     * @param name the name of the contact
//     * @param company the company the contact works for
//     * @param detailsURL a link to more information about the contact
//     * @param smallImageURL a link to a small picture for the contact
//     * @param birthdate the date the contact was born
//     * @param phone the phone number of the contact
//     */
//    public Contact(String name, String company,
//                   String detailsURL, String smallImageURL,
//                   Date birthdate, Phone phone) {
//        this.name = name;
//        this.company = company;
//        this.detailsURL = detailsURL;
//        this.smallImageURL = smallImageURL;
//        this.birthdate = birthdate;
//        this.phone = phone;
//    }

    static class Phone {
        String work, home, mobile;

        public String getWork() {
            return work;
        }

        public String getHome() {
            return home;
        }

        public String getMobile() {
            return mobile;
        }
    }
}
