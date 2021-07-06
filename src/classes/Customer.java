package classes;

import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Customer {
    String name, address, pNumb;
    double total;
    Vector<FoodList> foodCart = new Vector<FoodList>();

    public Customer(String name, String address, String pNumb){
        this.name = name;
        this.address = address;
        this.pNumb = pNumb;
    }

    public String getName(){ return name; }
    public String getAdress(){ return address; }
    public String getNumb(){ return pNumb; }
    
    public void addCart() throws FileNotFoundException{
        Scanner foodSelection = new Scanner(System.in);
        Scanner in = new Scanner(new File("./assets/foodList.txt"));
        
        
        System.out.print("Please enter the food code: ");
        String code = foodSelection.next();

        while(in.hasNextLine()){
            String s = in.nextLine();
            
            if(s.length() > 0){
                String[] arr = s.split(",");
                String categoryName = arr[0];
                String foodName = arr[1];
                double pricelist = Double.parseDouble(arr[2]);
                String foodCode = arr[3];

                if(code.toUpperCase().equals(foodCode)){
                    FoodList data = new FoodList(foodName, pricelist, foodCode, categoryName);
                    foodCart.add(data);
                }
                
            }
        }
    }

    public void removeFoodCart(){
        Scanner inp = new Scanner(System.in);
        boolean out = true;
        while(out != false){
            System.out.println("**************************************************\n"+
                              "|                     FOOD CART                  |\n"+
                              "**************************************************\n");
            if(foodCart.size() != 0){
                int i = 1;
                for(FoodList list : foodCart){
                    System.out.println(i + ". " + list.getFoodName() + "\t\tRM" + list.getPrice());
                    i++;
                }

                System.out.print("Enter the food number to remove (Enter 0 to exit):");
                int index = inp.nextInt();

                if(index <= foodCart.size() && index != 0){
                    foodCart.remove(index - 1);
                }else if(index == 0){
                    out = false;
                }
            }else{
                System.out.println("Food Cart is Empty");
                out = false;
            }
        }
    }

    public void count(){
        for(FoodList list : foodCart){
            total += list.getPrice();
        }
        System.out.println("\nTotal bill: RM " + total +"\n");
    }

    public void printReceipt(){
        int i = 1;
        System.out.println("**************************************************\n"+
                           "|                 CUSTOMER INVOICE               |\n"+
                           "**************************************************\n");
        System.out.println("Name   : " + getName());
        System.out.println("Address: " + getAdress());
        System.out.println("Phone Number: " + getNumb());
        System.out.println("\nOrdered Items ");
        for(FoodList list : foodCart){
            System.out.println(i + ". " + list.getFoodName() + "\t\tRM" + list.getPrice());
            i++;
        }
        count();
    }
}
