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

public class InquilinoDao implements GenericDao<Inquilino> {
    @Override
    public boolean insert(Inquilino inquilino) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Inquilino(Nome, CPF, Telefone1, Telefone2) " +
                        "VALUES(?, ?, ?, ?)"
            );
            stmt.setString(1, inquilino.getNome());
            stmt.setString(2, inquilino.getCpf());
            stmt.setString(3, inquilino.getTelefone1());
            stmt.setString(4, inquilino.getTelefone2());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Inquilino " +
                        "WHERE Codigo = ?"
            );
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, Inquilino inquilino) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Inquilino " +
                        "SET Nome = ?, CPF = ?, Telefone1 = ?, Telefone2 = ? " +
                        "WHERE Codigo = ?"
            );
            stmt.setString(1, inquilino.getNome());
            stmt.setString(2, inquilino.getCpf());
            stmt.setString(3, inquilino.getTelefone1());
            stmt.setString(4, inquilino.getTelefone2());
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Inquilino findById(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Inquilino " +
                        "WHERE Codigo = ?"
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                String cpf = rs.getString("CPF");
                String telefone1 = rs.getString("Telefone1");
                String telefone2 = rs.getString("Telefone2");

                return new Inquilino(codigo, nome, cpf, telefone1, telefone2);
            }
            return null;
        }
    }

    @Override
    public List<Inquilino> findAll() throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Inquilino"
            );
            ResultSet rs = stmt.executeQuery();

            List<Inquilino> inquilinos = new ArrayList<>();
            while(rs.next()) {
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                String cpf = rs.getString("CPF");
                String telefone1 = rs.getString("Telefone1");
                String telefone2 = rs.getString("Telefone2");

                inquilinos.add(new Inquilino(codigo, nome, cpf, telefone1, telefone2));
            }
            return inquilinos;
        }
    }
}
