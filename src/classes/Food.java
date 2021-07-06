package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;



class FoodList {
    String foodName, foodCode, foodCategory;
    Double price;

    FoodList(String foodName, Double price, String foodCode, String foodCategory){
        this.foodName = foodName;
        this.price = price;
        this.foodCode = foodCode;
        this.foodCategory = foodCategory;
    }

    public String getFoodCategory(){ return foodCategory; }
    public String getFoodName(){ return foodName; }
    public double getPrice(){ return price; }
    public String getFoodCode(){ return foodCode; }
} 




interface foodFunction {
    void sortFood() throws FileNotFoundException;
    void addFood() throws FileNotFoundException;
    void deleteFood() throws FileNotFoundException;
    void UploadFoodlist();
}

public class Food implements foodFunction{
    ArrayList<FoodList> foodList = new ArrayList<FoodList>();
    int repeat = 0;

    public static enum FoodCategory {
        CASE1("KOREAN FOODS"),
        CASE2("JAPANESE FOODS"),
        CASE3("MALAY FOODS"), 
        CASE4("CHINESE FOODS"),
        CASE5("INDIAN FOODS");
    
        private String categoryName;
        
        FoodCategory(String categoryName){
            this.categoryName = categoryName;
        }
    
        public String getCategory(){ return categoryName; }
    }

    public void sortFood() throws FileNotFoundException {

        Scanner input = new Scanner(new File("./assets/foodList.txt"));

        while(input.hasNextLine()){
            String s = input.nextLine();
            
            if(s.length() > 0){
                String[] arr = s.split(",");
                String categoryName = arr[0];
                String foodName = arr[1];
                double pricelist = Double.parseDouble(arr[2]);
                String foodCode = arr[3];

                FoodList food = new FoodList(foodName.toUpperCase(), pricelist,foodCode,categoryName);
                foodList.add(food);
                
            }
        }
    }

    public void addFood() throws FileNotFoundException{
        Scanner edit = new Scanner(System.in);
        int i = 1;
        String categoryName, foodCode;

        System.out.println("\nFood Categoary:-");
        for(FoodCategory output : FoodCategory.values()){
            System.out.println(i + ". " + output.getCategory());
            i++;
        }

        System.out.print("Select The Food Category: ");
        int adminChoice = edit.nextInt();

        System.out.print("\nEnter the food name: ");
        String foodName = edit.next();

        System.out.print("Enter the food price: ");
        double foodPrice = edit.nextDouble();

        System.out.print("Enter the food code number (eg: 01): ");
        String foodId = edit.next();

        if(adminChoice == 1){
            categoryName = "CASE1";
            foodCode = "KF_" + foodId;
        }else if(adminChoice == 2){
            categoryName = "CASE2";
            foodCode = "JF_" + foodId;
        }else if(adminChoice == 3){
            categoryName = "CASE3";
            foodCode = "MF_" + foodId;
        }else if(adminChoice == 4){
            categoryName = "CASE4";
            foodCode = "CF_" + foodId;
        }else{
            categoryName = "CASE5";
            foodCode = "IF_" + foodId;
        }

        if(repeat == 0){
            sortFood();
            repeat++;
        }

        FoodList food = new FoodList(foodName, foodPrice,foodCode,categoryName);
        foodList.add(food);
        UploadFoodlist();
    }

    public void deleteFood() throws FileNotFoundException{
        Scanner deleteScanner = new Scanner(System.in);
        System.out.print(" To delete Food item, Please Enter the Food ID : ");
        String FoodId= deleteScanner.nextLine();

        if(repeat == 0){
            sortFood();
            repeat++;
        }

        int a = 0;
        int temp=0;
        for(FoodList foodListChoice : foodList){
                
            if(foodListChoice.getFoodCode().equals(FoodId)){
                System.out.println("\n The Food is deleted: ");
                System.out.println( foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
                
                temp=a;
            }
            a++;
        }
        foodList.remove(temp);
        UploadFoodlist();
    }

    public void UploadFoodlist()
    {

        try{

            File file = new File("./assets/FoodList.txt");
            PrintWriter writer= new PrintWriter(file);

            for(FoodList f : foodList)
            {
                writer.println(f.getFoodCategory() + ","+ f.getFoodName()+ ","+ f.getPrice()+ ","+ f.getFoodCode());
            }
            writer.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void print(int option) throws FileNotFoundException {
        if(repeat == 0){
            sortFood();
            repeat++;
        }

        for(FoodList foodListChoice : foodList){
            if(foodListChoice.getFoodCategory().equals("CASE1") && option == 1){
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
            }else if(foodListChoice.getFoodCategory().equals("CASE2") && option == 2){
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
            }else if(foodListChoice.getFoodCategory().equals("CASE3") && option == 3){
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
            }else if(foodListChoice.getFoodCategory().equals("CASE4") && option == 4){
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
            }else if(foodListChoice.getFoodCategory().equals("CASE5") && option == 5){
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
            }
        } 
    }
    
    public void print() throws FileNotFoundException {
        if(repeat == 0){
            sortFood();
            repeat++;
        }

        System.out.println("\nKorean Food");
        for(FoodList foodListChoice : foodList){
            if(foodListChoice.getFoodCategory().equals("CASE1"))
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
        }

        System.out.println("\nJapanese Food");
        for(FoodList foodListChoice : foodList){
            if(foodListChoice.getFoodCategory().equals("CASE2"))
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
        }

        System.out.println("\nMalay Food");
        for(FoodList foodListChoice : foodList){
            if(foodListChoice.getFoodCategory().equals("CASE3"))
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
        }

        System.out.println("\nChinese Food");
        for(FoodList foodListChoice : foodList){
            if(foodListChoice.getFoodCategory().equals("CASE4"))
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
        }

        System.out.println("\nIndian Food");
        for(FoodList foodListChoice : foodList){
            if(foodListChoice.getFoodCategory().equals("CASE5"))
                System.out.println(foodListChoice.getFoodCode() + ". " + foodListChoice.getFoodName() + " : RM " + foodListChoice.getPrice());
        }
    }

}
