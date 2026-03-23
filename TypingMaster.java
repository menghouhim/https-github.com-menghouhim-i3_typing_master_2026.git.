import java.util.Scanner;
import java.util.HashMap;

public class TypingMaster {
    
    private static HashMap<String, String> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = null;
    
    public static void main(String[] args) {
        int choice;
        
        do {
            // Display welcome menu
            System.out.println("\n========================================");
            System.out.println("Welcome to I3 Typing Master v2026");
            System.out.println("========================================");
            System.out.println("To begin, please select one of the following options:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Typing Test");
            System.out.println("4. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Choose an option: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    typingTest();
                    break;
                case 4:
                    System.out.println("\nExiting program. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
            
        } while (choice != 4);
        
        scanner.close();
    }
    
    // Register method
    public static void register() {
        System.out.println("\n=== New User Registration ===");
        System.out.print("Input a unique username (left blank to cancel): ");
        String username = scanner.nextLine();
        
        if (username.isEmpty()) {
            System.out.println("Registration cancelled.\n");
            return;
        }
        
        if (users.containsKey(username)) {
            System.out.println("Username already exists! Please try again.\n");
            return;
        }
        
        System.out.print("Input password: ");
        String password = scanner.nextLine();
        
        System.out.print("Input confirm password: ");
        String confirmPassword = scanner.nextLine();
        
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match! Registration failed.\n");
            return;
        }
        
        users.put(username, password);
        System.out.println("Registration successful! You can now login.\n");
    }
    
    // Login method
    public static void login() {
        System.out.println("\n=== Authentication ===");
        System.out.print("Username (left blank to cancel): ");
        String username = scanner.nextLine();
        
        if (username.isEmpty()) {
            System.out.println("Login cancelled.\n");
            return;
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Login successful! Welcome " + username + "!\n");
        } else {
            System.out.println("Invalid username or password!\n");
        }
    }
    
    // Typing Test method
    public static void typingTest() {
        if (currentUser == null) {
            System.out.println("\nPlease login first to take the typing test!\n");
            return;
        }
        
        System.out.println("\n=== Typing Test Level 1 ===");
        System.out.println("Type the following paragraph(s) to test your typing speed");
        System.out.println("(press ENTER to end the test and display the result):\n");
        
        String testText = "ffff jjjj ddd kkkk ssss llll aaaa ;;;; fdsa jkl; fdsa jkl; asdf jkl; asdf jkl; sad dad had lad ask all fall hall flask shall gash hash flash glass glad flag sag hash lash. Ask a glad lad all a salad.";
        
        System.out.println(testText);
        System.out.println("\nStart typing: ");
        
        long startTime = System.currentTimeMillis();
        String userInput = scanner.nextLine();
        long endTime = System.currentTimeMillis();
        
        int timeTaken = (int)((endTime - startTime) / 1000); // in seconds
        int totalCharacters = userInput.length();
        int mistakes = calculateMistakes(testText, userInput);
        
        int speedCPM = (int)((double)totalCharacters / timeTaken * 60);
        int speedWPM = speedCPM / 5;
        
        System.out.println("\n=== Typing Test Level 1 Result ===");
        System.out.println("Speed (characters/min): " + speedCPM);
        System.out.println("Speed (words/min): " + speedWPM);
        System.out.println("Total input characters: " + totalCharacters);
        System.out.println("Total mistakes: " + mistakes);
        
        System.out.println("\n1. Next level");
        System.out.println("2. Logout");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();
        
        switch (option) {
            case 1:
                System.out.println("\nLevel 2 coming soon...\n");
                break;
            case 2:
                currentUser = null;
                System.out.println("\nLogged out successfully!\n");
                break;
            case 3:
                System.out.println("\nExiting...\n");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid option.\n");
        }
    }
    
    // Calculate mistakes between original and user input
    public static int calculateMistakes(String original, String userInput) {
        int mistakes = 0;
        int minLength = Math.min(original.length(), userInput.length());
        
        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) != userInput.charAt(i)) {
                mistakes++;
            }
        }
        
        mistakes += Math.abs(original.length() - userInput.length());
        return mistakes;
    }
}