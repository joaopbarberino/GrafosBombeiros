package buscaLargura;
// implementacao de uma fila não circular
/**
 *
 * @author fabio.aglubacheski
 */
public class Fila {
    private int first, last;
    private int elementos[];

    public Fila(int N) {
        this.first = 0;
        this.last = 0;
        elementos = new int[N];
        
    }
    public void insere( int elemento ){
        this.elementos[this.last] = elemento;
        this.last++;
    }
    public int remove( ){
        int elemento = this.elementos[this.first];
        this.first++;
        return elemento;
    }
    public boolean isEmpty(){
        return this.first == this.last;
    }
    public boolean isFull(){
        return this.last == elementos.length;
    }
    // calcula o numero de elementos na Fila
    public int size(){
        return this.last - this.first;
    }
    
}
