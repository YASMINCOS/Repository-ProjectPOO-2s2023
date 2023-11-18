package br.ucb.poo.adocao;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.ucb.poo.autenticacao.Autenticacao;
import br.ucb.poo.adocao.dao.PetDao;
import br.ucb.poo.adocao.dao.UsuarioDAO;
import br.ucb.poo.adocao.dao.ProcessoDeAdocaoDao;
import br.ucb.poo.adocao.model.Pet;
import br.ucb.poo.adocao.model.ProcessoAdocao;
import br.ucb.poo.adocao.model.Usuario;

public class App {
    private static Map<String, UsuarioDAO> usuariosCadastrados = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Autenticacao.setUsuariosCadastrados(usuariosCadastrados);

        while (true) {
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    fazerCadastro();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void fazerLogin() {
        System.out.print("Digite o e-mail: ");
        String email = scanner.next();
        System.out.print("Digite a senha: ");
        String senha = scanner.next();

        Usuario usuario = Autenticacao.fazerLogin(email, senha);

        if (usuario != null) {
            System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getNome() + "!");
            menuPrincipal(usuario);
        } else {
            System.out.println("Falha no login. Verifique suas credenciais ou faça cadastro.");
        }
    }

    private static void fazerCadastro() {
        System.out.print("Digite o nome: ");
        String nome = scanner.next();
        System.out.print("Digite o e-mail: ");
        String email = scanner.next();
        System.out.print("Digite a senha: ");
        String senha = scanner.next();

        Usuario novoUsuarioDAO = Autenticacao.fazerCadastro(nome, email, senha);

        if (novoUsuarioDAO != null) {
            Usuario novoUsuario = new Usuario(novoUsuarioDAO.getNome(), novoUsuarioDAO.getEmail(), novoUsuarioDAO.getSenha());
            System.out.println("Cadastro bem-sucedido. Bem-vindo, " + novoUsuario.getNome() + "!");
            menuPrincipal(novoUsuario);
        } else {
            System.out.println("Falha no cadastro. E-mail já cadastrado.");
        }
    }

    private static void menuPrincipal(Usuario usuario) {
        while (true) {
            System.out.println("\n----- Menu Principal -----");
            System.out.println("1 - Consultar Pets Disponíveis");
            System.out.println("2 - Visualizar Processos de Adoção");
            System.out.println("3 - Iniciar Processos de Adoção");
            System.out.println("4 - Excluir Conta");
            System.out.println("5 - Atualizar Informações da Conta");
            System.out.println("6 - Logout");
            System.out.print("Escolha uma opção: ");

            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();

                switch (escolha) {
                    case 1:
                        consultarPetsDisponiveis();
                        break;
                    case 2:
                        visualizarProcessosAdocao(usuario);
                        break;
                    case 3:
                        iniciarProcessoAdocao(usuario);
                        break;
                    case 4:
                        excluirConta(usuario);
                        break;
                    case 5:
                        atualizarInformacoesConta(usuario);
                        break;

                    case 6:
                        System.out.println("Logout realizado. Retornando ao menu inicial.");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
            }
        }
    }

    private static void consultarPetsDisponiveis() {
        List<Pet> petsDisponiveis = PetDao.consultarPetsDisponiveis();

        if (petsDisponiveis.isEmpty()) {
            System.out.println("Não há pets disponíveis no momento.");
        } else {
            System.out.println("Pets Disponíveis:");
            for (Pet pet : petsDisponiveis) {
                System.out.println("ID: " + pet.getId() + ", Nome: " + pet.getNome() + ", Raça: " + pet.getRaca());
            }
        }
    }

    private static void visualizarProcessosAdocao(Usuario usuario) {
        List<ProcessoAdocao> processosUsuario = ProcessoDeAdocaoDao.visualizarProcessosAdocao(usuario.getId());

        if (processosUsuario.isEmpty()) {
            System.out.println("Você não tem processos de adoção em andamento.");
        } else {
            System.out.println("Processos de Adoção em Andamento:");
            for (ProcessoAdocao processo : processosUsuario) {
                System.out.println("ID do Processo: " + processo.getId() +
                        ", Status: " + processo.getStatus() +
                        ", Pet ID: " + processo.getPetId());
            }
        }
    }

    private static void iniciarProcessoAdocao(Usuario usuario) {
        consultarPetsDisponiveis();

        System.out.print("Digite o ID do pet para o qual deseja iniciar o processo de adoção: ");
        int petId = scanner.nextInt();

        if (PetDao.verificarDisponibilidade(petId)) {
            ProcessoAdocao novoProcesso = new ProcessoAdocao(0, usuario.getId(), petId, "Em andamento");

            if (ProcessoDeAdocaoDao.iniciarProcessoAdocao(novoProcesso)) {
                System.out.println("Processo de adoção iniciado com sucesso!");
            } else {
                System.out.println("Falha ao iniciar o processo de adoção. Tente novamente.");
            }
        } else {
            System.out.println("Pet não disponível para adoção. Escolha outro pet.");
        }
    }

    private static void excluirConta(Usuario usuario) {
        if (Autenticacao.excluirConta(usuario.getEmail())) {
            System.out.println("Conta excluída com sucesso. Saindo do sistema.");
            System.exit(0);
        } else {
            System.out.println("Falha ao excluir a conta. Tente novamente.");
        }
    }

    private static void atualizarInformacoesConta(Usuario usuario) {
        System.out.println("Escolha o que deseja atualizar:");
        System.out.println("1 - Nome");
        System.out.println("2 - Senha");
        System.out.println("3 - Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                System.out.print("Digite o novo nome: ");
                String novoNome = scanner.next();
                usuario.setNome(novoNome);
                System.out.println("Nome atualizado com sucesso.");
                if (!Autenticacao.atualizarNome(usuario.getEmail(), novoNome)) {
                    System.out.println("Falha ao atualizar o nome. Tente novamente.");
                }
                break;
            case 2:
                System.out.print("Digite a nova senha: ");
                String novaSenha = scanner.next();
                usuario.setSenha(novaSenha);
                System.out.println("Senha atualizada com sucesso.");
                if (!Autenticacao.atualizarSenha(usuario.getEmail(), novaSenha)) {
                    System.out.println("Falha ao atualizar a senha. Tente novamente.");
                }
                break;
            case 3:
                System.out.println("Retornando ao menu principal.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
}
