package service;

import dao.CustomerDAO;
import dao.ItemDAO;
import dao.PurchaseDAO;

import dto.Customer;
import dto.Item;
import dto.Purchase;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class MyShopUpload {

    private CustomerDAO customerDAO;
    private ItemDAO itemDAO;
    private PurchaseDAO purchaseDAO;
    private Scanner s;

    public MyShopUpload(){
        customerDAO = new CustomerDAO();
        itemDAO = new ItemDAO();
        purchaseDAO = new PurchaseDAO();
        s = new Scanner(System.in);
    }


    public void storeCustomerDetails(){

        int numCustomers = 0;

        while(true){
            s = new Scanner(System.in);

            System.out.print("How many customers would you like to enter? ");
            try{
                numCustomers = s.nextInt();
                break;
            }
            catch (Exception e){
                System.out.println("\nEnter in only valid integer input.\n");
            }
        }

        for(int i = 1; i <= numCustomers; ++i){
            Customer newCustomer = new Customer();
            s = new Scanner(System.in);

            System.out.print("Enter Customer Code: ");
            newCustomer.setCustomerCode(s.next());

            System.out.print("Enter Customer Name: ");
            newCustomer.setCustomerName(s.next());

            s = new Scanner(System.in);
            System.out.print("Enter Customer Phone: ");
            newCustomer.setPhone(s.nextLine());

            s= new Scanner(System.in);
            System.out.print("Enter Customer Address: ");
            newCustomer.setAddress(s.nextLine());

            customerDAO.insertCustomer(newCustomer);

        }

    }

    public void storeItemDetails(){

        int numItems = 0;

        while(true){
            s = new Scanner(System.in);

            System.out.print("How many items would you like to enter? ");
            try{
                numItems = s.nextInt();
                break;
            }
            catch (Exception e){
                System.out.println("\nEnter in only valid integer input.\n");
            }
        }

        for(int i = 1; i <= numItems; ++i){
            s = new Scanner(System.in);
            Item newItem = new Item();

            System.out.print("\nEnter Item Code: ");
            newItem.setItemCode(s.next());

            s = new Scanner(System.in);
            System.out.print("\nEnter Item Name: ");
            newItem.setItemName(s.nextLine());

            s = new Scanner(System.in);
            System.out.print("\nEnter Item Price: ");
            newItem.setItemPrice(s.nextDouble());

            System.out.print("\nEnter Item Quantity ");
            newItem.setQuantity(s.nextInt());

            itemDAO.insertItem(newItem);

        }

    }

    public void purchaseDetails(){

        int numPurchases = 0;

        while(true){
            s = new Scanner(System.in);

            System.out.print("How many purchases would you like to enter? ");
            try{
                numPurchases = s.nextInt();
                break;
            }
            catch (Exception e){
                System.out.println("\nEnter in only valid integer input.\n");
            }
        }


        for(int i = 1; i <= numPurchases; ++i){
            Purchase newPurchase = new Purchase();

            s = new Scanner(System.in);
            System.out.print("\nEnter Purchase Transaction ID: ");
            newPurchase.setId(s.next());

            s = new Scanner(System.in);
            System.out.print("\nEnter Purchase Customer Code: ");
            newPurchase.setCustomerCode(s.next());

            System.out.print("\nEnter Purchase Item Code: ");
            newPurchase.setItemCode(s.next());


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            String dateEntered;
            while(true){
                System.out.print("\nEnter Purchase Date in this format only:\n" +
                        "yyyy-MM-dd\n");
                s = new Scanner(System.in);

                try{

                    dateEntered = s.nextLine();
                    date = dateFormat.parse(dateEntered);
                    break;

                }
                catch (Exception e){
                    System.out.print("\nTime entry does not match required format.\n");
                }

            }
            newPurchase.setDateOfPurchase(new Timestamp(date.getTime()));

            while (true){
                try {
                    s = new Scanner(System.in);
                    System.out.print("\nEnter Quantity: ");
                    newPurchase.setQuantity(s.nextInt());
                    break;
                }
                catch (Exception e){
                    System.out.print("\nEnter only integer numbers.\n");
                }
            }

            purchaseDAO.insertPurchase(newPurchase);

        }

    }


}
