package test.utils;

import com.tngtech.java.junit.dataprovider.DataProvider;
import test.utils.model.Customer;

import java.util.Random;

/**
 * Created by ilya on 01/03/2017.
 */
public class DataProviders {
    static final Random random = new Random();

    @DataProvider
    public static Object[][] validCustomer() {
        return new Object[][] {
                { Customer.newEntity()
                        .withFirstname("Firstname" + random.nextInt(100))
                        .withLastname("Lastname" + random.nextInt(100))
                        .withAddress("Address" + random.nextInt(100))
                        .withPostcode(String.valueOf(10000 + random.nextInt(89999)))
                        .withCity("City" + random.nextInt(100))
//                      .withCountry("US")
//                      .withZone("KS")
                        .withEmail("email" + random.nextInt(100) + "@email.com")
                        .withPhone(String.valueOf(1000000000 + random.nextInt(999999999)))
                        .withPassword("password" + random.nextInt(100))
                        .build() },
        };
    }
}
