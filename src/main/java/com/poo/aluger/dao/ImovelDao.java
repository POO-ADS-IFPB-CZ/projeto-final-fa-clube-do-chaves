package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Proprietario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImovelDao implements GenericDao<Imovel> {
    @Override
    public boolean insert(Imovel imovel) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Imovel(Foto, Rua, Numero, Bairro, Cidade, Estado, Tipo, AreaTotal, QtdQuartos, " +
                            "Status, QtdBanheiros, Descricao, CodigoProprietario) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setBytes(1, imovel.getFoto());
            stmt.setString(2,imovel.getRua());
            stmt.setInt(3, imovel.getNumero());
            stmt.setString(4, imovel.getBairro());
            stmt.setString(5, imovel.getCidade());
            stmt.setString(6, imovel.getEstado());
            stmt.setString(7, imovel.getTipo());
            stmt.setDouble(8, imovel.getAreaTotal());
            stmt.setInt(9, imovel.getQtdQuartos());
            stmt.setString(10, imovel.getStatus());
            stmt.setInt(11, imovel.getQtdBanheiros());
            stmt.setString(12, imovel.getDescricao());
            stmt.setInt(13, imovel.getProprietario().getCodigo());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Imovel " +
                        "WHERE Codigo = ?"
            );
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, Imovel imovel) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Imovel " +
                        "SET Foto = ?, Rua = ?, Numero = ?, Bairro = ?, Cidade = ?, Estado = ?, Tipo = ?," +
                            "AreaTotal = ?, QtdQuartos = ?, Status = ?, QtdBanheiros = ?, " +
                            "Descricao = ?, CodigoProprietario = ? " +
                        "WHERE Codigo = ?"
            );
            stmt.setBytes(1, imovel.getFoto());
            stmt.setString(2,imovel.getRua());
            stmt.setInt(3, imovel.getNumero());
            stmt.setString(4, imovel.getBairro());
            stmt.setString(5, imovel.getCidade());
            stmt.setString(6, imovel.getEstado());
            stmt.setString(7, imovel.getTipo());
            stmt.setDouble(8, imovel.getAreaTotal());
            stmt.setInt(9, imovel.getQtdQuartos());
            stmt.setString(10, imovel.getStatus());
            stmt.setInt(11, imovel.getQtdBanheiros());
            stmt.setString(12, imovel.getDescricao());
            stmt.setInt(13, imovel.getProprietario().getCodigo());
            stmt.setInt(14, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public Imovel findById(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Imovel " +
                        "WHERE Codigo = ? AND CodigoProprietario = ?"
            );
            stmt.setInt(1, id);
            stmt.setInt(2, codigoProprietario);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                int codigo = rs.getInt("Codigo");
                byte[] foto = rs.getBytes("Foto");
                String rua = rs.getString("Rua");
                int numero = rs.getInt("Numero");
                String bairro = rs.getString("Bairro");
                String cidade = rs.getString("Cidade");
                String estado = rs.getString("Estado");
                String tipo = rs.getString("Tipo");
                double areaTotal = rs.getDouble("AreaTotal");
                int qtdQuartos = rs.getInt("QtdQuartos");
                String status = rs.getString("Status");
                int qtdBanheiros = rs.getInt("QtdBanheiros");
                String descricao = rs.getString("Descricao");

                Proprietario proprietario = new ProprietarioDao().findById(codigoProprietario);

                return new Imovel(codigo, foto, rua, numero, bairro, cidade, estado, tipo, areaTotal, qtdQuartos,
                        status, qtdBanheiros, descricao, proprietario);
            }
            return null;
        }
    }

    public List<Imovel> findAllByProprietario(int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Imovel " +
                        "WHERE CodigoProprietario = ? " +
                        "ORDER BY Codigo"
            );
            stmt.setInt(1, codigoProprietario);
            ResultSet rs = stmt.executeQuery();

            ProprietarioDao proprietarioDao = new ProprietarioDao();
            Proprietario proprietario = proprietarioDao.findById(codigoProprietario);

            List<Imovel> imoveis = new ArrayList<>();
            while(rs.next()) {
                int codigo = rs.getInt("Codigo");
                byte[] foto = rs.getBytes("Foto");
                String rua = rs.getString("Rua");
                int numero = rs.getInt("Numero");
                String bairro = rs.getString("Bairro");
                String cidade = rs.getString("Cidade");
                String estado = rs.getString("Estado");
                String tipo = rs.getString("Tipo");
                double areaTotal = rs.getDouble("AreaTotal");
                int qtdQuartos = rs.getInt("QtdQuartos");
                String status = rs.getString("Status");
                int qtdBanheiros = rs.getInt("QtdBanheiros");
                String descricao = rs.getString("Descricao");

                Imovel imovel = new Imovel(
                        codigo,
                        foto,
                        rua,
                        numero,
                        bairro,
                        cidade,
                        estado,
                        tipo,
                        areaTotal,
                        qtdQuartos,
                        status,
                        qtdBanheiros,
                        descricao,
                        proprietario
                );

                imoveis.add(imovel);
            }

            return imoveis;
        }
    }

}
