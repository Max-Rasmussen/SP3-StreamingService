package utilityClasses;

import java.util.Scanner;

public class Userinput {
    static Scanner userInput = new Scanner(System.in);

    public static int promptInt(String message){
        System.out.println(message);
        boolean awaitingInput = true;

        while (awaitingInput){
            try {
                int number = userInput.nextInt();
                userInput.nextLine();
                if (number > 1){
                    return number;
                }else{
                    System.out.println("Invalid, input number above 0");
                }
            } catch (Exception e) {
                System.out.println("Invalid, input number");
            }
        }
        return 0;
    }


    public static String promptString(String message){
        System.out.println(message);

        return userInput.nextLine();
    }
}
