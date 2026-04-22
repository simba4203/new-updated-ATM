import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;


public class atmpartices {

    public static void savedata(double balance, ArrayList<String> histroy) {
        try{
            FileWriter fw = new FileWriter("ATM.txt");
            fw.write(balance+"/n");
            for (String t: histroy){
                fw.write(t = "/n");
            }
            fw.close();

        }catch(IOException e){
            System.out.println("Error to saving data ");
        }
        
    }
    public static double loadData(ArrayList<String> history){
        double balance = 5000.0;
        try{
            File file = new File("Atm.txt");
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                if(sc.hasNextLine()){
                    balance = Double.parseDouble(sc.nextLine());
                }
                while (sc.hasNextLine()) {
                    history.add(sc.nextLine());
                    
                }
                sc.close();;

                
            }
        }catch(Exception e){
            System.out.println("Error loading ATM Data");
        }
        return balance;
    }
    //save accout numbers 
    public static void saveAccout(int accNo, String name ){
        try{
            FileWriter fw = new FileWriter("accout.txt", true );
            fw.write(accNo+" "+ name+"/n");
            fw.close();
        }catch(IOException e){
            System.out.println("error saving account");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer,String> user = new HashMap<>();

        int pin = 4203;
        int enterpin;
        int attemp =0;
        Double balance=5000.00;
        Double limit=2000.00;
        ArrayList<String> history = new ArrayList<>();
        // load the data accoout
        HashMap<Integer,String>account=new HashMap<>();
        try{
            File file = new File("accounts.txt");
            if (file.exists()) {
                Scanner fileReader = new Scanner(file);
                
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] data = line.split("  ");
                    int acc = Integer.parseInt((data[0]));
                    String name = data[1];
                    
                    account.put(acc, name);

                    
                }
                fileReader.close();
                
            }
        }catch(Exception e ){
            System.out.println("error loading account ");
        }
        
        //pin try 
        while (attemp < 3) {
        System.out.println("enter the pin ");
        enterpin = sc.nextInt();

        if(enterpin == pin){
            int choice;
            do{
                  System.out.println("\n ATM MENU ");
                System.out.println("1. create account");
                System.out.println("2. Check Balance");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. money tranfer ");
                System.out.println("6. History");
                System.out.println("7. Exit");
                System.out.print("Choose option: ");

                choice =sc.nextInt();

                switch (choice) {

                    case 1:
                        sc.nextLine();
                        System.out.println("Enter new aacount numb");
                        int newAcc = sc.nextInt();
                        sc.nextLine();
                        if (account.containsKey((newAcc))) {
                            System.out.println("aacount already hai ");
                            break;
                            
                        }
                        System.out.println("enter name ");
                        String newName = sc.nextLine();
                        account.put(newAcc,newName);
                        saveAccout(newAcc, newName);

                        System.out.println("Accout create succefully ");
                        System.out.println("thanks to open your account in this bank");
                        break;
                    case 2:
                        System.out.println("balance" + balance ) ;    
                        
                        break;
                    case 3: 
                        System.out.print("Enter the amout to deposti:");
                        double deposit = sc.nextDouble();
                        if (deposit>0){
                        balance += deposit;
                        history.add("deposited $"+ deposit);
                        System.out.println(" pasie aagya accout mai bhai");
                        }else{
                            System.out.print("bhai amout sahi daal");
                        }
                        break;
                    case 4:
                        System.out.print("Enter amount: ");
                        double paisenikal = sc.nextDouble();
                         if (paisenikal > limit){
                             System.out.println("Limit utni nhi hai bhai " + limit);
                         } else if (paisenikal > balance) {
                        System.out.println("pasie nhi hai bhai!");
                           } else{
                            balance = balance - paisenikal;
                            history.add("withdraw"+paisenikal);
                            System.out.println("nikal gye pasie!");
                           }                
                            break;
                    
                    case 5:
                        sc.nextLine();
                        System.out.println("enter the nuumber ");
                        String name = sc.nextLine();
                        
                        System.out.println("enter the amout ");
                        double transfre = sc.nextDouble();

                        if(transfre>balance){
                            System.out.println("paise nhi hai utne bhai ");
                        }else if(transfre<=0){
                            System.out.println("bhai amout invaild hai");
                        }else{
                            balance -= transfre;
                            history.add("tranfer to "+transfre+"to"+name);
                            System.out.println("Transfer successful to " + name);
                        }
                        break;
                    case 6:
                        if(history.isEmpty()){
                            System.err.println("kuch nhi hai transaction ");
                        }else{
                            System.out.println("history hai ");
                            for(String t : history ) {
                                System.out.println(t);
                            }
                        }              
                    case 7:
                        System.out.println("thanks bhai use karne kele ");
                        break;
                
                    default:
                        System.out.println("bhai thik se dekh kar prees kar ");
                        break;
                }

            }while (choice !=7); 
              
            break;
            
        }else{
            attemp++;
            System.out.println(" thik se pin dal attempts left" + (3 - attemp));
        }
        if(attemp == 3){
            System.out.println("aacout block hogya bank ja ");
        }
    }
         
        sc.close();
        
    }
    
}
