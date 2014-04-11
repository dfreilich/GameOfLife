import java.util.Random;

public class GameOfLife {
    final static int ROWS = 7;
    final static int COLUMNS = 8;
    final static String FILLED_SPOT = "X";
    String[][] simulator;
    int[][] neighbors;
    private Random randomGenerator;

    public GameOfLife() {
        simulator = new String[ROWS + 1][COLUMNS + 1];
        neighbors = new int[ROWS + 1][COLUMNS + 1]; 
        randomGenerator = new Random();
        for (int i = 0; i <= ROWS; i++) {
            for (int j = 0; j <= COLUMNS; j++) {
                simulator[i][j] = " ";
                neighbors[i][j] = 0;
            }
        }
    }

    public void fillSpot (int row, int column) {
        simulator [row][column] = FILLED_SPOT;
    } 

    private void deleteSpot (int row, int column) {
        simulator[row][column] = " ";
    }
    
	public void randomSimulation() {
        for (int i = 1; i < ROWS; i++) {
            for (int j = 1; j < COLUMNS; j++) {
                int random = randomGenerator.nextInt(2);
                if (random == 1) {
                    fillSpot(i,j);
                }
            }
        }
    }

    private void getNeighbors (int row, int column) {
        int numNeighbors = 0;
        if (row < ROWS && row > 0 && column < COLUMNS && column > 0) {
            for (int i = row - 1; i<= row + 1; i++) {
                for (int j = column - 1; j<= column + 1; j++) {
                    String temp = simulator[i][j];
                    if (temp.contains(FILLED_SPOT)) {
                        numNeighbors++;
                    }
                }
            }
        }
        if (simulator[row][column].contains(FILLED_SPOT)) {
            numNeighbors--;
        }
        neighbors[row][column] = numNeighbors;
    }

    private void nextGenPlanning() {
        for (int i = 1; i < ROWS; i++) {
            for (int j = 1; j < COLUMNS; j++) {
                getNeighbors(i,j);
            }
        }
    }

    public void nextGen() {
        nextGenPlanning();
        for (int i = 1; i < ROWS; i++) {
            for (int j = 1; j < COLUMNS; j++) {
                if ((neighbors[i][j] >= 1) && neighbors[i][j] <= 3) {
                    fillSpot(i,j);
                }
                else {
                    deleteSpot(i,j);
                }
            }
        }
    }

    public String toString() {
        String string = "";
        for (int i = 1; i < ROWS; i++) {
            string += "|";
            for (int j = 1; j < COLUMNS; j++) {
                String temp = simulator[i][j];
                string += temp.charAt(0);
            }
            string += "|\n";
        }
        return string;
    }

    public void simulate (int numOfTrials) {
        String string = toString() + "\n--------------\n";
        for (int i = 0; i < numOfTrials; i++) {
            nextGen();
            string += toString() + "\n--------------\n";
        }
        System.out.println (string);
    }
    
    public void randomSimulate (int numOfTrials) {
        randomSimulation();
        String string = toString() + "\n--------------\n";
        for (int i = 0; i < numOfTrials; i++) {
            nextGen();
            string += toString() + "\n--------------\n";
        }
        System.out.println (string);
    }

    public static void main (String [] args) {
        GameOfLife game = new GameOfLife();
        game.randomSimulation();
        game.simulate(15);
    }   
}