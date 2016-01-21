package GameStudio.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fifteenPuzle.consoleui.PuzzleConsoleUI;
import minesweeper.consoleui.MineConsoleUI;
import pexeso.consoleui.PexesoConsoleUI;

@Component
public class GameStudio {
	@Autowired
	private MineConsoleUI mui;

	@Autowired
	private PuzzleConsoleUI pui;

	@Autowired
	private PexesoConsoleUI peui;

	public MineConsoleUI getMui() {
		return mui;
	}

	public void setMui(MineConsoleUI mui) {
		this.mui = mui;
	}

	public PuzzleConsoleUI getPui() {
		return pui;
	}

	public void setPui(PuzzleConsoleUI pui) {
		this.pui = pui;
	}

	public PexesoConsoleUI getPeui() {
		return peui;
	}

	public void setPeui(PexesoConsoleUI peui) {
		this.peui = peui;
	}

	static final Scanner sc = new Scanner(System.in);

	public void play() {
		System.out.println("Choose Game: ");
		System.out.println("[A] - for MINESWEEPER [rating:  " + mui.showRating() + " /10]" + " Number of voters: "
				+ mui.showVoters());
		System.out.println("[B] - for FIFTEEN PUZZLE [rating:  " + pui.showRating() + " /10]" + " Number of voters: "
				+ pui.showVoters());
		System.out.println("[C] - for PEXESO [rating:  " + peui.showRating() + " /10]" + " Number of voters: "
				+ peui.showVoters());

		String selection = sc.next().toUpperCase();
		switch (selection) {
		case "A":
			mui.play();
			break;

		case "B":
			pui.play();
			break;

		case "C":
			peui.play();
			break;

		default:
			break;
		}
	}

}
