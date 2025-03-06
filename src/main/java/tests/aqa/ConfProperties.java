package tests.aqa;

import lombok.extern.log4j.Log4j2;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfProperties {
    private static Properties PROPERTIES;

    /**
     * указание пути до файла с настройками
     */
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/conf.yaml")){
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
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