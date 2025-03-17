package nopCommerceAuto.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nopCommerceAuto.model.LoginTestData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDataReader {

    public static List<LoginTestData> readLoginTestData (String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<List<LoginTestData>>() {}
        );
    }
}
