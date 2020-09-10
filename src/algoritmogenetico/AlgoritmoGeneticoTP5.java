package algoritmogenetico;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGeneticoTP5 {

    private ArrayList<IndividuoTP5> populacao = new ArrayList();
    private int tamanhoPopulacao;
    private int geracoes;
    private float probMutacoes;
    private int constantElitismo;
    private int d;
    private ArrayList<IndividuoTP5> filhos = new ArrayList<IndividuoTP5>();
    
    public AlgoritmoGeneticoTP5(int tamanhoPopulacao, int geracoes, float probMutacoes, int constantElitismo, int d) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geracoes = geracoes;
        this.probMutacoes = probMutacoes;
        this.constantElitismo = constantElitismo;
        this.d = d;
        inicializaPopulacao();
    }

    public int getGeracoes() {
        return geracoes;
    }

    public void setGeracoes(int geracoes) {
        this.geracoes = geracoes;
    }
    
    public AlgoritmoGeneticoTP5(){}


    public ArrayList<IndividuoTP5> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(ArrayList<IndividuoTP5> populacao) {
        this.populacao = populacao;
    }
    
    public void rodaUmaGeracao(){
            //os proprios filhos substituem os pais
            int tamanhoTorneio = 2;
            ArrayList<IndividuoTP5> paisSelecionados = selecaoTorneio(tamanhoTorneio);
            //a mutacao ja se aplica no cruzamento
            cruzamentoDiscreto(paisSelecionados);
            //passa uma geracao
            this.geracoes++;
    }
    
    public void inicializaPopulacao(){
        for(int i=0;i<tamanhoPopulacao;i++){
            IndividuoTP5 individuo = new IndividuoTP5(d);
            individuo.criarIndividuo();
            populacao.add(individuo);
        }
    }
    
    public ArrayList<IndividuoTP5> selecaoTorneio(int tamanhoTorneio){
        
        ArrayList<IndividuoTP5> paisParaReproducao = new ArrayList<IndividuoTP5>();
        Random random = new Random();
        int numAleIndividuo1, numAleIndividuo2;
        
        for(int i=0; i<tamanhoPopulacao/2;i++){
            numAleIndividuo1 = random.nextInt(this.tamanhoPopulacao);
            numAleIndividuo2 = random.nextInt(this.tamanhoPopulacao);
            
            if(this.populacao.get(numAleIndividuo1).getFitness() > this.populacao.get(numAleIndividuo2).getFitness())
                paisParaReproducao.add(this.populacao.get(numAleIndividuo2));
            
            else
                paisParaReproducao.add(this.populacao.get(numAleIndividuo1));
        }
        
        return paisParaReproducao;
    }
    
    public void cruzamentoDiscreto(ArrayList<IndividuoTP5> paisSelecionados){
        
        int numAleIndividuo1, numAleIndividuo2;
        float probabilidadeHerdarGene;
        Random random = new Random();
        
        IndividuoTP5 pai1 = new IndividuoTP5(d);
        IndividuoTP5 pai2 = new IndividuoTP5(d);
        
        float genesNovoIndividuo[] = new float[d];
        for(int i=0; i<tamanhoPopulacao;i++){
            IndividuoTP5 filho = new IndividuoTP5(d);
            numAleIndividuo1 = random.nextInt(paisSelecionados.size());
            numAleIndividuo2 = random.nextInt(paisSelecionados.size());
            pai1 = paisSelecionados.get(numAleIndividuo1);
            pai2 = paisSelecionados.get(numAleIndividuo2);
            
            //monta os genes do filho de acordo com os pais dele
            for(int j=0; j<d;j++){
                probabilidadeHerdarGene = (float) (random.nextInt(100) * 0.01);
                if(probabilidadeHerdarGene > 0.5)
                    genesNovoIndividuo[j] = pai1.getGene(j);
                else
                    genesNovoIndividuo[j] = pai2.getGene(j);
            }
            for(int k=0; k<d;k++)
                filho.setGene(k, genesNovoIndividuo[k]);
            filho.calcularFitness();
            //mutacao nos filhos
            filho.aplicaMutacao(probMutacoes, pai1, pai2);
            
            filhos.add(filho);
        }
        
        ArrayList<IndividuoTP5> novaPopulacao = new ArrayList<IndividuoTP5>();
        populacao = bubbleSort(populacao);
        
        //add os melhores pais segundo a constante de elitismo
        for(int j=0; j<constantElitismo;j++)
            novaPopulacao.add(populacao.get(j));
        //add os filhos na populacao
        filhos = bubbleSort(filhos);
        for(int k=0; k<filhos.size()-constantElitismo;k++)
            novaPopulacao.add(filhos.get(k));
        
        filhos = new ArrayList<IndividuoTP5>();
        
        populacao = novaPopulacao;
    }
    
    public ArrayList<IndividuoTP5> bubbleSort(ArrayList<IndividuoTP5> individuos){
        
        IndividuoTP5 aux;
        for(int i = 0; i<individuos.size(); i++){
            for(int j = 0; j<individuos.size()-1; j++){
                if(individuos.get(j).getFitness() < individuos.get(j+1).getFitness()){
                    aux = individuos.get(j);
                    individuos.set(j, individuos.get(j+1));
                    individuos.set(j+1, aux);
                }
            }
	}
        return individuos;
    }
    
    public void imprimePopulacao(ArrayList<IndividuoTP5> populacao){
        for(int i=0;i<populacao.size();i++)
            System.out.println("Individuo " + (i+1) + ".    //Gene 1: " + populacao.get(i).getGene(0) 
                    + "     //Gene 2: " + populacao.get(i).getGene(1) + "       //Fitness: " + populacao.get(i).getFitness());
        System.out.println("######################################################################################");
    }
    
    
}
