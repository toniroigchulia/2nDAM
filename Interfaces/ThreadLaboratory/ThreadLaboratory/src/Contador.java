public class Contador {
    private int value;

    public Contador(int v){
        this.value = v;
    }

    public void inc(){
        this.value =+ 1;
    }
    
    public void dec(){
        this.value =- 1;
    }
    
    public int getValue(){
        return this.value;
    }
}
