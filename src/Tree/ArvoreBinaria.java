package Tree;

import java.util.LinkedList;
import java.util.Queue;


public class ArvoreBinaria {
    private No raiz = null;
    private int qntNos = 0;
    private String arvorePreOrdem = "";
    private int niveis = 1;

    public ArvoreBinaria(String arvoreInicial){
        String[] str = arvoreInicial.split(" ");
        for(int ii=0; ii < str.length; ii++){
            if(ii == 0){
                this.raiz = new No(Integer.parseInt(str[ii]));
                this.raiz.nivel = niveis++;
                qntNos++;
            }else{
                No atual = raiz;
                No anterior = null;
                while(atual != null){
                    anterior = atual;
                    if(Integer.parseInt(str[ii]) == atual.valor){ // valor repetido
                        return;
                    }
                    if(Integer.parseInt(str[ii]) < atual.valor){ // andar na arvore
                        atual = atual.esq;
                    }else{
                        atual = atual.dir;
                    }
                }
                if(Integer.parseInt(str[ii]) < anterior.valor){
                    anterior.esq = new No(Integer.parseInt(str[ii]));
                    anterior.esq.nivel = niveis;
                    if(anterior.dir != null){
                        this.niveis++;
                    }
                    qntNos++;
                }else{
                    anterior.dir = new No(Integer.parseInt(str[ii]));
                    anterior.dir.nivel = niveis;
                    if(anterior.esq != null){
                        this.niveis++;
                    }
                    qntNos++;
                }
            }   
        }
    };

    public int calculaAltura(No raiz){
        Queue<No> nosPorLevel = new LinkedList<>();
        int altura = 0;
        int qntNos = 0;
        No atual; 
        if (raiz == null) {
            return 0;
        }
        nosPorLevel.add(raiz);
        while(true){
            qntNos = nosPorLevel.size();
            if(qntNos == 0){
                return altura;
            }
            altura++;
            while (qntNos > 0){
                atual = nosPorLevel.peek();
                nosPorLevel.remove();
                if (atual.esq != null) {
                    nosPorLevel.add(atual.esq);
                }
    
                if (atual.dir != null) {
                    nosPorLevel.add(atual.dir);
                }
                qntNos--;
            }
        }
    }

    public No getRaiz() {
        return raiz;
    }

    public void inserirABB(int valor){
        if(raiz == null){
            raiz = new No(valor);
            qntNos++;
        }else{
            No atual = raiz;
            No anterior = null;
            while(atual != null){
                anterior = atual;
                if(valor == atual.valor){ // o valor já existe na arvore
                    System.out.println(valor + " já está na arvore, não pode ser inserido.");
                    return;
                }
                if(valor < atual.valor){
                    atual = atual.esq;
                }else{
                    atual = atual.dir;
                }
            }
            if(valor < anterior.valor){
                anterior.esq = new No(valor);
                anterior.esq.nivel = niveis;
                if(anterior.dir != null){
                    this.niveis++;
                }
                qntNos++;
            }else{
                anterior.dir = new No(valor);
                anterior.dir.nivel = niveis;
                if(anterior.esq != null){
                    this.niveis++;
                }
                qntNos++;
            }
            System.out.println(valor + " adicionado");
        }
    }

    public void buscaABB(int valor){
        if(raiz == null){
            return;
        }else{
            No atual = raiz;
            while(atual.valor != valor){
                if(atual != null){
                    if(valor > atual.valor){
                        atual = atual.dir;
                    }else{
                        atual = atual.esq;
                    }
                }
                if(atual == null){
                    System.out.println("Chave não encontrada");
                    return;
                }
            }
            System.out.println("Chave encontrada");
            return; 
        }
    }

    public void removerABB(int valor){
        if(raiz == null){
            return;
        }
        No atual = raiz;
        No anterior = null;
        while(atual != null){
            if(valor == atual.valor){
                if(atual == raiz){
                    raiz = removeNo(atual);
                }else{
                    if(anterior.dir == atual){
                        anterior.dir = removeNo(atual);
                    }else{
                        anterior.esq = removeNo(atual);
                    }
                }
                System.out.println(valor + " removido");
                qntNos--;
                return;
            }
            anterior = atual;
            if(valor > atual.valor){
                atual = atual.dir;
            }else{
                atual = atual.esq;
            }
        }
        System.out.println(valor + " não está na arvore, não pode ser removido");
    }

