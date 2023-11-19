import java.awt.Color;

public class ColorTarget implements Comparable<ColorTarget>{
    
    private int temperature;
    private Color color;
    
    public ColorTarget(Color c, int temp){
    
        temperature = temp;
        color = c;
    }
    
    public int getA() {
        
        return color.getAlpha();
    }
    
    public int getB() {
        
        return color.getBlue();
    }
    
    public int getG() {
        
        return color.getGreen();
    }
    
    public int getR() {
        
        return color.getRed();
    }
    
    public int getTemperature() {
    
        return temperature;
    }

    @Override
    public int compareTo(ColorTarget o) {
        
        if ((this.temperature - o.getTemperature()) > 0){
        
            return 1;
        } else if ((this.temperature - o.getTemperature()) < 0){
        
            return -1;
        } else {
        
            return 0;
        }
    }
}
