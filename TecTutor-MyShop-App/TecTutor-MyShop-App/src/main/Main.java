package main;

import service.MyShopDisplay;
import service.MyShopUpload;
import service.ConcessionMyShop;

import java.util.Scanner;


public class Main {

    public static void menu(MyShopUpload upload, MyShopDisplay display, ConcessionMyShop concession){

        int choice = 0;
        boolean isExit = false;
        String code = "";
        Scanner s;

        while(true){

            while (true){
                s = new Scanner(System.in);
                System.out.print("\n*** Select A Menu Option ***\n" +
                        "1- Enter Customers\n" +
                        "2- Enter Items\n" +
                        "3- Enter Purchases\n" +
                        "4- Display Customer By Customer Code\n" +
                        "5- Display All Items\n" +
                        "6- Display Purchases By Customer Code\n" +
                        "7- Calculate Concession Price By Item Code\n" +
                        "8- Exit\n");

                try{
                    choice = s.nextInt();
                    break;
                }
                catch (Exception e){
                    System.out.print("\nEnter only integer numbers.\n");
                }
            }

            if(!(choice >= 1 && choice <= 8)){
                System.out.println("\nInvalid selection entry.\n");
            }


            switch (choice){
                case 1:
                    upload.storeCustomerDetails();
                    break;
                case 2:
                    upload.storeItemDetails();
                    break;
                case 3:
                    upload.purchaseDetails();
                    break;
                case 4:
                    System.out.print("Enter Customer Code: ");
                    s = new Scanner(System.in);
                    code = s.next();
                    if(!display.customerDetails(code)){
                        System.out.print("\nThe customer code entered was not found.\n");
                    }
                    break;
                case 5:
                    if(!display.diplayAllItems()){
                        System.out.print("\nNo items on item table found.\n");
                    }
                    break;
                case 6:
                    System.out.print("Enter Customer Code: ");
                    s = new Scanner(System.in);
                    code = s.next();
                    if(!display.purchaseDetails(code)){
                        System.out.print("\nThe purchase for the customer code entered was not found.\n");
                    }
                    break;
                case 7:
                    System.out.print("Enter Item Code: ");
                    s = new Scanner(System.in);
                    code = s.next();
                    if(!concession.calculateConcession(code)){
                        System.out.print("\nNo items found for item code entered.\n");
                    }
                    break;

                case 8:
                    isExit = true;
                    break;
            }

            if(isExit){

                System.out.print("Have a Nice Day.");
                break;
            }

        }


    }

    public static void main(String[] args) {

        MyShopUpload upload1 = new MyShopUpload();

        MyShopDisplay display1 = new MyShopDisplay();

        ConcessionMyShop concession1 = new ConcessionMyShop();


        menu(upload1,display1, concession1);

    }
}