import java.util.Random;

public class Temperture {

    Random rand = new Random();

    public int[][] tempertureMap;
    private int width;
    private int height;

    private double coldChance;
    private double sparkChance;

    // Constructores
    public Temperture(int x, int y, double coldChance, double sparkChance) {
        this.tempertureMap = new int[y][x];

        setWidth(x);
        setHeight(y);
        setColdChance(coldChance);
        setSparkChance(sparkChance);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tempertureMap[i][j] = 0;
            }
        }
    }

    // Actualizar el mapa
    public void next() {

        cold();
        spark();
        calc();
    }

    // Enfriar el mapa
    private void cold() {

        for (int i = 0; i < width; i++) {

            double r = rand.nextDouble(1);

            if (r < coldChance) {

                this.tempertureMap[height - 1][i] = 0;
            }
        }
    }

    // Crear chispas
    private void spark() {

        for (int i = 0; i < width; i++) {

            double r = rand.nextDouble(1);

            if (r < sparkChance) {

                this.tempertureMap[height - 1][i] = 255;
            }
        }
    }

    // Calcular las temperaturas
    private void calc() {
        
        //Variables para posiciones
        int posInc;
        int posDer;
        int posIzq;
        
        int posCentD;
        int posDerD;
        int posIzqD;
        
        //Variable para guardar temperatura
        int mediaTemp;
        
        for (int i = height - 2; i > -1; i--) {

            // Calculamos la temperatura de la primera columna
            posInc = this.tempertureMap[i][0];
            posDer = this.tempertureMap[i][1];
            posCentD = this.tempertureMap[i + 1][0];
            posDerD = this.tempertureMap[i + 1][1];

            mediaTemp = (posInc + posCentD + posDerD + posDer) / 4;
            this.tempertureMap[i][0] = mediaTemp;

            // Calculamos la temperatura de la ultima columna
            posInc = this.tempertureMap[i][width - 1];
            posCentD = this.tempertureMap[i + 1][width - 1];
            posIzqD = this.tempertureMap[i + 1][width - 2];
            posIzq = this.tempertureMap[i][width - 2];

            mediaTemp = (posInc + posIzqD + posCentD + posIzq) / 4;
            this.tempertureMap[i][width - 1] = mediaTemp;

            // Calculamos todos los valores de entremedias
            for (int j = 1; j < width - 1; j++) {

                posInc = this.tempertureMap[i][j];
                posDer = this.tempertureMap[i][j + 1];
                posIzq = this.tempertureMap[i][j - 1];
                
                posIzqD = this.tempertureMap[i + 1][j - 1];
                posCentD = this.tempertureMap[i + 1][j];
                posDerD = this.tempertureMap[i + 1][j + 1];

                mediaTemp = (posInc + posIzqD + posCentD + posDerD + posDer + posIzq) / 6;

                this.tempertureMap[i][j] = mediaTemp;
            }
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColdChance(double coldChance) {
        this.coldChance = coldChance;
    }

    public void setSparkChance(double sparkChance) {
        this.sparkChance = sparkChance;
    }
}
