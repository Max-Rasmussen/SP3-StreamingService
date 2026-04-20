package streamingServiceLogik;

import utilityClasses.FileHandler;


import java.util.ArrayList;

public class StreamingService {

    private String streamingServiceName;
    protected ArrayList<User> users;
    protected static ArrayList<Media> movies;
    protected static ArrayList<Media> series;
    private Menu menu;


    public StreamingService(String name){
        this.streamingServiceName = name;
        loadMedia();
        loadUsers();
    }

    public static ArrayList<Media> getMovies() {
        return movies;
    }


    public static ArrayList<Media> getSeries() {
        return series;
    }

    private void loadMedia(){
       movies = FileHandler.loadMediaItems("Data/Movies.csv");
       series = FileHandler.loadMediaItems("Data/Series.csv");
    }


    private void loadUsers(){
        this.users = FileHandler.loadUsers("Data/Users.csv");
    }

    public void startStreamingService(){
        System.out.println("=== Velkommen til " + streamingServiceName + "===");
    }

    public void testUser(){
        System.out.println(users.getFirst().getSavedMedia());
        System.out.println(users.getFirst().getWatchedMovie());
    }
}


/*
public void testOmUserOgMediaBlirLoadet(){

        for (User user : users){
            System.out.println(user.getUserName());
        }

        System.out.println();
        System.out.println();
        System.out.println();

        for (Media movie : movies){
            System.out.println(movie.getTitle());
        }
        System.out.println();
        System.out.println();
        System.out.println();

       for (Media series : series){
           System.out.println(series.getTitle());
       }
    }
 */