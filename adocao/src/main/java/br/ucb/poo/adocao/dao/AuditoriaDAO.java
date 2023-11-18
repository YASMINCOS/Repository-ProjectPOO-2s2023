package br.ucb.poo.adocao.dao;

import br.ucb.poo.adocao.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditoriaDAO {
    private static final String SELECT_AUDITORIA = "SELECT * FROM Auditoria";

    public static void exibirAuditoria() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUDITORIA);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Ação: " + resultSet.getString("acao") +
                        ", Tabela Afetada: " + resultSet.getString("tabela_afetada") +
                        ", ID do Registro Afetado: " + resultSet.getInt("registro_afetado_id") +
                        ", Timestamp: " + resultSet.getTimestamp("data_hora"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
