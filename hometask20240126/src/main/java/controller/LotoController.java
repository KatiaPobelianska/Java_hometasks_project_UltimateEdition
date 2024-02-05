package controller;

import model.LotoCard;
import view.LotoView;

public class LotoController {
    private LotoCard playerCard;
    private LotoCard computerCard;
    private LotoView view;

    public LotoController() {
        playerCard = new LotoCard();
        computerCard = new LotoCard();
        view = new LotoView();
    }

    public void playGame() {
        while (true) {
            view.displayMessage("Player's turn:");
            playerTurn();

            if (playerCard.isCardEmpty()) {
                view.displayMessage("Congratulations! You won!");
                break;
            }

            view.displayMessage("Computer's turn:");
            computerTurn();

            if (computerCard.isCardEmpty()) {
                view.displayMessage("Computer won. Better luck next time!");
                break;
            }
        }
    }

    private void playerTurn() {
        int number = view.getInputNumber();
        if (playerCard.markNumber(number)) {
            view.displayMessage("Number " + number + " marked on your card.");
            playerCard.displayCard();
        } else {
            view.displayMessage("Invalid number. Try again.");
        }
    }

    private void computerTurn() {
        int number = (int) (Math.random() * 90) + 1;
        if (computerCard.markNumber(number)) {
            view.displayMessage("Computer marked number " + number + " on its card.");
            computerCard.displayCard();
        }
    }
}
