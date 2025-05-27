package in.finology.utils;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.Semaphore;

public class ObjectManager {
    private static final ThreadLocal<ObjectCreator> object = new ThreadLocal<>();
    private static final Semaphore semaphore = new Semaphore(2);

    public static void init(WebDriver driver, String featureURI, String scenarioName, String scenarioLine, String scenarioTags) {
        object.set(new ObjectCreator(driver, featureURI, scenarioName, scenarioLine, scenarioTags));
    }

    public static ObjectCreator getObject() {
        return object.get();
    }

    public static void remove() {
        object.remove();
    }

    public static void acquire() throws InterruptedException {
        semaphore.acquire();
    }

    public static void release() {
        semaphore.release();
    }
}