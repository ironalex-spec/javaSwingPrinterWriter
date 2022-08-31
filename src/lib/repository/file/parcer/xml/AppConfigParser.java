package lib.repository.file.parcer.xml;

import lib.repository.file.parcer.xml.AppConfigHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AppConfigParser {
    public static boolean parse() {
        boolean isParse = false;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            AppConfigHandler appConfigHandler = new AppConfigHandler();
            saxParser.parse("config.xml", appConfigHandler);
            isParse = appConfigHandler.getParsedValues() == 16;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isParse;
    }
}
