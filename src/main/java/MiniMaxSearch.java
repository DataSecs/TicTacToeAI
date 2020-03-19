/*
 * Created with love by DataSecs on 16.03.2020.
 */
public class MiniMaxSearch {

    public static void miniMax(int player) {
        int map = Map.map;
        Map.computerPlayer = player;

        int value = Integer.MIN_VALUE;

        for (int move : Map.moves) {
            int newValue = minValue(Map.setTile(map, move, player), (player + 1) % 2);

            if (newValue > value) {
                Map.returnMove = move;
                value = newValue;
            }
        }
    }

    private static int maxValue(int map, int player) {
        // Terminal test
        int result = Map.utility(map);
        if (result != 100) {
            return result;
        }

        Map.visitedStates++;
        int value = Integer.MIN_VALUE;

        // Calculate available moves
        int[] moves = new int[9];
        int moveSize = 0;
        for (int position = 0; position < 9; position++) {
            if ((map & (1 << position + 9)) != (1 << position + 9) && (map & (1 << position)) != (1 << position)) {
                moves[moveSize++] = position;
            }
        }

        // Execute moves
        for (int i = 0; i < moveSize; i++) {
            value = Math.max(value, minValue(Map.setTile(map, moves[i], player), (player + 1) % 2));
        }

        return value;
    }

    private static int minValue(int map, int player) {
        // Terminal test
        int result = Map.utility(map);
        if (result != 100) {
            return result;
        }

        Map.visitedStates++;
        int value = Integer.MAX_VALUE;

        // Calculate available moves
        int[] moves = new int[9];
        int moveSize = 0;
        for (int position = 0; position < 9; position++) {
            if ((map & (1 << position + 9)) != (1 << position + 9) && (map & (1 << position)) != (1 << position)) {
                moves[moveSize++] = position;
            }
        }

        // Execute moves
        for (int i = 0; i < moveSize; i++) {
            value = Math.min(value, maxValue(Map.setTile(map, moves[i], player), (player + 1) % 2));
        }

        return value;
    }
}