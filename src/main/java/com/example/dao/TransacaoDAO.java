package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Transacao;
import com.example.util.ConnectionFactory;

public class TransacaoDAO {

    public boolean realizarTransacao(Transacao transacao) {
        String sql = "INSERT INTO transacoes (cpf_usuario, tipo, valor) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, transacao.getCpfUsuario());
            preparedStatement.setString(2, transacao.getTipo());
            preparedStatement.setDouble(3, transacao.getValor());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Transacao> obterHistorico(String cpf) {
        List<Transacao> historico = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE cpf_usuario = ?";
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transacao transacao = new Transacao();
                    transacao.setCpfUsuario(resultSet.getString("cpf_usuario"));
                    transacao.setTipo(resultSet.getString("tipo"));
                    transacao.setValor(resultSet.getDouble("valor"));
                    historico.add(transacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historico;
    }
}
