package utilityClasses;

import java.util.Objects;
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
                if (number >= 0){
                    return number;
                }else{
                    System.out.println("Invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid, input number");
            }
        }
        return 0;
    }


    public static String promptString(String message){
        System.out.println(message);

        return userInput.nextLine();
    }


    public static boolean askYesNo(String message){
        while (true){
        String answer = promptString(message);
        if (answer.equals("y") || answer.equals("yes")){
            return true;
        } else if (answer.equals("n") || answer.equals("no")) {
            return false;
        }else{
            System.out.println("Invalid input");
        }
        }
        }
}
