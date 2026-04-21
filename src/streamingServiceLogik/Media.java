package streamingServiceLogik;



import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;

public class Media {
    private String title;
    private ArrayList<Category> categories;
    private double rating;
    private String releaseYear;
    private String onAir;
    private String seasons;


// behøver måske ikke geter for episodes da seasons String vil indeholde

    //Constructerene for movie&series (lavede det for at prøve fileReaderen)
    //String type skal skiftes ud med Enum på begge
    //Film csv eksempel: The Godfather; 1972; Crime, Drama; 9,2;
    //Serires csv eksempel: Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;

    public Media(String title, String releaseYear, ArrayList<Category> categories, double rating){
this.title = title;
this.releaseYear = releaseYear;
this.categories = categories;
this.rating = rating;





    }



    public Media(String title, String onAir, ArrayList<Category> categories, double rating, String seasons){
this.title = title;
this.onAir = onAir;
this.categories = categories;
this.rating = rating;
this.seasons = seasons;


    }


    public String getTitle() {
        return title;
    }

    public double getRating(){
        return rating;
    }


    public String getReleaseYear(){
        return releaseYear;
    }

    public String getOnAir(){
        return onAir;

    }
    public String getSeasons(){
        return seasons;
    }


    public String toString(){
        return title;
        // ikke færdig
    }

    public void play(){
        System.out.println( title + "afspilles nu ");
    }

    }
