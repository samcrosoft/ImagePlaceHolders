package com.samcrosoft.imageplaceholder;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import static spark.Spark.*;

/**
 * Created by Samuel on 16/10/2014.
 */
public class App {

    static String IMAGE_PNG_TYPE = "png";
    /**
     * This is the default with of the generated image
     * @static
     */
    static Integer DEFAULT_IMAGE_WIDTH = 300;

    /**
     * This is the default height of the generated image
     * @static
     */
    static Integer DEFAULT_IMAGE_HEIGHT = 300;
    /**
     * This will be the default font size for the text on the image
     * @static
     */
    static Integer DEFAULT_FONT_SIZE    = 22;
    /**
     * This will hold the font name that will be used to draw the string
     * @static
     */
    static String DEFAULT_FONT_NAME = "DejaVu Sans";



    /**
     *
     * @param args
     */
    public static void main(String[] args){


        get("/:width/:height", (req, res) -> {
            Integer iWidth = Integer.parseInt(req.params("width"));
            Integer iHeight = Integer.parseInt(req.params("height"));

            // make sure the width and height are properly set
            iWidth = iWidth == 0 ? App.DEFAULT_IMAGE_WIDTH : iWidth;
            iHeight = iHeight == 0 ? App.DEFAULT_IMAGE_HEIGHT : iHeight;

            BufferedImage oImgText = new App().createImageWithText(iWidth, iHeight);
            try {
                OutputStream oOutput = res.raw().getOutputStream();
                ImageIO.write(oImgText, App.IMAGE_PNG_TYPE, oOutput);
                oOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return oImgText;
        });


        get("/:width", (req, res) -> {
            Integer iWidth = Integer.parseInt(req.params("width"));
            // make sure the width and height are properly set
            iWidth = iWidth == 0 ? App.DEFAULT_IMAGE_WIDTH : iWidth;

            BufferedImage oImgText;
            // use the image width as the height also
            oImgText = new App().createImageWithText(iWidth, iWidth);
            try {
                OutputStream oOutput = res.raw().getOutputStream();
                ImageIO.write(oImgText, App.IMAGE_PNG_TYPE, oOutput);
                oOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return oImgText;
        });
    }

    /**
     * This method wil;l generate the image required, with a text on it as a placeholder
     * @return BufferedImage
     */
    private BufferedImage createImageWithText(Integer iWidth, Integer iHeight){
        BufferedImage bufferedImage = new
                BufferedImage(iWidth,iHeight,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        Font oFont = new Font(App.DEFAULT_FONT_NAME, Font.BOLD + Font.PLAIN, App.DEFAULT_FONT_SIZE);
        g.setFont(oFont);
        g.drawString("www.tutorialspoint.com", 20,20);
        g.drawString("www.tutorialspoint.com", 20,40);
        g.drawString("www.tutorialspoint.com", 20,60);
        g.drawString("www.tutorialspoint.com", 20,80);
        g.drawString("www.tutorialspoint.com", 20,100);
        return bufferedImage;
    }
}
