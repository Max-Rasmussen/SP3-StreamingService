package streamingServiceLogik;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class User {

    private String userName;
    private String password;
    private ArrayList<Media> savedMedia;
    private ArrayList<Media> watchedMovies;

    public User(String userName, String userPassWord, ArrayList<Media> savedMedia, ArrayList<Media> watchedMedia) {
        this.userName = userName;
        this.password = userPassWord;
        this.savedMedia = savedMedia;
        this.watchedMovies = watchedMedia;
    }




    public static void saveUsers(ArrayList<User> users) {
        try (FileWriter writer = new FileWriter("Data/Users.csv")) {
            for (User user : users) {
                writer.write(user.getUserName() + ";" + user.getPassword() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil!");
        }
    }




    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void addWatchedMovies(Media media) {
        if (!watchedMovies.contains(media)) {
            watchedMovies.add(media);
        }
    }

    public void addSavedMedia(Media media) {
        if (!savedMedia.contains(media)) {
            savedMedia.add(media);
        }
    }

    public void removeSavedMedia(Media media) {
        savedMedia.remove(media);
    }

    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }

    public ArrayList<Media> getWatchedMovie() {
        return watchedMovies;
    }


    public void printWatchedMedia(){

        int counter = 1;
        for (Media item : watchedMovies){
            System.out.println(counter + ". " +item);
            counter++;
        }
    }

    public void printSavedMedia(){

        int counter = 1;
        for (Media item : savedMedia){
            System.out.println(counter + ". " +item);
            counter++;
        }
    }

    @Override
    public String toString() {
        return "Username: " + userName +
                "\nSaved movies: " + savedMedia.size() +
                "\nWatched movies: " + watchedMovies.size();
    }

}