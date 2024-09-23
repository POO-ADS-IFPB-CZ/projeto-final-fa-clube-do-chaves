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
                    "INSERT INTO Imovel(I_Foto, I_Rua, I_Numero, I_Bairro, I_Cidade, I_Estado, I_Tipo, I_AreaTotal, I_QtdQuartos, " +
                            "I_Status, I_QtdBanheiros, I_Descricao, I_CodigoProprietario) " +
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
    public boolean delete(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Imovel " +
                        "WHERE I_Codigo = ? AND I_CodigoProprietario = ?"
            );
            stmt.setInt(1, id);
            stmt.setInt(2, codigoProprietario);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, int codigoProprietario, Imovel imovel) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Imovel " +
                        "SET I_Foto = ?, I_Rua = ?, I_Numero = ?, I_Bairro = ?, I_Cidade = ?, I_Estado = ?, I_Tipo = ?," +
                            "I_AreaTotal = ?, I_QtdQuartos = ?, I_Status = ?, I_QtdBanheiros = ?, " +
                            "I_Descricao = ?, I_CodigoProprietario = ? " +
                        "WHERE I_Codigo = ? AND I_CodigoProprietario = ?"
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
            stmt.setInt(15, codigoProprietario);

            return stmt.executeUpdate() > 0;
        }
    }

    public Imovel findById(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Imovel " +
                        "INNER JOIN Proprietario ON I_CodigoProprietario = P_Codigo " +
                        "WHERE I_Codigo = ? AND I_CodigoProprietario = ?"
            );
            stmt.setInt(1, id);
            stmt.setInt(2, codigoProprietario);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                //Dados do Imovel
                int I_Codigo = rs.getInt("I_Codigo");
                byte[] I_Foto = rs.getBytes("I_Foto");
                String I_Rua = rs.getString("I_Rua");
                int I_Numero = rs.getInt("I_Numero");
                String I_Bairro = rs.getString("I_Bairro");
                String I_Cidade = rs.getString("I_Cidade");
                String I_Estado = rs.getString("I_Estado");
                String I_TipoImovel = rs.getString("I_Tipo");
                double I_AreaTotal = rs.getDouble("I_AreaTotal");
                int I_QtdQuartos = rs.getInt("I_QtdQuartos");
                String I_Status = rs.getString("I_Status");
                int I_QtdBanheiros = rs.getInt("I_QtdBanheiros");
                String I_Descricao = rs.getString("I_Descricao");

                //Dados do Proprietario
                int P_Codigo = rs.getInt("P_Codigo");
                String P_Nome = rs.getString("P_Nome");
                String P_Email = rs.getString("P_Email");
                String P_Senha = rs.getString("P_Senha");

                Proprietario proprietario = new Proprietario(P_Codigo, P_Nome, P_Email, P_Senha);

                return new Imovel(I_Codigo, I_Foto, I_Rua, I_Numero, I_Bairro, I_Cidade, I_Estado,
                        I_TipoImovel, I_AreaTotal, I_QtdQuartos, I_Status, I_QtdBanheiros, I_Descricao, proprietario);
            }
            return null;
        }
    }

    public List<Imovel> findAll(int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Imovel " +
                        "INNER JOIN Proprietario ON I_CodigoProprietario = P_Codigo " +
                        "WHERE I_CodigoProprietario = ? " +
                        "ORDER BY I_Codigo"
            );
            stmt.setInt(1, codigoProprietario);
            ResultSet rs = stmt.executeQuery();

            List<Imovel> imoveis = new ArrayList<>();
            while(rs.next()) {
                //Dados do Imovel
                int I_Codigo = rs.getInt("I_Codigo");
                byte[] I_Foto = rs.getBytes("I_Foto");
                String I_Rua = rs.getString("I_Rua");
                int I_Numero = rs.getInt("I_Numero");
                String I_Bairro = rs.getString("I_Bairro");
                String I_Cidade = rs.getString("I_Cidade");
                String I_Estado = rs.getString("I_Estado");
                String I_TipoImovel = rs.getString("I_Tipo");
                double I_AreaTotal = rs.getDouble("I_AreaTotal");
                int I_QtdQuartos = rs.getInt("I_QtdQuartos");
                String I_Status = rs.getString("I_Status");
                int I_QtdBanheiros = rs.getInt("I_QtdBanheiros");
                String I_Descricao = rs.getString("I_Descricao");

                //Dados do Proprietario
                int P_Codigo = rs.getInt("P_Codigo");
                String P_Nome = rs.getString("P_Nome");
                String P_Email = rs.getString("P_Email");
                String P_Senha = rs.getString("P_Senha");

                Proprietario proprietario = new Proprietario(P_Codigo, P_Nome, P_Email, P_Senha);

                Imovel imovel = new Imovel(I_Codigo, I_Foto, I_Rua, I_Numero, I_Bairro, I_Cidade, I_Estado,
                        I_TipoImovel, I_AreaTotal, I_QtdQuartos, I_Status, I_QtdBanheiros, I_Descricao, proprietario);

                imoveis.add(imovel);
            }

            return imoveis;
        }
    }

}
