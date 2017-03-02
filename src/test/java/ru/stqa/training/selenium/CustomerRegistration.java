package ru.stqa.training.selenium;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.utils.model.Customer;
import test.utils.DataProviders;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by ilya on 08/02/2017.
 */
@RunWith(DataProviderRunner.class)
public class CustomerRegistration extends TestBase{

    @Test
    @UseDataProvider(value = "validCustomer", location = DataProviders.class)
    public void customerRegistration(Customer customer){
        //сохраняем множество идентификаторов существующих клиентов до регистрации нового
        Set<String> oldCustomerIds = app.getCustomerIds();

        app.registerNewCustomer(customer);

        //сохраняем множество идентификаторов существующих клиентов после регистрации нового
        Set<String> newCustomerIds = app.getCustomerIds();

        //проверяем, что клиенты существовавшие до добавления нового никуда не делись
        assertTrue(newCustomerIds.containsAll(oldCustomerIds));
        //проверяем, что в множестве появился новый клиент
        assertTrue(newCustomerIds.size() == oldCustomerIds.size() + 1);
    }
}
