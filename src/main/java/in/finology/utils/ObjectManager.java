package in.finology.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.Semaphore;

public class ObjectManager extends Base {
    private static Logger log = LogManager.getLogger(ObjectManager.class);
    static int count1 = 1;
    static int count2 = 1;

    private static final ThreadLocal<ObjectCreator> object = new ThreadLocal<>();
    private static final Semaphore semaphore = new Semaphore(maxParallelThreadAllowed, fair);

    public static void init(WebDriver driver, String featureURI, String scenarioName, String scenarioLine, String scenarioTags) {
        try {
            object.set(new ObjectCreator(driver, featureURI, scenarioName, scenarioLine, scenarioTags));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while initiating Object on ObjectManager");
        }
    }

    public static ObjectCreator getObject() {
        try {
            ObjectCreator obj = object.get();
            if (obj != null) {
             return obj;
            } else {
                init(null, "#"  + count1++ + " Error : no object found in getObject method : ", "ObjectManager getObject", "null", "null");
                return object.get();
            }
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Object on ObjectManager");
            init(null, "#"  + count2++ + " Error no object found in getObject method : ", "ObjectManager getObject", "null", "null");
            return object.get();
        }
    }

    public static void remove() {
        try {
            object.remove();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while removing Object on ObjectManager");
        }
    }

    public static void acquire() {
        try {
            semaphore.acquire();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while acquiring thread on ObjectManager");
        }
    }

    public static void release() {
        try {
            semaphore.release();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while releasing thread on ObjectManager");
        }
    }
}