package example.lab2;

public class Lab2 {

    private static int[] board = new int[8];
    private static int variant = 1;

    private static boolean validator(int y) {
        int x = board[y];
        for (int i = 1; i <= y; i++) {
            int t = board[y - i];
            if (t == x
                    || t == x - i
                    || t == x + i) {
                return true;
            }
        }

        return false;
    }

    private static void printBoard() {
        System.out.println("\n");
        System.out.println("Вариант " + variant++);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                System.out.print((board[y] == x) ? "|Q" : "|_");
            }
            System.out.println("|");
        }
    }

    private static void genBoards(int howMany) {
        if (howMany >= 92) {
            howMany = 92;
        }
        if (howMany < 1) {
            howMany = 1;
        }
        int y = 0;
        board[0] = -1;
        while (y >= 0) {
            do {
                board[y]++;
            } while ((board[y] < 8) && validator(y));
            if (board[y] < 8) {
                if (y < 7) {
                    board[++y] = -1;
                } else {
                    printBoard();
                }
            } else {
                y--;
            }

            if (howMany + 1 == variant) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        genBoards(0);
    }
}