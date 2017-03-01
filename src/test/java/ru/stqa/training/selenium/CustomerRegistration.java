package ru.stqa.training.selenium;

import org.junit.Before;
import org.junit.Test;
import test.utils.Customer;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by ilya on 08/02/2017.
 */
public class CustomerRegistration extends TestBase{
    private Customer customer;

    @Before
    public void setup(){
        customer = new Customer();
    }

    @Test
    public void customerRegistration(){
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
