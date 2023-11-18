package br.ucb.poo.autenticacao;

import java.util.Map;

import br.ucb.poo.adocao.dao.UsuarioDAO;
import br.ucb.poo.adocao.model.Usuario;

public class Autenticacao {

    public static void setUsuariosCadastrados(Map<String, UsuarioDAO> usuarios) {
    }

    public static Usuario fazerLogin(String email, String senha) {
        Usuario usuario = UsuarioDAO.buscarPorEmail(email);

        if (usuario != null && usuario.autenticar(senha)) {
            return usuario;
        }

        return null;
    }

    public static Usuario fazerCadastro(String nome, String email, String senha) {
        Usuario usuarioExistente = UsuarioDAO.buscarPorEmail(email);
        if (usuarioExistente != null) {
            return null;
        }
    
        Usuario novoUsuario = new Usuario(nome, email, senha);
    
        UsuarioDAO novoUsuarioDAO = new UsuarioDAO();
        novoUsuarioDAO.salvarNoBanco(novoUsuario);
    
        return novoUsuario;
    }

    public static boolean excluirConta(String email) {
        return UsuarioDAO.excluirConta(email);
    }

    public static boolean atualizarNome(String email, String novoNome) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
    
        if (usuario != null) {
            usuario.setNome(novoNome);
            return usuarioDAO.atualizarConta(usuario);
        }
    
        return false;
    }
    
    public static boolean atualizarSenha(String email, String novaSenha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
    
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            return usuarioDAO.atualizarConta(usuario);
        }
    
        return false;
    }
    
}

