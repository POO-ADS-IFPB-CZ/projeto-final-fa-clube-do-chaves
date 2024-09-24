package com.poo.aluger.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Pagamento;

public class PagamentoDao implements GenericDao<Pagamento>{

  @Override
  public boolean insert(Pagamento pagamento) {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO Pagamento (PG_Valor, PG_DataPagamento, PG_FormaPagamento, PG_CodigoContrato) " +
              "VALUES (?, ?, ?, ?)");
      stmt.setDouble(1, pagamento.getValor());
      Date sqlDate = java.sql.Date.valueOf(pagamento.getDataPagamento());
      stmt.setDate(2, sqlDate);
      stmt.setString(3, pagamento.getFormaPagamento());
      stmt.setInt(4, pagamento.getContratoAluguel().getCodigo());

      return stmt.executeUpdate() > 0;

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return false;
  }

  @Override
  public boolean delete(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
              "DELETE FROM Pagamento " +
                "WHERE PG_Codigo = ? AND PG_Codigo IN " +
                  "(SELECT CA_CodigoPagamento " +
                  "FROM ContratoAluguel " +
                  "WHERE CA_CodigoProprietario = ?)"
      );
      stmt.setInt(1, id);
      stmt.setInt(2, codigoProprietario);

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public boolean update(int id, int codigoProprietario, Pagamento pagamento) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
              "UPDATE Pagamento " +
                "SET PG_Valor = ?, PG_DataPagamento = ?, PG_FormaPagamento = ?, PG_CodigoContrato = ? " +
                "WHERE PG_Codigo = ? AND PG_Codigo IN " +
                  "(SELECT CA_CodigoPagamento " +
                  "FROM ContratoAluguel " +
                  "WHERE CA_CodigoProprietario = ?)"
      );
      stmt.setDouble(1, pagamento.getValor());
      Date sqlDate = java.sql.Date.valueOf(pagamento.getDataPagamento());
      stmt.setDate(2, sqlDate);
      stmt.setString(3, pagamento.getFormaPagamento());
      stmt.setInt(4, pagamento.getContratoAluguel().getCodigo());
      stmt.setInt(5, id);
      stmt.setInt(6, codigoProprietario);

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public List<Pagamento> findAll(int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM Pagamento " +
              "WHERE PG_CodigoContrato IN ( " +
                "SELECT CA_Codigo " +
                "FROM ContratoAluguel " +
                "WHERE CA_CodigoProprietario = ?)"
      );
      stmt.setInt(1, codigoProprietario);

      ResultSet rs = stmt.executeQuery();

      List<Pagamento> pagamentos = new ArrayList<>();
      while (rs.next()) {
        //Dados do Pagamento
        int PG_Codigo = rs.getInt("PG_Codigo");
        double PG_Valor = rs.getDouble("PG_Valor");
        Date PG_DataPagamento = rs.getDate("PG_DataPagamento");
        String PG_FormaPagamento = rs.getString("PG_FormaPagamento");
        int PG_CodigoContrato = rs.getInt("PG_CodigoContrato");

        ContratoAluguel contratoAluguel = new ContratoAluguelDao().findById(PG_CodigoContrato, codigoProprietario);

        Pagamento pagamento = new Pagamento(PG_Codigo, PG_Valor, PG_DataPagamento.toLocalDate(), PG_FormaPagamento,
            contratoAluguel);
        pagamentos.add(pagamento);
      }

      return pagamentos;
    }
  }

  @Override
  public Pagamento findById(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
              "SELECT * " +
                "FROM Pagamento " +
                "WHERE PG_Codigo = ? " +
                "AND PG_CodigoContrato IN " +
                  "(SELECT CA_Codigo " +
                  "FROM ContratoAluguel " +
                  "WHERE CA_CodigoProprietario = ?)"
      );
      stmt.setInt(1, id);
      stmt.setInt(2, codigoProprietario);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int PG_Codigo = rs.getInt("PG_Codigo");
        double PG_Valor = rs.getDouble("PG_Valor");
        Date PG_DataPagamento = rs.getDate("PG_DataPagamento");
        String PG_FormaPagamento = rs.getString("PG_FormaPagamento");
        int PG_CodigoContrato = rs.getInt("PG_CodigoContrato");

        ContratoAluguel contratoAluguel = new ContratoAluguelDao().findById(PG_CodigoContrato, codigoProprietario);

        return new Pagamento(PG_Codigo, PG_Valor, PG_DataPagamento.toLocalDate(), PG_FormaPagamento, contratoAluguel);
      }
      return null;
    }
  }
}
