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
    private String email;
    private String phone;
    private String password;

    public Customer() {
        final Random random = new Random();
        this.firstname = "Firstname" + random.nextInt(100);
        this.lastname = "Lastname" + random.nextInt(100);
        this.address1 = "Address" + random.nextInt(100);;
        this.postcode = String.valueOf(10000 + random.nextInt(89999));
        this.city = "City" + random.nextInt(100);
        this.email = "email" + random.nextInt(100) + "@email.com";
        this.phone = String.valueOf(1000000000 + random.nextInt(999999999));
        this.password = "password" + random.nextInt(100);
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
