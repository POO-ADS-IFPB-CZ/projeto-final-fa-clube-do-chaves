package com.poo.aluger.dao;

import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Inquilino;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.ImageConverter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratoAluguelDao implements GenericDao<ContratoAluguel> {

  public boolean insert(ContratoAluguel contratoAluguel) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO ContratoAluguel(CA_DataInicio, CA_DataTermino, CA_ValorAluguel, CA_DiaPagamento, " +
              "CA_CodigoProprietario, CA_CodigoInquilino, CA_CodigoImovel) " +
              "VALUES(?, ?, ?, ?, ?, ?, ?)");
      stmt.setDate(1, java.sql.Date.valueOf(contratoAluguel.getDataInicio()));
      stmt.setDate(2,
          contratoAluguel.getDataTermino() != null ? java.sql.Date.valueOf(contratoAluguel.getDataTermino()) : null);
      stmt.setDouble(3, contratoAluguel.getValorAluguel());
      stmt.setDate(4, java.sql.Date.valueOf(contratoAluguel.getDiaPagamento()));
      stmt.setInt(5, contratoAluguel.getProprietario().getCodigo());
      stmt.setInt(6, contratoAluguel.getInquilino().getCodigo());
      stmt.setInt(7, contratoAluguel.getImovel().getCodigo());

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public boolean delete(int id, int codigoProprietario) throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "DELETE FROM ContratoAluguel " +
              "WHERE CA_Codigo = ? AND CA_CodigoProprietario = ?");
      stmt.setInt(1, id);
      stmt.setInt(2, codigoProprietario);

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public boolean update(int id, int codigoProprietario, ContratoAluguel contratoAluguel)
      throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "UPDATE ContratoAluguel " +
              "SET CA_DataInicio = ?, CA_DataTermino = ?, CA_ValorAluguel = ?, CA_DiaPagamento = ?, CA_CodigoProprietario = ?, CA_CodigoInquilino = ?, CA_CodigoImovel = ? "
              +
              "WHERE CA_Codigo = ? AND CA_CodigoProprietario = ?");
      stmt.setDate(1, Date.valueOf(contratoAluguel.getDataInicio()));
      stmt.setDate(2, contratoAluguel.getDataTermino() != null ? Date.valueOf(contratoAluguel.getDataTermino()) : null);
      stmt.setDouble(3, contratoAluguel.getValorAluguel());
      stmt.setDate(4, Date.valueOf(contratoAluguel.getDiaPagamento()));
      stmt.setInt(5, contratoAluguel.getProprietario().getCodigo());
      stmt.setInt(6, contratoAluguel.getInquilino().getCodigo());
      stmt.setInt(7, contratoAluguel.getImovel().getCodigo());
      stmt.setInt(8, id);
      stmt.setInt(9, codigoProprietario);

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public ContratoAluguel findById(int id, int codigoProprietario)
      throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM ContratoAluguel " +
              "INNER JOIN Imovel ON CA_CodigoImovel = I_Codigo " +
              "INNER JOIN Inquilino ON CA_CodigoInquilino = INQ_Codigo " +
              "INNER JOIN Proprietario ON CA_CodigoProprietario = P_Codigo " +
              "WHERE CA_Codigo = ? AND CA_CodigoProprietario = ?");
      stmt.setInt(1, id);
      stmt.setInt(2, codigoProprietario);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        // Dados do Contrato Aluguel
        int CA_Codigo = rs.getInt("CA_Codigo");
        LocalDate CA_DataInicio = rs.getDate("CA_DataInicio").toLocalDate();
        LocalDate CA_DataTermino = rs.getDate("CA_DataTermino") != null ? rs.getDate("CA_DataTermino").toLocalDate()
            : null;
        double CA_ValorAluguel = rs.getDouble("CA_ValorAluguel");
        LocalDate CA_DiaPagamento = rs.getDate("CA_DiaPagamento").toLocalDate();
        int CA_CodigoInquilino = rs.getInt("CA_CodigoInquilino");
        int CA_CodigoImovel = rs.getInt("CA_CodigoImovel");

        // Dados do Imovel
        int I_Codigo = rs.getInt("I_Codigo");
        byte[] I_Foto = rs.getBytes("I_Foto");
        BufferedImage image = ImageConverter.convertBytesToImage(I_Foto);
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

        // Dados do Proprietario
        int P_Codigo = rs.getInt("P_Codigo");
        String P_Nome = rs.getString("P_Nome");
        String P_Email = rs.getString("P_Email");
        String P_Senha = rs.getString("P_Senha");

        // Dados do Inquilino
        int INQ_Codigo = rs.getInt("INQ_Codigo");
        String INQ_Nome = rs.getString("INQ_Nome");
        String INQ_Cpf = rs.getString("INQ_CPF");
        String INQ_Telefone1 = rs.getString("INQ_Telefone1");
        String INQ_Telefone2 = rs.getString("INQ_Telefone2");

        Proprietario proprietario = new Proprietario(P_Codigo, P_Nome, P_Email, P_Senha);
        Imovel imovel = new Imovel(I_Codigo, image, I_Rua, I_Numero, I_Bairro, I_Cidade, I_Estado, I_TipoImovel,
            I_AreaTotal, I_QtdQuartos, I_Status, I_QtdBanheiros, I_Descricao, proprietario);
        Inquilino inquilino = new Inquilino(INQ_Codigo, INQ_Nome, INQ_Cpf, INQ_Telefone1, INQ_Telefone2);

        return new ContratoAluguel(CA_DataInicio, CA_DataTermino, CA_ValorAluguel, CA_DiaPagamento, inquilino, imovel,
            proprietario);
      }
      return null;
    }
  }

  @Override
  public List<ContratoAluguel> findAll(int codigoProprietario)
      throws SQLException, IOException, ClassNotFoundException {
    try (Connection connection = DBConnector.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(
          "SELECT * " +
              "FROM ContratoAluguel " +
              "INNER JOIN Imovel ON CA_CodigoImovel = I_Codigo " +
              "INNER JOIN Inquilino ON CA_CodigoInquilino = INQ_Codigo " +
              "INNER JOIN Proprietario ON CA_CodigoProprietario = P_Codigo " +
              "WHERE CA_CodigoProprietario = ?");
      stmt.setInt(1, codigoProprietario);

      ResultSet rs = stmt.executeQuery();

      List<ContratoAluguel> contratos = new ArrayList<>();
      while (rs.next()) {
        // Dados do Contrato Aluguel
        int CA_Codigo = rs.getInt("CA_Codigo");
        LocalDate CA_DataInicio = rs.getDate("CA_DataInicio").toLocalDate();
        LocalDate CA_DataTermino = rs.getDate("CA_DataTermino") != null ? rs.getDate("CA_DataTermino").toLocalDate()
            : null;
        double CA_ValorAluguel = rs.getDouble("CA_ValorAluguel");
        LocalDate CA_DiaPagamento = rs.getDate("CA_DiaPagamento").toLocalDate();
        int CA_CodigoInquilino = rs.getInt("CA_CodigoInquilino");
        int CA_CodigoImovel = rs.getInt("CA_CodigoImovel");

        // Dados do Imovel
        int I_Codigo = rs.getInt("I_Codigo");
        byte[] I_Foto = rs.getBytes("I_Foto");
        BufferedImage image = ImageConverter.convertBytesToImage(I_Foto);
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

        // Dados do Proprietario
        int P_Codigo = rs.getInt("P_Codigo");
        String P_Nome = rs.getString("P_Nome");
        String P_Email = rs.getString("P_Email");
        String P_Senha = rs.getString("P_Senha");

        // Dados do Inquilino
        int INQ_Codigo = rs.getInt("INQ_Codigo");
        String INQ_Nome = rs.getString("INQ_Nome");
        String INQ_Cpf = rs.getString("INQ_CPF");
        String INQ_Telefone1 = rs.getString("INQ_Telefone1");
        String INQ_Telefone2 = rs.getString("INQ_Telefone2");

        Proprietario proprietario = new Proprietario(P_Codigo, P_Nome, P_Email, P_Senha);
        Imovel imovel = new Imovel(I_Codigo, image, I_Rua, I_Numero, I_Bairro, I_Cidade, I_Estado, I_TipoImovel,
            I_AreaTotal, I_QtdQuartos, I_Status, I_QtdBanheiros, I_Descricao, proprietario);
        Inquilino inquilino = new Inquilino(INQ_Codigo, INQ_Nome, INQ_Cpf, INQ_Telefone1, INQ_Telefone2);

        ContratoAluguel contrato = new ContratoAluguel(CA_DataInicio, CA_DataTermino, CA_ValorAluguel, CA_DiaPagamento,
            inquilino, imovel, proprietario);
        contratos.add(contrato);
      }
      return contratos;
    }
  }
}
