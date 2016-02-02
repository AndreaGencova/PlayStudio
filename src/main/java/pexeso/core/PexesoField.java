package pexeso.core;

import java.util.Random;

import GameStudio.states.GameState;
import GameStudio.states.TileState;

public class PexesoField {

	private final int rowCount;
	private final int columnCount;
	private final Card[][] field;
	private GameState state = GameState.PLAYING;
	private final String game = "Pexeso";
	private long startMillis;

	public PexesoField(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;

		if (!(rowCount > 0 && columnCount > 0)) {
			throw new IllegalArgumentException("Cele zle");
		}

		if (rowCount * columnCount % 2 != 0)
			throw new IllegalArgumentException("Pocet prvkov musi byt parny");

		field = new Card[rowCount][columnCount];
		generate();
		startMillis = System.currentTimeMillis();
	}

	private void generate() {
		Random random = new Random();
		int counter = 0;
		int pair = 0;

		while (counter < rowCount * columnCount / 2) {
			while (pair < 2) {

				int row = random.nextInt(rowCount);
				int column = random.nextInt(columnCount);

				if (field[row][column] == null) {
					field[row][column] = new Card((char) (counter + 'A'));
					field[row][column].setState(TileState.CLOSED);
					pair++;
				}
			}
			counter++;
			pair = 0;
		}
	}

	public boolean isSameCards(Card card1, Card card2) {
		return card1.getValue() == card2.getValue();

	}

	public void openField(Card card) {
		if (card.getState().equals(TileState.CLOSED))
			card.setState(TileState.OPEN);
	}

	public void closeField(Card card) {
		if (card.getState().equals(TileState.OPEN))
			card.setState(TileState.CLOSED);
	}

	public void markFiel(Card card) {
		card.setState(TileState.MARKED);
	}

	public boolean isSolved() {
		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++)
				if (!field[row][column].getState().equals(TileState.MARKED))
					return false;
		return true;
	}

	public Card getTile(int row, int column) {
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

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

}
