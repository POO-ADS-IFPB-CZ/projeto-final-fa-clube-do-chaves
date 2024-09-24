package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Inquilino;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InquilinoDao {

  public boolean insert(Inquilino inquilino) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO Inquilino(Nome, CPF, Telefone1, Telefone2) " +
              "VALUES(?, ?, ?, ?)");
      stmt.setString(1, inquilino.getNome());
      stmt.setString(2, inquilino.getCpf());
      stmt.setString(3, inquilino.getTelefone1());
      stmt.setString(4, inquilino.getTelefone2());

      return stmt.executeUpdate() > 0;
    }
  }

  public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "DELETE FROM Inquilino " +
              "WHERE Codigo = ?");
      stmt.setInt(1, id);

      return stmt.executeUpdate() > 0;
    }
  }

  public boolean update(int id, Inquilino inquilino) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "UPDATE Inquilino " +
              "SET Nome = ?, CPF = ?, Telefone1 = ?, Telefone2 = ? " +
              "WHERE Codigo = ?");
      stmt.setString(1, inquilino.getNome());
      stmt.setString(2, inquilino.getCpf());
      stmt.setString(3, inquilino.getTelefone1());
      stmt.setString(4, inquilino.getTelefone2());
      stmt.setInt(5, id);

      return stmt.executeUpdate() > 0;
    }
  }

  public List<Inquilino> findAll(int idProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT inquilino.* " +
              "FROM Inquilino " +
              "INNER JOIN ContratoAluguel on Inquilino.inq_codigo = ContratoAluguel.ca_CodigoInquilino " +
              "WHERE ContratoAluguel.ca_CodigoProprietario = ?");
      stmt.setInt(1, idProprietario);
      ResultSet rs = stmt.executeQuery();

      List<Inquilino> inquilinos = new ArrayList<>();
      while (rs.next()) {
        int codigo = rs.getInt("INQ_Codigo");
        String nome = rs.getString("INQ_Nome");
        String cpf = rs.getString("INQ_CPF");
        String telefone1 = rs.getString("INQ_Telefone1");
        String telefone2 = rs.getString("INQ_Telefone2");

        inquilinos.add(new Inquilino(codigo, nome, cpf, telefone1, telefone2));
      }
      return inquilinos;
    }
  }

  public Inquilino findById(int idInquilino) {
    try (Connection conn = DBConnector.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(
          "SELECT * " +
              "FROM Inquilino " +
              "WHERE INQ_Codigo = ?");
      stmt.setInt(1, idInquilino);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int codigo = rs.getInt("INQ_Codigo");
        String nome = rs.getString("INQ_Nome");
        String cpf = rs.getString("INQ_CPF");
        String telefone1 = rs.getString("INQ_Telefone1");
        String telefone2 = rs.getString("INQ_Telefone2");

        return new Inquilino(codigo, nome, cpf, telefone1, telefone2);
      }
      return null;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}
