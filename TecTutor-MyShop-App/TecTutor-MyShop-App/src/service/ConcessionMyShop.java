package service;

import connect.DataConnect;

import java.sql.*;

public class ConcessionMyShop {

    private Connection con;

    public ConcessionMyShop(){

        con = DataConnect.getConnect();
    }

    public boolean calculateConcession(String itemCode) {

        boolean isFound = false;
        String itemName = "";
        double itemPrice = 0.0, concessionPrice = 0.0;
        String itemQuery = "SELECT item_name, item_price FROM item WHERE item_code = ?";

        try {
            CallableStatement itemStatement = con.prepareCall(itemQuery);

            itemStatement.setString(1, itemCode);

            ResultSet resultSet = itemStatement.executeQuery();

            if (resultSet.next()) {
                isFound = true;
                itemName = resultSet.getString("item_name");
                itemPrice = resultSet.getDouble("item_price");
            }
        }
        catch (Exception e) {
            System.out.print("\nError accessing item information with item code input.\n");
            e.printStackTrace();
        }

        String procedureCall = "{ CALL concession_calculate(?, ?) }";
        try {
            CallableStatement getProcedure = con.prepareCall(procedureCall);
            getProcedure.setString(1, itemCode);
            getProcedure.registerOutParameter(2, java.sql.Types.DOUBLE);
            getProcedure.executeUpdate();
            concessionPrice = getProcedure.getDouble(2);
        }
        catch (Exception e){
            System.out.print("\nError trying to call procedure.\n");
            e.printStackTrace();
        }

        System.out.print("\nItem Code: " + itemCode +
                "\nItem Name: " + itemName +
                "\nItem Price: " + itemPrice +
                "\nConcession Price: " + concessionPrice + "\n");

        return isFound;
    }


}
