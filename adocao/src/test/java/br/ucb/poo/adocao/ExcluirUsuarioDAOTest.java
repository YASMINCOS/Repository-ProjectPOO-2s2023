package br.ucb.poo.adocao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ucb.poo.adocao.dao.UsuarioDAO;
import br.ucb.poo.adocao.model.Usuario;

public class ExcluirUsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() {
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void testExcluirUsuario() {
        Usuario novoUsuario = new Usuario("Teste Exclusao", "teste_exclusao@email.com", "senha");
        usuarioDAO.salvarNoBanco(novoUsuario);

        assertNotNull(usuarioDAO.buscarPorEmail("teste_exclusao@email.com"));

        assertTrue(usuarioDAO.excluirConta("teste_exclusao@email.com"));

        assertNull(usuarioDAO.buscarPorEmail("teste_exclusao@email.com"));
    }
}

