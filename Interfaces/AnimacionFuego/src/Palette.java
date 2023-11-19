import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Color;

public class Palette {

    Color[] colorsPalette = new Color[256];
    ArrayList<ColorTarget> colorTarget = new ArrayList<ColorTarget>();
    
    public Palette() {
    }

    public void calc() {

        tempCheck();

        // Ordenamos la lista de colorsTargets
        colorTarget.sort(Comparator.naturalOrder());

        // Llenamos la colorsPalette
        for (int i = 0; i < colorTarget.size() - 1; i++) {

            float dist = (colorTarget.get(i + 1).getTemperature() - (colorTarget.get(i).getTemperature()));

            float incR = ((colorTarget.get(i + 1).getR() - colorTarget.get(i).getR()) / dist);
            float incG = ((colorTarget.get(i + 1).getG() - colorTarget.get(i).getG()) / dist);
            float incB = ((colorTarget.get(i + 1).getB() - colorTarget.get(i).getB()) / dist);
            float incA = ((colorTarget.get(i + 1).getA() - colorTarget.get(i).getA()) / dist);

            while (dist >= 0) {

                int tempActu = Math.round((dist + colorTarget.get(i).getTemperature()));

                int actR = Math.round((dist * incR) + colorTarget.get(i).getR());
                int actG = Math.round((dist * incG) + colorTarget.get(i).getG());
                int actB = Math.round((dist * incB) + colorTarget.get(i).getB());
                int actA = Math.round((dist * incA) + colorTarget.get(i).getA());

                Color newColor = new Color(actR, actG, actB, actA);

                colorsPalette[tempActu] = newColor;

                dist = dist - 1;
            }
        }
    }

    public void tempCheck() {

        boolean temp0 = false;
        boolean temp255 = false;

        // Comprbamos que dentro de la lista haya la temperatura 0 y 255
        for (ColorTarget i : colorTarget) {

            if (i.getTemperature() == 0) {

                temp0 = true;
            }

            if (i.getTemperature() == 255) {

                temp255 = true;
            }
        }

        // Si no estan dentro las añadimos
        if (!temp0) {

            Color c = new Color (0,0,0,0);
            
            colorTarget.add(new ColorTarget(c, 0));
        }
        if (!temp255) {

            Color c = new Color (255,255,255,255);
            
            colorTarget.add(new ColorTarget(c, 255));
        }
    }
    
    //Añadir colorTargets
    public void addColorTarget(int temp, int r, int g, int b, int a) {

        Color c = new Color (r, g, b, a);
        
        colorTarget.add(new ColorTarget(c, temp));
    }
}
