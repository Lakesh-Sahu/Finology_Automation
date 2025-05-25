package in.finology.utils;

import org.openqa.selenium.WebDriver;

public class ObjectManager {
    private static final ThreadLocal<ObjectCreator> object = new ThreadLocal<>();

    public static void init(WebDriver driver, String className, String methodName, String description) {
        object.set(new ObjectCreator(driver, className, methodName, description));
    }

    public static ObjectCreator getObject() {
        return object.get();
    }

    public static void remove() {
        object.remove();
    }
}