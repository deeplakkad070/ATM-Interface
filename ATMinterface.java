package company;
import java.io.IOException;
import java.util.Scanner;
class BankAccount {
    int Balance;
    int Trasnsaction;
    String Customer_Name;
    String Customer_Id;
    int flag=0;

    BankAccount(String Name,String Id)
    {
        Customer_Name=Name;
        Customer_Id=Id;
    }
    public void clean_screen() {
        try{
            try{
                final String str = System.getProperty("str.name");

                if (str.contains("windows"))
                {
                    Runtime.getRuntime().exec("cls");
                }
                else
                {
                    Runtime.getRuntime().exec("clear");
                }
            }catch(final Exception e){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }
        }catch(final Exception es){
            System.out.println("nothing here!");
        }
    }
    void checkId() throws IOException {
        clean_screen();
        System.out.println("Welcome"+Customer_Name);
        System.out.print("Please Enter THe Customer ID or Pin:");
        Scanner id = new Scanner(System.in);
        String s = id.nextLine();
        if (s.equals(Customer_Id))
        {
            clean_screen();
            seeMenu();
        }
        else
        {
            System.out.println("___________________________________");
            System.out.println("Invalid Customer_ID");
            System.out.println("___________________________________");

            if(flag < 3)
            {
                flag++;
                checkId();
            }
        }
    }

    void Deposit(int amount)
    {
        if(amount != 0)
        {
            Balance=Balance+amount;
            Trasnsaction = amount;
        }
    }

    void Withdraw(int amount)
    {
        if(this.Balance > amount)
        {
            Balance=Balance-amount;
            Trasnsaction = -amount;
        }
        else
        {
            System.out.println("Sorry you have not enogh Balance");
        }
    }

    void getprevTransaction()
    {
        if (Trasnsaction > 0)
        {
            System.out.println("Deposited:"+Trasnsaction);
        }
        else if (Trasnsaction < 0)
        {
            System.out.println("Withdraw:"+Math.abs(Trasnsaction));
        }
        else
        {
            System.out.println("No Transaction Occured");
        }
    }

    public void transfer(double amount,BankAccount acc) throws IOException {
        if (this.Balance < amount)
        {
            clean_screen();
            System.out.println("____________________________________");
            System.out.println("Transfer Failes beacause of not sufficient balance");
            System.out.println("____________________________________");
        }
        else
        {
            this.Balance -= amount;
            acc.Balance += amount;
            System.out.println("Account of " + this.Customer_Name + " become $"+ this.Balance);
            System.out.println("Account of " + acc.Customer_Name + " become $" + acc.Balance);
            System.out.println("\n");
        }
    }
    private void seeMenu() throws IOException
    {
        char option;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome "+Customer_Name);
        System.out.println("Your ID is:"+Customer_Id);
        do {
            System.out.println("\n");
            System.out.println("A.Check Balance");
            System.out.println("B.Deposit");
            System.out.println("C.Withdraw");
            System.out.println("D.Previous Transaction");
            System.out.println("E.Transfer");
            System.out.println("F.Exit");

            System.out.println("___________________________________");
            System.out.println("Enter your option");
            System.out.println("___________________________________");
            option = sc.next().charAt(0);
            option = Character.toUpperCase(option);
            System.out.println("\n");

            switch (option) {
                case 'A' -> {
                    clean_screen();
                    System.out.println("----------------------------");
                    System.out.println("Balance " + Balance);
                    System.out.println("____________________________");
                    System.out.println("\n");
                }
                case 'B' -> {
                    clean_screen();
                    System.out.println("----------------------------");
                    System.out.println("Enter Amount to deposit");
                    System.out.println("____________________________");
                    int amount = sc.nextInt();
                    Deposit(amount);
                    System.out.println("\n");
                }
                case 'C' -> {
                    clean_screen();
                    System.out.println("----------------------------");
                    System.out.println("Enter Amount to withdraw");
                    System.out.println("____________________________");
                    int amount2 = sc.nextInt();
                    Withdraw(amount2);
                    System.out.println("\n");
                }
                case 'D' -> {
                    clean_screen();
                    System.out.println("----------------------------");
                    getprevTransaction();
                    System.out.println("____________________________");
                    System.out.println("\n");
                }
                case 'E' -> {
                    clean_screen();
                    System.out.println("----------------------------");
                    System.out.println("To whom");
                    BankAccount bb = new BankAccount("Raj", "1002");
                    System.out.println(bb.Customer_Name);
                    System.out.println("____________________________");
                    System.out.println("Amount to transfer");
                    double am = sc.nextDouble();
                    System.out.println("____________________________");
                    transfer(am, bb);
                }
                case 'F' -> {
                    clean_screen();
                    System.out.println("____________________________");
                }
                default -> {
                    clean_screen();
                    System.out.println("Invalid Choise of Option!!! Please Re-Enter Your option ");
                }
            }

        }while (option!='F');
        System.out.println("Thank for using our service ");
    }
}
public class ATMinterface{
    public static void main(String[] args) throws IOException {
        BankAccount ba = new BankAccount("Deep","1001");
        ba.checkId();
    }
}

