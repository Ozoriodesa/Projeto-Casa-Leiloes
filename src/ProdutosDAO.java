/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;

    public class ProdutosDAO{

        public ArrayList<ProdutosDTO> listarProdutos() {

            ArrayList<ProdutosDTO> lista = new ArrayList<>();
            Connection con = conectaDAO.getConnection();

            if (con == null) {
                System.out.println("Conexão NULL na listagem");
                return lista;
            }

            try {
                String sql = "SELECT * FROM produtos";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    ProdutosDTO p = new ProdutosDTO();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setValor(rs.getDouble("valor"));
                    p.setStatus(rs.getString("status"));
                    lista.add(p);
                }

            } catch (SQLException e) {
                System.out.println("Erro ao listar: " + e.getMessage());
            }

            return lista;
        }


        public boolean cadastrarProduto(ProdutosDTO produto) {

            Connection con = conectaDAO.getConnection();

            if (con == null) {
                System.out.println("Conexão NULL no DAO");
                return false;
            }
            try {    
                String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, produto.getNome());
                pst.setDouble(2, produto.getValor());
                pst.setString(3, produto.getStatus());
                pst.execute();

                return true;

            } catch (SQLException e) {
                System.out.println("Erro SQL: " + e.getMessage());
                return false;
            }
        }

        public boolean venderProduto(int id_produto_venda) {

            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

            try (Connection conn = conectaDAO.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id_produto_venda);
                stmt.executeUpdate();
                return true;

            } catch (SQLException e) {
                System.out.println("Erro ao vender produto: " + e.getMessage());
                return false;
            }
        }
       
         
        
      
    }

    

