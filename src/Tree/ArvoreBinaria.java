package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    private No raiz = null;
    private int qntNos = 0;
    private LinkedList<Integer> arvoreOrdemSimetrica = new LinkedList<>();
    private String arvorePreOrdem = "";

    public ArvoreBinaria(String arvoreInicial){
        String[] str = arvoreInicial.split(" ");
        for(int ii=0; ii < str.length; ii++){
            if(ii == 0){
                this.raiz = new No(Integer.parseInt(str[ii]));  
            }else{
                No atual = raiz;
                No anterior = null;
                while(atual != null){
                    anterior = atual;
                    if( Integer.parseInt(str[ii]) == atual.valor){
                        return;
                    }
                    if(Integer.parseInt(str[ii]) < atual.valor){
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

    public void imprimeArvore(int s){
        int count = qntNos*10;
        if(s == 1){
        }else{
        }
    }

    public boolean ehCompleta(){
        if(this.raiz == null){
            return true;
        }
        Queue<No> fila = new LinkedList<>();

        boolean noIncompleto = false;
        fila.add(this.raiz);
        while(!fila.isEmpty()){
            No temp = fila.remove();
            if(temp.esq != null){
                if(noIncompleto == true){ // significa que um nó incompleto já foi encontrado
                    return false;
                }
                fila.add(temp.esq);
            }else{
                noIncompleto = true;
            }
            if(temp.dir != null){
                if(noIncompleto == true){ // significa que um nó incompleto já foi encontrado
                    return false;
                }
                fila.add(temp.dir);
            }else{
                noIncompleto = true;
            }
        }
        return true;
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

    private void preOrdem(No raiz){ /*complexidade O(n), percorre todos os nós*/
        arvorePreOrdem += raiz.valor + " "; 
        if(raiz.esq != null){
            preOrdem(raiz.esq);
        }
        if(raiz.dir != null){
            preOrdem(raiz.dir);
        }
    }

    private void inOrdem(No raiz){
        if(raiz.esq != null){
            inOrdem(raiz.esq);
        }

        this.arvoreOrdemSimetrica.add(raiz.valor);

        if(raiz.dir != null){
            inOrdem(raiz.dir);
        }
    }

    public String pre_ordem(){
        preOrdem(raiz);
        return arvorePreOrdem;
    }

    public int enesimoElemento(int n){
        inOrdem(this.raiz);
        return arvoreOrdemSimetrica.get(n-1);
    }
}