public class App {
    public static void main(String[] args) throws Exception {
        
        // //Tablas
        // for (int i = 1; i <= 10; i++){
        //     System.out.println(" ");
        //     System.out.print("Tabla del "+ i);
        //     System.out.println(" ");
        //     System.out.println(" ");
        //     for (int y = 1; y <= 10; y++){
                
        //         System.out.println(i*y);
            
        //     };
        // };
        
        // //SUMA DE MATRICES
        // int[][] matrx1 = {{2, 7},{9,0}};
        
        // int[][] matrx2 = {{6, 9},{5, 25}};
        
        // int[][] result = new int [2][2];
        
        // for (int i = 0; i < matrx1.length; i++) {
        //     for (int t = 0; t < matrx1[i].length; t++) {
        //         result[i][t] = matrx1[i][t] + matrx2[i][t];
        //     }
        // }
        
        // for (int i = 0; i < matrx1.length; i++) {
        //     for (int t = 0; t < matrx1[i].length; t++) {
        //         System.out.println(result[i][t]);
        //     }
        // }
        
        //MULTIPLICACION MATRICES
        int[][] matrx3 = {{1, 7},
                          {5, 8},
                          {6, 3}};
        
        int[][] matrx4 = {{4, 8, 3},
                          {5, 9, 0}};
        
        int result[][] = new int [3][3];
        
        for (int i = 0; i < 3; i++) {
            
            // System.out.println(matrx3[i][0]);
            // System.out.println(matrx3[i][0+1]);
            // System.out.println(" ");
            
            for (int t = 0; t < 3; t++) {
                // System.out.println(matrx4[0][t]);
                // System.out.println(matrx4[0+1][t]);
                // System.out.println(" ");
                
                result[i][t] = (matrx3[i][0] * matrx4[0][t]) + (matrx3[i][0+1] * matrx4[0+1][t]);
            }
        }
        
        for (int i = 0; i < result.length; i++) {
            for (int t = 0; t < result[i].length; t++) {
                System.out.println(result[i][t]);
            }
        }
    }
}
