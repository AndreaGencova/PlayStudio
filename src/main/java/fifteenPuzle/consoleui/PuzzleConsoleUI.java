package fifteenPuzle.consoleui;

import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GameStudio.score.GameState;
import GameStudio.score.HallOfFame;
import fifteenPuzle.core.PuzzleField;

public class PuzzleConsoleUI {
	private PuzzleField field;
	private static final Pattern INPUT_PATTERN = Pattern.compile("([WSAD])");
	private HallOfFame hallOfFame;

	public PuzzleConsoleUI(PuzzleField field) {
		this.field = field;
	}

	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public double showRating() {
		try {
			return hallOfFame.averageByAgragationFunction(field.getGame());
		} catch (Exception e) {
			System.err.println("Average rating is not available");
			e.printStackTrace();
		}
		return 0;
	}

	public Long showVoters() {
		try {
			return hallOfFame.countOfVoters(field.getGame());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void play() {
		show();
		System.out.println("Choose direction or exit program [X]");
		System.out.println("[A] - LEFT \t [D] - RIGHT \t [W] - UP \t [S] - DOWN");

		while (field.getState() == GameState.PLAYING) {
			processInput();
			show();
		}

		if (field.getState() == GameState.SOLVED) {
			String name = System.getProperty("user.name");
			try {
				hallOfFame.addScore(name, field.getPlayingSeconds());
				hallOfFame.loadScore();

				System.out.println("Insert your comment: ");
				String comm = new Scanner(System.in).nextLine();
				hallOfFame.loadComment(name, field.getGame(), comm);

				System.out.println("Rating: ");
				int rate = new Scanner(System.in).nextInt();

				if (rate > 10 || rate < 0)
					System.err.println("Rating is in bad range. Range must be 0-10");
				else
					hallOfFame.setRating("user1", field.getGame(), rate);

			} catch (Exception e) {
				System.err.println("Nepodarilo sa ulozit score");
				e.printStackTrace();
			}

			System.err.println("--------------YOU WIN--------------");
			System.out.println(hallOfFame);
		} else {
			System.err.println("Prehral si!");
		}

	}

	private void processInput() {
		@SuppressWarnings("resource")
		String line = new Scanner(System.in).nextLine().toUpperCase();

		if ("X".equals(line)) {
			System.err.println("------EXIT GAME------");
			System.exit(1);
		}

		Matcher matcher = INPUT_PATTERN.matcher(line);
		if (matcher.matches()) {
			if ("W".equals(matcher.group(1))) {
				field.moveToUp();
			}
			if ("S".equals(matcher.group(1))) {
				field.moveToDown();
			}
			if ("A".equals(matcher.group(1))) {
				field.moveToLeft();

			}
			if ("D".equals(matcher.group(1))) {
				field.moveToRight();
			}
			if (field.isSolved())
				field.setState(GameState.SOLVED);
		} else {
			System.out.println("Bad opinion.");
		}
	}

	public void show() {
		final String hSep = "\t";
		final String vSep = "\n";

		for (int row = 0; row < field.getRowCount(); row++) {

			for (int column = 0; column < field.getColumnCount(); column++) {

				if (field.getTile(row, column) == 0)
					System.out.print("O" + hSep);
				if (field.getTile(row, column) != 0)
					System.out.print(field.getTile(row, column) + hSep);

			}
			System.out.println(vSep);

		}

	}
}
