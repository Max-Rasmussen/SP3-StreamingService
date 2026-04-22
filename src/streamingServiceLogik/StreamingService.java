package streamingServiceLogik;

import utilityClasses.FileHandler;



import java.awt.*;
import java.util.ArrayList;

public class StreamingService {

    private String streamingServiceName;
    protected static ArrayList<User> users;
    public static ArrayList<Media> movies;
    public static ArrayList<Media> series;
    private Menu menu;


    public StreamingService(String name){
        this.streamingServiceName = name;
        this.menu = new Menu();
        loadMedia();
        loadUsers();

    }

    private void loadMedia(){
        movies = FileHandler.loadMediaItems("Data/Movies.csv");
        series = FileHandler.loadMediaItems("Data/Series.csv");
    }

    private void loadUsers(){
        users = FileHandler.loadUsers("Data/Users.csv");
    }

    public static ArrayList<Media> getMovies() {
        return movies;
    }


    public static ArrayList<Media> getSeries() {
        return series;
    }

    public static ArrayList<Media> getAllMedia(){
        ArrayList<Media> allMedia = new ArrayList<>();
        allMedia.addAll(movies);
        allMedia.addAll(series);
        return allMedia;
    }

    public static ArrayList<User> getUsers(){
        return users;
    }



    public static void addUser(User user){
        users.add(user);
    }

    public void startStreamingService(){
        System.out.println("=== Welcome to " + streamingServiceName + "===");
        System.out.println();
        menu.start();

    }


}





