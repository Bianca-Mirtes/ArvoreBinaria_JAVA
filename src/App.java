import java.util.Arrays;
import java.util.Collection;
import java.util.*;

import Tree.*;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();
        int[] lista = {32, 13, 5, 41, 20, 60};
        for(var dado : lista){
            arvore.inserirABB(dado);
        }

        arvore.preOrdem(arvore.getRaiz());
        arvore.removerABB(32);
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        arvore.preOrdem(arvore.getRaiz());

        //arvore.posOrdem(arvore.getRaiz());
        //arvore.inOrdem(arvore.getRaiz());

        //System.out.println(arvore.buscaABB(13));
    }
}
