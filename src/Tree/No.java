package Tree;

public class No {
    public int valor;
    public No esq;
    public No dir;
    public int qntFilhosDir;
    public int qntFilhosEsq;
    public int nivel;

    public No(int valor){
        this.valor = valor;
        esq = null;
        dir = null;
        this.nivel = 0;
    }
}
