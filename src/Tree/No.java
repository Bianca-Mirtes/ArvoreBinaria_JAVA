package Tree;

public class No {
    public int valor;
    public No esq;
    public No dir;
    public int posInOrdem;

    public No(int valor){
        this.valor = valor;
        esq = null;
        dir = null;
    }
}
