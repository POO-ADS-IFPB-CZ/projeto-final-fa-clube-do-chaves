package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Manutencao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDao implements GenericDao<Manutencao> {

    @Override
    public boolean insert(Manutencao manutencao) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Manutencao(Tipo, DataInicio, DataTermino, Custo, CodigoImovel) " +
                        "VALUES(?, ?, ?, ?, ?)"
            );

            stmt.setString(1, manutencao.getTipo());
            stmt.setDate(2, java.sql.Date.valueOf(manutencao.getDataInicio()));
            stmt.setDate(3, manutencao.getDataTermino() != null ? java.sql.Date.valueOf(manutencao.getDataTermino()) : null);
            stmt.setDouble(4, manutencao.getCusto());
            stmt.setInt(5, manutencao.getImovel().getCodigo());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Manutencao " +
                        "WHERE Codigo = ?"
            );
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, Manutencao manutencao) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Manutencao " +
                        "SET Tipo = ?, DataInicio = ?, DataTermino = ?, Custo = ?, CodigoImovel = ? " +
                        "WHERE Codigo = ?"
            );
            stmt.setString(1, manutencao.getTipo());
            stmt.setDate(2, java.sql.Date.valueOf(manutencao.getDataInicio()));
            stmt.setDate(3, manutencao.getDataTermino() != null ? java.sql.Date.valueOf(manutencao.getDataTermino()) : null);
            stmt.setDouble(4, manutencao.getCusto());
            stmt.setInt(5, manutencao.getImovel().getCodigo());
            stmt.setInt(6, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public Manutencao findById(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Manutencao M " +
                        "INNER JOIN Imovel I ON M.CodigoImovel = I.Codigo " +
                        "WHERE M.Codigo = ? AND I.CodigoProprietario = ?"
            );
            stmt.setInt(1, id);
            stmt.setInt(2, codigoProprietario);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int codigo = rs.getInt("Codigo");
                String tipo = rs.getString("Tipo");
                LocalDate dataInicio = rs.getDate("DataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("DataTermino") != null ? rs.getDate("DataTermino").toLocalDate() : null;
                double custo = rs.getDouble("Custo");
                int codigoImovel = rs.getInt("CodigoImovel");

                ImovelDao imovelDao = new ImovelDao();
                Imovel imovel = imovelDao.findById(codigoImovel, codigoProprietario);

                return new Manutencao(codigo, tipo, dataInicio, dataTermino, custo, imovel);

            }
            return null;
        }
    }

    public List<Manutencao> findAllByProprietario(int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT M.* " +
                        "FROM Manutencao M " +
                        "INNER JOIN Imovel I ON M.CodigoImovel = I.Codigo " +
                        "WHERE I.CodigoProprietario = ? " +
                        "ORDER BY M.Codigo"
            );
            stmt.setInt(1, codigoProprietario);
            ResultSet rs = stmt.executeQuery();

            ImovelDao imovelDao = new ImovelDao();

            List<Manutencao> manutencoes = new ArrayList<>();
            while(rs.next()) {
                int codigo = rs.getInt("Codigo");
                String tipo = rs.getString("Tipo");
                LocalDate dataInicio = rs.getDate("DataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("DataTermino") != null ? rs.getDate("DataTermino").toLocalDate() : null;
                double custo = rs.getDouble("Custo");
                int codigoImovel = rs.getInt("CodigoImovel");

                Imovel imovel = imovelDao.findById(codigoImovel, codigoProprietario);

                Manutencao manutencao = new Manutencao(codigo, tipo, dataInicio, dataTermino, custo, imovel);

                manutencoes.add(manutencao);
            }
            return manutencoes;
        }
    }

}
