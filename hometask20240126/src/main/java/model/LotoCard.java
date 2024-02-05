package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotoCard {
    private boolean[][] card;

    public LotoCard() {
        card = new boolean[3][9];
        initializeCard();
    }

    private void initializeCard() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 90; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        int index = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                card[row][col] = numbers.get(index) <= 15;
                index++;
            }
        }
    }

    public boolean markNumber(int number) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                if (card[row][col] && (col * 10 + row + 1) == number) {
                    card[row][col] = false;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCardEmpty() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                if (card[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayCard() {
        System.out.println("Loto Card:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(card[row][col] ? "X\t" : "\t");
            }
            System.out.println();
        }
    }
}
