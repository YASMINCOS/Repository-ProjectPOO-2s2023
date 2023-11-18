package br.ucb.poo.adocao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.poo.adocao.database.DatabaseConnector;
import br.ucb.poo.adocao.model.ProcessoAdocao;

public class ProcessoDeAdocaoDao {

    public static List<ProcessoAdocao> visualizarProcessosAdocao(int usuarioId) {
        List<ProcessoAdocao> processos = new ArrayList<>();
        String sql = "SELECT * FROM ProcessoAdocao WHERE usuario_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, usuarioId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int petId = resultSet.getInt("pet_id");
                    String status = resultSet.getString("status");

                    ProcessoAdocao processoAdocao = new ProcessoAdocao(id, usuarioId, petId, status);
                    processos.add(processoAdocao);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return processos;
    }

    public static boolean iniciarProcessoAdocao(ProcessoAdocao novoProcesso) {
        String sqlInsert = "INSERT INTO ProcessoAdocao (usuario_id, pet_id, status) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementInsert = connection.prepareStatement(sqlInsert)) {

            connection.setAutoCommit(false); 

            statementInsert.setInt(1, novoProcesso.getUsuarioId());
            statementInsert.setInt(2, novoProcesso.getPetId());
            statementInsert.setString(3, "em andamento");

            int rowsInserted = statementInsert.executeUpdate();

            if (rowsInserted > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback(); 
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