    private No removeNo(No atual){
        No temp1, temp2;
        if(atual.esq == null){
            temp2 = atual.dir;
            return temp2;
        }
        temp1 = atual;
        temp2 = atual.esq;
        while(temp2.dir != null){
            temp1 = temp2;
            temp2 = temp2.dir;
        }
        if(temp1 != atual){
            temp1.dir = temp2.esq;
            temp2.esq = atual.esq;
        }
        temp2.dir = atual.dir;
        return temp2;
    }

    private void diagramaBarras(No raiz, String espacamento){
        if(raiz != null){
            System.out.print(espacamento + raiz.valor);
            for(int ii=0; ii < qntNos*4 - espacamento.length(); ii++){
                if(ii == qntNos*4 - espacamento.length() - 1){
                    System.out.print("-\n");
                }else{
                    System.out.print("-");
                }
            }
            diagramaBarras(raiz.esq, espacamento+"   ");
            diagramaBarras(raiz.dir, espacamento+"   ");
        }else{
            return;
        }
    }

    private void parentesesAninhados(No raiz){ // larguei de mão, consegui o de barras!!!!
    }

    public void imprimeArvore(int s){
        if(s == 1){
            diagramaBarras(raiz, "");
        }else{
            parentesesAninhados(raiz);
        }
    }

    public boolean ehCompleta(){
        if(this.raiz == null){
            return true;
        }
        int altura = calculaAltura(this.raiz);
        if(Math.pow(2, altura-1) <= qntNos && qntNos <= (Math.pow(2, altura) - 1)){
            return true;
        }
        return false;
    }

    public boolean ehCheia(){
        if(this.raiz == null){
            return true;
        }
        Queue<No> fila = new LinkedList<>();
        fila.add(this.raiz);
        while(!fila.isEmpty()){
            No temp = fila.peek();
            fila.remove();
     
            if (temp.esq == null && temp.dir == null){ // é uma folha, ignora
                continue;
            }
     
            if (temp.esq == null ^ temp.dir == null){
                return false;
            }
            fila.add(temp.esq);
            fila.add(temp.dir);
        }
        return true;
    };

    public void imprimeNiveis(No raiz, int nivel){
        if (raiz == null)
            return;
        if (nivel == 1)
            System.out.print(raiz.valor + " ");
        else if (nivel > 1) {
            imprimeNiveis(raiz.esq, nivel - 1);
            imprimeNiveis(raiz.dir, nivel - 1);
        }
    }

    private void preOrdem(No raiz){ /*complexidade O(n), percorre todos os nós*/
        arvorePreOrdem += raiz.valor + " "; 
        if(raiz.esq != null){
            preOrdem(raiz.esq);
        }
        if(raiz.dir != null){
            preOrdem(raiz.dir);
        }
    }

    public void inOrdem(No raiz){
        if(raiz.esq != null){
            inOrdem(raiz.esq);
        }
        System.out.println(raiz.valor + " " + raiz.nivel);

        if(raiz.dir != null){
            inOrdem(raiz.dir);
        }
    }

    public String pre_ordem(){
        preOrdem(raiz);
        return arvorePreOrdem;
    }

    /*public int enesimoElemento(int n){
        inOrdem(this.raiz);
        No atual = this.raiz;
        while(atual.posInOrdemSimetrica != n){
            if(atual != null){
                if(n > atual.posInOrdemSimetrica){
                    atual = atual.dir;
                }else{
                    atual = atual.esq;
                }
            }
            if(atual == null){
                return -1;
            }
        }
        return atual.valor;
    }*/

    public int posicao(int valor){
        if(raiz == null){
            return -1;
        }else{
            No atual = raiz;
            while(atual.valor != valor){
                if(atual != null){
                    if(valor > atual.valor){
                        atual = atual.dir;
                    }else{
                        atual = atual.esq;
                    }
                }
                if(atual == null){
                    return -1;
                }
            }
            System.out.println("Chave encontrada");
            return -1;//atual.posInOrdemSimetrica; 
        }
    }
}
