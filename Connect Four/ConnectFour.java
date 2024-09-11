// A class to represent a game of connect four that extends the
// AbstractStrategyGame abstract class.

import java.util.*;

public class ConnectFour extends AbstractStrategyGame {

  private int[][] board;
  private int currentPlayer;

  // Constructs a new Connect Four game
  // Initiliazes the current player to player 1
  // Initiliazes an empty board
  public ConnectFour() {
    currentPlayer = 1;
    board =
      new int[][] {
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
      };
  }

  // Takes input from the parameter to specify the move the player
  // with the next turn wishes to make, then executes that move.
  // The player is prompted to enter a column number between 1 and 7.
  // The player's piece is placed in the lowest available row in the specified column.
  // If any part of the move is illegal, throws an IllegalArgumentException.
  // The following are considered illegal moves:
  // - Entering a number that is not between 1 and 7
  // - Entering a number for a column that is full
  public void makeMove(Scanner input) {
    System.out.print("Which column? (1-7): ");
    if (!input.hasNextInt()) {
      throw new IllegalArgumentException(
        "Please enter a number between 1 and 7."
      );
    }

    int column = input.nextInt();
    if (column < 1 || column > 7) {
      throw new IllegalArgumentException(
        "Please enter a number between 1 and 7."
      );
    }

    column--;

    boolean inProgress = true;
    int rowCheck = 5;
    while (inProgress) {
      if (rowCheck < 0) {
        throw new IllegalArgumentException("That column is full!");
      }
      if (board[rowCheck][column] == 0) {
        board[rowCheck][column] = currentPlayer;
        inProgress = false;
      } else {
        rowCheck--;
      }
    }
    switchPlayer();
  }

  // Switches the current player
  // If the current player is 1, it switches to player 2
  // If the current player is 2, it switches to player 1
  private void switchPlayer() {
    if (currentPlayer == 1) {
      currentPlayer = 2;
    } else {
      currentPlayer = 1;
    }
  }

  // Returns the index of the player who has won the game,
  // 1 if player 1, 2 if player 2, 0 if a tie occurred,
  // and -1 if the game is not over.
  public int getWinner() {
    if (isBoardFull()) {
      return 0;
    }

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (board[i][j] != 0) {
          if (j + 3 < 7) {
            if (
              board[i][j] == board[i][j + 1] &&
              board[i][j] == board[i][j + 2] &&
              board[i][j] == board[i][j + 3]
            ) {
              return board[i][j];
            }
          }
          if (i + 3 < 6) {
            if (
              board[i][j] == board[i + 1][j] &&
              board[i][j] == board[i + 2][j] &&
              board[i][j] == board[i + 3][j]
            ) {
              return board[i][j];
            }
          }
          if (i + 3 < 6 && j + 3 < 7) {
            if (
              board[i][j] == board[i + 1][j + 1] &&
              board[i][j] == board[i + 2][j + 2] &&
              board[i][j] == board[i + 3][j + 3]
            ) {
              return board[i][j];
            }
          }
          if (i + 3 < 6 && j - 3 >= 0) {
            if (
              board[i][j] == board[i + 1][j - 1] &&
              board[i][j] == board[i + 2][j - 2] &&
              board[i][j] == board[i + 3][j - 3]
            ) {
              return board[i][j];
            }
          }
        }
      }
    }
    return -1;
  }

  // Returns true if the board is full, false otherwise
  private boolean isBoardFull() {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (board[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  // Constructs and returns a String that provides detailed instructions for playing the game.
  public String instructions() {
    return (
      "Welcome to Connect Four!\n" +
      "----------------------------------\n" +
      "HOW TO READ THE GAME STATE:\n" +
      "The game board is displayed as a 7x6 grid of characters. Each cell can be:\n" +
      "- '1' for Player 1's discs\n" +
      "- '2' for Player 2's discs\n" +
      "- '0' for an empty slot\n" +
      "The columns are numbered from 1 to 7.\n" +
      "\n" +
      "HOW TO MAKE A MOVE:\n" +
      "To make a move, enter the column number where you would like to drop your disc.\n" +
      "The disc will fall into the lowest available space within that column.\n" +
      "\n" +
      "HOW THE GAME ENDS:\n" +
      "The game ends when one player forms a horizontal, vertical, or diagonal line\n" +
      "of four of their discs, or when all slots are filled, resulting in a draw.\n" +
      "\n" +
      "WHO WINS:\n" +
      "The winner is the first player to align four of their discs vertically,\n" +
      "horizontally, or diagonally. If all slots are filled and no player has\n" +
      "accomplished this, the game is declared a draw.\n" +
      "\n" +
      "ADDITIONAL INFORMATION:\n" +
      "Players alternate turns, starting with the player who holds the 1 discs.\n" +
      "Use strategic placement and foresight to outmaneuver your opponent and\n" +
      "block their attempts to connect four while working towards your own four-in-a-row."
    );
  }

  // Returns a String representation of the current state of the board.
  public String toString() {
    String result = "";
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        result += board[i][j] + " ";
      }
      result += "\n";
    }
    return result;
  }

  // Returns the index of the player who will take the next turn.
  public int getNextPlayer() {
    return currentPlayer;
  }
}

