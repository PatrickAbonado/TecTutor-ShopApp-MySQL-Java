package dao;

import connect.DataConnect;

import dto.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PurchaseDAO {

    private Connection con;
    private PreparedStatement stat;

    public PurchaseDAO(){
        con = DataConnect.getConnect();
    }

    public void insertPurchase(Purchase purchase){

        try{
            stat = con.prepareStatement("insert into purchase(transaction_id, " +
                    "customer_code, item_code, date_of_purchase, quantity) values(?,?,?,?,?)");
            stat.setString(1, purchase.getId());
            stat.setString(2, purchase.getCustomerCode());
            stat.setString(3, purchase.getItemCode());
            stat.setTimestamp(4, purchase.getDateOfPurchase());
            stat.setInt(5, purchase.getQuantity());
            int result = stat.executeUpdate();

            if(result > 0){
                System.out.println("\nData inserted into the purchase table.\n");
            }

        }
        catch (Exception e){
            System.out.println("\nProblem connecting to item table for insert.\n");
            e.printStackTrace();
        }

    }

}
