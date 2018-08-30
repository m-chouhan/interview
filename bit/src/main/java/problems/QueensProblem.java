package problems;

import java.util.Stack;

/**
 * Prints Solution of QueensProblem
 * https://www.geeksforgeeks.org/printing-solutions-n-queen-problem/
 */
public class QueensProblem {

    static abstract class ChessPiece {
        int x;
        int y;

        ChessPiece(int x, int y) {
            this.x = x;
            this.y = y;
        }

        abstract boolean marks(ChessPiece cp);

        @Override
        public abstract String toString();
    }

    static class Queen extends ChessPiece {

        Queen(int x, int y) {
            super(x, y);
        }

        @Override
        boolean marks(ChessPiece cp) {
            return (cp.x == x ||
                    cp.y == y ||
                    Math.abs(cp.x - x) == Math.abs(cp.y - y)
            );
        }

        @Override
        public String toString() {
            String str = "";
            for (int i = 1; i <= 8; ++i) {
                if (i == y) str += "Q";
                else str += "--";
            }
            return str;
        }
    }

    public static void main(String args[]) {
        Stack<ChessPiece> Solution = new Stack<>();
        Solution.push(new Queen(1, 1));
        ChessPiece current = new Queen(2, 1);

        while (Solution.size() < 8) {

            if (!checkForConflicts(current, Solution)) {
                Solution.add(current);
                current = new Queen(current.x + 1, 1);
            } else current = tryToResolveConflicts(current, Solution);
        }

        Solution.forEach(item -> System.out.println(item.toString()));
        System.out.println("*****************");
    }

    private static ChessPiece tryToResolveConflicts(ChessPiece current, Stack<ChessPiece> solution) {

        if (current.y >= 8) {
            if (solution.size() == 0) throw new RuntimeException("No Solution Exist for this!!");
            return tryToResolveConflicts(solution.pop(), solution);
        } else current.y++;
        return current;
    }


    private static boolean checkForConflicts(ChessPiece current, Stack<ChessPiece> solution) {
        for (ChessPiece item : solution) {
            if (item.marks(current))
                return true;
        }
        return false;
    }
}
