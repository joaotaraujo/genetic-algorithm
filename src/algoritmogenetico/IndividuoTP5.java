package algoritmogenetico;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class IndividuoTP5 {
    
    private float gene[] = new float[2]; 
    private float fitness;
    private int d;
    
    public void criarIndividuo() {
        Random random = new Random();
        
        for(int j=0;j<d;j++)
            gene[j] = (float) (random.nextInt(100) * 0.01);
        calcularFitness();
    }

    public IndividuoTP5(int d) {
        this.d = d;
        this.fitness = 0;
    }
    
    public void calcularFitness() {
        
        float subFunction[] = new float[2];
        float e = (float) 2.718281828459;
        this.fitness=0;
        for(int i=0; i<d; i++){
            if(gene[i] < 0.4696)
                subFunction[i] = (float) (-0.5 * Math.pow( e, -0.5*(Math.pow(gene[i]-0.4,2)/Math.pow(0.05,2))));
            else if(gene[i] >= 0.4696 && gene[0] <= 0.5304)
                subFunction[i] = (float) (-0.6 * Math.pow( e, -0.5*(Math.pow(gene[i]-0.5,2)/Math.pow(0.02,2))));
            else 
                subFunction[i] = (float) (-0.5 * Math.pow( e, -0.5*(Math.pow(gene[i]-0.6,2)/Math.pow(0.05,2))));
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

    public void aplicaMutacao(float probMutacao, IndividuoTP5 pai1, IndividuoTP5 pai2){
        
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
