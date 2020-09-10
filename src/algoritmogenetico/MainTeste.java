package algoritmogenetico;

public class MainTeste {
    
    public static void main(String[] args){
        
        //testa algoritmo 5
        /*AlgoritmoGeneticoTP5 ag5 = new AlgoritmoGeneticoTP5(100,1, (float) 0.01,1,1);
    
        for(int i=0; i<100;i++){
            ag5.rodaUmaGeracao();
            ag5.imprimePopulacao(ag5.getPopulacao());
        }*/
        
        //testa algoritmo 3
        AlgoritmoGeneticoTP5 ag3 = new AlgoritmoGeneticoTP5(100,1, (float) 0.01,1,1);
    
        for(int i=0; i<100;i++){
            ag3.rodaUmaGeracao();
            ag3.imprimePopulacao(ag3.getPopulacao());
        }
    }
}
