package pkgenum;


public class Enum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Prioridade pMin = Prioridade.MIN;
        Prioridade pMax = Prioridade.MAX;
        System.out.println(pMin.name());
        System.out.println(pMax.name());
        
        System.out.println(pMin.ordinal());
        System.out.println(pMax.ordinal());
        
        System.out.println(pMin.getValor());
        System.out.println(pMax.getValor());
    }
    
}
