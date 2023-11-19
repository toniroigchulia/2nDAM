package graphicselementalstask;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author juanm
 */
public class Viewer extends Canvas {

    private BufferedImage backgroundImg;
    private MyAnimation foregroundImg;
    private BufferStrategy bs;


    public Viewer(int pixWidth, int pixHeight, MyAnimation foregroundImg) {
        Dimension d = new Dimension(pixWidth, pixHeight);
        this.setPreferredSize(d);
        this.loadBackground();
        this.foregroundImg = foregroundImg;
        this.bs = null;
    }


    private void loadBackground() {
        try {
            this.backgroundImg = ImageIO.read(new File("C:\\Users\\Toni\\Documents\\2n DAM\\Interfaces\\Test\\image\\bg.jpg"));
            System.out.println("Background loaded :-)");
            System.out.println("Width: " + this.backgroundImg.getWidth());
            System.out.println("Height: " + this.backgroundImg.getHeight());
        } catch (IOException e) {
            System.err.println("Error loading background. ");
            System.err.println(e);
        }
    }


    @Override
    public void paint(Graphics g) {
        System.out.println("Overrided paint()");
        // this.myPaint();
    }


    public void paintBackground() {
        if (this.bs == null) {
            System.out.println("kgd");
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.backgroundImg, 0, 0, this.getWidth(), this.getHeight(), null);

        bs.show();
        g.dispose();
    }


    public void paintForegroundImage() {
        if (this.bs == null) {
            System.out.println("kgd");
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.foregroundImg, this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2, null);
        this.foregroundImg.next();

        bs.show();
        g.dispose();
    }

}
