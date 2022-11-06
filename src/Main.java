import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private String word;
    private String wordInverted;

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

        word = word.replaceAll("\\s", "");
        word = word.toLowerCase();
    }
    
    private int method(){
        int selection = 0;
        Scanner sel = new Scanner(System.in);
        
        try{
            System.out.println("""
                    \nSelect method:\s
                    1. Using reverse() function\s
                    2. Using for loop\s
                    3. Using getBytes()\s
                    4. Using buffer\s
                    5. Exit\s""");
            
            selection = sel.nextInt();
            while(selection < 0 || selection > 5){
                System.out.println("Selection out of range. Try again...");
                selection = sel.nextInt();
            }
        }catch (InputMismatchException ex){
            System.out.println("Integer expected! Try again");
            sel.nextLine();
        }
        return selection;
    }

    private void checker_usingReverse(){
        StringBuilder wordInvertedF = new StringBuilder();
        wordInvertedF.append(word);
        wordInverted = String.valueOf(wordInvertedF.reverse());
        resultPrinter();
    }

    private void checker_usingForLoop(){
        char[] invertedChars = new char[word.length()];
        int cnt = 0;

        for(int i = word.length() - 1; i >= 0; i--){
            invertedChars[cnt] = word.charAt(i);
            cnt++;
        }
        wordInverted = new String (invertedChars);
        resultPrinter();
    }

    private void checker_usingGetBytes(){
        byte[] byteArray = word.getBytes();
        byte[] result = new byte[byteArray.length];

        for(int i = 0; i < byteArray.length; i++){
            result[i] = byteArray[byteArray.length - i - 1];
        }
        wordInverted = new String(result);
        resultPrinter();
    }

    private void checker_usingBuffer(){
        StringBuffer inverted = new StringBuffer(word);
        wordInverted = String.valueOf(inverted.reverse());
        resultPrinter();
    }

    protected void resultPrinter(){
        System.out.print("\nword: " + ANSI_YELLOW + word + ANSI_RESET +
                "\nInverted word: " + ANSI_YELLOW + wordInverted + ANSI_RESET +
                "\nAre the words the same?: ");

        if (word.compareTo(String.valueOf(wordInverted)) == 0){
            System.out.print( ANSI_GREEN + "Yes\n\n" + ANSI_RESET);
        }else System.out.print(ANSI_RED + "No\n\n" + ANSI_RESET);
    }



    public static void main(String[] args) {
        boolean running = true;
        Main obj = new Main();

        while(running) {
            obj.input();
            switch (obj.method()) {
                case 1 -> obj.checker_usingReverse();
                case 2 -> obj.checker_usingForLoop();
                case 3 -> obj.checker_usingGetBytes();
                case 4 -> obj.checker_usingBuffer();
                case 5 -> running = false;
                default -> throw new IllegalStateException("Unexpected value: " + obj.method());
            }
        }
    }
}