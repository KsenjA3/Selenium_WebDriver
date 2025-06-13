package utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;

public class Log4jAllureIntegration {

    private static final Logger logger = LogManager.getLogger(Log4jAllureIntegration.class);

    public static void logToAllure(String message) {
        logger.info(message);
        Allure.addAttachment("Log4j", new ByteArrayInputStream(message.getBytes()));
    }



}

