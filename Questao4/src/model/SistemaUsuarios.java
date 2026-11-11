package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SistemaUsuarios {

    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();

    private String normalizar(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }

    public boolean inserir(String email, String nome, String senha) {
        String chave = normalizar(email);

        if (chave.isEmpty() || !chave.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }

        if (usuarios.containsKey(chave)) {
            return false;
        }
        usuarios.put(chave, new Usuario(chave, nome.trim(), senha));
        return true;
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios.values()); // cópia: a coleção interna fica protegida
    }

    public boolean remover(String email) {
        return usuarios.remove(normalizar(email)) != null;
    }

    public int total() {
        return usuarios.size();
    }
}