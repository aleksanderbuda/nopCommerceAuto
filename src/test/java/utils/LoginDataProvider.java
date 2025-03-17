package utils;

import nopCommerceAuto.model.LoginTestData;
import nopCommerceAuto.utils.JsonDataReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "credentialsData")
    public static Object[][] getCredentialsData() throws IOException {
        return loadTestData("src/test/resources/valid_invalid_credentials.json");
    }

    @DataProvider(name = "emailFormatData")
    public static Object[][] getEmailFormatData() throws IOException {
        return loadTestData("src/test/resources/invalid_email_format.json");
    }

    private static Object[][] loadTestData(String filePath) throws IOException {
        List<LoginTestData> testData = JsonDataReader.readLoginTestData(filePath);
        return testData.stream()
                .map(tc -> new Object[]{tc.getEmail(), tc.getPassword(), tc.isExpectSuccess()})
                .toArray(Object[][]::new);
    }
}
