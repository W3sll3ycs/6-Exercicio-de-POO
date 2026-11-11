package model;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Navegador navegador;
    private final Scanner entrada;

    public Menu(Navegador navegador) {
        this.navegador = navegador;
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
                case 1 -> visitar();
                case 2 -> voltar();
                case 3 -> paginaAtual();
                case 4 -> historico();
                case 0 -> System.out.println("Fechando o navegador...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirOpcoes() {
        System.out.println("\n=== Navegador ===");
        System.out.println("1 - Visitar nova página");
        System.out.println("2 - Voltar à página anterior");
        System.out.println("3 - Ver página atual");
        System.out.println("4 - Ver histórico (pilha)");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void visitar() {
        System.out.print("Título da página: ");
        String titulo = entrada.nextLine();

        try {
            navegador.visitar(titulo);
            System.out.println("Página aberta: " + navegador.paginaAtual());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void voltar() {
        String anterior = navegador.voltar();
        if (anterior == null) {
            System.out.println("Não há página anterior para voltar.");
        } else {
            System.out.println("Voltou para: " + anterior);
        }
    }

    private void paginaAtual() {
        String atual = navegador.paginaAtual();
        if (atual == null) {
            System.out.println("Nenhuma página aberta.");
        } else {
            System.out.println("Página atual: " + atual);
        }
    }

    private void historico() {
        List<String> pilha = navegador.listarHistorico();
        if (pilha.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }
        System.out.println("--- Pilha (topo -> base) ---");
        for (int i = 0; i < pilha.size(); i++) {
            String marca = (i == 0) ? "  <- atual" : "";
            System.out.println((i + 1) + ". " + pilha.get(i) + marca);
        }
    }
}