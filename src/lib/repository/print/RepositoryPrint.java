package lib.repository.print;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.IOException;

public class RepositoryPrint {
    public static void printSelectedPngFile(PrintService myService, String filePathAndName){
        try {
            FileInputStream fis = new FileInputStream(filePathAndName);
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, null);
            DocPrintJob printJob = myService.createPrintJob();
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
            fis.close();
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
