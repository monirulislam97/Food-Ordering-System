package classes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Administrator extends Food{
    final String password = "12345";
    public void editOption() throws FileNotFoundException{
        boolean loop = true;

        while(loop != false){
            Scanner option = new Scanner(System.in);
            System.out.println("\n Dear Admin what you would like to do?  ");
            System.out.println(" 1. Show All Food");
            System.out.println(" 2. Add New Food");
            System.out.println(" 3. Delete Existing Food");
            System.out.println(" 4. Exit");
            System.out.print("Selec your Option: ");
            int adminOption = option.nextInt();

            switch(adminOption){
                case 1:
                    print();
                    break;
                case 2: 
                    addFood();
                    break;
                case 3:
                    deleteFood();
                    break;
                case 4:
                    loop = false;
                    System.out.println("Thank Admin!\n\n");
                    break;
                default:
                    System.out.println("Please Enter Only The Available Option (1-4)\n");
            }
           // option.close();
        }
    }

    public boolean verify(String input){
        if(input.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public void login(){
        Scanner data = new Scanner(System.in);
        boolean loop = true;
        while(loop != false){
            System.out.print("\n Enter Your password (Default: 12345 ): ");
            String inputPassword = data.nextLine();
            if(verify(inputPassword) != true){
                System.out.println("Password Entered Wrong! Try again");
            }else{
                loop = false;
            }
        }   
    }
}
