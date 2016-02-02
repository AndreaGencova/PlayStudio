package pexeso.consoleui;

import java.util.Scanner;

import GameStudio.comment.CommentService;
import GameStudio.comment.CommentServiceImpl;
import GameStudio.console.MainConsole;
import GameStudio.rating.RatingService;
import GameStudio.rating.RatingServiceImpl;
import GameStudio.score.HallOfFameAbs;
import GameStudio.score.HallOfFameService;
import GameStudio.states.GameState;
import pexeso.core.Card;
import pexeso.core.PexesoField;

public class PexesoConsoleUI extends MainConsole {
	private PexesoField field;
	// private static final Pattern INPUT_PATTERN = Pattern.compile("([WSAD])");

	private static Scanner sc = new Scanner(System.in);

	public PexesoConsoleUI(PexesoField field) {
		this.field = field;
	}

	public void play() {
		show();

		while (field.getState() == GameState.PLAYING) {
			processInput();
			show();
		}

		if (field.getState() == GameState.SOLVED) {
			String name = System.getProperty("user.name");
			try {
				getHallOfFame().addScore(name, field.getPlayingSeconds());
				getHallOfFame().loadScore();

				System.out.println("Insert your comment: ");
				String comm = new Scanner(System.in).nextLine();
				getComment().addComment(name, field.getGame(), comm);

				System.out.println("Rating: ");
				int rate = new Scanner(System.in).nextInt();

				if (rate > 10 || rate < 0)
					System.err.println("Rating is in bad range. Range must be 0-10");
				else
					getRating().setRating("user3", field.getGame(), rate);

				System.out.println(getComment().loadComment().toString());
			} catch (Exception e) {
				System.err.println("Nepodarilo sa ulozit score");
				e.printStackTrace();
			}

			System.out.println("Vyhral si!");

		} else {
			System.out.println("Prehral si!");
		}
	}

	public void show() {
		showHeader();
		showField();
	}

	private void processInput() {
		Card card1 = null;
		Card card2 = null;

		System.out.println("Open field: ");

		String selection1 = sc.next().toUpperCase();

		if ("X".equals(selection1)) {
			System.err.println("------------EXIT GAME------------");
			System.exit(0);
		}

		int row1 = selection1.charAt(0) - 'A';
		int column1 = Integer.parseInt(selection1.substring(1, selection1.length()));

		card1 = field.getTile(row1, column1);
		field.openField(card1);
		show();

		String selection2 = sc.next().toUpperCase();

		if ("X".equals(selection2)) {
			System.err.println("------------EXIT GAME------------");
			System.exit(0);
		}

		int row2 = selection2.charAt(0) - 'A';
		int column2 = Integer.parseInt(selection2.substring(1, selection2.length()));

		card2 = field.getTile(row2, column2);
		field.openField(card2);
		show();

		if (!field.isSameCards(card1, card2)) {
			field.closeField(card1);
			field.closeField(card2);
		} else {
			field.markFiel(card1);
			field.markFiel(card2);
		}

		if (field.isSolved()) {
			System.err.println("--------------------YOU WIN--------------------------");
			field.setState(GameState.SOLVED);
		}

	}

	private void showField() {
		final String hSep = "\t";
		final String vSep = "\n";

		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.print((char) (row + 'A') + " |" + hSep);

			for (int column = 0; column < field.getColumnCount(); column++) {

				Card card = field.getTile(row, column);

				switch (card.getState()) {
				case CLOSED:
					System.out.print("-" + hSep);
					break;
				case OPEN:
					System.out.print(card + hSep);
					break;
				case MARKED:
					System.out.print(" " + hSep);
					break;
				default:
					break;
				}
			}

			System.out.println(vSep + "  |");
		}
	}

	private void showHeader() {
		System.out.println("Time: " + field.getPlayingSeconds() + " s");

		System.out.print(" ");
		for (int column = 0; column < field.getColumnCount(); column++) {
			System.out.print(" " + column);
		}
		System.out.println();
	}

	@Override
	public String getGameName() {
		return field.getGame();
	}
}
