package ru.stqa.training.selenium;

import org.junit.Before;
import test.utils.Application;

public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void before(){
        if (tlApp.get() != null){
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit(); app = null;
                })
        );
    }
}
