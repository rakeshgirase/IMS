package com.exuberant.ims.getway;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.list.ListProduct;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.SQL;
import com.exuberant.ims.util.PropertyService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CurrentProductGetway {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String db = PropertyService.getInstance().getProperty("db");
    SQL sql = new SQL();
    public void save(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".Products values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, currentProduct.productId);
            this.pst.setString(3, currentProduct.productName);
            this.pst.setString(4, currentProduct.quantity);
            this.pst.setString(5, currentProduct.description);
            this.pst.setString(6, currentProduct.supplierId);
            this.pst.setString(7, currentProduct.brandId);
            this.pst.setString(8, currentProduct.catagoryId);
            this.pst.setString(9, currentProduct.unitId);
            this.pst.setString(10, currentProduct.pursesPrice);
            this.pst.setString(11, currentProduct.sellPrice);
            this.pst.setString(12, currentProduct.rmaId);
            this.pst.setString(13, currentProduct.userId);
            this.pst.setString(14, currentProduct.date);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");
        }
    }
    public void view(CurrentProduct currentProduct) {
        currentProduct.currentProductList.clear();
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("SELECT SQL_NO_CACHE * FROM " + this.db + ".Products");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void selectedView(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where id=?");
            this.pst.setString(1, currentProduct.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewFistTen(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        currentProduct.currentProductList.clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products limit 0,15");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchView(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        currentProduct.currentProductList.clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where ProductId like ? or ProductName like ?");
            this.pst.setString(1, "%" + currentProduct.productId + "%");
            this.pst.setString(2, "%" + currentProduct.productId + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchBySupplyer(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        currentProduct.currentProductList.clear();
        currentProduct.supplierId = this.sql.getIdNo(currentProduct.supplierName, currentProduct.supplierId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where SupplyerId=?");
            this.pst.setString(1, currentProduct.supplierId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchByBrand(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        currentProduct.currentProductList.clear();
        currentProduct.supplierId = this.sql.getIdNo(currentProduct.supplierName, currentProduct.supplierId, "Supplyer", "SupplyerName");
        currentProduct.brandId = this.sql.getBrandID(currentProduct.supplierId, currentProduct.brandId, currentProduct.brandName);
        System.out.println("Brand ID: " + currentProduct.brandId);
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where BrandId=?");
            this.pst.setString(1, currentProduct.brandId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchByCatagory(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        currentProduct.currentProductList.clear();
        currentProduct.supplierId = this.sql.getIdNo(currentProduct.supplierName, currentProduct.supplierId, "Supplyer", "SupplyerName");
        currentProduct.brandId = this.sql.getBrandID(currentProduct.supplierId, currentProduct.brandId, currentProduct.brandName);
        currentProduct.catagoryId = this.sql.getCatagoryId(currentProduct.supplierId, currentProduct.brandId, currentProduct.catagoryId, currentProduct.catagoryName);
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where CatagoryId=?");
            this.pst.setString(1, currentProduct.catagoryId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchByRMA(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        currentProduct.currentProductList.clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where RMAId=?");
            this.pst.setString(1, currentProduct.rmaId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.currentProductList.addAll(new ListProduct[]{new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sView(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where ProductId=?");
            this.pst.setString(1, currentProduct.productId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.id = this.rs.getString(1);
                currentProduct.productId = this.rs.getString(2);
                currentProduct.productName = this.rs.getString(3);
                currentProduct.quantity = this.rs.getString(4);
                currentProduct.description = this.rs.getString(5);
                currentProduct.supplierId = this.rs.getString(6);
                currentProduct.brandId = this.rs.getString(7);
                currentProduct.catagoryId = this.rs.getString(8);
                currentProduct.unitId = this.rs.getString(9);
                currentProduct.pursesPrice = this.rs.getString(10);
                currentProduct.sellPrice = this.rs.getString(11);
                currentProduct.rmaId = this.rs.getString(12);
                currentProduct.userId = this.rs.getString(13);
                currentProduct.date = this.rs.getString(14);
                currentProduct.supplierName = this.sql.getName(currentProduct.supplierId, currentProduct.supplierName, "Supplyer");
                currentProduct.brandName = this.sql.getName(currentProduct.brandId, currentProduct.brandName, "Brands");
                currentProduct.catagoryName = this.sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "Catagory");
                currentProduct.unitName = this.sql.getName(currentProduct.unitId, currentProduct.unitName, "Unit");
                currentProduct.rmaName = this.sql.getName(currentProduct.rmaId, currentProduct.rmaName, "RMA");
                currentProduct.userName = this.sql.getName(currentProduct.userId, currentProduct.userName, "User");
                currentProduct.rmaDayesss = this.sql.getDayes(currentProduct.rmaDayesss, currentProduct.rmaId);
                long dateRMA = Long.parseLong(currentProduct.rmaDayesss);
                currentProduct.warrentyVoidDate = LocalDate.now().plusDays(dateRMA).toString();
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cbSupplyer(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                currentProduct.supplyerList = this.rs.getString(2);
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("update " + this.db + ".Products set ProductId=?, ProductName=?, Quantity=?, Description=?, SupplyerId=?, BrandId=?, CatagoryId=?, UnitId=?, PursesPrice=?, SellPrice=?, RMAId=?, Date=?  where Id=?");
            this.pst.setString(1, currentProduct.productId);
            this.pst.setString(2, currentProduct.productName);
            this.pst.setString(3, currentProduct.quantity);
            this.pst.setString(4, currentProduct.description);
            this.pst.setString(5, currentProduct.supplierId);
            this.pst.setString(6, currentProduct.brandId);
            this.pst.setString(7, currentProduct.catagoryId);
            this.pst.setString(8, currentProduct.unitId);
            this.pst.setString(9, currentProduct.pursesPrice);
            this.pst.setString(10, currentProduct.sellPrice);
            this.pst.setString(11, currentProduct.rmaId);
            this.pst.setString(12, currentProduct.date);
            this.pst.setString(13, currentProduct.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            this.rs.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Update : update sucess");
            alert.setContentText("Category  '" + currentProduct.productId + "' Update Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("delete from " + this.db + ".Products where id=?");
            this.pst.setString(1, currentProduct.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isNotSoled(CurrentProduct currentProduct) {
        this.con = this.dbCon.getConnection();
        boolean isNotSoled = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Sell where ProductId=?");
            this.pst.setString(1, currentProduct.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Sucess");
                alert.setHeaderText("WARNING : ");
                alert.setContentText("This product has been  soled you can't delete it");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotSoled;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            isNotSoled = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotSoled;
    }
}
