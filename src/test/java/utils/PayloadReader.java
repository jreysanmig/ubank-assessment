package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadReader {

    public static String readFileAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
