package project;

import java.util.Random;
import java.util.Scanner;

// Employee class
class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;

    // Parameterized Constructor
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

// CredentialService class
class CredentialService {

    // Method to generate Email
    public String generateEmailAddress(String firstName, String lastName, String department) {
        return firstName.toLowerCase() + lastName.toLowerCase() + "@" 
               + department.toLowerCase() + ".skit.ac.in";
    }

    // Method to generate Random Password
    public String generatePassword() {
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_-+=<>?/";

        String values = capitalLetters + smallLetters + numbers + specialChars;
        Random random = new Random();

        char[] password = new char[8]; // Password length = 8
        password[0] = capitalLetters.charAt(random.nextInt(capitalLetters.length()));
        password[1] = smallLetters.charAt(random.nextInt(smallLetters.length()));
        password[2] = numbers.charAt(random.nextInt(numbers.length()));
        password[3] = specialChars.charAt(random.nextInt(specialChars.length()));

        // Fill remaining with random characters
        for (int i = 4; i < 8; i++) {
            password[i] = values.charAt(random.nextInt(values.length()));
        }

        return new String(password);
    }

    // Show credentials
    public void showCredentials(Employee emp) {
        System.out.println("Dear " + emp.getFirstName() + " your generated credentials are as follows:");
        System.out.println("Email ---> " + emp.getEmail());
        System.out.println("Password ---> " + emp.getPassword());
    }
}

// Main Application class

public class Skitapp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // Take employee details
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();

        Employee emp = new Employee(firstName, lastName);

        // Choose department
        System.out.println("Please enter the department from the following:");
        System.out.println("1. Technical");
        System.out.println("2. Admin");
        System.out.println("3. Human Resource");
        System.out.println("4. Legal");

        int choice = sc.nextInt();
        String dept = "";

        switch (choice) {
            case 1: dept = "tech"; break;
            case 2: dept = "admin"; break;
            case 3: dept = "hr"; break;
            case 4: dept = "legal"; break;
            default: 
                System.out.println("Invalid choice! Assigning General dept."); 
                dept = "general";
        }

        emp.setDepartment(dept);

        CredentialService cs = new CredentialService();

        // Generate email & password
        String email = cs.generateEmailAddress(emp.getFirstName(), emp.getLastName(), emp.getDepartment());
        String password = cs.generatePassword();

        emp.setEmail(email);
        emp.setPassword(password);

        // Show credentials
        cs.showCredentials(emp);

        sc.close();
	

	}

}
