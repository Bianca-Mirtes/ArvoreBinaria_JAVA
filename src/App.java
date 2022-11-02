import Tree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("src/entradas/input1.txt");
        Path path2 = Paths.get("src/entradas/input2.txt");

        ArvoreBinaria arvore = new ArvoreBinaria(Files.readAllLines(path1).get(0));

        List<String> comandos = Files.readAllLines(path2);
        for(var dado : comandos){
            if(dado.contains(" ")){
                String comando = dado.split(" ")[0];
                int valor = Integer.parseInt(dado.split(" ")[1]);
                if(comando.equals("ENESIMO")){
                    //System.out.println(arvore.enesimoElemento(valor));
                }else if(comando.equals("INSIRA")){
                    arvore.inserirABB(valor);
                }else if(comando.equals("REMOVA")){
                    arvore.removerABB(valor);
                }else if(comando.equals("BUSCAR")){
                    arvore.buscaABB(valor);
                }else if(comando.equals("POSICAO")){

                }else if(comando.equals("IMPRIMA")){
                    arvore.imprimeArvore(valor);
                }
            }else{
                String comando = dado.split(" ")[0];
                if(comando.equals("CHEIA")){
                    if(arvore.ehCheia()){
                        System.out.println("A arvore é cheia.");
                    }else{
                        System.out.println("A arvore não é cheia.");
                    }
                }else if(comando.equals("MEDIANA")){

                }else if(comando.equals("PREORDEM")){
                    System.out.println(arvore.pre_ordem());
                }else if(comando.equals("COMPLETA")){
                    if(arvore.ehCompleta()){
                        System.out.println("A arvore é completa.");
                    }else{
                        System.out.println("A arvore não é completa.");
                    }
                }
            }
        }
    }
}
