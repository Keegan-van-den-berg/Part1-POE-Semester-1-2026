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
        //Sets global username to the username recieved from main class
        username = Username;
        
        //Initializes username validity boolean
        boolean userValid = false;
        
        //Checks if username is correctly formatted
        if (username.length() <= 5 && username.contains("_")){ //Checks the length and if it conatians an underscore
            userValid = true;
        } else {
            userValid = false;
        }
        
        //If username is correctly formatted
        if (userValid == true){
            System.out.println("Username successfully captured!");
        }
        
        return(userValid);
    }
    
    public boolean checkPasswordComplexity(String Password){
        //sets global password variable to Password variable from main class
        password = Password;
        
        //Initializes password validity boolean
        boolean passwordValid = false;
        
        //Checks if password meets correct complexity
        //Check the length
        if (password.length() >= 8){
            passwordValid = true;
        } else {
            passwordValid = false;
        }
        
        //checks if password contains uppercase
        for (int i = 0; i < password.length(); i++){ //Checks every character in password
            if (Character.isUpperCase(password.charAt(i))){ //Checks if the Character is uppercase
                passwordValid = true;
                break; //Breaks out of for loop if there is an uppercase
            } else {
                passwordValid = false;
            }
        }
        
        //Checks if password contains a digit
        for (int i = 0; i < password.length(); i++){ //Loops through every character in password
            if (Character.isDigit(password.charAt(i))){ //Checks if character is a digit
                passwordValid = true;
                break; //Breaks out of for loop if there is a digit
            } else {
                passwordValid = false;
            }
        }
        
        //Checks if password contains a special character
        for (int i = 0; i < password.length(); i++){ //Loops through every character in password
            if (!Character.isLetterOrDigit(password.charAt(i))){ // Checks if character is a special character
                passwordValid = true;
                break; //Breaks out of for loop if special char is found
            } else { 
                passwordValid = false;
            }
        }
        
        //Displays a message if all requirements for password has been met
        if (passwordValid == true){
            System.out.println("Password successfully captured!");
        }
        
        return(passwordValid);
    }
    
    public boolean checkCellPhoneNumber(String Phone){
        //Sets the global phoneNumber variable to the phone variable recieved from the main class
        phoneNumber = Phone;
        
        //Initializes the phone validity boolean
        boolean phoneValid = false;
        
        //Checks if phone number is correctly formatted
        if (phoneNumber.matches("\\+27\\d{9}")){ //checks if phone number begins with "+27" and is 9 digits
            phoneValid = true;
        }
        
        //Shows a message if all phone number requirements have been met
        if (phoneValid == true){
            System.out.println("Phone number successfully added!");
        }
        
        return(phoneValid);
    }
    
    public String registerUser(boolean userValid, boolean passwordValid, boolean phoneValid){
        //Initializes the message variable
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
        
        //Message if phone number is invalid
        if(phoneValid == false){
            message = "Cell number is incorrectly formatted or does not have an international code."
                    + "\nPlease correct the number and try again.";
        }
        
        //Message if all three inputs are valid
        if (userValid == true && passwordValid == true && phoneValid == true){
            message = "Username and password are correctly formatted,"
                    + "\nand the user has successfully been registered.";
        }
        
        return(message);
    }
    
    //Sets the name and surname of the user
    public void setNameAndSurname(String Name, String Surname){
        name = Name;
        surname = Surname;
    }
    
    //Writes user info to txt file
    public boolean writeUser(){
        //Initializes the boolean that verifies if the info was written to the txt file
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
            
            //Closes the txt file
            writer.close();
            
            //Sets the written boolean to true
            written = true;
        }catch(IOException e){ //If the try loop experiences any errors
            e.getStackTrace(); //returns the error
        }
        
        return(written);
    }
    
    public boolean loginUser(String Username, String Password){
        //Sets the global username to the username from the main class
        username = Username;
        
        //Sets the global password to the password from the main class
        password = Password;
        
        //Initializes the boolean variables that confirm whether or not the username and password were found in the txt file
        boolean userFound = false;
        boolean passwordFound = false;
        
        //Initializes the boolean that is only true if both the username and password were found
        boolean match = false;
        
        //Initializes the string variable that the text in the txt file will be written to
        String line = "";
        
        try{
            //creates a new reader object
            BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
            
            while((line = reader.readLine()) != null){ //While the line is not empty
                if(line.contains("Username: ")){ //checks if the line is for the username
                    String storedUsername = line.substring(10); //Copies the username on that line
                    
                    //Reads the next line
                    String nextLine = reader.readLine();
                    
                    //Checks if the line is not empty and is for the password
                    if(nextLine != null && nextLine.contains("Password: ")){
                        String storedPassword = nextLine.substring(10); //Copies the password
                        
                        //Checks if the entered username and password match what is stored in the txt file
                        if(storedUsername.equals(username) && storedPassword.equals(password)){
                            match = true; //Sets the match true if they both match
                            break;
                        }
                    }
                }
            }
            //Closes the text file
            reader.close();
        } catch(IOException e){
            e.getStackTrace();
        }
        
        return(match);
    }
    
    public String getNameAndSurname(String Username, String Password){
        //Initializes the name and surname variables
        String nameAndSurname = "";
        String storedName = "";
        String storedSurname = "";
        
        try{
            //Creates a new buffered reader object
            BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
            
            //initializes the string that the line in the txt file will be written to
            String line = "";
            
            //Runs while the line is not empty
            while((line = reader.readLine()) != null){
                if (line.contains("Username: ")){ //Checks if the line is for the username
                   String checkUsername = line.substring(10); //Copies the username
                   
                   //Reads the next line
                   String nextCheckLine = reader.readLine();
                   //checks if the line is not empty and that it is for the password
                   if (nextCheckLine != null && nextCheckLine.contains("Password: ")){
                       String checkPassword = nextCheckLine.substring(10); //copies the password
                       
                       //Checks if the entered username & password match what is in the txt file
                       if(checkUsername.equals(Username) && checkPassword.equals(Password)){
                           String nameLine = reader.readLine(); // copies the next line
                           
                           //checks if line is not empty and that it is has the name
                           if (nameLine != null && nameLine.contains("Name: ")){ 
                               storedName = nameLine.substring(6); // copies the name
                               
                               String surnameLine = reader.readLine(); //copies the next line
                               //checks if line is not empty and has the surname
                               if (surnameLine != null && surnameLine.contains("Surname: ")){
                                   storedSurname = surnameLine.substring(9);//copies the surname
                                   break;
                               }
                           }
                       }
                   }
                }
            }
            //closes the text file
            reader.close();
        } catch (IOException e){
            e.getStackTrace();
        }
        
        nameAndSurname = storedName + " " + storedSurname; //sets the name and surname string
        return(nameAndSurname);
    }
    
    public String returnLoginStatus(boolean match, String NameAndSurname){
        //initializes the message string 
        String message = "";
        //Checks if the username and password match what is in the txt file
        if (match == true){
            //displays welcome message
            message = "Welcome " + NameAndSurname + ", its great to see you!";
        } else {
            //shows error message
            message = "Username or password incorrect, please try again.";
        }
        
        return(message);
    }
    
}
