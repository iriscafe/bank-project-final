package com.example.dao;

import com.example.model.Usuario;
import com.example.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private static final String INSERIR_ADMIN = "INSERT INTO admin (nome, cpf, email, senha, telefone, admin) VALUES (?, ?, ?, ?, ?, true)";
    private static final String LISTAR_USUARIOS = "SELECT * FROM usuario";
    private static final String EXCLUIR_USUARIO = "DELETE FROM usuario WHERE cpf = ?";

    public boolean inserirAdmin(String nome, String cpf, String email, String senha, String telefone) {
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_ADMIN)) {

            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, senha);
            preparedStatement.setString(5, telefone);

            int linhasAfetadas = preparedStatement.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LISTAR_USUARIOS)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = new Usuario(
                            resultSet.getString("nome"),
                            resultSet.getString("cpf"),
                            resultSet.getString("email"),
                            resultSet.getString("senha"),
                            resultSet.getString("telefone")
                    );
                    usuarios.add(usuario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
    public boolean excluirUsuario(String cpf) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXCLUIR_USUARIO)) {

            preparedStatement.setString(1, cpf);

            int linhasAfetadas = preparedStatement.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}