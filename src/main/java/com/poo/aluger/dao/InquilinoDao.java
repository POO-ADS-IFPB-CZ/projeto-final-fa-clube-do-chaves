package com.poo.aluger.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Inquilino;

public class InquilinoDao {

  public int insert(Inquilino inquilino) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO Inquilino(INQ_Nome, INQ_CPF, INQ_Telefone1, INQ_Telefone2) " +
              "VALUES(?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, inquilino.getNome());
      stmt.setString(2, inquilino.getCpf());
      stmt.setString(3, inquilino.getTelefone1());
      stmt.setString(4, inquilino.getTelefone2());

      int affectedRows = stmt.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating user failed, no rows affected.");
      }

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }
    }
  }

  public boolean delete(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "DELETE FROM Inquilino " +
              "WHERE INQ_Codigo = ? AND INQ_Codigo IN " +
              "(SELECT CA_CodigoInquilino FROM ContratoAluguel WHERE CA_CodigoProprietario = ?)");
      stmt.setInt(1, id);
      stmt.setInt(2, codigoProprietario);

      return stmt.executeUpdate() > 0;
    }
  }

  public boolean update(int id, int codigoProprietario, Inquilino inquilino)
      throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "UPDATE Inquilino " +
              "SET INQ_Nome = ?, INQ_CPF = ?, INQ_Telefone1 = ?, INQ_Telefone2 = ? " +
              "WHERE INQ_Codigo = ? AND INQ_Codigo IN " +
              "(SELECT CA_CodigoInquilino FROM ContratoAluguel WHERE CA_CodigoProprietario = ?)");
      stmt.setString(1, inquilino.getNome());
      stmt.setString(2, inquilino.getCpf());
      stmt.setString(3, inquilino.getTelefone1());
      stmt.setString(4, inquilino.getTelefone2());
      stmt.setInt(5, id);
      stmt.setInt(6, codigoProprietario);

      return stmt.executeUpdate() > 0;
    }
  }

  public List<Inquilino> findAll(int idProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT INQ.* " +
              "FROM Inquilino INQ " +
              "INNER JOIN ContratoAluguel on INQ_Codigo = CA_CodigoInquilino " +
              "WHERE CA_CodigoProprietario = ?");
      stmt.setInt(1, idProprietario);
      ResultSet rs = stmt.executeQuery();

      List<Inquilino> inquilinos = new ArrayList<>();
      while (rs.next()) {
        int INQ_Codigo = rs.getInt("INQ_Codigo");
        String INQ_Nome = rs.getString("INQ_Nome");
        String INQ_Cpf = rs.getString("INQ_CPF");
        String INQ_Telefone1 = rs.getString("INQ_Telefone1");
        String INQ_Telefone2 = rs.getString("INQ_Telefone2");

        inquilinos.add(new Inquilino(INQ_Codigo, INQ_Nome, INQ_Cpf, INQ_Telefone1, INQ_Telefone2));
      }
      return inquilinos;
    }
  }

  public Inquilino findById(int idInquilino, int idProprietario)
      throws SQLException, IOException, ClassNotFoundException {
    try (Connection conn = DBConnector.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(
          "SELECT INQ.* " +
              "FROM Inquilino INQ " +
              "INNER JOIN ContratoAluguel ON INQ_Codigo = CA_CodigoInquilino " +
              "WHERE INQ_Codigo = ? AND CA_CodigoProprietario = ?");
      stmt.setInt(1, idInquilino);
      stmt.setInt(2, idProprietario);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int INQ_Codigo = rs.getInt("INQ_Codigo");
        String INQ_Nome = rs.getString("INQ_Nome");
        String INQ_Cpf = rs.getString("INQ_CPF");
        String INQ_Telefone1 = rs.getString("INQ_Telefone1");
        String INQ_Telefone2 = rs.getString("INQ_Telefone2");

        return new Inquilino(INQ_Codigo, INQ_Nome, INQ_Cpf, INQ_Telefone1, INQ_Telefone2);
      }

      return null;
    }
  }
}
