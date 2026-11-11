package model;

import java.util.List;
import java.util.Set;

public interface OperacoesStreams {

    List<Integer> removerNegativos(List<Integer> numeros);

    List<String> iniciadasComP(List<String> textos);

    Set<String> palavrasUnicas(String paragrafo);
}