import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

// make a test that does nothing so we just specify this in build.gradle
// (if we specify nothing, we get everything).
public class LoggingTest {
    @Test
    public void doNothing() {
        Logger logger = LogManager.getRootLogger();
        logger.info ("It lives");
    }
}
