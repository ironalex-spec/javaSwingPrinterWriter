package lib.repository.file;

import com.sun.jimi.core.Jimi;
import lib.app.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JIMIConvertImageAPI {
    private static JIMIConvertImageAPI single_instance = null;

    public static JIMIConvertImageAPI getInstance() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new JIMIConvertImageAPI();
        }
        return single_instance;
    }

    public boolean convertPCX_TO_PNG(String pathPCX, String pathPng){
        boolean isExecute = false;

        try {
            convertPCX_To_FileType(pathPCX, pathPng, "image/png");
            isExecute = true;
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return isExecute;
    }

    private static void convertPCX_To_FileType(java.lang.String srcImageFilename, java.lang.String dstImageFilename, String fileType)

            throws IOException, Exception {

        // read the image head

        byte[] head = new byte[128];

        int bitPlane = 0, type = 0;

        long fileLength = new File(srcImageFilename).length();

        BufferedInputStream openFile =

                new BufferedInputStream(new FileInputStream(srcImageFilename));

        long readLen = openFile.read(head, 0, 128);



        if ((head[3] & 0xff) == 1 && (head[65] & 0xff) == 1) {

            type = 0;

            bitPlane = 1;

        } else if (head[3] == 1 && head[65] == 2) {

            type = 1;

            bitPlane = 2;

        } else if (head[3] == 2 && head[65] == 1) {

            type = 2;

            bitPlane = 1;

        } else if (head[3] == 1 && head[65] == 3) {

            type = 3;

            bitPlane = 3;

        } else if (head[3] == 1 && head[65] == 4) {

            type = 4;

            bitPlane = 4;

        } else if (head[3] == 4 && head[65] == 1) {

            type = 5;

            bitPlane = 1;

        } else if (head[3] == 8 && head[65] == 1) {

            type = 6;

            bitPlane = 1;

        } else if (head[3] == 8 && head[65] == 3) {

            type = 7;

            bitPlane = 3;

        }

        int width =

                ((((int) head[9] & 0x0ff) << 8) | ((int) head[8] & 0xff))

                        - ((((int) head[5] & 0x0ff) << 8) | ((int) head[4] & 0xff))

                        + 1;



        int height =

                ((((int) head[11] & 0x0ff) << 8) | ((int) head[10] & 0xff))

                        - ((((int) head[7] & 0x0ff) << 8) | ((int) head[6] & 0xff))

                        + 1;

        int version = head[1];


        int color = 0;

        if (version == 2 || version > 3)

            color = 1;

        else

            color = 0;



        int bytesPerLine = ((((int) head[67] & 0x0ff) << 8) | ((int) head[66] & 0xff));


        BufferedImage innerImage;
        int[] unComData;
        int inc;
        byte[] tempAry;

        switch (type) {
            case 0:
                if ((bytesPerLine*8)>width)

                    width=bytesPerLine*8;

                innerImage =

                        new BufferedImage(

                                width,

                                height,

                                BufferedImage.TYPE_BYTE_BINARY);

                java.awt.image.WritableRaster raster = innerImage.getRaster();


                unComData = new int[width * height];

                inc = 0;

                tempAry = new byte[1];

                while ((readLen = openFile.read(tempAry)) != -1) {

                    if (((int) tempAry[0] & 0xff) < 192) {

                        unComData[inc] = (int) tempAry[0] & 0xff;

                        inc++;

                    } else {

                        int count = ((int) tempAry[0]) & 0x3f;

                        readLen = openFile.read(tempAry);


                        for (int j = 0; j < count; j++)

                            unComData[inc + j] = (int) tempAry[0] & 0xff;

                        inc += count;

                    }

                }


                int[] data2 = new int[width * height];
                int k = 0;



                for (int j = 0; j < inc; j++) {

                    int temp = unComData[j];



                    for (int i = 1; i < 255; i *= 2, k++) {

                        if ((temp & 0x80) == 0x80)
                            data2[k] |= 0x01;
                        else
                            data2[k] = 0x00;

                        temp <<= 1;

                    }

                }

                try {

                    raster.setPixels(0, 0, width, height, data2);



                    innerImage =

                            new BufferedImage(

                                    innerImage.getColorModel(),

                                    raster,

                                    false,

                                    null);

                    Jimi.putImage(fileType, innerImage, dstImageFilename);

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("set image error!");

                    e.printStackTrace();

                }

                break;

            case 7:

                innerImage =

                        new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                raster = innerImage.getRaster();

                unComData = new int[width * height * 3];

                inc = 0;

                tempAry = new byte[1];


                while ((readLen = openFile.read(tempAry)) != -1) {

                    if (((int) tempAry[0] & 0xff) < 192) {

                        unComData[inc] = (int) tempAry[0] & 0xff;

                        inc++;

                    } else {

                        int count = ((int) tempAry[0]) & 0x3f;

                        readLen = openFile.read(tempAry);

                        for (int j = 0; j < count; j++)

                            unComData[inc + j] = (int) tempAry[0] & 0xff;

                        inc += count;

                    }



                }

                int[] rAry = new int[bytesPerLine];

                int[] gAry = new int[bytesPerLine];

                int[] bAry = new int[bytesPerLine];

                int len = unComData.length;

                for (int i = 0; i < len; i += bytesPerLine * 3) {

                    for (int j = 0; j < bytesPerLine; j++) {

                        rAry[j] = unComData[i + j];

                        gAry[j] = unComData[i + bytesPerLine + j];

                        bAry[j] = unComData[i + 2 * bytesPerLine + j];

                    }



                    for (int j = 0; j < bytesPerLine; j++) {

                        unComData[i + (j * 3)] = rAry[j];

                        unComData[i + (j * 3) + 1] = gAry[j];

                        unComData[i + (j * 3) + 2] = bAry[j];

                    }

                }

                try {

                    raster.setPixels(0, 0, width, height, unComData);

                    innerImage =

                            new BufferedImage(

                                    innerImage.getColorModel(),

                                    raster,

                                    false,

                                    null);


                    Jimi.putImage(fileType, innerImage, dstImageFilename);

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("set image error!");

                    e.printStackTrace();

                }

                break;


            default:
                break;
        }

        openFile.close();
    }
}
