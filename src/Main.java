import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public String word;

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void input(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter word or sentence you want to check: ");

        try{
            word = scan.nextLine();

            while(word.isEmpty()){
                System.out.println("String cannot be empty!");
                word = scan.nextLine();
            }
        }catch (InputMismatchException ex) {
            System.out.println("Unknown error! Try again");
            word = scan.nextLine();
        }
    }

    private void checker_usingReverse(){
        word = word.replaceAll("\\s", "");
        word = word.toLowerCase();

        StringBuilder wordInverted = new StringBuilder();
        wordInverted.append(word);
        wordInverted.reverse();


        System.out.print("word: " + ANSI_YELLOW + word + ANSI_RESET +
                        "\nInverted word: " + ANSI_YELLOW + wordInverted + ANSI_RESET +
                        "\nAre the words the same?: ");

        if (word.compareTo(String.valueOf(wordInverted)) == 0){
            System.out.print( ANSI_GREEN + "Yes" + ANSI_RESET);
        }else System.out.print(ANSI_RED + "No" + ANSI_RESET);
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.input();
        obj.checker_usingReverse();
    }
}