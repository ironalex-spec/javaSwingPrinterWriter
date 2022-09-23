package test.java;


/**

 * @author Koustubha Kale

 * koustubha_kale@yahoo.com

 * I have made liberal use of the code I have found on the net in this,

 * although I did not find one that actually worked. Whereas this does. You can
use this

 * in the same manner if you find it usefull.

 */

import com.sun.jimi.core.*;

import com.sun.jimi.core.raster.*;

import com.sun.jimi.core.JimiException;

import javax.swing.*;

import javax.swing.event.*;

import java.awt.*;

import java.awt.color.ICC_ColorSpace;

import java.awt.color.ICC_ProfileRGB;

import java.awt.event.*;

import java.awt.image.*;

import java.beans.PropertyVetoException;

import java.io.*;

import java.util.*;

import javax.imageio.*;

import java.util.Arrays;



public class ImageMagickTest {

    public static void main(String args[]) {

//String readFormats[] = Jimi.getDecoderTypes();

//String writeFormats[] = Jimi.getEncoderTypes();

//System.out.println("Decoders: " + Arrays.asList(readFormats));

//System.out.println("Encoders: " + Arrays.asList(writeFormats));



        try {

//Image im = Jimi.getImage("ims.pcx", Jimi.SYNCHRONOUS);

//System.out.println("Image Height="+im.getHeight()+", Image Width="+im.getWidth());


//System.out.println("Is err? "+im.isError());

//Jimi.putImage("image/jpg", im, "ims-1.jpg");

//System.out.println("Wrote Image.");

//loadImage("ASCPRIN.pcx");

            loadImage("DR.pcx");

        } catch (Exception e) {

            System.out.println("Err :" + e);

            e.printStackTrace();

        }



    }



    public static void loadImage(java.lang.String imageFilename)

            throws IOException, Exception {

// read the image head

        byte[] head = new byte[128];

        int bitPlane = 0, type = 0;

        long fileLength = new File(imageFilename).length();

        BufferedInputStream openFile =

                new BufferedInputStream(new FileInputStream(imageFilename));

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

        System.out.print("nPlanes=" + (head[65] & 0xff));

        System.out.print("Image type =" + type);

        int width =

                ((((int) head[9] & 0x0ff) << 8) | ((int) head[8] & 0xff))

                        - ((((int) head[5] & 0x0ff) << 8) | ((int) head[4] & 0xff))

                        + 1;



        int height =

                ((((int) head[11] & 0x0ff) << 8) | ((int) head[10] & 0xff))

                        - ((((int) head[7] & 0x0ff) << 8) | ((int) head[6] & 0xff))

                        + 1;

        System.out.print("; Width,height=" + width + "," + height);

        int version = head[1];

        System.out.println("; version=" + version);

        int color = 0;

        if (version == 2 || version > 3)

            color = 1;

        else

            color = 0;



        int bytesPerLine = //(int) head[67] & 0x0ff;

                ((((int) head[67] & 0x0ff) << 8) | ((int) head[66] & 0xff));

        System.out.println("bytesPerLine=" + bytesPerLine);



        if (type == 0) {

            if ((bytesPerLine*8)>width)

                width=bytesPerLine*8;

            BufferedImage innerImage =

                    new BufferedImage(

                            width,

                            height,

                            BufferedImage.TYPE_BYTE_BINARY);

            java.awt.image.WritableRaster raster = innerImage.getRaster();



            int[] unComData = new int[width * height];

            int inc = 0;

            byte[] tempAry = new byte[1];



            while ((readLen = openFile.read(tempAry)) != -1) {



                if (((int) tempAry[0] & 0xff) < 192) {

//for (int j = 0; j < 8; j++)

                    unComData[inc] = (int) tempAry[0] & 0xff;

//inc += 8;

//System.out.println("Data value="+((int) tempAry[0] & 0xff));

                    inc++;

                } else {

                    int count = ((int) tempAry[0]) & 0x3f;

                    readLen = openFile.read(tempAry);

//System.out.println("multiple Data value="+((int) tempAry[0] & 0xff));

//System.out.println("Count="+count);

                    for (int j = 0; j < count; j++)

                        unComData[inc + j] = (int) tempAry[0] & 0xff;

                    inc += count;

                }



            }





            System.out.println(

                    "Palett?=" + (unComData[unComData.length - 770] & 0xff));

            System.out.println("inc=" + inc);

            System.out.println("unComData length=" +unComData.length);

            int[] data2 = new int[width * height];

            int k = 0;





            for (int j = 0; j < inc; j++) {

                int temp = unComData[j];



                for (int i = 1; i < 255; i *= 2, k++) {



//System.out.println("i="+i);

                    if ((temp & 0x80) == 0x80)

                        data2[k] |= 0x01;

                    else

                        data2[k] = 0x00;

                    temp <<= 1;

                }

            }



            System.out.println("data2 length=" + k);

            try {

                raster.setPixels(0, 0, width, height, data2);



                innerImage =

                        new BufferedImage(

                                innerImage.getColorModel(),

                                raster,

                                false,

                                null);

/*JFrame frame=new JFrame();

JLabel l=new JLabel();

l.setIcon(new ImageIcon(innerImage));

frame.getContentPane().add(l);

frame.pack();

frame.show();

*/



                Jimi.putImage("image/jpg", innerImage, "ASCPRIN.jpg");

                Jimi.putImage("image/png", innerImage, "ASCPRIN.png");

                ImageIO.write(innerImage, "jpg", new File("ASCPRIN11.jpg"));



            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("set image error!");

                e.printStackTrace();

            }

        }

        if (type == 7) {

            BufferedImage innerImage =

                    new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            java.awt.image.WritableRaster raster = innerImage.getRaster();



            int[] unComData = new int[width * height * 3];

            int inc = 0;

            byte[] tempAry = new byte[1];



            while ((readLen = openFile.read(tempAry)) != -1) {



                if (((int) tempAry[0] & 0xff) < 192) {

                    unComData[inc] = (int) tempAry[0] & 0xff;

                    inc++;

                } else {

                    int count = ((int) tempAry[0]) & 0x3f;

                    readLen = openFile.read(tempAry);



//System.out.println("Count="+count);

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

// System.out.println(i+bytesPerLine+j);

                    gAry[j] = unComData[i + bytesPerLine + j];

                    bAry[j] = unComData[i + 2 * bytesPerLine + j];

                }



                for (int j = 0; j < bytesPerLine; j++) {



//for (int k = 0; k < 3; k++) {

                    unComData[i + (j * 3)] = rAry[j];

                    unComData[i + (j * 3) + 1] = gAry[j];

                    unComData[i + (j * 3) + 2] = bAry[j];



//}

                }

            }

            System.out.println("unComData length=" + unComData.length);

            System.out.println("inc=" + inc);



            try {

                raster.setPixels(0, 0, width, height, unComData);

//ColorModel cm=new DirectColorModel(java.awt.color.ColorSpace.getInstance(java.awt.color.ColorSpace.CS_sRGB),false,false,Transparency.TRANSLUCENT,DataBuffer.TYPE_BYTE);


                innerImage =

                        new BufferedImage(

                                innerImage.getColorModel(),

                                raster,

                                false,

                                null);

                Jimi.putImage("image/jpg", innerImage, "joshi-2.jpg");

                Jimi.putImage("image/png", innerImage, "joshi-2.png");

                ImageIO.write(innerImage, "jpg", new File("joshi.jpg"));

            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("set image error!");

                e.printStackTrace();

            }





        }



//

        openFile.close();

    }



}
//end of file..
