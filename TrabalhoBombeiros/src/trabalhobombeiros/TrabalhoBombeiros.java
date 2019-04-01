package trabalhobombeiros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author João Pedro Barberino Silva 
 * Centro Universitário Senac Santo Amaro
 * Jogos Digitais - 3º Semestre
 */

public class TrabalhoBombeiros {

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
        int verticeInicial = 1, verticeFinal, verticeX, verticeY;

        // Pega o vértice final do caminho, ou seja,
        // onde está acontecendo o incêndio.
        linha = objBufferizado.readLine();
        vetorString = linha.split(" ");
        verticeFinal = Integer.parseInt(vetorString[0]);
        System.out.println("O incêndio está no vértice: " + verticeFinal);
        System.out.println("Erros: ");

        // Instancia o grafo
        Grafo objGrafo;
        objGrafo = new Grafo(21);
        while (linha != "0 0") {
            // Pega uma linha de vértices no format "X Y"
            linha = objBufferizado.readLine();
            // Separa a linha em X e Y e armazena
            vetorString = linha.split(" ");
            verticeX = Integer.parseInt(vetorString[0]);
            verticeY = Integer.parseInt(vetorString[1]);

            // Condição de parada de leitura
            if (verticeX == 0 && verticeY == 0) {
                break;
            } else {
                // Validação da entrada
                switch (valida_entrada(verticeX, verticeY, objGrafo)) {
                    case 1:
                        objGrafo.insereA(verticeX, verticeY);
                        break;

                    case 2:
                        System.out.println("Par X: " + verticeX + " Y: "
                                + verticeY + ". Os vértices devem ser diferentes!");
                        break;

                    case 3:
                        System.out.println("Par X: " + verticeX + " Y: "
                                + verticeY + ". Essa aresta já foi inserida!");
                        break;

                    case 4:
                        System.out.println("Par X: " + verticeX + " Y: "
                                + verticeY + ". Não pode haver mão dupla!");
                        break;

                    case 5:
                        System.out.println("Par X: " + verticeX + " Y: "
                                + verticeY + ". Essa aresta formará um ciclo!");
                        break;
                }
            }
        }

        System.out.println("");
        System.out.println("Grafo: ");
        objGrafo.mostra();

        System.out.println("");
        // Chama o método que percorre o grafo a partir do vértice onde está
        // o quartel dos bombeiros
        objGrafo.mostraCaminhos(verticeInicial, verticeFinal);
        System.out.println("");
        System.out.println("Existem " + objGrafo.getQtdCaminho()
                + " caminho(s) do quartel de bombeiros na esquina "
                + verticeInicial); 
        System.out.println("até o incêndio na esquina " + verticeFinal + ".");

    }

    // Valida os valores do arquivo de acordo com as especificações do exercício
    public static int valida_entrada(int verticeX, int verticeY, Grafo objGrafo) {
        // Caso os vértices sejam iguais
        if ((verticeX == verticeY) && (verticeX != 0 && verticeY != 0)) {
            return 2;
        }
        // Caso a aresta já tenha sido inserida
        if (objGrafo.procuraA(verticeX, verticeY)) {
            return 3;
        }
        // Caso a aresta já tenha sido inserida em um sentido, evita "mão-dupla"
        if (objGrafo.procuraA(verticeY, verticeX)) {
            return 4;
        }
        // Caso a inserção da aresta forme um ciclo no grafo
        if (objGrafo.formaCiclo(verticeX, verticeY)) {
            return 5;
        }

        return 1;
    }
}
