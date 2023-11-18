package br.ucb.poo.adocao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.ucb.poo.adocao.dao.UsuarioDAO;
import br.ucb.poo.adocao.model.Usuario;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() {
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void testSalvarNoBanco() {
        Usuario novoUsuario = new Usuario("Teste", "teste@email.com", "senha");
        assertTrue(usuarioDAO.salvarNoBanco(novoUsuario));
    }
}
