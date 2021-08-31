import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int getChoice(String[] options, String prompt) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int choice = 0;
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print(prompt);
            try {
                choice = input.nextInt();
                if (choice < 1 || choice > options.length) throw new InputMismatchException();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.\n");
                input.nextLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int roleChoice = getChoice(new String[]{"Recipient", "Ministry of Health", "Vaccination Center", "Quit"}, "Enter your role: ");
        if (roleChoice == 1) {
            int action = getChoice(new String[]{"Register", "Sign in"}, "Enter your action: ");
            System.out.print("Enter your name: ");
            String name = input.nextLine();
            if (action == 1) {
                System.out.print("Enter your phone number: ");
                String phoneNumber = input.nextLine();
                Recipient recipient = new Recipient(name, phoneNumber);
                recipient.register();
            } else if (action == 2) {
                Recipient recipient = new Recipient(name);
                recipient.signIn();
            }
        } else if (roleChoice == 2) {
            MinistryOfHealth ministryOfHealth = new MinistryOfHealth();
            int action = getChoice(new String[]{"View recipient data", "Search a recipient", "Distribute vaccines", "View statistics"}, "Enter your action: ");
            if (action == 1) {
                ministryOfHealth.printRecipientData();
            } else if (action == 2) {
                System.out.print("Enter recipient's name: ");
                String name = input.nextLine();
                ministryOfHealth.searchRecipient(name);
            }
        }
    }
}
