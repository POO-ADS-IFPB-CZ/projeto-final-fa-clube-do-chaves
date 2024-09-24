package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Proprietario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProprietarioDao {

  public boolean insert(Proprietario proprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO Proprietario(P_Nome, P_Email, P_Senha) " +
              "VALUES (?, ?, ?)");
      stmt.setString(1, proprietario.getNome());
      stmt.setString(2, proprietario.getEmail());
      stmt.setString(3, proprietario.getSenha());

      return stmt.executeUpdate() > 0;
    }
  }

  public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "DELETE FROM Proprietario " +
              "WHERE P_Codigo = ?");
      stmt.setInt(1, id);

      return stmt.executeUpdate() > 0;
    }
  }

  public boolean update(int id, Proprietario proprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "UPDATE Proprietario " +
              "SET P_Nome = ?, P_Email = ?, P_Senha = ? " +
              "WHERE P_Codigo = ?");
      stmt.setString(1, proprietario.getNome());
      stmt.setString(2, proprietario.getEmail());
      stmt.setString(3, proprietario.getSenha());
      stmt.setInt(4, id);

      return stmt.executeUpdate() > 0;
    }
  }

  public Proprietario findById(int id) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM Proprietario " +
              "WHERE P_Codigo = ?");
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int P_Codigo = rs.getInt("P_Codigo");
        String P_Nome = rs.getString("P_Nome");
        String P_Email = rs.getString("P_Email");
        String P_Senha = rs.getString("P_Senha");

        return new Proprietario(P_Codigo, P_Nome, P_Email, P_Senha);
      }
      return null;
    }

  }

  public Proprietario findByEmail(String email) throws SQLException, ClassNotFoundException, IOException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM Proprietario " +
              "WHERE P_Email = ?");
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int codigo = rs.getInt("P_Codigo");
        String nome = rs.getString("P_Nome");
        String senha = rs.getString("P_Senha");

        return new Proprietario(codigo, nome, email, senha);
      }
      return null;
    }
  }
}
