package model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Demonstracao {

    private final OperacoesStreams operacoes;

    public Demonstracao(OperacoesStreams operacoes) {
        this.operacoes = operacoes;
    }

    public void executar() {
        removerNegativos();
        iniciadasComP();
        palavrasUnicas();
    }

    private void removerNegativos() {
        System.out.println("=== 1. Remover elementos menores que 0 ===");
        List<Integer> numeros = Arrays.asList(5, -3, 8, 0, -1, 12, -20, 7);

        System.out.println("Lista original: " + numeros);
        System.out.println("Sem negativos:  " + operacoes.removerNegativos(numeros));
        System.out.println();
    }

    private void iniciadasComP() {
        System.out.println("=== 2. Strings que iniciam com a letra P ===");
        List<String> nomes = Arrays.asList("Pedro", "Ana", "Paulo", "Bruno", "python", "Maria", "Patricia", "Carlos");

        System.out.println("Lista original: " + nomes);
        System.out.println("Iniciam com P:");
        operacoes.iniciadasComP(nomes).forEach(nome -> System.out.println("  - " + nome));
        System.out.println();
    }

    private void palavrasUnicas() {
        System.out.println("=== 3. Palavras únicas de um parágrafo ===");
        String paragrafo = "Java é uma linguagem de programação. Programação em Java é divertida, "
                + "e quem estuda Java aprende programação orientada a objetos.";

        System.out.println("Parágrafo: " + paragrafo);
        Set<String> unicas = operacoes.palavrasUnicas(paragrafo);
        System.out.println("Palavras únicas (" + unicas.size() + "):");
        unicas.forEach(palavra -> System.out.println("  - " + palavra));
    }
}