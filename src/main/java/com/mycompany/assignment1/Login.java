/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment1;

/**
 *
 * @author Heave
 */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Login {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNumber;
    
    public boolean checkUserName(String Username){
        username = Username;
        boolean userValid = false;
        //Checks if username is correctly formatted
        if (username.length() <= 5 && username.contains("_")){
            userValid = true;
        } else {
            userValid = false;
        }
        
        if (userValid == true){
            System.out.println("Username successfully captured!");
        }
        
        return(userValid);
    }
    
    public boolean checkPasswordComplexity(String Password){
        password = Password;
        boolean passwordValid = false;
        
        //Checks if password meets correct complexity
        //Check the length
        if (password.length() >= 8){
            passwordValid = true;
        }
        
        //checks if password contains uppercase
        for (int i = 0; i < password.length(); i++){
            if (Character.isUpperCase(password.charAt(i))){
                passwordValid = true;
                break;
            } else {
                passwordValid = false;
            }
        }
        
        //Checks if password contains a digit
        for (int i = 0; i < password.length(); i++){
            if (Character.isDigit(password.charAt(i))){
                passwordValid = true;
                break;
            } else {
                passwordValid = false;
            }
        }
        
        //Checks if password contains a special character
        for (int i = 0; i < password.length(); i++){
            if (!Character.isLetterOrDigit(password.charAt(i))){
                passwordValid = true;
                break;
            } else { 
                passwordValid = false;
            }
        }
        
        if (passwordValid == true){
            System.out.println("Password successfully captured!");
        }
        
        return(passwordValid);
    }
    
    public boolean checkCellPhoneNumber(String Phone){
        phoneNumber = Phone;
        boolean phoneValid = false;
        
        //Checks if phone number is correctly formatted
        if (phoneNumber.matches("\\+27\\d{9}")){
            phoneValid = true;
        }
        
        if (phoneValid == true){
            System.out.println("Phone number successfully added!");
        }
        
        return(phoneValid);
    }
    
    public String registerUser(boolean userValid, boolean passwordValid, boolean phoneValid){
        String message = "";
        
        //Message if username is invalid
        if (userValid == false){
            message = "Username is incorrectly formatted."
                    + "\nPlease ensure that your username contains a underscore,"
                    + "\nand is no more than five characters in length."
                    + "\nPlease try again.";
        }
        
        //Message if password is invalid
        if (passwordValid == false){
            message = "Password is not correctly formatted."
                    + "\nPlease ensure that the password contains an uppercase letter,"
                    + "\na digt, a special character, and is at least eight characters in length."
                    + "\nPlease try again.";
        }
        
        if(phoneValid == false){
            message = "Cell number is incorrectly formatted or does not have an international code."
                    + "\nPlease correct the number and try again.";
        }
        
        if (userValid == true && passwordValid == true && phoneValid == true){
            message = "Username and password are correctly formatted,"
                    + "\nand the user has successfully been registered.";
        }
        
        return(message);
    }
    
    public void setNameAndSurname(String Name, String Surname){
        name = Name;
        surname = Surname;
    }
    
    //Writes user info to txt file
    public boolean writeUser(){
        boolean written = false;
        try{
            //Creates a writer object
            FileWriter writer = new FileWriter("Users.txt", true);
            
            //Writes info to text file
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Name: " + name + "\n");
            writer.write("Surname: " + surname + "\n");
            writer.write("Phone: " + phoneNumber + "\n");
            writer.write("\n");
            
            writer.close();
            written = true;
        }catch(IOException e){
            e.getStackTrace();
        }
        
        return(written);
    }
    
    public boolean loginUser(String Username, String Password){
        username = Username;
        password = Password;
        
        
        boolean userFound = false;
        boolean passwordFound = false;
        
        boolean match = false;
        
        String line;
        //creates a new reader object
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
            
            while((line = reader.readLine()) != null){
                if(line.contains("Username: ")){
                    String storedUsername = line.substring(10);
                    
                    String nextLine = reader.readLine();
                    
                    if(nextLine != null && nextLine.contains("Password: ")){
                        String storedPassword = nextLine.substring(10);
                        
                        if(storedUsername.equals(username) && storedPassword.equals(password)){
                            match = true;
                            break;
                        }
                    }
                }
            }
            
            reader.close();
        } catch(IOException e){
            e.getStackTrace();
        }
        
        return(match);
    }
    
    public String getNameAndSurname(String Username, String Password){
        String nameAndSurname = "";
        String storedName = "";
        String storedSurname = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
            
            String line = "";
            
            while((line = reader.readLine()) != null){
                if (line.contains("Username: ")){
                   String checkUsername = line.substring(10);
                   
                   String nextCheckLine = reader.readLine();
                   if (nextCheckLine != null && nextCheckLine.contains("Password: ")){
                       String checkPassword = nextCheckLine.substring(10);
                       
                       if(checkUsername.equals(Username) && checkPassword.equals(Password)){
                           String nameLine = reader.readLine();
                           
                           if (nameLine != null && nameLine.contains("Name: ")){
                               storedName = nameLine.substring(6);
                               
                               String surnameLine = reader.readLine();
                               if (surnameLine != null && surnameLine.contains("Surname: ")){
                                   storedSurname = surnameLine.substring(9);
                                   break;
                               }
                           }
                       }
                   }
                }
            }
            
            reader.close();
        } catch (IOException e){
            e.getStackTrace();
        }
        
        nameAndSurname = storedName + " " + storedSurname;
        return(nameAndSurname);
    }
    
    public String returnLoginStatus(boolean match, String NameAndSurname){
        String message = "";
        if (match == true){
            message = "Welcome " + NameAndSurname + ", its great to see you!";
        } else {
            message = "Username or password incorrect, please try again.";
        }
        
        return(message);
    }
    
}
