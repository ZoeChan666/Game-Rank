import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] averageCombatScore = {304.0, 276.9, 236.2, 264.1, 233.5, 210.9, 119.1};
        double[] kda = {1.88, 1.93, 1.56, 1.44, 1.51, 1.32, 0.91};
        int[] playerIds = {37990, 18308, 70831, 92010, 59071, 93014, 204165};
        String[] playerNames = {"Pleia", "RrRRr", "chris", "Asuka", "JogAU", "Twilight", "Alice"};

        Scanner scanner = new Scanner(System.in);
        try {
            checkAverageCombatScore(averageCombatScore, playerIds, playerNames, scanner);
            checkKdaRanking(kda, playerIds, playerNames, scanner);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void checkAverageCombatScore(double[] averageCombatScore, int[] playerIds, 
            String[] playerNames, Scanner scanner) {
        Map<Integer, Integer> idToIndex = new HashMap<>();
        for (int i = 0; i < playerIds.length; i++) {
            idToIndex.put(playerIds[i], i);
        }

        System.out.println("Please enter the user's ID whose AverageCombatScore you want to check:");
        try {
            int userId = scanner.nextInt();
            Integer index = idToIndex.get(userId);
            
            if (index != null) {
                System.out.printf("The user's AverageCombatScore you want to check is %s,%.1f%n",
                    playerNames[index], averageCombatScore[index]);
            } else {
                System.out.println("User ID not found!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // 清除输入缓冲
        }
    }

    private static void checkKdaRanking(double[] kda, int[] playerIds, 
            String[] playerNames, Scanner scanner) {
        Player[] players = new Player[kda.length];
        for (int i = 0; i < kda.length; i++) {
            players[i] = new Player(playerNames[i], playerIds[i], kda[i]);
        }

        java.util.Arrays.sort(players, (a, b) -> Double.compare(b.kda, a.kda));

        while (true) {
            System.out.println("\nPlease enter the player ID to check their KDA and ranking (enter -1 to exit):");
            try {
                int searchId = scanner.nextInt();
                if (searchId == -1) {
                    break;
                }
                
                boolean found = false;
                for (int i = 0; i < players.length; i++) {
                    if (players[i].id == searchId) {
                        System.out.println(String.format("%s#%d KDA:%.2f Rank:%d",
                            players[i].name, players[i].id, players[i].kda, i + 1));
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    System.out.println("Player ID not found!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // 清除输入缓冲
            }
        }
    }

    private static class Player {
        String name;
        int id;
        double kda;

        Player(String name, int id, double kda) {
            this.name = name;
            this.id = id;
            this.kda = kda;
        }
    }
}