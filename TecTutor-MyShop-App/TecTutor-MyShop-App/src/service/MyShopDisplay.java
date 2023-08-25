package service;

import connect.DataConnect;

import dao.CustomerDAO;
import dao.ItemDAO;
import dao.PurchaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MyShopDisplay {


    private CustomerDAO customerDAO;
    private ItemDAO itemDAO;
    private PurchaseDAO purchaseDAO;
    private Scanner s;

    public MyShopDisplay(){
        customerDAO = new CustomerDAO();
        itemDAO = new ItemDAO();
        purchaseDAO = new PurchaseDAO();
        s = new Scanner(System.in);
    }


    public boolean customerDetails(String custId){

        boolean isFound = false;

        try {
            PreparedStatement stat;
            Connection con = DataConnect.getConnect();
            stat = con.prepareStatement("select * from customer");
            ResultSet resultSet = stat.executeQuery();

            while (resultSet.next()){

                if(resultSet.getString(1).equalsIgnoreCase(custId)){
                    isFound = true;
                    System.out.print("\n*** Customer #" + custId + " ***\n" +
                            "Customer Name: " + resultSet.getString(2) +
                    "\nCustomer Phone: " + resultSet.getString(3)
                    + "\nCustomer Address: " + resultSet.getString(4) + "\n");

                }
            }

        }
        catch (Exception e){
            System.out.print("\nSql or something error trying to display customer details.");
            e.printStackTrace();
        }

        return isFound;
    }


    public boolean diplayAllItems(){
        int counter = 0;

        try {
            PreparedStatement stat;
            Connection con = DataConnect.getConnect();
            stat = con.prepareStatement("select * from item");
            ResultSet resultSet = stat.executeQuery();

            while (resultSet.next()){

                ++counter;

                System.out.print("\n*** All Items ***\n" +
                        "\nItem Code: " + resultSet.getString(1) +
                        "\nItem Name: " + resultSet.getString(2) +
                        "\nItem Price: " + resultSet.getDouble(3) +
                        "\nItem Quantity: " + resultSet.getInt(4) + "\n");

            }

        }
        catch (Exception e){
            System.out.print("\nSql or something error trying to display all items details.");
            e.printStackTrace();
        }

        if(counter > 0){
            return true;
        }

        return false;
    }

public boolean purchaseDetails(String customer_id){

        boolean isFound = false;

    try {
        PreparedStatement stat;
        Connection con = DataConnect.getConnect();
        stat = con.prepareStatement("select * from purchase");
        ResultSet resultSet = stat.executeQuery();

        while (resultSet.next()){

            if(resultSet.getString(2).equalsIgnoreCase(customer_id)){
                isFound = true;
                System.out.print("\nCustomer Name: " + customerDAO.getCustomerName(customer_id) +
                        "\nItem Name: " + itemDAO.getItemName(resultSet.getString(3))
                        + "\nPurchase Quantity: " + resultSet.getInt(5) + "\n");

            }
        }

    }
    catch (Exception e){
        System.out.print("\nSql or something error trying to display customer details.");
        e.printStackTrace();
    }

    return isFound;
}


}
