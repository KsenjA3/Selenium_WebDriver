package tests.aqa.utils;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Log4j2
public class ConfProperties {
    private static Properties PROPERTIES;

    /**
     * указание пути до файла с настройками
     */
    static {
        try (var bufIn = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/main/resources/conf.yaml"), "UTF-8"))){
            PROPERTIES = new Properties();
            PROPERTIES.load(bufIn);
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key); }
}