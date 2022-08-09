package lib.service.file.parcers.xml;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class AppConfigParser {
    public static boolean parse() {
        boolean isParse = false;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            AppConfigHandler appConfigHandler = new AppConfigHandler();
            saxParser.parse("config.xml", appConfigHandler);
            isParse = appConfigHandler.getParsedValues() == 11;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isParse;
    }
}
