package Tree;

public class No {
    public int valor;   // valor do nó
    public No esq;      // filho à esquerda
    public No dir;      // filho à direita
    public int qntFilhosesq;
    public int qntFilhosdir;

    public No(int valor){
        this.valor = valor;
        esq = null;
        dir = null;
        this.qntFilhosesq = 0;
        this.qntFilhosdir = 0;
    }
}
