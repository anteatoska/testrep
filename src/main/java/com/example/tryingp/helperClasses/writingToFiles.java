package com.example.tryingp.helperClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.tryingp.Controllers.Controller;
import com.example.tryingp.Models.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class writingToFiles {

    //Method that is used to read the information about the person loggin in.
    //Based on their username and password(which will be saved in a file)
    //The program decides with which credentials in the text the user's input matches with
    //In the roles.txt file,the role of the person is stored as the third element
    //The method will return data[2] for this reason.
    public static String readCredentials(String username, String password) {
        // Create a file object for the roles file
        File file = new File("res/roles.txt");

        try {
            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);
            // Read each line of the file
            while (scanner.hasNext()) {
                // Split the line into an array using the comma as a separator
                String[] data = scanner.nextLine().split(",");
                // Check if the username and password match the values in the array
                if (data[0].equalsIgnoreCase(username) && data[1].equalsIgnoreCase(password)) {
                    // Return the role if the match is found
                    return data[2];
                }
            }
            // Return null if no match is found
            return null;
        } catch (Exception ignored) {
            // Return null if an exception is thrown while reading the file
            return null;
        }
    }

    //Method used to write roles in the roles.txt file
    public static void writeRoles() {
        // Create a file object for the roles file
        File file = new File("res/roles.txt");

        try {
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(file);
            //As the first line we need to write some credentials that we ourselves will use to log in
            // Write the first line of the file with the administrator credentials
            writer.write("admin,admin,Administrator");
            // "people" is an ObservableList that is used to store people,getting
            //information from another method called getPersons.
            //Accessing it through the Controller component we write
            //each person's username, password, and role in separate lines
            for (Person person : Controller.people) {
                writer.write("\n" + person.getUserName() + "," + person.getPassword() + "," + person.getRole().toString());
            }
            // Close the writer to save the file
            writer.close();
        } catch (IOException exception) {
            // Print the stack trace if an IOException is thrown
            exception.printStackTrace();
        }
    }

    //Method used to write books in the books.txt file
    public static ObservableList<Book> getBooks(){
        // Create an ObservableList to store the books
        ObservableList<Book> books = FXCollections.observableArrayList();
        // Define the file location for the books data
        File file = new File("res/books.txt");
        try {
            // Check if the file has been created
            if (file.exists()){
                // Create a Scanner object to read the file
                Scanner scanner = new Scanner(file);
                // Read the file line by line
                while (scanner.hasNextLine()){
                    // Split the line into data fields
                    String[] Data = scanner.nextLine().split(",");
                    // Create a new book object using the data fields
                    books.add(new Book(Data[0],Data[1],Double.parseDouble(Data[2]),Double.parseDouble(Data[3]),Double.parseDouble(Data[4]),Data[5],Data[6],Data[7],Integer.parseInt(Data[8]), LocalDate.parse(Data[9])));
                }
            }
        } catch (IOException e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e);
        }
        // Return the list of books
        return books;
    }

    public static ObservableList<Person> getPersons(){
        // Create an ObservableList to store the persons
        ObservableList<Person> people = FXCollections.observableArrayList();
        // Define the file location for the persons data
        File file = new File("res/persons.txt");
        try {
            // Create the file if it does not exist
            file.createNewFile();
            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);
            // Read the file line by line
            while (scanner.hasNextLine()){
                // Split the line into data fields
                String[] Data = scanner.nextLine().split(",");
                // Determine the type of person based on the role field
                if (Data[6].equalsIgnoreCase("Librarian")){
                    // Create a new Librarian object using the data fields
                    people.add(new Librarian(Data[0],Data[4],Data[5],Data[1],Integer.parseInt(Data[3]),Data[2],Role.Librarian,Double.parseDouble(Data[7])));
                }else if (Data[6].equalsIgnoreCase("Manager")){
                    // Create a new Manager object using the data fields
                    people.add(new Manager(Data[0],Data[4],Data[5],Data[1],Integer.parseInt(Data[3]),Data[2],Role.Manager));
                }else if (Data[6].equalsIgnoreCase("Administrator")){
                    // Create a new Administrator object using the data fields
                    people.add(new Administrator(Data[0],Data[4],Data[5],Data[1],Integer.parseInt(Data[3]),Data[2],Role.Administrator));
                }
            }
        } catch (IOException e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e);
        }
        return people;
    }

    public static String getNumberOfLibrarians(){
        // retrieve list of people from the BooksPersonsController
        ObservableList<Person> people = Controller.people;
        int numberOfLibrarians = 0;
        // iterate over each person in the list
        for (Person person: people) {
            // check if the person is an instance of Librarian
            if (person instanceof Librarian){
                numberOfLibrarians++;
            }
        }
        // return the number of Librarians as a string
        return String.valueOf(numberOfLibrarians);
    }

    public static String getNumberOfManagers(){
        // retrieve list of people from the BooksPersonsController
        ObservableList<Person> people = Controller.people;
        int numberOfManagers = 0;
        // iterate over each person in the list
        for (Person person: people) {
            // check if the person is an instance of Manager
            if (person instanceof Manager){
                numberOfManagers++;
            }
        }
        // return the number of Managers as a string
        return String.valueOf(numberOfManagers);
    }

    public static int getNumberOfBills(){
// Create a File object representing the directory "res/Bills"
        File file = new File("res/Bills");
// Check if the directory exists
        if (file.exists()) {
// If it exists, return the number of files (bills) in the directory
            return file.listFiles().length;
        }
// If the directory does not exist, return 0
        return 0;
    }

    public static void writeBill(String billId, double totalBill, ObservableList<Book> books){
        // Create a File object representing the bill file
        File file = new File("res/Bills/" + billId + ".txt");
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            // Create a new file
            file.createNewFile();
            // Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(file);
            // Write the bill header information
            writer.write("Bill Id: " + billId);
            writer.write("\nDate: " + dateFormat.format(calendar.getTime()));
            // Write the books included in the bill
            int i = 0;
            for (Book book: books) {
                writer.write("\n" + ++i + ": " + book.toStringBill());
            }
            // Write the total bill amount
            writer.write("\nTotal: " + totalBill);
            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            // Throw a runtime exception if an IOException occurs
            throw new RuntimeException(e);
        }
    }

    public static double getTotalBill(){
        File file = new File("res/totalBill.bin"); // Create a file object with the file path "res/totalBill.bin"
        if (file.exists()) { // Check if the file exists
            try (FileInputStream fis = new FileInputStream(file); // Create a FileInputStream object
                 DataInputStream dis = new DataInputStream(fis)) { // Wrap the FileInputStream object with a DataInputStream object
                return dis.readDouble(); // Read the double value from the DataInputStream object
            } catch (IOException e) { // Catch the IOException in case of any error
                return 0; // Return 0 if an error occurs
            }
        }
        return 0; // Return 0 if the file does not exist
    }

    public static double getTotalCost(){
        File file = new File("res/totalCost.bin"); // Create a file object with the file path "res/totalCost.bin"
        if (file.exists()) { // Check if the file exists
            try (FileInputStream fis = new FileInputStream(file); // Create a FileInputStream object
                 DataInputStream dis = new DataInputStream(fis)) { // Wrap the FileInputStream object with a DataInputStream object
                return dis.readDouble(); // Read the double value from the DataInputStream object
            } catch (IOException e) { // Catch the IOException in case of any error
                return 0; // Return 0 if an error occurs
            }
        }
        return 0; // Return 0 if the file does not exist
    }


    public static int getBooksSold(){
// Check if the file "res/booksSold.bin" exists
        File file = new File("res/booksSold.bin");
        if (file.exists()) {
// If the file exists, try to read the integer value from the file
            try (FileInputStream fis = new FileInputStream(file);
                 DataInputStream dis = new DataInputStream(fis)) {
// Return the read integer value
                return dis.readInt();
            } catch (IOException e) {
// In case of IOException, return 0
                return 0;
            }
        }
// If the file doesn't exist, return 0
        return 0;
    }

    public static void writeBooks(){
        // Create the file "res/books.txt"
        File file = new File("res/books.txt");
        try {
            file.createNewFile();
            // Create a FileWriter instance to write the data to the file
            FileWriter writer = new FileWriter(file);
            // Write each book's information to the file
            for (Book book: Controller.books){
                writer.write(book.toString() + "\n");
            }
            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            // In case of IOException, throw a new RuntimeException with the caught exception
            throw new RuntimeException(e);
        }
    }


    public static void writePersons(){
        File file = new File("res/persons.txt");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Person person: Controller.people){
                writer.write(person.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeTotalBill(double total){
        File file = new File("res/totalBill.bin");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            file.createNewFile();
            dos.writeDouble(total);
            dos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeTotalCost(double total){
        File file = new File("res/totalCost.bin");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            file.createNewFile();
            dos.writeDouble(total);
            dos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeBooksSold(int quantity){
        File file = new File("res/booksSold.bin");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            file.createNewFile();
            dos.writeInt(quantity);
            dos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

