import java.util.Scanner;

public class PhoneBook {
    private Contact head;
    private int len = 0;

    private static class Contact {
        String name;
        String number;
        Contact next;

        Contact(String name, String number) {
            this.name = name;
            this.number = number;
        }
    }

    public void addContact(String name, String number) {
        Contact newContact = new Contact(name, number);
        newContact.next = head;
        head = newContact;
        len++;
        System.out.println("Contact added successfully.");
    }

    public void displayContacts() {
        if (head == null) {
            System.out.println("Phone book is empty.");
            return;
        }

        Contact current = head;
        while (current != null) {
            System.out.println("Name: " + current.name);
            System.out.println("Number: " + current.number);
            System.out.println("--------------------------");
            current = current.next;
        }
    }

    public void searchContact(String query) {
        if (head == null) {
            System.out.println("Phone book is empty.");
            return;
        }

        Contact current = head;
        boolean found = false;
        int index = 1;

        while (current != null) {
            if (current.name.contains(query) || current.number.contains(query)) {
                System.out.println("Name: " + current.name);
                System.out.println("Number: " + current.number);
                System.out.println("Index: " + index);
                System.out.println("--------------------------");
                found = true;
            }
            current = current.next;
            index++;
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        }
    }

    public void deleteContact(String query) {
        if (head == null) {
            System.out.println("Phone book is empty.");
            return;
        }

        Contact current = head;
        Contact prev = null;
        boolean found = false;

        while (current != null) {
            if (current.name.contains(query) || current.number.contains(query)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    head = current.next;
                }
                current = null;
                len--;
                System.out.println("Contact deleted successfully.");
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        }
    }

    public void deleteAllContacts() {
        head = null;
        len = 0;
        System.out.println("All contacts deleted successfully.");
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("Phone Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Delete All Contacts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            option = input.nextInt();

            input.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Number: ");
                    String number = input.nextLine();
                    addContact(name, number);
                    break;

                case 2:
                    displayContacts();
                    break;

                case 3:
                    System.out.print("Enter search query: ");
                    String query = input.nextLine();
                    searchContact(query);
                    break;

                case 4:
                    System.out.print("Enter contact name or number to delete: ");
                    String deleteQuery = input.nextLine();
                    deleteContact(deleteQuery);
                    break;

                case 5:
                    deleteAllContacts();
                    break;

                case 6:
                    System.out.println("Exiting Phone Book.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }
    }
}
