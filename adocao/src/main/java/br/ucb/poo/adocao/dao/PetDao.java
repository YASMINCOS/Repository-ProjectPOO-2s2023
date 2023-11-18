package br.ucb.poo.adocao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.poo.adocao.database.DatabaseConnector;
import br.ucb.poo.adocao.model.Pet;

public class PetDao {
    public static List<Pet> consultarPetsDisponiveis() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM Pet WHERE disponivel = true";
    
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
    
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String raca = resultSet.getString("raca");
                boolean disponivel = resultSet.getBoolean("disponivel");
    
                Pet pet = new Pet(id, nome, raca, disponivel);
                pets.add(pet);
            }
    
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    
        return pets;
    }
    
    public static boolean verificarDisponibilidade(int petId) {
        String sql = "SELECT disponivel FROM Pet WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, petId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("disponivel");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return false;
    }
}
