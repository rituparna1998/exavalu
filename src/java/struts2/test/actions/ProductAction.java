/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts2.test.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import struts2.test.beans.Product;
import struts2.test.dao.Admin;

/**
 *
 * @author ritup
 */
public class ProductAction extends ActionSupport{
    private int productId;
    private String productName;
    private String productMake;
    private double price;
    private int availability;
    private boolean status;
    
    
    private String msg = "";
    private Admin admin = null;
    private int ctr = 0;
    ResultSet rs = null;
     
    Product product = null;
    private List<Product> productList = null;
    private boolean noData = false;
    private String submitType;
    
    
    public String addProducts()
    {
        setAdmin(new Admin());
        try {
            setCtr(getAdmin().addProduct(getProductName(), getProductMake(), getPrice(), getAvailability()));
            if (getCtr() > 0) {
                setMsg("Product added Successfully");
            } else {
                setMsg("Some error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ADDPRODUCT";
    }
    
    public String showProducts()
    {
        admin = new Admin();
        try {
            setProductList(new ArrayList<>());
            setProductList(admin.reportproduct());
            

            if (!productList.isEmpty() ) {
                setNoData(false);
                System.out.println("Products retrieve = "+getProductList().size());
                System.out.println("setting nodata=false");
            } else {
                setNoData(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "REPORTPRODUCT";
    }
    
    public String updateProducts()
    {
        Admin dao=new Admin();
         try {
            if (getSubmitType().equals("updateproductdata")) {
                Product product = dao.fetchProductDetails(productId);
                if (product != null) {
                    productId = product.getProductId();
                    productName = product.getProductName();
                    productMake = product.getProductMake();
                    price = product.getPrice();
                    availability = product.getAvailability();
                   
                }
            } else {
                int i = dao.updateProductDetails(productId, productName, productMake, price, availability);
                if (i > 0) {
                    setMsg("Product Updated Successfuly");
                } else {
                    setMsg("error");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "UPDATEPRODUCT";
    }

    public String deleteProducts()
    {
        Admin dao=new Admin();
        try {
            int isDeleted = dao.deleteProduct(getProductId());
            if (isDeleted > 0) {
                setMsg("Record deleted successfully");
            } else {
                setMsg("Some error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "DELETEPRODUCT";
    }
    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productMake
     */
    public String getProductMake() {
        return productMake;
    }

    /**
     * @param productMake the productMake to set
     */
    public void setProductMake(String productMake) {
        this.productMake = productMake;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the availability
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the admin
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * @return the ctr
     */
    public int getCtr() {
        return ctr;
    }

    /**
     * @param ctr the ctr to set
     */
    public void setCtr(int ctr) {
        this.ctr = ctr;
    }

    /**
     * @return the productList
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * @return the noData
     */
    public boolean isNoData() {
        return noData;
    }

    /**
     * @param noData the noData to set
     */
    public void setNoData(boolean noData) {
        this.noData = noData;
    }

    /**
     * @return the submitType
     */
    public String getSubmitType() {
        return submitType;
    }

    /**
     * @param submitType the submitType to set
     */
    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }
    
}
