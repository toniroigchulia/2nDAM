/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicselementalstask;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import static java.lang.Math.random;
import static java.lang.Math.round;
import java.util.Random;


/**
 *
 * @author juanm
 */
public class MyAnimation extends BufferedImage {

    int step;


    // --- Consructors ---
    //
    public MyAnimation(int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_ARGB);

        this.initAllBlack();
        this.next();
    }


    // --- Mètodes públics ---
    //          
    public void initAllBlack() {
        Color color = new Color(0, 0, 0, 0);

        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                this.setRGB(x, y, color.getRGB());
            }
        }
    }


    public void next() {

        Color color = new Color(new Random().nextInt(255), (this.step * 7) % 255, (128 + (this.step * 2)) % 255, 255);

        if (this.step >= this.getWidth()) {
            this.step = 0;
        }

        this.initAllBlack();

        for (int x = 0; x < this.getWidth(); x++) {
            this.setRGB(x, this.step, color.getRGB());
        }

        this.step += 3;
    }
}
