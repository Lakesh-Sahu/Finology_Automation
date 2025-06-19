package in.finology.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.Semaphore;

// This class is used to create and manage the ObjectCreator class per thread for parallel execution
// ThreadLocal manages the ObjectCreator objects
// Semaphore manages the maximum number of threads and its cycle
public class ObjectManager extends Base {
    private static final Logger log = LogManager.getLogger(ObjectManager.class);
    static int getObjectErrorCount = 1;

    private static final ThreadLocal<ObjectCreator> object = new ThreadLocal<>();
    private static final Semaphore semaphore = new Semaphore(maxParallelThreadAllowed, fair);

    public static void init(WebDriver driver, String featureURI, String scenarioName, String scenarioLine, String scenarioTags) {
        try {
            object.set(new ObjectCreator(driver, featureURI, scenarioName, scenarioLine, scenarioTags));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while initiating in init method of ObjectManager class");
        }
    }

    // Get the ObjectCreator Class Object of current thread
    public static ObjectCreator getObject() {
        try {
            ObjectCreator obj = object.get();
            if (obj != null) {
             return obj;
            } else {
                init(null, "#"  + getObjectErrorCount++ + " Error : no object found in getObject method of ObjectCreator class : ", "ObjectManager getObject", "null", "null");
                return object.get();
            }
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Object on ObjectManager");
            init(null, "#"  + getObjectErrorCount++ + " Exception no object found in getObject method of ObjectCreator class : ", "ObjectManager getObject", "null", "null");
            return object.get();
        }
    }

    // Remove the ObjectCreator Class Object of current thread
    public static void remove() {
        try {
            object.remove();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while removing Object on remove method of ObjectCreator class");
        }
    }

    // Acquire 1 permit of Semaphore
    public static void acquire() {
        try {
            semaphore.acquire();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while acquiring thread on acquire method of ObjectCreator class");
        }
    }

    // Release 1 permit of Semaphore
    public static void release() {
        try {
            semaphore.release();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while releasing thread on release method of ObjectCreator class");
        }
    }
}