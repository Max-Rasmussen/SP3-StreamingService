package streamingServiceLogik;
import utilityClasses.FileHandler;
import utilityClasses.Userinput;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;

public class Menu {

    private Scanner scanner;
    protected User currentUser;



    public void startMenu() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== HBO ===");
            System.out.println("1. Opret bruger");
            System.out.println("2. Log ind");
            System.out.println("0. Afslut");

            boolean choosing = true;
            while (choosing) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        createUser();
                        break;

                    case 2:
                        login();
                        break;

                    case 0:
                        FileHandler.saveUsers(StreamingService.getUsers());
                        System.exit(0);
                        choosing = false;
                        break;

                    default:
                        System.out.println("Ugyldigt valg!");
                        break;
                }
            }
        }
    }

    private void createUser() {

        System.out.println("You will need to make a username and password");
        System.out.println();
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password, new ArrayList<Media>(), new ArrayList<Media>());
        StreamingService.addUser(newUser);


        System.out.println("User is created!");
        System.out.println();
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : StreamingService.getUsers()) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {

                currentUser = user;
                System.out.println("Login succes!");

                System.out.println();
                //metode skal kaldes for at komme videre til menu
            }
        }

        System.out.println("Wrong login!");
    }


    private void showMenu(){
        System.out.println("=== Media library ===");
        System.out.println("1. Search media by title");
        System.out.println("2. Search media by category");
        System.out.println("3. See watched media");
        System.out.println("4. See saved media");
        System.out.println("5. Quit streaming service");

        boolean choosing = true;
        while (choosing){
            int choice = Userinput.promptInt("What would you like to do?");
            switch (choice){

                case 1:

                    break;


                case 2:


                    break;


                case 3:


                    break;

                case 4:


                    break;


                case 5:
                    FileHandler.saveUsers(StreamingService.getUsers());
                    System.out.println("Logging out...");
                    System.out.println("Exiting");
                    System.exit(0);

                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }


    public ArrayList<Media> searchMedia(String input) {
        ArrayList<Media> results = new ArrayList<>();

        for (Media media : StreamingService.getAllMedia()) {
            if (media.getTitle().toLowerCase().contains(input.toLowerCase())) {
                results.add(media);
            }
        }
        return results;
    }


    public ArrayList<Media> sortByCategory(Category category) {
        ArrayList<Media> results = new ArrayList<>();

        for (Media media : StreamingService.getAllMedia()) {
            if (media.getCategories().contains(category)) {
                results.add(media);
            }
        }

        return results;
    }
}
