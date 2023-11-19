import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Viewer extends Canvas {

    private BufferedImage backgroundImg;
    private FireAnimation FireImg;
    private BufferStrategy bs;

    public Viewer(int pixWidth, int pixHeight, FireAnimation FireImg) {
        Dimension d = new Dimension(pixWidth, pixHeight);
        this.setPreferredSize(d);
        this.loadBackground();
        this.FireImg = FireImg;
        bs = null;
    }

    // Cargamos la imagen de fondo para su uso
    private void loadBackground() {

        try {

            this.backgroundImg = ImageIO
                    .read(new File("..\\image\\bg.jpg"));
        } catch (Exception e) {
            System.err.println("Error loading background. ");
            System.err.println(e);
        }
    }

    // Metodo para dibujar en el buffer el background
    public void prepairBackground() {
        if (this.bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.backgroundImg, 0, 0, this.getWidth(), this.getHeight(), null);

        g.dispose();
    }

    // Metdo para mostrar el background
    public void paintBackground() {

        prepairBackground();
        bs.show();
    }

    // Metodo para dibujar en el buffer el fuego
    public void prepairFire() {
        if (this.bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        FireImg.createFireImatge();

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.FireImg, (int) (this.getWidth() / 2.43), (int) (this.getHeight() / 1.655),
                (int) (this.getWidth() / 2.2), (int) (this.getHeight() / 4.618), null);
        this.FireImg.createFireImatge();

        g.dispose();
    }

    // Metodo para mostrar el fuego
    public void paintFire() {

        prepairBackground();
        prepairFire();
        bs.show();
    }
}
