package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Navegador {

    private final Deque<String> historico = new ArrayDeque<>();

    public void visitar(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título da página não pode ser vazio.");
        }
        historico.push(titulo.trim());
    }

    public String voltar() {
        if (!podeVoltar()) {
            return null;
        }
        historico.pop();         // descarta a página atual
        return historico.peek(); // a nova página atual (a anterior)
    }

    public String paginaAtual() {
        return historico.peek();
    }

    public boolean podeVoltar() {
        return historico.size() > 1;
    }

    public List<String> listarHistorico() {
        return new ArrayList<>(historico);
    }
}
