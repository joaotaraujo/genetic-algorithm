package algoritmogenetico;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class IndividuoTP3 {
    
    private float gene[] = new float[2]; 
    private float fitness;
    private int d;
    
    public void criarIndividuo() {
        Random random = new Random();
        
        for(int j=0;j<d;j++)
            gene[j] = (float) (random.nextInt(100) * 0.01);
        calcularFitness();
    }

    public IndividuoTP3(int d) {
        this.d = d;
        this.fitness = 0;
    }
    
    public void calcularFitness() {
        
        float subFunction[] = new float[2];
        double exp = (double) 6;
        double exp2 = (double) 0.5;
        double sen=0;
        this.fitness=0;
        
        for(int i=0; i<d; i++){
            if (gene[i] >0.4 && gene[i] <=0.6){
                    sen = Math.abs(Math.pow(Math.sin(5*Math.PI*gene[i]),exp2));
                    subFunction[i] = (float) -(Math.exp(-2*Math.log(2)*Math.pow(((gene[i]-0.1)/0.8),2))*sen);
        }
        else {
            
                    sen = (Math.pow(Math.sin(5*Math.PI*gene[i]),exp));
                    subFunction[i] = (float) -((Math.exp(-2*Math.log(2)*Math.pow(((gene[i]-0.1)/0.8),2))*sen));
        } 
        }
        
        for(int k=0;k<d;k++)
            this.fitness = this.fitness + subFunction[k];
    }

    public float getGene(int i) {
        return gene[i];
    }

    public void setGene(int i, float gene) {
        this.gene[i] = gene;
    }
    
    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public void aplicaMutacao(float probMutacao, IndividuoTP3 pai1, IndividuoTP3 pai2){
        
        Random random = new Random();
        float probabilidade = (float) (random.nextInt(100) * 0.01);
        float vetorPertubacao[] = new float[2];
        
        if(probabilidade <= probMutacao){
            for(int i=0;i<d;i++){
                vetorPertubacao[i] = (float) (((float) (random.nextInt(100) * 0.01) * (pai2.gene[i] - pai1.gene[i])) * (2*(float) (random.nextInt(100) * 0.01)-1));
            }
        }
        
        for(int i=0;i<d;i++){
            this.gene[i] = this.gene[i] + vetorPertubacao[i];
        
            if(this.gene[i]<0)
                this.gene[i] = 0;
            if(this.gene[i]>1)
                this.gene[i] = 1;
        }
        calcularFitness();
    }
    
}
