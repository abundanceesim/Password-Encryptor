import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Filename: Encryptor.java
 * Description: This project provides a demonstration of password encryption. It uses 3 methods: encrypt(), decrypt() and isPasswordValid(). The methods encrypt() and decrypt() both
 * encrypt and decrypt the passwords using the encryption/decryption key provided by the user. The method isPasswordValid() performs validation on the user's password by checking is
 * the password meets the length and complexity requirements. The program displays a menu for the user to make a choice of either encryption or decryption. For encryption, it uses a
 * loop to perform the validation and will not encrypt the password if it doesn't meet the validation requirements.
 * @author Abundance Esim
 *
 */
public class Encryptor {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        //declaration and initialization of variables.
        String password = "";
        int key = 0;  //The key that would be used for either encryption or decryption.
        int option = 0;
        String displayMenu = "";


        System.out.println("Welcome to the Password Encryptor Program!");

        do {
            System.out.println("What would you like to do?");
            System.out.println("1) Encrypt");
            System.out.println("2) Decrypt");
            try { //checks for invalid input.
                option = console.nextInt();
                console.nextLine();
            } catch (InputMismatchException e) { //exception is caught and handled here.
                System.err.println("Input must be either \"1\" or \"2\"!");
                console.nextLine();
            }

            /**
             * switch-case option to display a menu to the user. The operation carried out by the program is determined by the user's choice.
             */
            switch (option){
                case 1:
                    System.out.println("Encryption selected.");
                    //encryption segment:
                    System.out.println("Please enter a password:");
                    System.out.println("*Password must be at least 8 characters, contain 1 uppercase letter, 1 lowercase letter and 1 digit(0-9).");

                    do {

                        if (console.hasNextLine()) {
                            password = console.nextLine();
                            if ((isPasswordValid(password)) == false) {
                                System.out.println("Password does not meet complexity requirements. Please enter a new password:");
                                console.hasNextLine();
                            }
                        }
                    } while ((isPasswordValid(password)) == false);

                    System.out.println("Your password is: " + password);

                    System.out.println("Please enter encryption key(integer): ");
                    try { //checks for invalid input.
                        key = console.nextInt();
                        console.nextLine();
                    } catch (InputMismatchException e) { //exception is handled here.
                        System.err.println("Input must be an integer!");
                        console.nextLine();
                    }

                    System.out.print("Your encrypted password is: ");
                    encrypt(password, key);
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Decryption selected.");
                    //decryption segment:
                    System.out.println("Please enter your encrypted password:");
                    password = console.nextLine();

                    System.out.println("Enter decryption key(integer): ");
                    try { //checks for invalid input
                        key = console.nextInt();
                        console.nextLine();
                    } catch (InputMismatchException e) { //exception is handled here.
                        System.err.println("Input must be an integer!");
                        console.nextLine();
                    }

                    System.out.print("Your decrypted password is: ");
                    decrypt(password, key);
                    System.out.println("");
                    break;
                default:
                    System.out.println("Invalid option entered. Please choose a valid option");
            }
            System.out.println("Continue? (Y/N)");
            displayMenu = console.nextLine();
        } while(displayMenu.equalsIgnoreCase("Y"));

        System.out.println("Thank you for using this program. Have a nice day!");
    }

    /**
     * This method determines if a valid or not using certain criteria such as password length and complexity.
     * @param s The password to be entered by the user.
     * @return true for valid password, false for invalid password.
     */
    public static Boolean isPasswordValid(String s) {
        return s.chars().anyMatch(Character::isUpperCase) &&
                s.chars().anyMatch(Character::isLowerCase) &&
                s.chars().anyMatch(Character::isDigit) &&
                (s.length() >= 8);
    }

    /**
     * This method encrypts the user's password by adding a key to each character in the password string. The key is an integer and it determines the number of characters to add to each character.
     * @param s The password to be entered by the user.
     * @param key The encryption key provided.
     */
    public static void encrypt(String s, int key){

        char[] chars = s.toCharArray();
        for (char c : chars){
            c += key;
            System.out.print(c);
        }
    }

    /**
     * This method decrypts the user's password by removing a key from each character in the password string. The key is an integer and it determines the number of characters to remove from each character.
     * @param s The encrypted password to be entered by the user.
     * @param key The decryption key provided.
     */
    public static void decrypt(String s, int key){
        char[] chars = s.toCharArray();
        for (char c : chars){
            c -= key;
            System.out.print(c);
        }
    }

}
