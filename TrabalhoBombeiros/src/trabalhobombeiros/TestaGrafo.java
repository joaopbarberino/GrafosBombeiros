package trabalhobombeiros;

public class TestaGrafo {
   public static void main(String[] args) {
       Grafo objGrafo;
       // instanciar um objeto da classe Grafo
       objGrafo = new Grafo(6);
       
       // insere a aresta 0-2
       objGrafo.insereA(0,2);
       objGrafo.insereA(0,4);
       objGrafo.insereA(0,3);       
       objGrafo.insereA(1,2);
       objGrafo.insereA(2,4);
       objGrafo.insereA(3,4);
       objGrafo.insereA(3,5);
       objGrafo.insereA(4,1);
       objGrafo.insereA(4,5);
       objGrafo.insereA(5,1);

       
       objGrafo.mostra();
       
       int seq[]={0,3,4,5,1,2};
       System.out.println("ehCaminho="+objGrafo.ehCaminho(seq));
    }
    
}

