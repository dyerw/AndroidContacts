package com.liamdyer.solsticecontacts;

import java.util.Date;

/**
 * Represents the attributes of a single contact.
 */
public class Contact {
    String name, company, detailsURL, smallImageURL;
    Date birthdate;
    Phone phone;
    int employeeId;

    public String toString() {
        return this.name + " " + phone.work;
    }

    public int getEmployeeId() {
        return employeeId;
    }

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
