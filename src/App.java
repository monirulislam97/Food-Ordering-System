import classes.Administrator;
import classes.Customer;
import classes.Food;
import classes.Food.FoodCategory;
import java.util.Scanner;

public class App{
    public static void main(String[] args) throws Exception {
        boolean loop = true;

        Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        while(loop != false){
            System.out.print("**************************************************\n"+
                         "|         WELCOME TO FOOD ORDERING SYSTEM        |\n"+
                         "**************************************************\n"+
                         "\nDEAR USER, YOU WANT TO ACCESS AS :\n\n"+
                         "  \t(1). Administrator \n"+
                         "  \t(2). Customer\n"+
                         "  \t(3). Exit  \n\n"+
                         "PLEASE CHOOSE AN OPTION- ");
            int loginOption = input.nextInt();

            switch(loginOption){
                case 1:
                    Administrator admin = new Administrator();
                    admin.login();
                    admin.editOption();
                    break;

                case 2:
                    Food food = new Food();
                    boolean exit = true;

                    System.out.println("\nPlease Enter Your Details");
                    System.out.print("Please insert your Name: ");
                    String name = input.next();
            
                    System.out.print("Please insert your Address: ");
                    String address = input.next();
            
                    System.out.print("Please insert your Phone Number: ");
                    String pNumb = input.next();

                    Customer cust = new Customer(name, address, pNumb);

                    while(exit != false){
                        int i = 1;
                        System.out.println("\nWhat cuisine do you like to order? ");
                        for(FoodCategory output : FoodCategory.values()){
                            System.out.println(i + ". " + output.getCategory());
                            i++;
                        }
                        System.out.println("6. View Food Cart");
                        System.out.println("7. Make payment");

                        System.out.print("Please insert your choice: ");
                        int categoryOption = input.nextInt();

                        switch(categoryOption){
                            case 1:
                                System.out.println("\nKorean Food:");
                                food.print(categoryOption);
                                cust.addCart();
                                break;

                            case 2:
                                System.out.println("\nJapanese Food:");
                                food.print(categoryOption);
                                cust.addCart();
                                break;
                            
                            case 3:
                                System.out.println("\nMalay Food:");
                                food.print(categoryOption);
                                cust.addCart();
                                break;

                            case 4:
                                System.out.println("\nChinese Food:");
                                food.print(categoryOption);
                                cust.addCart();
                                break;

                            case 5:
                                System.out.println("\nIndian Food:");
                                food.print(categoryOption);
                                cust.addCart();
                                break;

                            case 6:
                                cust.removeFoodCart();
                                break;

                            case 7:
                                exit = false;
                                cust.printReceipt();
                                System.out.println("\tThank You for Using our system\n");
                                break;
                            
                            default:
                                System.out.println("Please enter only the available option (1-6):");
                        }
                    }
                    break;

                case 3:
                    loop = false;
                    break;

                default:
                    System.out.println("Please enter only the available option (1-3):");
            }
        }
        input.close();
    }
}