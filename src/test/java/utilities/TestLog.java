package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {
    public static void main(String[] args) {


        Logger logger = LogManager.getLogger(TestLog.class);

        System.out.println("Some test execution started"    );
        System.out.println("goin to dzone.com");
        logger.info("on dzone.com");
        System.out.println("click in login button");
        logger.warn("login in to application");
    }

}
