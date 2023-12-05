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

    private static final String INSERIR_TRANSACAO = "INSERT INTO transacao (tipo, valor, cpf) VALUES (?, ?, ?)";

    public void inserirTransacao(String tipo, double valor, String cpf) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_TRANSACAO)) {

            preparedStatement.setString(1, tipo);
            preparedStatement.setDouble(2, valor);
            preparedStatement.setString(3, cpf);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir transação no banco de dados", e);
        }
    }

    public List<Transacao> obterHistorico(String cpf) {
        List<Transacao> historico = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE cpf = ?";
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transacao transacao = new Transacao();
                    transacao.setCpfUsuario(resultSet.getString("cpf"));
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
