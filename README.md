## Requisitos:
* É necessário ter instalado os pacotes essenciais de compilação do Ubuntu e o Java:
	- Ubuntu build-essential;
	- Java (JDK);

# Java
```
	sudo apt install default-jdk
```
# Compilar:
```
    javac App.java
```

# Executar:
```
    java App entradas/input1.txt entradas/input2.txt  
```

# Saída esperada:
```
A árvore não é cheia
A árvore é completa
20
36 adicionado
A árvore é cheia
32 13 5 20 41 36 60
32----------------------------------
	13----------------------------
		5-----------------------
		20----------------------
	41----------------------------
		36----------------------
		60----------------------
(32 (13 (5) (20)) (41 (36) (60)))
50 não está na árvore, não pode ser removido
15 adicionado
39 adicionado
32 removido
3
39 já está na árvore, não pode ser inserido
36
20
28,625
Chave encontrada
25 adicionado
25
```
# Saída do programa:
![](src/images/Captura%20de%20tela%202022-11-07%20231053.png)



