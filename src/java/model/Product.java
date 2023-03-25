
package model;

import javax.servlet.http.Part; //for image

/**
 *
 * @author Ashi
 */
public class Product implements java.io.Serializable{//serialization converts an obj into byte stream and vise versa
    private int autoindex;
    private String productName;
    private double productPrice;
    private int productQty;
    private String productDesc;
    private Part productImg;

    public Product(){
        
    }
    
    
public Product(int autoindex, String prodName, double prodPrice, int prodQty, String prodDesc){
    this.autoindex = autoindex;
    this.productName = prodName;
    this.productPrice = prodPrice;
    this.productQty = prodQty;
    this.productDesc = prodDesc;        
}
    
    public int getAutoindex() {
        return autoindex;
    }

    public void setAutoindex(int autoindex) {
        this.autoindex = autoindex;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Part getProductImg() {
        return productImg;
    }

    public void setProductImg(Part productImg) {
        this.productImg = productImg;
    }
    
}