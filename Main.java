import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a text to count words:");
        String input = sc.nextLine();
        String[] words = input.split("[\\s\\p{Punct}]+");

       
        int wordCount = 0;
        for (String word : words) 
        {
            if (!word.isEmpty()) 
            {  
                wordCount++;
            }
        }
        System.out.println("Total number of words: " + wordCount);
    }
}

