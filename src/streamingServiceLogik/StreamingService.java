package streamingServiceLogik;

import utilityClasses.FileHandler;


import java.util.ArrayList;

public class StreamingService {

    protected ArrayList<User> users;
    protected ArrayList<Media> movies;
    protected ArrayList<Media> series;


    public StreamingService(){
        loadMedia();
        loadUsers();
    }

    private void loadMedia(){
       this.movies = FileHandler.loadMediaItems("Data/Movies.csv");
       this.series = FileHandler.loadMediaItems("Data/Series.csv");
    }


    private void loadUsers(){
        this.users = FileHandler.loadUsers("Data/Users.csv");
    }




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
}
