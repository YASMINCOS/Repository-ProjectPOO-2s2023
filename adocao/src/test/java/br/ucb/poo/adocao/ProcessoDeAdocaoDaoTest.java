package br.ucb.poo.adocao;

import static org.junit.Assert.*;
import org.junit.Test;
import br.ucb.poo.adocao.dao.ProcessoDeAdocaoDao;

public class ProcessoDeAdocaoDaoTest {

    @Test
    public void testVisualizarProcessosAdocao() {
        int usuarioId = 1;
        assertNotNull(ProcessoDeAdocaoDao.visualizarProcessosAdocao(usuarioId));
    }

}
