package streamingServiceLogik;




import java.util.ArrayList;
import java.util.Locale;

public class Media {
    private String title;
    private ArrayList<streamingServiceLogik.Category> categories;
    private double rating;
    private String releaseYear;
    private String onAir;
    private String seasons;



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



        @Override
        public String toString() {
            if (seasons != null) {
                return title + " " + releaseYear + "  Rating: " + rating + " Kategorier: " + categories + " Sæsoner " + seasons;

            }
            return title + " " + releaseYear + " Rating: " + rating + " Kategorier: " + categories;


        }



    public void play(){
        System.out.println( title + "afspilles nu ");
    }

}
