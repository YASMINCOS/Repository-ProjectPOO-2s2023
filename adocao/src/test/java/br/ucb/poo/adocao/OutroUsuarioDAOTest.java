package br.ucb.poo.adocao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ucb.poo.adocao.dao.UsuarioDAO;
import br.ucb.poo.adocao.model.Usuario;

public class OutroUsuarioDAOTest {

    @Test
    public void testAtualizarConta() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = new Usuario("Teste02", "teste02@email.com", "senha");
        usuarioDAO.salvarNoBanco(usuario);

        usuario.setNome("Novo Nome02");
        usuario.setSenha("nova_senha02");

        assertTrue(usuarioDAO.atualizarConta(usuario));

        Usuario usuarioAtualizado = usuarioDAO.buscarPorEmail("test02e@email.com");
        assertNotNull(usuarioAtualizado);

        assertEquals("Novo Nome02", usuarioAtualizado.getNome());
        assertEquals("nova_senha02", usuarioAtualizado.getSenha());
    }
}

