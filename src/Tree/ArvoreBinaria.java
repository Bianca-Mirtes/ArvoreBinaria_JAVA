package Tree;

public class ArvoreBinaria {
    private No raiz = null;
    private int qntNos = 0;

    public ArvoreBinaria(){};

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
        }
    }

    public No buscaABB(int valor){
        if(raiz == null){
            System.out.println("Arvore Vazia!!!");
            return null;
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
                    return null;
                }
            }
            return atual; 
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

    public boolean ehCheia(){
        return true;
    }

    public void preOrdem(No raiz){ /*complexidade O(n), percorre todos os nós*/
        System.out.println(raiz.valor + " ");
        
        if(raiz.esq != null){
            preOrdem(raiz.esq);
        }
        if(raiz.dir != null){
            preOrdem(raiz.dir);
        }
    }

    public void posOrdem(No raiz){
        if(raiz.esq != null){
            posOrdem(raiz.esq);
        }

        if(raiz.dir != null){
            posOrdem(raiz.dir);
        }
        System.out.println(raiz.valor + " ");
    }

    public void inOrdem(No raiz){
        if(raiz.esq != null){
            inOrdem(raiz.esq);
        }

        System.out.println(raiz.valor + " ");

        if(raiz.dir != null){
            inOrdem(raiz.dir);
        }
    }

    public String pre_ordem(){
        return "";
    }
}