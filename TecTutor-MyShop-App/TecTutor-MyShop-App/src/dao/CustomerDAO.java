package dao;

import connect.DataConnect;
import dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {

    private Connection con;
    private PreparedStatement stat;

    public CustomerDAO(){

        con = DataConnect.getConnect();
    }

    public void insertCustomer(Customer customer){

        try{
            stat = con.prepareStatement("insert into customer(customer_code, customer_name, phone_number, address) values(?,?,?,?)");
            stat.setString(1, customer.getCustomerCode());
            stat.setString(2, customer.getCustomerName());
            stat.setString(3, customer.getPhone());
            stat.setString(4, customer.getAddress());
            int result = stat.executeUpdate();

            if(result > 0){
                System.out.println("\nData inserted into the customer table.\n");
            }

        }
        catch (Exception e){
            System.out.println("\nProblem connecting to customer table for insert.\n");
            e.printStackTrace();
        }

    }

    public String getCustomerName(String code){

        String name = "";

        try {
            PreparedStatement stat;
            Connection con = DataConnect.getConnect();
            stat = con.prepareStatement("select * from customer");
            ResultSet resultSet = stat.executeQuery();

            while (resultSet.next()){

                if(resultSet.getString(1).equalsIgnoreCase(code)){

                    name = resultSet.getString(2);

                }
            }

        }
        catch (Exception e){
            System.out.print("\nSql or something error trying to get customer name.\n");
            e.printStackTrace();
        }

        return name;
    }

}
