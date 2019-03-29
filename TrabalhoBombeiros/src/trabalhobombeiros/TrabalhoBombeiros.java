/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhobombeiros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author joao.pbsilva20
 */
public class TrabalhoBombeiros {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Abre um arquivo e atribui o objeto de leitura
        //a uma variavel da classe FileReader
        FileReader objLeitura;
        objLeitura = new FileReader("entrada.txt");
        // Para fazer a leitura bufferizada utilizaremos
        // a classe BufferedReader
        BufferedReader objBufferizado;
        objBufferizado = new BufferedReader(objLeitura);

        String linha, vetorString[];
        int verticeDestino, verticeX = 1, verticeY = 1;

        // Pega o vértice final do caminho, ou seja,
        // onde está acontecendo o incêndio.
        linha = objBufferizado.readLine();
        vetorString = linha.split(" ");
        verticeDestino = Integer.parseInt(vetorString[0]);
        System.out.println("O incêndio está no vértice: " + verticeDestino);
        System.out.println("Erros: ");

        // Instancia o grafo
        Grafo objGrafo, objGrafoCiclo;
        objGrafo = new Grafo(21);
        objGrafoCiclo = new Grafo(21);

        while (linha != "0 0") {
            linha = objBufferizado.readLine();
            vetorString = linha.split(" ");
            verticeX = Integer.parseInt(vetorString[0]);
            verticeY = Integer.parseInt(vetorString[1]);

            if (verticeX == 0 && verticeY == 0) {
                break;
            } else {

                switch (tratamentoDeEntrada(verticeX, verticeY, objGrafo, objGrafoCiclo, verticeDestino)) {
                    // Caso entrada seja válida
                    case 1:
                        objGrafo.insereA(verticeX, verticeY);
                        objGrafoCiclo.insereA(verticeX, verticeY);
                        
                        break;

                    // Caso vertices sejam iguais
                    case 2:
                        System.out.println("Par X: " + verticeX + " Y: " + verticeY
                                + ". Os vértices devem ser diferentes!");
                        break;

                    // Caso já exista uma aresta X Y
                    case 3:
                        System.out.println("Par X: " + verticeX + " Y: " + verticeY
                                + ". Essa aresta já foi inserida!");
                        break;
                        
                    // Caso já exista uma aresta Y X, ou seja, uma mão dupla
                    case 4:
                        System.out.println("Par X: " + verticeX + " Y: " + verticeY
                                + ". Não pode haver mão dupla!");
                        break;

                    // Caso forme um ciclo
                    case 5:
                        System.out.println("Par X: " + verticeX +  " Y: " + verticeY
                                 + ". Essa aresta formará um ciclo!");
                        break;
                }
            }
        }

        System.out.println("");
        System.out.println("Grafo: ");
        objGrafo.mostra();

        System.out.println("");
        objGrafo.mostraCaminhos(1, verticeDestino);
        System.out.println("");

    }

    public static int tratamentoDeEntrada(int verticeX, int verticeY, Grafo objGrafo, Grafo objGrafoCiclo, int verticeDestino) {
        if ((verticeX == verticeY) && (verticeX != 0 && verticeY != 0)) {
            return 2;
        }

        if (objGrafo.procuraA(verticeX, verticeY)) {
            return 3;
        }

        if (objGrafo.procuraA(verticeY, verticeX)) {
            return 4;
        }

        if (objGrafoCiclo.formaCiclo(verticeX, verticeY)) {
            return 5;
        }

        return 1;
    }
}
