package minesweeper.core;

import GameStudio.states.TileState;

public abstract class Tile {
	private TileState state = TileState.CLOSED;
	
	public TileState getState() {
		return state;
	}
	
	void setState(TileState state) {
		this.state = state;
	}
}
