/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignment1;

/**
 *
 * @author Heave
 */
import java.util.Scanner;
public class Assignment1 {

    public static void main(String[] args) {
        //Global variables for value validity
        boolean userValid;
        boolean passwordValid;
        boolean phoneValid;
        
        //Initializing Name and surname
        String Name = "";
        String Surname = "";
        
        //Initializes name and surname for login message
        String NameAndSurname = ""; 
        
        Scanner scanner = new Scanner(System.in);
        
        //creates a new login object
        Login account1 = new Login();
        
        //Prints out the menu
        System.out.println("What would you like to do:"
                + "\n[1] Create an account"
                + "\n[2] Login");
        
        //Gets the choice the user picked
        int choice = scanner.nextInt();
        
        //Clears scanner for the next time it is used
        scanner.nextLine();
        
        switch (choice){
            case 1:
                //Asks for name
                System.out.println("Enter your first name: ");
                Name = scanner.nextLine();
        
                //Asks for surname
                System.out.println("Enter your surname:");
                Surname = scanner.nextLine();
        
                //Sets Name and Surname
                account1.setNameAndSurname(Name, Surname);
        
                //Asks for username
                System.out.println("Enter your username: ");
                String Username = scanner.nextLine();
                userValid = account1.checkUserName(Username);
                
                //Looping for when username is incorrectly formatted
                if (userValid == false){
                    while (account1.checkUserName(Username) == false){
                        System.out.println(account1.registerUser(false, true, true)); //Prints error message
                        System.out.println("Enter your username: ");
                        String usernameReattempt = scanner.nextLine();
                        userValid = account1.checkUserName(usernameReattempt);
                    }
                }
        
                //Asks for password
                System.out.println("Enter your password: ");
                String Password = scanner.nextLine();
                passwordValid = account1.checkPasswordComplexity(Password);
                
                //Looping for when password is incorrectly formatted
                if (passwordValid == false){
                    while (account1.checkPasswordComplexity(Password) == false){
                        System.out.println(account1.registerUser(true, false, true)); //Prints error message
                        System.out.println("Enter your password: ");
                        String passwordReattempt = scanner.nextLine();
                        passwordValid = account1.checkPasswordComplexity(passwordReattempt);
                    }
                }
                        
                //Asks for phone number
                System.out.println("Enter your phone number: ");
                String Phone = scanner.nextLine();
                phoneValid = account1.checkCellPhoneNumber(Phone);
                
                //Looping for when phone is incorrectly formatted
                if (phoneValid == false){
                    while (account1.checkCellPhoneNumber(Phone) == false){
                        System.out.println(account1.registerUser(true, true, false));//Prints error message
                        System.out.println("Enter your phone number: ");
                        String phoneReattempt = scanner.nextLine();
                        phoneValid = account1.checkCellPhoneNumber(phoneReattempt);
                    }
                }
        
                //Registers user
                if (account1.writeUser() == true){
                    //Prints message that the user has been registered
                    System.out.println(account1.registerUser(userValid, passwordValid, phoneValid));
                } else {
                    System.out.println("An error occured, please try again.");
                }
                
                break;
                
            case 2:
                System.out.println("Enter your username: ");
                String loginUsername = scanner.nextLine();
                
                System.out.println("Enter your password: ");
                String loginPassword = scanner.nextLine();
                
                //True only if the username and password both match
                boolean match = account1.loginUser(loginUsername, loginPassword);
                
                if(match == true){
                    //Gets name and surname for user after successful login
                    NameAndSurname = account1.getNameAndSurname(loginUsername, loginPassword);
                }
                
                //returns login status message
                System.out.println(account1.returnLoginStatus(match, NameAndSurname));
                break;
                
            default:
                //If the user did not pick 1 or 2 in the main menu
                System.out.println("Please choose either of the given options.");
                break;
        }
        
    }
}
