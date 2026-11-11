package model;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final SistemaUsuarios sistema;
    private final Scanner entrada;

    public Menu(SistemaUsuarios sistema) {
        this.sistema = sistema;
        this.entrada = new Scanner(System.in);
    }

    public void executar() {
        int opcao;

        do {
            exibirOpcoes();

            if (!entrada.hasNextLine()) break;
            String linha = entrada.nextLine().trim();
            opcao = linha.matches("\\d+") ? Integer.parseInt(linha) : -1;

            switch (opcao) {
                case 1 -> inserir();
                case 2 -> listar();
                case 3 -> remover();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirOpcoes() {
        System.out.println("\n=== Sistema de Usuários ===");
        System.out.println("1 - Inserir usuário");
        System.out.println("2 - Listar usuários");
        System.out.println("3 - Remover usuário");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void inserir() {
        System.out.print("E-mail: ");
        String email = entrada.nextLine();
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        try {
            if (sistema.inserir(email, nome, senha)) {
                System.out.println("Usuário cadastrado com sucesso.");
            } else {
                System.out.println("Já existe um usuário com esse e-mail.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listar() {
        List<Usuario> lista = sistema.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("--- Usuários (" + lista.size() + ") ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private void remover() {
        System.out.print("E-mail do usuário a remover: ");
        String email = entrada.nextLine();

        if (sistema.remover(email)) {
            System.out.println("Usuário removido.");
        } else {
            System.out.println("Nenhum usuário encontrado com esse e-mail.");
        }
    }
}
