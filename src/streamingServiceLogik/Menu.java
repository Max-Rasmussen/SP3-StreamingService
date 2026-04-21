package streamingServiceLogik;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Media> mediaList;
    public Menu(ArrayList<Media> mediaList) {
        this.mediaList = mediaList;
    }
    public ArrayList<Media> searchMedia(String input) {
        ArrayList<Media> results = new ArrayList<>();

        for (Media media : mediaList) {
            if (media.getTitle().toLowerCase().contains(input.toLowerCase())) {
                results.add(media);
            }
        }
        return results;
    }
}

public ArrayList<Media> sortByCategory(Category category) {
    ArrayList<Media> results = new ArrayList<>();

    for (Media media : mediaList) {
        if (media.getCategories().contains(category)) {
            results.add(media);
        }
    }

    return results;
}







