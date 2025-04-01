package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            String testName = result.getName();
            String status = getResultStatusName(result.getStatus());
            LOGGER.warn("Retrying..: {} (Status: {}), try: {}/{}",
                    testName, status, (retryCount + 1), MAX_RETRY_COUNT);

            retryCount++;
            return true;
        }
        return false;
    }

    private String getResultStatusName(int status) {
        return switch (status) {
            case ITestResult.SUCCESS -> "SUCCESS";
            case ITestResult.FAILURE -> "FAILURE";
            case ITestResult.SKIP -> "SKIP";
            default -> "UNDEFINED";
        };
    }
}