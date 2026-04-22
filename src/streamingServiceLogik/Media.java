package streamingServiceLogik;




import java.util.ArrayList;


public class Media {
    private String title;
    private ArrayList<Category> categories;
    private double rating;
    private String releaseYear;
    private String onAir;
    private String seasons;


    public Media(String title, String releaseYear, ArrayList<Category> categories, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.categories = categories;
        this.rating = rating;
    }

    public Media(String title, String onAir, ArrayList<Category> categories, double rating, String seasons) {
        this.title = title;
        this.onAir = onAir;
        this.categories = categories;
        this.rating = rating;
        this.seasons = seasons;
    }


    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }


    public String getReleaseYear() {
        return releaseYear;
    }

    public String getOnAir() {
        return onAir;

    }

    public String getSeasons() {
        return seasons;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }


    @Override
    public String toString() {
        if (seasons != null) {
            return title + " " + onAir + " Rating: " + rating + " Kategorier: " + categories + " Sæsoner: " + seasons;
        }
        return title + " " + releaseYear + " Rating: " + rating + " Kategorier: " + categories;
    }

        public void play (User user) {
            System.out.println(title + "afspilles nu ");
            user.addWatchedMovies(this);
        }

    }
