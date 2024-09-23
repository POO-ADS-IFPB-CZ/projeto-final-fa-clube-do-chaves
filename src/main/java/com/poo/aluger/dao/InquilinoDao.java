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
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Inquilino(INQ_Nome, INQ_CPF, INQ_Telefone1, INQ_Telefone2) " +
                        "VALUES(?, ?, ?, ?)"
            );
            stmt.setString(1, inquilino.getNome());
            stmt.setString(2, inquilino.getCpf());
            stmt.setString(3, inquilino.getTelefone1());
            stmt.setString(4, inquilino.getTelefone2());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Inquilino " +
                        "WHERE INQ_Codigo = ?"
            );
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(int id, Inquilino inquilino) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Inquilino " +
                        "SET INQ_Nome = ?, INQ_CPF = ?, INQ_Telefone1 = ?, INQ_Telefone2 = ? " +
                        "WHERE INQ_Codigo = ?"
            );
            stmt.setString(1, inquilino.getNome());
            stmt.setString(2, inquilino.getCpf());
            stmt.setString(3, inquilino.getTelefone1());
            stmt.setString(4, inquilino.getTelefone2());
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public Inquilino findById(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Inquilino " +
                        "WHERE INQ_Codigo = ?"
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
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

    public List<Inquilino> findAll() throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Inquilino " +
                        "ORDER BY INQ_Codigo"
            );
            ResultSet rs = stmt.executeQuery();

            List<Inquilino> inquilinos = new ArrayList<>();
            while(rs.next()) {
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
}
