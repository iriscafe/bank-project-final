package com.example.dao;

import com.example.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO {
    private static final String INSERIR_ADMIN = "INSERT INTO admin (nome, email, senha) VALUES (?, ?, ?)";
    private static final String EDITAR_USUARIO = "UPDATE admin SET nome=?, email=?, senha=? WHERE cpf=?";
    private static final String DELETAR_USUARIO = "DELETE FROM admin WHERE cpf=?";

    public boolean inserirAdmin(String nome, String email, String senha) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_ADMIN)) {

            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, senha);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir administrador no banco de dados", e);
        }
    }

    public boolean editarUsuario(int id, String novoNome, String novoEmail, String novaSenha) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDITAR_USUARIO)) {

            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novoEmail);
            preparedStatement.setString(3, novaSenha);
            preparedStatement.setInt(4, id);

            int linhasAfetadas = preparedStatement.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar usuário no banco de dados", e);
        }
    }
    public boolean deletarUsuario(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETAR_USUARIO)) {

            preparedStatement.setInt(1, id);

            int linhasAfetadas = preparedStatement.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário no banco de dados", e);
        }
    }
}
