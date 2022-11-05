package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ArvoreBinaria {
    private No raiz = null;
    private int qntNos = 0;
    private String arvorePreOrdem = "";
    private int posNoInOrdem = 0;
    private int countInOrdem = 0;
    private int elemNoInOrdem = 0;
    private double mediana = 0;

    public ArvoreBinaria(String arvoreInicial){
        String[] str = arvoreInicial.split(" ");
        for(int ii=0; ii < str.length; ii++){
            if(ii == 0){
                this.raiz = new No(Integer.parseInt(str[ii]));
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
                    qntNos++;
                }else{
                    anterior.dir = new No(Integer.parseInt(str[ii]));
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
                    System.out.println(valor + " já está na árvore, não pode ser inserido");
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
                qntNos++;
            }else{
                anterior.dir = new No(valor);
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
        System.out.println(valor + " não está na árvore, não pode ser removido");
    }

    private No removeNo(No atual){
        No temp1, temp2;
        if(atual.esq == null){
            temp2 = atual.dir;
            return temp2;
        }
        if(atual.dir == null){
            temp2 = atual.esq;
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
            for(int ii=0; ii < 36 - espacamento.length() - Integer.toString(raiz.valor).length(); ii++){
                if(ii == 36 - espacamento.length() - Integer.toString(raiz.valor).length() - 1){
                    System.out.print("-\n");
                }else{
                    System.out.print("-");
                }
            }
            diagramaBarras(raiz.esq, espacamento+"    ");
            diagramaBarras(raiz.dir, espacamento+"    ");
        }else{
            return;
        }
    }

    private void parentesesAninhados(No raiz){ // larguei de mão, consegui o de barras!!!!
        Stack<No> pilha = new Stack<>();
        pilha.add(raiz);
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            
            if (atual.esq == null) {
                System.out.print("("+atual.valor);
            } else {
                System.out.print("("+atual.valor+" ");
            }

            if (atual.esq != null) {
                parentesesAninhados(atual.esq);
            }
            if (atual.dir != null) {
                parentesesAninhados(atual.dir);
            }
            System.out.print(")");
        }
    }

    public void imprimeArvore(int s){
        if(s == 1){
            diagramaBarras(raiz, "");
        }else{
            parentesesAninhados(raiz);
            System.out.println();
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

    private String preOrdem(No raiz){
        this.arvorePreOrdem += raiz.valor + " ";
        if(raiz.esq != null){
            preOrdem(raiz.esq);
        }
        if(raiz.dir != null){
            preOrdem(raiz.dir);
        }
        return this.arvorePreOrdem;
    }

    public void PosInOrdem(No raiz, int valor, int verif){
        if(raiz.esq != null){
            PosInOrdem(raiz.esq, valor, verif);
        }
        countInOrdem++;
        raiz.posInOrdem = countInOrdem;
        if(verif == 2){
            if(raiz.valor == valor){
                posNoInOrdem = raiz.posInOrdem;
            }   
        }else if(verif == 3){
            if(raiz.posInOrdem == valor){
                elemNoInOrdem = raiz.valor;
            }
        }else{
            if(qntNos % 2 == 0){
                if(raiz.posInOrdem == qntNos / 2){
                    this.mediana += raiz.valor;
                }
                if(raiz.posInOrdem == (qntNos / 2) + 1){
                    if(mediana > raiz.valor){
                        this.mediana = raiz.valor;
                    }
                }
            }else{
                if(raiz.posInOrdem == ((qntNos / 2) + 1)){
                    this.mediana = raiz.valor;
                }
            }
        }
        if(raiz.dir != null){
            PosInOrdem(raiz.dir, valor, verif);
        }
        if(countInOrdem == qntNos){
            countInOrdem = 0;
        }
    }

    public String pre_ordem(){
        if(this.arvorePreOrdem.length() != 0){
            this.arvorePreOrdem = "";
        }
        return preOrdem(raiz);
    }

    public int enesimoElemento(int n){
        if(raiz == null){
            return 0;
        }
        PosInOrdem(this.raiz, n, 3);
        return this.elemNoInOrdem;
    }

    public int posicao(int valor){
        if(raiz == null){
            return 0;
        }
        PosInOrdem(this.raiz, valor, 2);
        return this.posNoInOrdem;
    }

    public double mediana(){
        this.mediana = 0;
        PosInOrdem(this.raiz, -1, 4);
        return this.mediana;
    }

    public double media(int elemento){
        int nos = 0;
        double soma = 0;
        No atual = this.raiz;
        while(atual.valor != elemento){
            if(atual != null){
                if(elemento > atual.valor){
                    atual = atual.dir;
                }else{
                    atual = atual.esq;
                }
            }
            if(atual == null){
                return 0;
            }
        }
        Stack<No> pilha = new Stack<>();
        pilha.push(atual);
        while(!pilha.empty()){
            No temp = pilha.pop();
            soma += temp.valor;
            nos++;
            if(temp.dir != null){
                pilha.push(temp.dir);
            }
            if(temp.esq != null){
                pilha.push(temp.esq);
            }         
        }
        return soma / nos;
    }
}