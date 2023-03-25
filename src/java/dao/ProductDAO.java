package dao;

import database.DBConnection;
import static database.DBConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

/**
 *
 * @author Ashi
 */
public class ProductDAO {
  private final Connection con;  
  
  public ProductDAO() throws SQLException, ClassNotFoundException{
      con= DBConnection.getConnection();
  }
  
  public List<Product> retrieveAllProduct() throws ClassNotFoundException,SQLException{
      List<Product> product = new ArrayList<>();
      String mySQLQuery ="select * from tblproduct";
      
      try(Connection connection = getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(mySQLQuery);){
          System.out.print(preparedStatement);
          
          ResultSet rs = preparedStatement.executeQuery();
          
          while(rs.next()){
              int autoindex = rs.getInt("AutoIndex");
              String prodName = rs.getString("ProductName");
              Double prodPrice = rs.getDouble("ProductPrice");
              int prodQty = rs.getInt("ProductQty");
              String prodDesc = rs.getString("ProductDesc");
              
              product.add(new Product(autoindex, prodName, prodPrice, prodQty, prodDesc));
               }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return product;
    }
  }


