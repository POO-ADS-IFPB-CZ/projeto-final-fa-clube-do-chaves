package com.poo.aluger.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Pagamento;

public class PagamentoDao {

  public boolean insert(Pagamento pagamento) {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO Pagamento (PG_Valor, PG_DataPagamento, PG_FormaPagamento, PG_CodContrato) " +
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

  public boolean delete(int id) {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "DELETE FROM Pagamento " +
              "WHERE PG_Codigo = ?");
      stmt.setInt(1, id);

      return stmt.executeUpdate() > 0;

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return false;
  }

  public boolean update(int id, Pagamento pagamento) {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "UPDATE Pagamento " +
              "SET PG_Valor = ?, PG_DataPagamento = ?, PG_FormaPagamento = ?, PG_CodContrato = ? " +
              "WHERE PG_Codigo = ?");
      stmt.setDouble(1, pagamento.getValor());
      Date sqlDate = java.sql.Date.valueOf(pagamento.getDataPagamento());
      stmt.setDate(2, sqlDate);
      stmt.setString(3, pagamento.getFormaPagamento());
      stmt.setInt(4, pagamento.getContratoAluguel().getCodigo());
      stmt.setInt(5, id);

      return stmt.executeUpdate() > 0;

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return false;
  }

  public List<Pagamento> findAll(int idProprietario) {
    List<Pagamento> pagamentos = new ArrayList<>();
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM Pagamento " +
              "WHERE PG_CodContrato IN ( " +
              "    SELECT CA_Codigo " +
              "    FROM ContratoAluguel " +
              "    WHERE CA_CodProprietario = ?)");
      stmt.setInt(1, idProprietario);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        int PG_Codigo = rs.getInt("PG_Codigo");
        double PG_Valor = rs.getDouble("PG_Valor");
        Date PG_DataPagamento = rs.getDate("PG_DataPagamento");
        String PG_FormaPagamento = rs.getString("PG_FormaPagamento");
        int PG_CodContrato = rs.getInt("PG_CodContrato");

        ContratoAluguel contratoAluguel = new ContratoAluguelDao().findById(PG_CodContrato, idProprietario);

        Pagamento pagamento = new Pagamento(PG_Codigo, PG_Valor, PG_DataPagamento.toLocalDate(), PG_FormaPagamento,
            contratoAluguel);
        pagamentos.add(pagamento);
      }
      return pagamentos;

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return pagamentos;
  }

  public Pagamento findById(int id) {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM Pagamento " +
              "WHERE PG_Codigo = ?");
      stmt.setInt(1, id);
      return null;

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return null;
  }
}
