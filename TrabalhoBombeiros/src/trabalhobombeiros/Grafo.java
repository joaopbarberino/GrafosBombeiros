package trabalhobombeiros;

import java.util.*;

public class Grafo {

    private int V; // Número de vértices
    private int A; // Número de arestas
    private int adj[][]; // Natriz de adjcência
    private int qtdCaminho; // Quantos caminhos existem de um vértice a outro

    // Inicializa os atributos da classe e cria a 
    // matriz de adjacência para V vértices
    public Grafo(int V) {
        this.V = V;
        this.A = 0; // nao tenho nenhuma aresta
        this.qtdCaminho = 0; // nao ha nenhum caminho no começo
        // criar a matriz de adjacencia
        adj = new int[V][V];
    }

    // Retorna quantos caminhos o grafo atual tem entre um dado vértice inicial
    // e outro final, após a busca de caminhos, feita no método "mostraCaminhos"
    public int getQtdCaminho() {
        return qtdCaminho;
    }

    // Método que permite zerar a quantidade de caminhos, caso, se por alguma
    // razão, os vértices inicial e final mudem após a inicialização do programa
    public void resetQtdCaminho() {
        this.qtdCaminho = 0;
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

    // Remove uma dada aresta v-w do grafo
    public void removeA(int v, int w) {
        this.adj[v][w] = 0;
        this.A = this.A - 1;
    }

    /*
     Para cada vértice v do grafo, este método imprime, 
     em uma linha, todos os vértices adjacentes a v. 
     */
    public void mostra() {
        for (int v = 0; v < this.A; v++) {
            System.out.print(v + ":");
            for (int w = 0; w < this.V; w++) {
                if (this.adj[v][w] == 1) {
                    System.out.print(" " + w);
                }
            }
            System.out.println();
        }
    }

    // Calcula o grau de entrada de um vértice
    public int indeg(int v) {
        int grauEntrada = 0;
        for (int i = 0; i < this.V; i++) {
            grauEntrada += this.adj[v][i];
        }
        return grauEntrada;
    }

    // Procura uma aresta no grafo
    public boolean procuraA(int v, int w) {
        return this.adj[v][w] == 1;
    }

    // Verifica se uma inserção de aresta formará um ciclo no grafo
    public boolean formaCiclo(int v, int w) {
        // Adiciona a aresta ao grafo para fazer o teste
        this.insereA(v, w);
        boolean ciclo = false, visitados[] = new boolean[this.V];
        ciclo = busca_ciclo(w, ciclo, visitados);
        // Remove pois, idenpendente do valor de ciclo, a funçãop desse método é
        // procurar um ciclo no grafo, e não adicionar uma aresta a ele
        this.removeA(v, w);
        return ciclo;
    }

    // Procura todos os caminhos possíveis entra o verticePartida e o
    // verticeDestino e os imprime enquanto procura
    public void mostraCaminhos(int verticePartida, int verticeDestino) {
        // Cria o vetor que guardará e mostrará um caminho por vez
        ArrayList<Integer> caminho = new ArrayList<>();
        System.out.println("Rotas: ");
        busca_caminho(verticePartida, verticeDestino, caminho);
    }

    // Busca em profundidade para identificar todos os caminhos possíveis
    public void busca_caminho(int v, int verticeDestino, ArrayList caminho) {
        // Adiciona o vértice atual ao caminho
        caminho.add(v);
        // Se o caminho chegou ao vértice destino, imprime o caminho
        if (v == verticeDestino) {
            // Incrementa a quantidade de caminhos do Grafo
            this.qtdCaminho++;
            for (int i = 0; i < caminho.size(); i++) {
                if (i < caminho.size() - 1) {
                    System.out.printf(caminho.get(i) + " → ");
                } else if (i == caminho.size() - 1) {
                    System.out.println(caminho.get(i));
                }
            }
        }
        for (int w = 0; w < this.V; w++) {// w anda na linha da matriz
            // Se w eh adjacente a v 
            if (this.adj[v][w] == 1) {
                busca_caminho(w, verticeDestino, caminho);
                // Na volta da recursividade, remove
                // o adjacente atual do caminho para forçar o 
                // programa a ir para as outras possibilidades
                caminho.remove(caminho.indexOf(w));
            }
        }
    }

    // Busca em profundidade para identificar todos os ciclos possíveis
    public boolean busca_ciclo(int v, boolean ciclo, boolean[] visitados) {
        // Visita o vértice atual
        visitados[v] = true;
        for (int w = 0; w < this.V; w++) { // w anda na linha da matriz
            // Se v-w é uma aresta e w não foi visitado
            if (this.adj[v][w] == 1 && !visitados[w]) {
                busca_ciclo(w, ciclo, visitados);
            } // Se, antes e depois davolta da recursividade
              // w-v é uma aresta e w foi visitado
            else if (this.adj[w][v] == 1 && visitados[w]) {
                // É um ciclo
                ciclo = true;
            }
            
        }
        return ciclo;
    }
}
