import java.util.Scanner;
import java.io.*;

public class EncryptionDecryption {
    static Scanner scanner = new Scanner(System.in);
    static String writeFileName;
    static boolean isEncrypt;
    static final int shift = 4;

    public static void main(String[] args) {
        int choice;
        
        System.out.println("1. Encrypt a file");
        System.out.println("2. Decrypt a file");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        choice = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter the file name or path: ");
        String readFileName = scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter the name for the encrypted file: ");
                writeFileName = scanner.nextLine();
                isEncrypt = true;
                ProcessFile(readFileName, writeFileName, true);
                break;

            case 2:
                System.out.print("Enter the name for the decrypted file: ");
                writeFileName = scanner.nextLine();
                isEncrypt = false;
                ProcessFile(readFileName, writeFileName, false);
                break;
            
            case 3:
                System.exit(0);
        
            default:
                System.out.println("An error occurred: Invalid Choice!");
                break;
        }
    }

    public static void ProcessFile(String inputFilePath, String outputFilePath, boolean isEncrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = isEncrypt ? Encryption(line) : Decryption(line);
                writer.write(processedLine);
                writer.newLine();
            }

            System.out.println("File processed successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static String Encryption(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                result.append((char) (((int) character + shift - base) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String Decryption(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                result.append((char) (((int) character - shift - base + 26) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
