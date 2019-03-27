package trabalhobombeiros;

public class Grafo {

    private int V; // número de vértices
    private int A; // número de arestas
    private int adj[][]; // matriz de adjcência

    // inicializa os atributos da classe e cria a 
    // matriz de adjacência para V vértices
    public Grafo(int V) {
        this.V = V;
        this.A = 0; // nao tenho nenhuma aresta
        // criar a matriz de adjacencia
        adj = new int[V][V];
    }
    
    public int getA() {
        return this.A;
    }
    /*
     Método insere uma aresta v-w no grafo. O método supõe 
     que v e w são distintos, positivos e menores que V. 
     Se o grafo já tem aresta v-w, o método não faz nada, 
     após a inserção da aresta, o atributo A da classe é 
     atualizado.
     */

    public void insereA(int v, int w) {
        if (this.adj[v][w] == 0) {
            this.adj[v][w] = 1;
            this.A++;
        }
    }

    public void removeA(int v, int w) {
        if (this.adj[v][w] == 1) {
            this.adj[v][w] = 0;
            this.A--;
        }
    }
    /*
     Para cada vértice v do grafo, este método imprime, 
     em uma linha, todos os vértices adjacentes a v. 
     */

    public void mostra() {
        for (int v = 0; v < 7; v++) {
            System.out.print(v + ":");
            for (int w = 0; w < this.V; w++) {
                if (this.adj[v][w] == 1) {
                    System.out.print(" " + w);
                }

            }
            System.out.println();
        }
    }

    // calcula o grau de entrada de um vértice
    public int indeg(int v) {
        int grauEntrada = 0;
        for (int i = 0; i < this.V; i++) {
            grauEntrada += this.adj[v][i];
        }

        return grauEntrada;

    }

    // procura uma aresta no grafo
    public boolean procuraA(int v, int w) {
        return this.adj[v][w] == 1;
    }
    
    // verifica se uma inserção formará um ciclo no grafo
    public boolean formaCiclo(int v, int w) {
        this.insereA(v, w);
        int visitados[] = new int[this.V];
        //boolean visitados[] = new boolean[this.V];
        boolean ciclo = false;
        ciclo = busca_ciclo(v, w, visitados, ciclo);
        this.removeA(v, w);
        return ciclo;
    }

    public void mostraCaminhos(int s, int t) {
        // cria o vetor de visitados
        boolean visitados[] = new boolean[this.V];
        int caminho[] = new int[this.V];

        System.out.println("Rotas: ");
        busca_prof(s, visitados);
        boolean avisitados[] = new boolean[this.V];
        System.out.println("");
        busca_prof(s, avisitados);

    }

    // implementacao da busca em profundidade a partir de somente um vertice
    public void busca_prof(int v, boolean visitados[]) {
        //marque v como visitado
        visitados[v] = true;
        System.out.print(v + " ");

        //para Cada vértice w adjacente a v faça
        for (int w = 1; w < this.V; w++) {// w anda na linha da matriz
            // se w eh adjacente a v E
            // se w não foi visitado então
            if (this.adj[v][w] == 1 && visitados[w] == false && visitados[6] == false) {

                busca_prof(w, visitados);
            }
        }
    }
   
    
    public boolean busca_ciclo(int v, int finaL, int visitados[], boolean ciclo) {
        //marque v como visitado
        visitados[v]++;

        //para Cada vértice w adjacente a v faça
        for (int w = 1; w < this.V; w++) {// w anda na linha da matriz
            // se w eh adjacente a v E
            // se w não foi visitado então

            if (this.adj[v][w] == 1 && visitados[v] < finaL/1.5) {
                busca_ciclo(w, finaL, visitados, ciclo);

            } else if (this.adj[v][w] == 1 && visitados[v] > finaL/1.5) {
                ciclo = true;
            }
        }
        return ciclo;
    }
    
    /*
    public boolean busca_ciclo(int v, int verticeY, boolean visitados[], boolean ciclo) {
       
        //marque v como visitado
        visitados[v] = true;
        

        //para Cada vértice w adjacente a v faça
        for (int w = 1; w < this.V; w++) {// w anda na linha da matriz
            // se w eh adjacente a v E
            // se w não foi visitado então
            if (this.adj[v][w] == 1 && visitados[w] == false) {
                busca_ciclo(w, verticeY, visitados, ciclo);
            } else if (this.adj[v][w] == 1 && visitados[w] == true && w == verticeY){
                ciclo = true;
            }
        }
        return ciclo;
    }
    */
}
