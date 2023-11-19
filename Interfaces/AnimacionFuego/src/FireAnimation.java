import java.awt.Color;
import java.awt.image.BufferedImage;

public class FireAnimation extends BufferedImage {

    private Temperture temperture;
    private Palette palette = new Palette();
    private int width;
    private int height;
    
    // Constructor de la fire animation
    public FireAnimation(int w, int h) {
        super(w, h, BufferedImage.TYPE_INT_ARGB);
    
        width = w;
        height = h;
    
        this.temperture = new Temperture(width, height, 0.9, 0.3);
        this.initAnimation();
    }
    
    // Antes de poder generar la animacion añadimos los colors targets
    // y calculamos la palleta de colores
    public void initAnimation() {

        palette.addColorTarget(0, 0, 0, 0, 0);
        palette.addColorTarget(130, 255, 255, 200, 255);
        palette.addColorTarget(55, 0, 0, 0, 100);
        palette.addColorTarget(60, 155, 0, 0, 110);
        palette.addColorTarget(72, 200, 100, 0, 180);
        palette.addColorTarget(155, 255, 255, 255, 255);
        palette.addColorTarget(255, 255, 255, 255, 255);
        palette.addColorTarget(112, 235, 235, 40, 250);

        palette.calc();
    }
    
    // Creamos la imagen del fuego
    public void createFireImatge() {

        temperture.next();

        for (int i = 0; i < height - 2; i++) {
            for (int j = 0; j < width; j++) {

                int tempAct = temperture.tempertureMap[i][j];

                Color colorAct = palette.colorsPalette[tempAct];

                this.setRGB(j, i, colorAct.getRGB());
            }
        }
    }
}
