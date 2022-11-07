package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ArvoreBinaria {
    private No raiz = null;     // raiz da arvore
    private int qntNos = 0;     // quantidade de nós da arvore
    private String arvorePreOrdem = ""; // arvore organizada no percurso em pré-ordem
    private int posNoInOrdem = 0;   // posição de um elemento na arvore no percuso em ordem simetrica
    private int countInOrdem = 0;   // contador para definir a posição de cada nó no percuso em ordem simetrica
    private int elemNoInOrdem = 0;  // elemento em uma determinada posição da arvore no percuso em ordem simetrica
    private double mediana = 0;     // valor central da arvore no percuso em ordem simetrica

    public ArvoreBinaria(String arvoreInicial){ // responsavel por construir a arvore binaria inicial
        String[] str = arvoreInicial.split(" "); // String contendo a arvore binaria inicial
        for(int ii=0; ii < str.length; ii++){
            if(ii == 0){ // criação da raiz
                this.raiz = new No(Integer.parseInt(str[ii]));
                qntNos++;
            }else{ // demais nós
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
                if(Integer.parseInt(str[ii]) < anterior.valor){ // inserção do novo nó à esquerda
                    anterior.esq = new No(Integer.parseInt(str[ii]));
                    qntNos++;
                }else{ // inserção do novo nó à direita
                    anterior.dir = new No(Integer.parseInt(str[ii]));
                    qntNos++;
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
            qntNos++;
        }else{ // busca o local correto para inserir o novo elemento caso não seja um elemento repetido
            No atual = raiz;
            No anterior = null;
            while(atual != null){
                anterior = atual;
                if(valor == atual.valor){ // o valor já existe na arvore
                    System.out.println(valor + " já está na árvore, não pode ser inserido");
                    return;
                }
                if(valor < atual.valor){ // andar pela arvore
                    atual = atual.esq;
                }else{
                    atual = atual.dir;
                }
            }
            if(valor < anterior.valor){ // insere o novo valor à esquerda
                anterior.esq = new No(valor);
                qntNos++;
            }else{ // insere o novo nó à direita
                anterior.dir = new No(valor);
                qntNos++;
            }
            System.out.println(valor + " adicionado");
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
            if(valor > atual.valor){ // andar pela arvore em busca do nó a ser removido
                atual = atual.dir;
            }else{
                atual = atual.esq;
            }
        }
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
        temp2 = atual.esq;  // para substituir o que se quer remover.
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

    private void diagramaBarras(No raiz, String espacamento){ // imprime a arvore na representação de diagrama de barras
        if(raiz != null){
            System.out.print(espacamento + raiz.valor); // imprime o espaçamento, que depende da profundidade na arvore, e o valor do nó
            for(int ii=0; ii < (qntNos*5) - espacamento.length() - Integer.toString(raiz.valor).length(); ii++){
                if(ii == (qntNos*5) - espacamento.length() - Integer.toString(raiz.valor).length() - 1){
                    System.out.print("-\n");
                }else{
                    System.out.print("-");
                }
            }
            diagramaBarras(raiz.esq, espacamento+"    "); // imprime a sub-arvore à esquerda da raiz
            diagramaBarras(raiz.dir, espacamento+"    "); // imprime a sub-arvore à diretira da raiz
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

    private String preOrdem(No raiz){ // constroi a String contendo a arvore no percurso em pre-ordem
        this.arvorePreOrdem += raiz.valor + " ";
        if(raiz.esq != null){
            preOrdem(raiz.esq);
        }
        if(raiz.dir != null){
            preOrdem(raiz.dir);
        }
        return this.arvorePreOrdem;
    }

    public void PosInOrdem(No raiz, int valor, int verif){ // determina a posição de cada elemento da arvore no percurso em ordem simetrica
        if(raiz.esq != null){
            PosInOrdem(raiz.esq, valor, verif);
        }
        countInOrdem++;
        raiz.posInOrdem = countInOrdem;
        if(verif == 2){ // metodo que chamou foi o posicao
            if(raiz.valor == valor){
                this.posNoInOrdem = raiz.posInOrdem; // determina a posição de determinado elemento na arvore
            }   
        }else if(verif == 3){ // metodo que chamou foi o enesimo
            if(raiz.posInOrdem == valor){
                this.elemNoInOrdem = raiz.valor; // determina o elemento que ocupa determinada posição na arvore
            }
        }else{ // metodo que chamou foi mediana
            if(qntNos % 2 == 0){ // quantidade par de nós
                if(raiz.posInOrdem == qntNos / 2){
                    this.mediana += raiz.valor;
                }
                if(raiz.posInOrdem == (qntNos / 2) + 1){
                    if(this.mediana > raiz.valor){
                        this.mediana = raiz.valor;
                    }
                }
            }else{ // quantidade impar de nós
                if(raiz.posInOrdem == ((qntNos / 2) + 1)){
                    this.mediana = raiz.valor;
                }
            }
        }
        if(raiz.dir != null){
            PosInOrdem(raiz.dir, valor, verif);
        }
        if(countInOrdem == qntNos){ // zera o contador das posições
            countInOrdem = 0;
        }
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
        PosInOrdem(this.raiz, n, 3);
        return this.elemNoInOrdem;
    }

    public int posicao(int valor){ // retorna a posição de valor na arvore em percurso na ordem simetrica
        if(raiz == null){
            return 0;
        }
        PosInOrdem(this.raiz, valor, 2);
        return this.posNoInOrdem;
    }

    public double mediana(){ // retorna o valor central da arvore
        if(this.mediana != 0){ // se o metodo já foi chamado antes, a mediana é zerada para novas determinações
           this.mediana = 0;
        }
        PosInOrdem(this.raiz, -1, 4);
        return this.mediana;
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