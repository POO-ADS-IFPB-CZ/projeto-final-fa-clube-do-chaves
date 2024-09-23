package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Manutencao;
import com.poo.aluger.model.Proprietario;

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
                    "INSERT INTO Manutencao(M_Tipo, M_DataInicio, M_DataTermino, M_Custo, M_CodigoImovel) " +
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
    public boolean delete(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Manutencao " +
                        "WHERE M_Codigo = ? AND M_CodigoImovel IN ( " +
                        "    SELECT I_Codigo " +
                        "    FROM Imovel " +
                        "    WHERE I_CodigoProprietario = ? " +
                        ")"
            );
            stmt.setInt(1, id);
            stmt.setInt(2, codigoProprietario);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, int codigoProprietario, Manutencao manutencao) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Manutencao " +
                        "INNER JOIN Imovel ON M_CodigoImovel = I_Codigo " +
                        "SET M_Tipo = ?, M_DataInicio = ?, M_DataTermino = ?, M_Custo = ?, M_CodigoImovel = ? " +
                        "WHERE M_Codigo = ? AND I_CodigoProprietario = ?"
            );
            stmt.setString(1, manutencao.getTipo());
            stmt.setDate(2, java.sql.Date.valueOf(manutencao.getDataInicio()));
            stmt.setDate(3, manutencao.getDataTermino() != null ? java.sql.Date.valueOf(manutencao.getDataTermino()) : null);
            stmt.setDouble(4, manutencao.getCusto());
            stmt.setInt(5, manutencao.getImovel().getCodigo());
            stmt.setInt(6, id);
            stmt.setInt(7, codigoProprietario);

            return stmt.executeUpdate() > 0;
        }
    }

    public Manutencao findById(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Manutencao " +
                        "INNER JOIN Imovel ON M_CodigoImovel = I_Codigo " +
                        "INNER JOIN Proprietario ON I_CodigoProprietario = P_Codigo " +
                        "WHERE M_Codigo = ? AND I_CodigoProprietario = ?"
            );
            stmt.setInt(1, id);
            stmt.setInt(2, codigoProprietario);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                //Dados da Manutencao
                int M_Codigo = rs.getInt("M_Codigo");
                String M_Tipo = rs.getString("M_Tipo");
                LocalDate M_DataInicio = rs.getDate("M_DataInicio").toLocalDate();
                LocalDate M_DataTermino = rs.getDate("M_DataTermino") != null ? rs.getDate("M.DataTermino").toLocalDate() : null;
                double M_Custo = rs.getDouble("M_Custo");

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

                return new Manutencao(M_Codigo, M_Tipo, M_DataInicio, M_DataTermino, M_Custo, imovel);

            }
            return null;
        }
    }

    public List<Manutencao> findAll(int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection = DBConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                        "FROM Manutencao " +
                        "INNER JOIN Imovel ON M_CodigoImovel = I_Codigo " +
                        "INNER JOIN Proprietario ON I_CodigoProprietario = P_Codigo " +
                        "WHERE I_CodigoProprietario = ? " +
                        "ORDER BY M_CodigoImovel"
            );
            stmt.setInt(1, codigoProprietario);

            ResultSet rs = stmt.executeQuery();

            List<Manutencao> manutencoes = new ArrayList<>();
            while(rs.next()) {
                //Dados da Manutencao
                int M_Codigo = rs.getInt("M_Codigo");
                String M_Tipo = rs.getString("M_Tipo");
                LocalDate M_DataInicio = rs.getDate("M_DataInicio").toLocalDate();
                LocalDate M_DataTermino = rs.getDate("M_DataTermino") != null ? rs.getDate("M.DataTermino").toLocalDate() : null;
                double M_Custo = rs.getDouble("M_Custo");

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

                Manutencao manutencao = new Manutencao(M_Codigo, M_Tipo, M_DataInicio, M_DataTermino, M_Custo, imovel);

                manutencoes.add(manutencao);
            }
            return manutencoes;
        }
    }

}
