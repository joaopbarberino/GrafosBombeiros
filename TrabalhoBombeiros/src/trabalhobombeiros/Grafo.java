package trabalhobombeiros;

import java.util.*;

public class Grafo {

    private int V; // número de vértices
    private int A; // número de arestas
    private int adj[][]; // matriz de adjcência
    private int qtdCaminho; // quantos caminhos existem de um vértice a outro

    // inicializa os atributos da classe e cria a 
    // matriz de adjacência para V vértices
    public Grafo(int V) {
        this.V = V;
        this.A = 0; // nao tenho nenhuma aresta
        this.qtdCaminho = 0; // nao ha nenhum caminho no começo
        // criar a matriz de adjacencia
        adj = new int[V][V];
    }

    // retorna quantos caminhos o grafo atual tem até um determinado caminho
    // esse atributo é controlado pelo método mostraCaminhos.
    public int getQtdCaminho() {
        return qtdCaminho;
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

    // Remove uma dada aresta do grafo
    public void removeA(int v, int w) {
        this.adj[v][w] = 0;
        this.A = this.A - 1;
    }

    /*
     Para cada vértice v do grafo, este método imprime, 
     em uma linha, todos os vértices adjacentes a v. 
     */
    public void mostra() {
        for (int v = 1; v < this.A; v++) {
            System.out.print(v + ":");
            for (int w = 1; w < this.V; w++) {
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
        boolean ciclo = false, visitados[] = new boolean[this.V];
        ciclo = busca_ciclo(w, ciclo, visitados);
        this.removeA(v, w);
        return ciclo;
    }

    public void mostraCaminhos(int verticePartida, int verticeDestino) {
        // cria o vetor de visitados
        ArrayList<Integer> caminho = new ArrayList<>();
        System.out.println("Rotas: ");
        busca_prof(verticePartida, verticeDestino, caminho);
    }

    // implementacao da busca em profundidade a partir de somente um vertice
    public void busca_prof(int v, int verticeDestino, ArrayList caminho) {
        caminho.add(v);
        // Se o caminho chegou ao destino
        if (v == verticeDestino) {
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
            // se w eh adjacente a v 
            if (this.adj[v][w] == 1) {
                busca_prof(w, verticeDestino, caminho);
                caminho.remove(caminho.indexOf(w));
            }
        }
    }

    public boolean busca_ciclo(int v, boolean ciclo, boolean[] visitados) {
        visitados[v] = true;
        for (int w = 0; w < this.V; w++) {
            if (this.adj[v][w] == 1 && !visitados[w]) {
                busca_ciclo(w, ciclo, visitados);
            } else if (this.adj[w][v] == 1 && visitados[w]) {
                ciclo = true;
            }
        }
        return ciclo;
    }
}
