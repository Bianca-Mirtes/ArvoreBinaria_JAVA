package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ArvoreBinaria {
    private No raiz = null;     // raiz da arvore
    private String arvorePreOrdem = ""; // arvore organizada no percurso em pré-ordem
    private int countInOrdem = 0;   // contador das posições no percurso em ordem simetrica

    // vetores para atualizar a quantidade de filhos à esquerda e direita da arvore
    private List<No> temp1 = new LinkedList<>(); 
    private List<Integer> temp2 = new LinkedList<>();

    public ArvoreBinaria(String arvoreInicial){ // responsavel por construir a arvore binaria inicial
        String[] str = arvoreInicial.split(" "); // String contendo a arvore binaria inicial
        for(int ii=0; ii < str.length; ii++){
            if(ii == 0){ // criação da raiz
                this.raiz = new No(Integer.parseInt(str[ii]));
            }else{ // demais nós
                No atual = raiz;
                No anterior = null;
                while(atual != null){
                    anterior = atual;
                    if(Integer.parseInt(str[ii]) == atual.valor){ // valor repetido
                        return;
                    }
                    if(Integer.parseInt(str[ii]) < atual.valor){ // andar na arvore
                        atual.qntFilhosesq++;
                        atual = atual.esq;
                    }else{
                        atual.qntFilhosdir++;
                        atual = atual.dir;
                    }
                }
                if(Integer.parseInt(str[ii]) < anterior.valor){ // inserção do novo nó à esquerda
                    anterior.esq = new No(Integer.parseInt(str[ii]));
                }else{ // inserção do novo nó à direita
                    anterior.dir = new No(Integer.parseInt(str[ii]));
                }
            }  
        }
    };

    private int calculaAltura(No raiz){ // retorna a altura da arvore
        if (raiz == null) {
            return 0;
        }
        Queue<No> nosPorLevel = new LinkedList<>(); // fila para fazer o percurso em nivel
        int altura = 0;
        int quantidadeNos = 0;
        No atual; 
        nosPorLevel.add(raiz);
        while(true){
            quantidadeNos = nosPorLevel.size();
            if(quantidadeNos == 0){ // chegou ao final da arvore
                return altura;
            }
            altura++;
            while (quantidadeNos > 0){ // sai quando um nivel foi analisado para a altura e a quantidade de nós serem atualizadas
                atual = nosPorLevel.peek();
                nosPorLevel.remove();
                if (atual.esq != null) {
                    nosPorLevel.add(atual.esq);
                }
    
                if (atual.dir != null) {
                    nosPorLevel.add(atual.dir);
                }
                quantidadeNos--;
            }
        }
    }

    public No getRaiz() { // retorna a raiz da arvore
        return raiz;
    }

    public void inserirABB(int valor){ // insere um novo nó à arvore
        if(raiz == null){ // se true, inicializa a raiz da arvore
            raiz = new No(valor);
            //qntNos++;
        }else{ // busca o local correto para inserir o novo elemento caso não seja um elemento repetido
            No atual = raiz;
            No anterior = null;
            while(atual != null){
                anterior = atual;
                if(valor == atual.valor){ // o valor já existe na arvore
                    System.out.println(valor + " já está na árvore, não pode ser inserido");
                    temp1.clear();
                    temp2.clear();
                    return;
                }
                if(valor < atual.valor){ // andar pela arvore
                    temp1.add(atual);
                    temp2.add(1);
                    atual = atual.esq;
                }else{
                    temp1.add(atual);
                    temp2.add(2);
                    atual = atual.dir;
                }
            }
            for(int pp=0; pp < temp1.size(); pp++){
                if(temp2.get(pp) == 1){
                    temp1.get(pp).qntFilhosesq++;
                }else{
                    temp1.get(pp).qntFilhosdir++;
                }
            }
            if(valor < anterior.valor){ // insere o novo valor à esquerda
                anterior.esq = new No(valor);
                //qntNos++;
            }else{ // insere o novo nó à direita
                anterior.dir = new No(valor);
                //qntNos++;
            }
            System.out.println(valor + " adicionado");
            temp1.clear();
            temp2.clear();
        }
    }

    public void buscaABB(int valor){ // busca um nó na arvore que armazene o valor
        if(raiz == null){ // se true, retorna o metodo, pois não há o que buscar
            return;
        }else{
            No atual = this.raiz;
            while(atual.valor != valor){
                if(atual != null){
                    if(valor > atual.valor){ // andar pela arvore em busca do nó
                        atual = atual.dir;
                    }else{
                        atual = atual.esq;
                    }
                }
                if(atual == null){ // o nó com esse valor não existe na arvore
                    System.out.println("Chave não encontrada");
                    return;
                }
            }
            System.out.println("Chave encontrada"); // nó encontrado
            return; 
        }
    }

    public void removerABB(int valor){ // remove um nó da arvore
        if(raiz == null){  // se true, retorna o metodo, pois não há o que remover
            return;
        }
        No atual = raiz;
        No anterior = null;
        while(atual != null){
            if(valor == atual.valor){ // Nó foi encontrado e é feito o tratamamento da remoção de acordo com o caso
                if(atual == this.raiz){
                    this.raiz = removeNo(atual);
                    raiz.qntFilhosesq = atual.qntFilhosesq-1;
                    raiz.qntFilhosdir = atual.qntFilhosdir; 
                }else{
                    if(anterior.dir == atual){
                        anterior.dir = removeNo(atual);
                    }else{
                        anterior.esq = removeNo(atual);
                    }
                }
                for(int pp=0; pp < temp1.size(); pp++){
                    if(temp2.get(pp) == 1){
                        temp1.get(pp).qntFilhosesq--;
                    }else{
                        temp1.get(pp).qntFilhosdir--;
                    }
                }
                System.out.println(valor + " removido");
                //qntNos--;
                temp1.clear();
                temp2.clear();
                return;
            }
            anterior = atual; 
            if(valor > atual.valor){ // andar pela arvore em busca do nó a ser removido
                temp1.add(atual);
                temp2.add(1);
                atual = atual.dir;
            }else{
                temp1.add(atual);
                temp2.add(2);
                atual = atual.esq;
            }
        }
        temp1.clear();
        temp2.clear();
        System.out.println(valor + " não está na árvore, não pode ser removido"); // nó com o valor não foi encontrado
    }

    private No removeNo(No atual){ // trata os três casos da remoção em arvore binaria
        No temp1, temp2;
        if(atual.esq == null){ // Caso 1 e 2: Nó com 1 filho e nó folha
            temp2 = atual.dir;
            return temp2;
        }
        if(atual.dir == null){ // Caso 1 e 2: Nó com 1 filho e nó folha
            temp2 = atual.esq;
            return temp2;
        }
        temp1 = atual;      // Caso 3: Nó com 2 filhos. Pega o nó mais à direita da subarvore à esquerda e retorna ele   
        this.temp1.add(temp1);
        this.temp2.add(1);
        temp2 = atual.esq;  // para substituir o que se quer remover.
        while(temp2.dir != null){
            this.temp1.add(temp2);
            this.temp2.add(2);
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

    private void diagramaBarras(No raiz, String espacamento){ // imprime a arvore na representação de diagrama de barras
        if(raiz != null){
            System.out.print(espacamento + raiz.valor); // imprime o espaçamento, que depende da profundidade na arvore, e o valor do nó
            for(int ii=0; ii < ((this.raiz.qntFilhosesq + this.raiz.qntFilhosdir + 1)*5) - espacamento.length() - Integer.toString(raiz.valor).length(); ii++){
                if(ii == ((this.raiz.qntFilhosesq + this.raiz.qntFilhosdir + 1)*5) - espacamento.length() - Integer.toString(raiz.valor).length() - 1){
                    System.out.print("-\n");
                }else{
                    System.out.print("-");
                }
            }
            diagramaBarras(raiz.esq, espacamento+"    "); // imprime a sub-arvore à esquerda da raiz
            diagramaBarras(raiz.dir, espacamento+"    "); // imprime a sub-arvore à direita da raiz
        }else{ // arvore vazia
            return;
        }
    }

    private void parentesesAninhados(No raiz){ // imprime a arvore na representação de parenteses aninhados
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

    public void imprimeArvore(int s){ // imprime a arvore na representação desejada
        if(s == 1){
            diagramaBarras(raiz, "");
        }else{
            parentesesAninhados(raiz);
            System.out.println();
        }
    }

    public boolean ehCompleta(){ // determina se a arvore é completa ou não
        if(this.raiz == null){ // arvore vazia
            return true;
        }
        int qntNos = this.raiz.qntFilhosesq + this.raiz.qntFilhosdir + 1;
        int altura = calculaAltura(this.raiz); // calcula a altura da arvore
        // se a quantidade de nós estiver nesse intervalo (2^h-1 <= qntNos <= (2^h) - 1), então as folhas da arvore estão no ultimo ou penultimo nivel.
        if(Math.pow(2, altura-1) <= qntNos && qntNos <= (Math.pow(2, altura) - 1)){ 
            return true;
        }
        return false;
    }

    public boolean ehCheia(){ // determina se a arvore é cheia
        if(this.raiz == null){ // arvore vazia
            return true;
        }
        Queue<No> fila = new LinkedList<>();
        fila.add(this.raiz);
        while(!fila.isEmpty()){ // percorre a arvore
            No temp = fila.peek();
            fila.remove();
            if (temp.esq == null && temp.dir == null){ // nó folha, continua a busca
                continue;
            }
            if (temp.esq == null ^ temp.dir == null){ // encontrado um nó que não possui dois filhos
                return false;
            }
            fila.add(temp.esq);
            fila.add(temp.dir);
        }
        return true; // todos os nós da arvore (exceto as folhas) possuem dois filhos
    };

    public String preOrdem(No raiz){ // constroi a String contendo a arvore no percurso em pre-ordem
        this.arvorePreOrdem += raiz.valor + " ";
        if(raiz.esq != null){
            preOrdem(raiz.esq);
        }
        if(raiz.dir != null){
            preOrdem(raiz.dir);
        }
        return this.arvorePreOrdem;
    }

    private int inOrdem(No raiz, int valor, int verif){ // determina a posição de cada elemento da arvore no percurso em ordem simetrica
        Stack<No> pilha = new Stack<>();
        No atual = raiz;
        while(!pilha.isEmpty() || atual != null){
            if(atual != null){
                pilha.push(atual);
                atual = atual.esq;
            }else{
                atual = pilha.pop();
                this.countInOrdem++;
                if(verif == 2){ // metodo que chamou foi o posicao
                    if(atual.valor == valor){
                        return this.countInOrdem; // retorna a posição de determinado elemento na arvore
                    }   
                }else if(verif == 3){ // metodo que chamou foi o enesimo
                    if(this.countInOrdem == valor){
                        return atual.valor; // retorna o elemento que ocupa determinada posição na arvore
                    }
                }
                atual = atual.dir;
            }
        }
        return 0;
    }

    public String pre_ordem(){
        if(this.arvorePreOrdem.length() != 0){ // caso já tenha sido chamado antes, esvazia a String para novas concatenações
            this.arvorePreOrdem = "";
        }
        return preOrdem(raiz);
    }

    public int enesimoElemento(int n){ // retorna o elemento que ocupa a posição n no percuso em ordem simetrica
        if(raiz == null){ // arvore vazia
            return 0;
        }
        No atual;
        if(n == raiz.qntFilhosesq + 1){
            return raiz.valor;
        }else if(n < raiz.qntFilhosesq + 1){
            atual = raiz.esq;
            this.countInOrdem = 0;
            return inOrdem(atual, n, 3); // percorre a subarvore esquerda;
        }else if(n > raiz.qntFilhosesq + 1){
            atual = raiz.dir;
            this.countInOrdem = this.raiz.qntFilhosesq + 1;
            return inOrdem(atual, n, 3); // percorre a subarvore direita.
        }
        return 0;
    }

    public int posicao(int valor){ // retorna a posição de valor na arvore em percurso na ordem simetrica
        if(raiz == null){ // arvore vazia
            return 0;
        }
        if(raiz.valor == valor){
            return raiz.qntFilhosesq + 1;
        }else if(valor < raiz.valor){
            this.countInOrdem = 0;
            return inOrdem(raiz.esq, valor, 2);
        }else if(valor > raiz.valor){
            this.countInOrdem = this.raiz.qntFilhosesq + 1;
            return inOrdem(raiz.dir, valor, 2);
        }
        return 0;
    }

    public int mediana(){ // retorna o valor central da arvore
        if(raiz == null){
            return 0;
        }
        int qntNos = this.raiz.qntFilhosesq + this.raiz.qntFilhosdir + 1;
        if(qntNos % 2 == 0){
            int valorCentral1 = enesimoElemento(qntNos / 2);
            int valorCentral2 = enesimoElemento((qntNos / 2) + 1);
            if(valorCentral1 < valorCentral2){
                return valorCentral1;
            }else{
                return valorCentral2;
            }
        }else{
            return enesimoElemento((qntNos / 2) + 1);
        }
    }

    public double media(int elemento){ // determina a media dos valores dos nós os quais elemento é a raiz
        if(raiz == null){ // arvore vazia
            return 0;
        }
        int nos = 0;
        double soma = 0;
        No atual = this.raiz;
        while(atual.valor != elemento){ // busca para encontrar o elemento
            if(atual != null){
                if(elemento > atual.valor){
                    atual = atual.dir;
                }else{
                    atual = atual.esq;
                }
            }
            if(atual == null){ // elemento não encontrado
                return 0;
            }
        }
        Stack<No> pilha = new Stack<>(); // percorre os filhos para pegar os valores e fazer a média
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
