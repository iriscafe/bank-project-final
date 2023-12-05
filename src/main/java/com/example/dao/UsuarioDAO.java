package com.example.dao;

import com.example.model.Usuario;
import com.example.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public class UsuarioDAO {
    private static final String INSERIR_USUARIO = "INSERT INTO usuario (nome, cpf, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
    private static final String BUSCAR_SENHA_POR_EMAIL = "SELECT senha FROM usuario WHERE email = ?";
    private static final String EDITAR_NOMEUSUARIO = "UPDATE usuario SET nome = ? WHERE cpf = ?";
     private static final String EDITAR_EMAILUSUARIO = "UPDATE usuario SET email = ? WHERE cpf = ?";
    private static final String EXCLUIR_USUARIO = "DELETE FROM usuario WHERE cpf = ?";
    private static final String BUSCAR_USER_POR_CPF = "SELECT * FROM usuario WHERE cpf = ?";


    public boolean inserirUsuario(String nome, String cpf, String email, String senha, String telefone) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_USUARIO)) {
    
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

	public int getIdUsuarioByEmail(String email) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM usuario WHERE email = ?")) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter ID do usuário por e-mail", e);
        }

        return 0;
    }

    public String getCPFPorSessao(HttpSession session) {
        
        if (session != null && session.getAttribute("email") != null) {
            String email = session.getAttribute("email").toString();
            return getCPFByEmail(email);
        }
        return null;
    }

    public boolean editarNomeUsuario(String cpf, String novoNome) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDITAR_NOMEUSUARIO)) {
    
                preparedStatement.setString(1, novoNome);
                preparedStatement.setString(2, cpf);
            int linhasAfetadas = preparedStatement.executeUpdate();
            return linhasAfetadas > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarEmailUsuario(String cpf, String novoEmail) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDITAR_EMAILUSUARIO)) {
    
                preparedStatement.setString(1, novoEmail);
                preparedStatement.setString(2, cpf);
            int linhasAfetadas = preparedStatement.executeUpdate();
            return linhasAfetadas > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    public Usuario getUsuarioByCPF(String cpf) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_USER_POR_CPF)) {
    
            preparedStatement.setString(1, cpf);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setCpf(resultSet.getString("cpf"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setSenha(resultSet.getString("senha"));
                    usuario.setTelefone(resultSet.getString("telefone"));
                    return usuario;
                }
            }
    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por CPF", e);
        }
    
        return null;
    }

    public String getCPFByEmail(String email) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT cpf FROM usuario WHERE email = ?")) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("cpf");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter CPF do usuário por e-mail", e);
        }

        return null;
    }

    

    
}

