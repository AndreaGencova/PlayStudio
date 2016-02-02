package fifteenPuzle.core;

import java.util.Random;

import GameStudio.states.GameState;

public class PuzzleField {

	private final int rowCount;
	private final int columnCount;
	private final int[][] field;
	private final int mark = 0;
	private GameState state = GameState.PLAYING;
	private final String game = "Puzzle";
	private long startMillis;
	private int actualZeroRow;
	private int actualZeroColumn;

	public PuzzleField(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;

		if (!(rowCount > 0 && columnCount > 0)) {
			throw new IllegalArgumentException("Cele zle");
		}

		field = new int[rowCount][columnCount];
		generate();
		shuffle();
		startMillis = System.currentTimeMillis();
	}

	private void generate() {

		int value = 1;
		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++) {

				field[row][column] = value;
				value++;

			}

		field[rowCount - 1][columnCount - 1] = mark;
	}

	private void shuffle() {
		Random random = new Random();

		for (int shuffleCount = 0; shuffleCount < (rowCount * rowCount) * (columnCount * columnCount); shuffleCount++) {

			int index = random.nextInt(4);

			switch (index) {
			case 0:
				moveToUp();
				break;
			case 1:
				moveToDown();
				break;
			case 2:
				moveToLeft();
				break;
			case 3:
				moveToRight();
				break;

			default:
				break;
			}
		}

	}

	public int getTile(int row, int column) {
		return field[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public String getGame() {
		return game;
	}

	private void getActualPosition() {
		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++)
				if (field[row][column] == mark) {
					actualZeroRow = row;
					actualZeroColumn = column;
				}
	}

	public void moveToUp() {
		getActualPosition();
		if (actualZeroRow >= 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow - 1][actualZeroColumn];
			field[actualZeroRow - 1][actualZeroColumn] = mark;
		}
	}

	public void moveToDown() {
		getActualPosition();
		if (actualZeroRow < rowCount - 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow + 1][actualZeroColumn];
			field[actualZeroRow + 1][actualZeroColumn] = mark;
		}

	}

	public void moveToLeft() {
		getActualPosition();
		if (actualZeroColumn >= 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow][actualZeroColumn - 1];
			field[actualZeroRow][actualZeroColumn - 1] = mark;
		}

	}

	public void moveToRight() {
		getActualPosition();
		if (actualZeroColumn < columnCount - 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow][actualZeroColumn + 1];
			field[actualZeroRow][actualZeroColumn + 1] = mark;
		}
	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

	public boolean isSolved() {
		int value = 1;

		if (field[rowCount - 1][columnCount - 1] != mark) {
			return false;
		}

		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++) {
				if (!(row == rowCount - 1 && column == columnCount - 1)) {
					if (field[row][column] != value) {
						return false;
					}
					value++;
				}

			}
		return true;
	}

}
