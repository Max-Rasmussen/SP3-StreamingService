package streamingServiceLogik;
import utilityClasses.FileHandler;
import utilityClasses.Userinput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Menu {

    protected User currentUser;




    public void start() {
        boolean choosing = true;
        while (choosing) {
            System.out.println("1. Register user                   2. Login                      0. Quit");


               int choice = Userinput.promptInt("");

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

    private void createUser() {

        System.out.println("You will need to make a username and password");
        System.out.println();
        String username = Userinput.promptString("Username: ");

        String password = Userinput.promptString("Password: ");

        for(User user : StreamingService.getUsers()){
            if (username.equals(user.getUserName()) && password.equals(user.getPassword())){
                System.out.println("User already exists!");
                return;
            }
        }

        User newUser = new User(username, password, new ArrayList<Media>(), new ArrayList<Media>());

        StreamingService.addUser(newUser);

        System.out.println("User created!");
        System.out.println();
    }

    private void login() {
        String username = Userinput.promptString("Username: ");


        String password = Userinput.promptString("Password: ");

        for (User user : StreamingService.getUsers()) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {

                currentUser = user;
                System.out.println("Login succes!");

                System.out.println();
                showMenu();
            }
        }

        System.out.println("Wrong login!");
    }


    private void showMenu(){
        while (true){
        System.out.println("=== Media library ===");
        System.out.println("1. Search media by title");
        System.out.println("2. Search media by category");
            System.out.println("3. Search media by rating");
        System.out.println("4. See saved media");
        System.out.println("5. See watched media");
        System.out.println("6. Quit streaming service");

            int choice = Userinput.promptInt("What would you like to do?");
            switch (choice){

                case 1:
                    String movieSearch = Userinput.promptString("Search for media:");
                    selectMedia(searchMedia(movieSearch));
                    break;


                case 2:

                    ArrayList<Category> categories = printCatagories();
                    ArrayList<Category> chosenCategories = new ArrayList<>();
                    boolean choosingCategory = true;
                    while (choosingCategory) {
                       int index;
                        while (true) {
                            index = Userinput.promptInt("Which category do you want to search for?") - 1;
                            if (index > categories.size()){
                                System.out.println("Invalid, try again");
                            }else{
                                break;
                            }
                        }

                        System.out.println(categories.get(index) + " added");
                        chosenCategories.add(categories.get(index));
                        boolean additional = Userinput.askYesNo("Do you want to add additional category (y/n)");
                        if (!additional) {
                            choosingCategory = false;
                            ArrayList<Media> sortedList = sortByCategory(chosenCategories);
                            selectMedia(sortedList);
                        }
                    }
                    break;

                case 3:

                    System.out.println("1. Sort from highest to lowest");
                    System.out.println("2. Sort from lowest to highest");
                    System.out.println("3. Sort from custom input (has to be at least this rating to show up)");
                    System.out.println("4. Go back");
                    int ratingChoice = Userinput.promptInt("How do you want to sort by rating?");


                    switch (ratingChoice){

                        case 1:
                            selectMedia(sortByRatingHighestToLowest());

                            break;

                        case 2:
                            selectMedia(sortByRatingLowestToHighest());

                            break;

                        case 3:
                            double chosenRating = Userinput.promptDouble("Input rating to sort by (can be decimal)");
                            selectMedia(atLeastThisRating(chosenRating));

                        break;


                        case 4:

                        break;

                        default:
                            System.out.println("Invalid input");
                            break;
                    }

                    break;

                case 4:
                    if (!currentUser.getSavedMedia().isEmpty()){
                        selectMedia(currentUser.getSavedMedia());
                    }else{
                        System.out.println("No saved movies");
                    }

                    break;

                case 5:
                    if (!currentUser.getWatchedMedia().isEmpty()){
                        selectMedia(currentUser.getWatchedMedia());
                    }else{
                        System.out.println("No watched movies");
                    }

                    break;




                case 6:
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

    private void selectMedia(ArrayList<Media> listToPrint) {

        if (listToPrint.isEmpty()){
            System.out.println("No media with that name / category / rating");
            return;
        }

        int counter = 1;
        for (Media item : listToPrint) {
            System.out.println(counter + ".   " + item);
            counter++;
        }

        System.out.println();

        int choseMovie = 0;
        while (true) {
            choseMovie = Userinput.promptInt("Which media do you want to select? Input 0 to go back. (input number)") - 1;



            if (choseMovie > listToPrint.size()){
                System.out.println("Invalid, try again");
            }else{
                break;
            }


        }

        if(choseMovie < 0) {
            return;
        }else {
            System.out.println("Selected: " + listToPrint.get(choseMovie));

            boolean choosing = true;

            while (choosing) {
                int choiceToDo = Userinput.promptInt("1. Watch " + listToPrint.get(choseMovie).getTitle() + "           2. Add " + listToPrint.get(choseMovie).getTitle() + " to saved list      3. Go back");

                if (choiceToDo == 1) {
                    listToPrint.get(choseMovie).play(currentUser);
                    choosing = false;
                } else if (choiceToDo == 2) {
                    currentUser.addSavedMedia(listToPrint.get(choseMovie));
                    choosing = false;
                } else if (choiceToDo == 3) {
                    break;
                } else {
                    System.out.println("Invalid input");
                }
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




    public ArrayList<Media> sortByCategory(ArrayList<Category> categories) {
        ArrayList<Media> results = new ArrayList<>();


        for (Category category : categories) {
            for (Media media : StreamingService.getAllMedia()) {
                if (media.getCategories().contains(category)) {
                    if (!results.contains(media)) {
                        results.add(media);
                    }
                }
            }
        }

        return results;
    }

    public ArrayList<Category> printCatagories(){
        ArrayList<Category> categories = new ArrayList<>();

        categories.addAll(Arrays.asList(Category.values()));

        int counter = 0;
        for (Category catagory : categories){
            counter++;
            System.out.println(counter + ". " +catagory);
        }
        return categories;
    }

    public ArrayList<Media> sortByRatingLowestToHighest(){
        ArrayList<Media> beforeSorting = new ArrayList<>();
        beforeSorting.addAll(StreamingService.getAllMedia());

        beforeSorting.sort(Comparator.comparingDouble(Media::getRating));

        return beforeSorting;
    }

    public ArrayList<Media> atLeastThisRating(double rating){
        ArrayList<Media> beforeSorting = new ArrayList<>();
        beforeSorting.addAll(StreamingService.getAllMedia());

        beforeSorting.removeIf(item -> item.getRating() < rating);

        beforeSorting.sort(Comparator.comparingDouble(Media::getRating).reversed());

        return beforeSorting;
    }

    public ArrayList<Media> sortByRatingHighestToLowest(){
        ArrayList<Media> beforeSorting = new ArrayList<>();
        beforeSorting.addAll(StreamingService.getAllMedia());

        beforeSorting.sort(Comparator.comparingDouble(Media::getRating).reversed());

        return beforeSorting;

    }
}
