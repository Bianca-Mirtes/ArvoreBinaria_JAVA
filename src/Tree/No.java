package Tree;

public class No {
    public int valor;   // valor do nó
    public No esq;      // filho à esquerda
    public No dir;      // filho à direita
    public int posInOrdem; // posição no percurso em ordem simetrica

    public No(int valor){
        this.valor = valor;
        esq = null;
        dir = null;
    }
}
