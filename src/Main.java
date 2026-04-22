import streamingServiceLogik.Media;
import streamingServiceLogik.StreamingService;
import streamingServiceLogik.User;
import utilityClasses.FileHandler;

import java.util.ArrayList;

public class Main {

    static void main() {

        StreamingService test = new StreamingService("HBO");


        test.startStreamingService();


    }
}
