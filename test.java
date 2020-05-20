import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TicTacToe extends JFrame implements ActionListener {

	JPanel p;
	static JButton[][] b = new JButton[3][3];
	JButton play;
	JButton reset;

	int x;
	int o;

	JLabel xScore;
	JLabel oScore;
	JLabel divider;
	JLabel crossWon;
	JLabel circleWon;
	JLabel tie;

	Font buttonFont = new Font("Arial", Font.PLAIN, 50);

	// Colors used
	Color buttonDefaultColor = Color.ORANGE;
	Color buttonWinningColor = Color.GREEN;
	Color backgroundColor = Color.BLACK;
	Color btnBackgroundColor = Color.GRAY;
	Color textColor = Color.BLACK;
	Color outsideText = Color.WHITE;

	static int count = 9;
	private boolean gameOver = false;

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public TicTacToe() {

		super("TicTacToe Game");

		p = new JPanel();
		p.setBackground(backgroundColor);

		JLabel l = new JLabel(
				"                                 TicTacToe: The Java Game                                ");
		l.setForeground(outsideText);
		l.setFont(new Font(l.getName(), Font.PLAIN, 30));
		p.add(l); // Adding game title at the top

		xScore = new JLabel("X: " + x);
		xScore.setForeground(Color.CYAN);
		xScore.setFont(buttonFont);

		oScore = new JLabel("O: " + o);
		oScore.setForeground(Color.MAGENTA);
		oScore.setFont(buttonFont);

		divider = new JLabel(" | ");
		divider.setForeground(Color.WHITE);
		divider.setFont(buttonFont);

		play = new JButton("Play");
		play.setPreferredSize(new Dimension(200, 100));
		play.addActionListener(this);
		play.setActionCommand("Play");
		play.setFont(buttonFont);
		play.setBackground(btnBackgroundColor);
		play.setOpaque(true);
		play.setBorderPainted(false);
		play.setForeground(textColor);
		
		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(200, 100));
		reset.addActionListener(this);
		reset.setActionCommand("Reset");
		reset.setFont(buttonFont);
		reset.setBackground(btnBackgroundColor);
		reset.setOpaque(true);
		reset.setBorderPainted(false);
		reset.setForeground(textColor);

		crossWon = new JLabel("X's Win the Round");
		crossWon.setFont(new Font(l.getName(), Font.PLAIN, 80));
		crossWon.setForeground(outsideText);

		circleWon = new JLabel("O's Win the Round");
		circleWon.setFont(new Font(l.getName(), Font.PLAIN, 80));
		circleWon.setForeground(outsideText);

		tie = new JLabel("It's a Tie!");
		tie.setFont(new Font(l.getName(), Font.PLAIN, 100));
		tie.setForeground(outsideText);

		// creating the button objects
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				b[r][c] = new JButton();
				b[r][c].setPreferredSize(new Dimension(200, 200));
				b[r][c].setActionCommand(r + "," + c);
				b[r][c].addActionListener(this);
				b[r][c].setBackground(buttonDefaultColor);
				b[r][c].setOpaque(true);
				b[r][c].setBorderPainted(false);
				p.add(b[r][c]);
			}
		}

		p.add(play);
		p.add(reset);
		p.add(xScore);
		p.add(divider);
		p.add(oScore);
		// add the panel
		add(p); // setting up the panel
		setSize(800, 1000); // Setting size of window
		setResizable(false); // Can't change window size
		setVisible(true);

	}

	public boolean checkMarker(String marker) {
		for (int r = 0; r < 3; r++) {
			if (b[r][0].getText().equals(marker))
				if (b[r][0].getText().equals(b[r][1].getText()) && b[r][0].getText().equals(b[r][2].getText())) {
					b[r][0].setBackground(buttonWinningColor);
					b[r][1].setBackground(buttonWinningColor);
					b[r][2].setBackground(buttonWinningColor);
					return true;
				}
		}
		for (int c = 0; c < 3; c++) {
			if (b[0][c].getText().equals(marker))
				if (b[0][c].getText().equals(b[1][c].getText()) && b[0][c].getText().equals(b[2][c].getText())) {
					b[0][c].setBackground(buttonWinningColor);
					b[1][c].setBackground(buttonWinningColor);
					b[2][c].setBackground(buttonWinningColor);
					return true;
				}
		}
		if (b[0][0].getText().equals(marker))
			if (b[0][0].getText().equals(b[1][1].getText()) && b[0][0].getText().equals(b[2][2].getText())) {
				b[0][0].setBackground(buttonWinningColor);
				b[1][1].setBackground(buttonWinningColor);
				b[2][2].setBackground(buttonWinningColor);
				return true;
			}
		if (b[0][2].getText().equals(marker))
			if (b[0][2].getText().equals(b[1][1].getText()) && b[0][2].getText().equals(b[2][0].getText())) {
				b[0][2].setBackground(buttonWinningColor);
				b[1][1].setBackground(buttonWinningColor);
				b[2][0].setBackground(buttonWinningColor);
				return true;
			}
		return false;
	}
	
	public boolean checkCross() {
		
		return checkMarker("X");

	}

	public boolean checkCircle() {
		
		return checkMarker("O");

	}

	public boolean boardFull() {
		if (count == 0)
			return true;
		return false;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Reset")) {
			reset();
			return;
		}

		if (e.getActionCommand().equals("Play")) {
			play();
			return;
		}

		if (gameOver)
			return;

		int r = Integer.parseInt(e.getActionCommand().substring(0, 1));
		int c = Integer.parseInt(e.getActionCommand().substring(2, 3));

		if (count % 2 != 0) {
			if (b[r][c].getText().equals("")) {
				b[r][c].setFont(buttonFont);
				b[r][c].setText("X");
				count--;
			} else
				return;

		} else if (count % 2 == 0) {
			if (b[r][c].getText().equals("")) {
				b[r][c].setFont(buttonFont);
				b[r][c].setText("O");
				count--;
			} else
				return;
		}

		if (checkCross()) {
			x++;
			xScore.setText("X:" + x);
			if (x == 3 && x > o) {
				crossWon.setText("X's Won the Game!");
			}
			p.add(crossWon);
			p.repaint();
			gameOver = true;
		}

		else if (checkCircle()) {
			o++;
			oScore.setText("O:" + o);
			if (o == 3 && o > x) {
				circleWon.setText("O's Won the Game!");
			}
			p.add(circleWon);
			p.repaint();
			gameOver = true;

		} else if (boardFull()) {
			p.add(tie);
			p.repaint();
			gameOver = true;
		}
	}

	public void play() {
		count = 9;
		if (x == 3 || o == 3) {
			reset();
		}
		for (JButton[] buttonr : b) {
			for (JButton buttonc : buttonr) {
				buttonc.setText("");
				buttonc.setBackground(buttonDefaultColor);
			}
		}
		p.remove(crossWon);
		p.remove(circleWon);
		p.remove(tie);
		p.repaint();
		gameOver = false;
	}

	public void reset() {
		x = 0;
		xScore.setText("X:" + x);
		crossWon.setText("X's Win the Round!");
		o = 0;
		oScore.setText("O:" + o);
		circleWon.setText("O's Win the Round!");
		play();
	}

}
