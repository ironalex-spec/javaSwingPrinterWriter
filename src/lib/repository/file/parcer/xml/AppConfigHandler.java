package lib.repository.file.parcer.xml;

import lib.app.Settings;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AppConfigHandler extends DefaultHandler {
    private boolean isTemplateFolder = false;
    private boolean isTemplateTemporaryFolder = false;
    private boolean isTemplatePrintingFolder = false;
    private boolean isImageMagickApiFolder = false;
    private boolean isLabelExternalPCXfolder = false;
    private boolean isLabelPCXToPNGfolder = false;

    private boolean isTemplateDefaultName = false;
    private boolean isTemplateTemporaryDefaultName = false;
    private boolean isLabelPrintingName = false;

    private boolean isPPIInch = false;
    private boolean isPPICm = false;

    private boolean isWindowHeight = false;
    private boolean isWindowWidth = false;
    private boolean isWindowPosition = false;

    private boolean isContentPosition = false;

    private boolean isTypePrinting = false;

    private int intParseValues = 0;



    //parser starts parsing a specific element inside the document
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        checkClassFields(qName, true);
    }

    //parser ends parsing the specific element inside the document
    public void endElement(String uri, String localName, String qName) throws SAXException {
        checkClassFields(qName, false);
    }

    //reads the text value of the currently parsed element
    public void characters(char ch[], int start, int length) throws SAXException {
        if(isTemplateFolder) {
            Settings.TEMPLATE_FOLDER = new String(ch, start, length);
            intParseValues++;
        } else if (isTemplateTemporaryFolder) {
            Settings.TEMPLATE_TEMP_FOLDER = new String(ch, start, length);
            intParseValues++;
        } else if (isTemplatePrintingFolder) {
            Settings.TEMPLATE_PRINTING_FOLDER = new String(ch, start, length);
            intParseValues++;
        } else if (isImageMagickApiFolder) {
            Settings.IMAGE_MAGICK_API_FOLDER = new String(ch, start, length);
            intParseValues++;
        } else if (isLabelExternalPCXfolder) {
            Settings.LABEL_EXTERNAL_PCX_FOLDER = new String(ch, start, length);
            intParseValues++;
        } else if (isLabelPCXToPNGfolder) {
            Settings.LABEL_PCX_TO_PNG_FOLDER = new String(ch, start, length);
            intParseValues++;


        } else if (isTemplateDefaultName) {
            Settings.TEMPLATE_DEFAULT_NAME = new String(ch, start, length);
            intParseValues++;
        } else if (isTemplateTemporaryDefaultName) {
            Settings.TEMPLATE_TEMP_DEFAULT_NAME = new String(ch, start, length);
            intParseValues++;
        } else if (isLabelPrintingName) {
            Settings.TEMPLATE_PRINTING_NAME = new String(ch, start, length);
            intParseValues++;


        } else if (isPPIInch) {
            Settings.PPI_INCH_Screen = Integer.parseInt(new String(ch, start, length));
            intParseValues++;
        } else if (isPPICm) {
            Settings.PPI_CM_Screen = Integer.parseInt(new String(ch, start, length));
            intParseValues++;


        } else if (isWindowHeight) {
            Settings.baseWindowHeight = Integer.parseInt(new String(ch, start, length));
            intParseValues++;
        } else if (isWindowWidth) {
            Settings.baseWindowWidth = Integer.parseInt(new String(ch, start, length));
            intParseValues++;
        } else if (isWindowPosition) {
            Settings.baseWindowPosition = new String(ch, start, length);
            intParseValues++;
        } else if (isContentPosition) {
            Settings.baseWindowContentPosition = Integer.parseInt(new String(ch, start, length));
            intParseValues++;
        }

        else if (isTypePrinting) {
            Settings.typePrinting = Integer.parseInt(new String(ch, start, length));
            intParseValues++;
        }
    }

    public int getParsedValues(){
        return intParseValues;
    }

    private void checkClassFields(String qName, boolean isStart){
        if(isStart) {
            isTemplateFolder = qName.equalsIgnoreCase("template_folder");
            isTemplateTemporaryFolder = qName.equalsIgnoreCase("template_temporary_folder");
            isTemplatePrintingFolder = qName.equalsIgnoreCase("template_printing_folder");
            isImageMagickApiFolder = qName.equalsIgnoreCase("image_magick_api_folder");
            isLabelExternalPCXfolder = qName.equalsIgnoreCase("label_external_PCX_folder");
            isLabelPCXToPNGfolder = qName.equalsIgnoreCase("label_PCX_TO_PNG_folder");

            isTemplateDefaultName = qName.equalsIgnoreCase("template_default_name");
            isTemplateTemporaryDefaultName = qName.equalsIgnoreCase("template_temporary_default_name");
            isLabelPrintingName = qName.equalsIgnoreCase("label_printing_name");

            isPPIInch = qName.equalsIgnoreCase("PPI_INCH");
            isPPICm = qName.equalsIgnoreCase("PPI_CM");

            isWindowHeight = qName.equalsIgnoreCase("height");
            isWindowWidth = qName.equalsIgnoreCase("width");
            isWindowPosition = qName.equalsIgnoreCase("position");
            isContentPosition = qName.equalsIgnoreCase("content_position");

            isTypePrinting = qName.equalsIgnoreCase("type_printing");
        } else {
            isTemplateFolder = false;
            isTemplateTemporaryFolder = false;
            isTemplatePrintingFolder = false;
            isImageMagickApiFolder = false;
            isLabelExternalPCXfolder = false;
            isLabelPCXToPNGfolder = false;

            isTemplateDefaultName = false;
            isTemplateTemporaryDefaultName = false;
            isLabelPrintingName = false;

            isPPIInch = false;
            isPPICm = false;

            isWindowHeight = false;
            isWindowWidth = false;
            isWindowPosition = false;
            isContentPosition = false;
            isTypePrinting = false;
        }
    }
}
