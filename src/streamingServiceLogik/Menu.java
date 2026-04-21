package streamingServiceLogik;
import utilityClasses.FileHandler;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;

public class Menu {

    private Scanner scanner;
    private ArrayList<User> users;
    private User currentUser;

    public void startMenu() {
        scanner = new Scanner(System.in);
        users = FileHandler.loadUsers("Data/Users.csv");
    }

    public void start() {
        while (true) {
            System.out.println("\n=== HBO ===");
            System.out.println("1. Opret bruger");
            System.out.println("2. Log ind");
            System.out.println("0. Afslut");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> login();
                case 0 -> System.exit(0);
                default -> System.out.println("Ugyldigt valg!");
            }
        }
    }

    private void createUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password, new ArrayList<Media>(), new ArrayList<Media>());
        users.add(newUser);


        System.out.println("User is created!");
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {

                currentUser = user;
                System.out.println("Login succes!");
                //metode skal kaldes for at komme videre til menu

            }
        }

        System.out.println("Forkert login!");
    }



    private ArrayList<Media> mediaList;

    public Menu() {
        this.mediaList = StreamingService.getAllMedia();
    }

    public ArrayList<Media> searchMedia(String input) {
        ArrayList<Media> results = new ArrayList<>();

        for (Media media : mediaList) {
            if (media.getTitle().toLowerCase().contains(input.toLowerCase())) {
                results.add(media);
            }
        }
        return results;
    }


    public ArrayList<Media> sortByCategory(Category category) {
        ArrayList<Media> results = new ArrayList<>();

        for (Media media : mediaList) {
            if (media.getCategories().contains(category)) {
                results.add(media);
            }
        }

        return results;
    }
}
