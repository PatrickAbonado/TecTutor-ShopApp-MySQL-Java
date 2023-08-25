package dao;

import connect.DataConnect;

import dto.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {

    private Connection con;
    private PreparedStatement stat;

    public ItemDAO(){
        con = DataConnect.getConnect();
    }

    public void insertItem(Item item){

        try{
            stat = con.prepareStatement("insert into item(item_code, item_name, item_price, quantity) values(?,?,?,?)");
            stat.setString(1, item.getItemCode());
            stat.setString(2, item.getItemName());
            stat.setDouble(3, item.getItemPrice());
            stat.setInt(4, item.getQuantity());
            int result = stat.executeUpdate();

            if(result > 0){
                System.out.println("\nData inserted into the item table.\n");
            }

        }
        catch (Exception e){
            System.out.println("\nProblem connecting to item table for insert.\n");
            e.printStackTrace();
        }

    }

    public String getItemName(String code){

        String name = "";

        try {
            PreparedStatement stat;
            Connection con = DataConnect.getConnect();
            stat = con.prepareStatement("select * from item");
            ResultSet resultSet = stat.executeQuery();

            while (resultSet.next()){

                if(resultSet.getString(1).equalsIgnoreCase(code)){

                    name = resultSet.getString(2);

                }
            }

        }
        catch (Exception e){
            System.out.print("\nSql or something error trying to get item name.\n");
            e.printStackTrace();
        }

        return  name;
    }


}
