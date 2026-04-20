package utilityClasses;

import streamingServiceLogik.Media;
import streamingServiceLogik.StreamingService;
import streamingServiceLogik.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {


    private static ArrayList<String> readData(String filepath) {

        File fileWeWantToRead = new File(filepath);

        //Vi sætter den ikke til at være vores læser endnu, da man skal gøre dette i en try catch.
        Scanner scannerThatReadsFile;

        try {
            scannerThatReadsFile = new Scanner(fileWeWantToRead);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("A file could not be read in readData");
        }


        //Laver en arraylist og tilføjer string linjerne til den
        ArrayList<String> stringLines = new ArrayList<>();
        while (scannerThatReadsFile.hasNextLine()) {
            stringLines.add(scannerThatReadsFile.nextLine());
        }
        return stringLines;
    }


    public static ArrayList<Media> loadMediaItems(String filePath) {
        ArrayList<String> lines = readData(filePath);

        boolean isASeries = false;


        //Ny tom arraylist blir lavet som vi fylder med media objekter senere
        ArrayList<Media> mediaItems = new ArrayList<>();

        //Tjekker først hvilken slags media det er
        //Hvis den er mindre end 4 er det en movie, ellers en serie

        String[] checkMedia = lines.getFirst().split(";");
        if (checkMedia.length > 4) {
            isASeries = true;
        }

        for (String line : lines) {
            String[] splitLine = line.split(";");

            String title = splitLine[0];
            String releaseDateOrRunTime = splitLine[1];
            String catagories = splitLine[2];


            //Der var problemer med ratings fordi de brugte et komma i stedet for et punktum i csv'en, dette var mit work around

            //Jeg splitter ratings op, hvis der nu er 2 ting efter at ha splittet det op samler jeg dem igen med et punktum i stedet
            //Derefter kan jeg parse det med Double.parseDouble

            String[] ratingsSplitted = splitLine[3].split(",");
            double ratings;
            if (ratingsSplitted.length == 1) {
                ratings = Double.parseDouble(ratingsSplitted[0].trim());
            } else {
                ratings = Double.parseDouble(ratingsSplitted[0].trim() + "." + ratingsSplitted[1].trim());
            }

            String seasons;
            if (isASeries) {
                seasons = splitLine[4];
                Media newSeries = new Media(title, releaseDateOrRunTime, catagories, ratings, seasons);
                mediaItems.add(newSeries);
            } else {
                Media newMovie = new Media(title, releaseDateOrRunTime, catagories, ratings);
                mediaItems.add(newMovie);
            }
        }
        return mediaItems;
    }

    public static ArrayList<Media> handleUserSavedWatchedMovie(String[] splittedSavedList){


        ArrayList<Media> theMovies = StreamingService.getMovies();
        ArrayList<Media> theSeries = StreamingService.getSeries();

        ArrayList<Media> savedMedia = new ArrayList<>();

        for (String indexnumberthingy : splittedSavedList){

                if (indexnumberthingy.contains("m")) {
                    String withoutLetter;
                    if (indexnumberthingy.contains("W")) {
                        withoutLetter = indexnumberthingy.replace("Wm", "");
                    }else{
                        withoutLetter = indexnumberthingy.replace("m", "");
                    }
                    int theActualMovieNumber = Integer.parseInt(withoutLetter);
                    savedMedia.add(theMovies.get(theActualMovieNumber));

                } else {
                    String withoutLetter;
                    if (indexnumberthingy.contains("W")) {
                        withoutLetter = indexnumberthingy.replace("Ws", "");
                    }else{
                        withoutLetter = indexnumberthingy.replace("s", "");
                    }
                    int theActualSeriesNumber = Integer.parseInt(withoutLetter);
                    savedMedia.add(theSeries.get(theActualSeriesNumber));
                }
            }
        return savedMedia;
    }

    public static ArrayList<User> loadUsers(String filePath) {
        ArrayList<String> lines = readData(filePath);

        //Ny tom arraylist blir lavet som vi fylder med user objekter

        ArrayList<User> users = new ArrayList<>();


        //Blir ved med at lave lines om til user til der ik er fler lines
        for (String line : lines) {
            String[] splitLine = line.split(";");

            String userName = splitLine[0];
            String userPassWord = splitLine[1];

            String[] splittedSavedList = splitLine[2].split(",");
            String[] splittedWatchedList = splitLine[3].split(",");
            ArrayList<Media> savedMedia = handleUserSavedWatchedMovie(splittedSavedList);
            ArrayList<Media> watchedMeda = handleUserSavedWatchedMovie(splittedWatchedList);


            User newUser = new User(userName, userPassWord, savedMedia, watchedMeda);
            users.add(newUser);
        }
        return users;
    }

    public static void saveUsers(ArrayList<User> users) {
        try (FileWriter writer = new FileWriter("Data/Users.csv")) {
            for (User user : users) {
                writer.write(user.getUserName() + ";" + user.getPassword() + ";");

                for (Media item : user.getSavedMedia()) {
                    for (Media item2 : StreamingService.getMovies()) {
                        int counter = 1;
                        if (item == item2) {
                            writer.write("m" + counter + ",");
                        }
                        counter++;
                    }

                    for (Media item3 : StreamingService.getSeries()) {
                        int counter = 1;
                        if (item == item3) {
                            writer.write("s" + counter + ",");
                        }
                        counter++;
                    }
                    writer.write(";");
                }

                for (Media item : user.getWatchedMovie()) {
                    for (Media item3 : StreamingService.getMovies()) {
                        int counter = 1;
                        if (item == item3){
                            writer.write("m" + counter + ",");
                        }
                        counter++;
                    }

                    for (Media item4 : StreamingService.getSeries()){
                        int counter = 1;
                        if (item == item4){
                            writer.write("s" + counter + ",");
                        }
                        counter++;
                    }
                    writer.write(";" + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil!");
        }
    }
}
