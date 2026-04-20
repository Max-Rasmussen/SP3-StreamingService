package streamingServiceLogik;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static sun.security.util.KnownOIDs.Data;


public class User {

    private String userName;
    private String password;
    private ArrayList<Media> savedMedia;
    private ArrayList<Media> watchedMovies;

    public User(String userName, String userPassWord) {
        this.userName = userName;
        this.password = userPassWord;
        this.savedMedia = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
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


    @Override
    public String toString() {
        return "Username: " + userName +
                "\nSaved movies: " + savedMedia.size() +
                "\nWatched movies: " + watchedMovies.size();
    }

}