package model;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProcessadorStreams implements OperacoesStreams {

    @Override
    public List<Integer> removerNegativos(List<Integer> numeros) {
        return numeros.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> iniciadasComP(List<String> textos) {
        return textos.stream()
                .filter(s -> s != null && !s.isEmpty())
                .filter(s -> Character.toUpperCase(s.charAt(0)) == 'P')
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> palavrasUnicas(String paragrafo) {
        if (paragrafo == null || paragrafo.trim().isEmpty()) {
            return new LinkedHashSet<>();
        }

        return Arrays.stream(paragrafo.toLowerCase().split("[^\\p{L}\\p{N}]+"))
                .filter(palavra -> !palavra.isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new)); // Set sem repetição, mantendo a ordem
    }
}