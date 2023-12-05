package com.example.dao;

import com.example.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private static final String INSERIR_USUARIO = "INSERT INTO usuario (nome, cpf, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";

    public boolean inserirUsuario(String nome, String cpf, String email, String senha, String telefone) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_USUARIO)) {
    
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, senha);
            preparedStatement.setString(5, telefone);
    
            int linhasAfetadas = preparedStatement.executeUpdate();
    
            // Retorna true se pelo menos uma linha foi afetada, indicando sucesso na inserção
            return linhasAfetadas > 0;
    
        } catch (SQLException e) {
            // Loga ou trata a exceção, se necessário
            e.printStackTrace();
            return false;
        }
    }
    
    private static final String BUSCAR_SENHA_POR_EMAIL = "SELECT senha FROM usuario WHERE email = ?";
    public String getSenhaByEmail(String email) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_SENHA_POR_EMAIL)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("senha");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter senha do usuário por e-mail", e);
        }
        return null;
    }
}

