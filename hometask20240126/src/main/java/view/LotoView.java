package view;

import java.util.Scanner;

public class LotoView {
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getInputNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        return scanner.nextInt();
    }
}
