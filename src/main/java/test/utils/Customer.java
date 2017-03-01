package test.utils;

import java.util.Random;

/**
 * Created by ilya on 08/02/2017.
 */
public class Customer {
    private String firstname;
    private String lastname;
    private String address1;
    private String postcode;
    private String city;
    private String country;
    private String zone;
    private String email;
    private String phone;
    private String password;

    public static Builder newEntity() { return new Customer().new Builder(); }
    public class Builder {
        private Builder() {}
        public Builder withFirstname(String firstname) { Customer.this.firstname = firstname; return this; }
        public Builder withLastname(String lastname) { Customer.this.lastname = lastname; return this; }
        public Builder withAddress(String address1) { Customer.this.address1 = address1; return this; }
        public Builder withPostcode(String postcode) { Customer.this.postcode = postcode; return this; }
        public Builder withCity(String city) { Customer.this.city = city; return this; }
//        public Builder withCountry(String country) { Customer.this.country = country; return this; }
//        public Builder withZone(String zone) { Customer.this.zone = zone; return this; }
        public Builder withEmail(String email) { Customer.this.email = email; return this; }
        public Builder withPhone(String phone) { Customer.this.phone = phone; return this; }
        public Builder withPassword(String password) { Customer.this.password = password; return this; }
        public Customer build() {return Customer.this; }
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
