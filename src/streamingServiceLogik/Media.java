package streamingServiceLogik;

public class Media {
    protected String title;

    //Constructerene for movie&series (lavede det for at prøve fileReaderen)
    //String type skal skiftes ud med Enum på begge
    //Film csv eksempel: The Godfather; 1972; Crime, Drama; 9,2;
    //Serires csv eksempel: Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;

    public Media(String title, String releaseYear, String catagories, double rating){
this.title = title;
    }



    public Media(String title, String onAir, String catagories, double rating, String seasons){
this.title = title;
    }


    public String getTitle() {
        return title;
    }
}
