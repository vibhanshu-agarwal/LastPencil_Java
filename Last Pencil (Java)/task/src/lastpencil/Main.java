package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("How many pencils would you like to use:");
    Scanner scanner = new Scanner(System.in);
    scanner.useDelimiter("\\r?\\n");
    String pencils = scanner.next();
    do {
      // How to handle blank input?
      if (pencils.isBlank()) {
        System.out.println("The number of pencils should be numeric");
        pencils = scanner.next();
        continue;
      }
      // Check if the input is a number, if not display message
      // The number of pencils should be numeric
      if (!pencils.matches("\\d+")) {
        System.out.println("The number of pencils should be numeric");
        pencils = scanner.next();
      }
      // The number of pencils should be positive
      else if (Integer.parseInt(pencils) <= 0) {
        System.out.println("The number of pencils should be positive");
        pencils = scanner.next();
      } else {
        break;
      }

    } while (true);

    System.out.println("Who will be the first (John, Jack)");
    String first = scanner.next();
    do {
      // If the first player is not John or Jack, display message
      if (!first.equals("John") && !first.equals("Jack")) {
        System.out.println("Choose between 'John' and 'Jack'");
        first = scanner.next();
      } else {
        break;
      }
    } while (true);

    // If first is Jack, then John is the bot
//    String bot = first.equals("Jack") ? "John" : "Jack";
    String bot = "Jack";
    String pencilsToTake = "";
    do {
      "|".repeat(Integer.parseInt(pencils)).chars().forEach(c -> System.out.print((char) c));
      System.out.println();
//      System.out.printf("%s's turn!%n", first);
      // If first does not equal bot then it is a human player

      if (!first.equals(bot)) {
        System.out.printf("%s's turn:%n", first);
        pencilsToTake = scanner.next();
        // Handle skipped input
        if (pencilsToTake.isBlank()) {
          pencils = scanner.next();
        }
//        System.out.println(pencilsToTake);
        // If the input is not a number if not display message
        //    Possible values: '1', '2' or '3'
        if (!pencilsToTake.matches("\\d+")
            || Integer.parseInt(pencilsToTake) < 1
            || Integer.parseInt(pencilsToTake) > 3) {
          System.out.println("Possible values: '1', '2' or '3'");
          continue;
        }

      } else {
        System.out.printf("%s's turn!%n", first);
        // Number of pencils to take depends on the number of pencils left
        //          5,9,13,17... - bot takes a random number of pencils from 1 to 3
        //          1 - bot takes the last pencil and loses
        //          4,8,12,16... - bot takes 3 pencils
        //          3,7,11,15... - bot takes 2 pencils
        //          2,6,10,14... - bot takes 1 pencil
        if (Integer.parseInt(pencils) == 1) {
          pencilsToTake = "1"; // bot takes the last pencil and loses
        } else if (Integer.parseInt(pencils) % 4 == 0) {
          pencilsToTake = "3"; // bot takes 3 pencils
        } else if (Integer.parseInt(pencils) % 4 == 1) {
          Random rand = new Random();
          pencilsToTake = String.valueOf(rand.nextInt(3) + 1);
        } else if (Integer.parseInt(pencils) % 4 == 2) {
          pencilsToTake = "1"; // bot takes 1 pencil
        }
        // If the number of pencils is 3, bot takes 2 pencils
        else if (Integer.parseInt(pencils)  % 4 == 3) {
          pencilsToTake = "2";
        }
        // output the bot's move
        System.out.println(pencilsToTake);
      }

      // If pencil taken is more than the number of pencils left, display message
      //    Too many pencils were taken
      if (Integer.parseInt(pencilsToTake) > Integer.parseInt(pencils)) {
        System.out.println("Too many pencils were taken");
        continue;
      }
      pencils = String.valueOf(Integer.parseInt(pencils) - Integer.parseInt(pencilsToTake));
      first = first.equals("John") ? "Jack" : "John";
    } while (Integer.parseInt(pencils) > 0);

    System.out.printf("%s won!%n", first);
  }
}
