package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Proprietario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProprietarioDao implements GenericDao<Proprietario> {

    @Override
    public boolean insert(Proprietario proprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Proprietario(Nome, Email, Senha) " +
                        "VALUES (?, ?, ?)"
            );
            stmt.setString(1, proprietario.getNome());
            stmt.setString(2, proprietario.getEmail());
            stmt.setString(3, proprietario.getSenha());
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Proprietario " +
                        "WHERE Codigo = ?"
            );
            stmt.setInt(1, id);
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, Proprietario proprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Proprietario " +
                        "SET Nome = ?, Email = ?, Senha = ? " +
                        "WHERE Codigo = ?"
            );
            stmt.setString(1, proprietario.getNome());
            stmt.setString(2, proprietario.getEmail());
            stmt.setString(3, proprietario.getSenha());
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Proprietario findById(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Proprietario " +
                        "WHERE Codigo = ?"
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                String email = rs.getString("Email");
                String senha = rs.getString("Senha");

                return new Proprietario(codigo, nome, email, senha);
            }
            return null;
        }
    }

}
