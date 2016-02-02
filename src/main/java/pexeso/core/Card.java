package pexeso.core;

import GameStudio.states.TileState;

public class Card {
	private TileState state = TileState.CLOSED;
	private final char value;

	public Card(char value) {
		this.value = value;
	}

	public TileState getState() {
		return state;
	}

	void setState(TileState state) {
		this.state = state;
	}

	public char getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
