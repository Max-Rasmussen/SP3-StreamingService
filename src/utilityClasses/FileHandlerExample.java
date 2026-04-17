package utilityClasses;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandlerExample {






    public static ArrayList<String> readFile(){

        ArrayList<String> Data = new ArrayList<>();
        File movies = new File("Data/Movies.csv");
        Scanner readFile;

        try {
            readFile = new Scanner(movies);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Fil pathen er forkert");
        }

        while (readFile.hasNextLine()){
            Data.add(readFile.nextLine());
        }

        ArrayList<Media> convertedMedia = new ArrayList<>();
        for (String line : Data){
            String[] splittedData = line.split(";");

            String mediaName = splittedData[0];

            int releaseYear = Integer.parseInt(splittedData[1]);

            String[] catagoriesSplit = splittedData[2].split(",");


            Media movie = new Media(mediaName, releaseYear, catagoriesSplit, rating);
            convertedMedia.add(movie);
        }
        return convertedMedia;

    }



}
