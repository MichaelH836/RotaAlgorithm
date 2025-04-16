package algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	private JPanel[] panels;
	private JFrame frame;

	private int noOfDays = 7;
	private int noOfShifts;
	private ArrayList<Person> people = new ArrayList<>();

	public Main() {
		this.panels = new JPanel[2];
		this.frame = new JFrame("RotaSorter");
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(1000, 500));

		JPanel text = new JPanel();
		text.setLayout(null);
		text.setBounds(0, 0, 1000, 120);
		text.setBackground(Color.LIGHT_GRAY);
		JPanel buttons = new JPanel();
		buttons.setLayout(null);
		buttons.setBounds(100, 150, 800, 320);
		buttons.setBackground(new Color(255, 255, 3, 255));
		panels[0] = text;
		panels[1] = buttons;

		layeredPane.add(text, 1);
		layeredPane.add(buttons, 2);

		frame.setLayout(new BorderLayout());
		frame.add(layeredPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		shiftsPerDay();
	}

	private void shiftsPerDay() {
		JLabel label = new JLabel("How many shifts per day?");
		label.setFont(new Font("Arial Black", Font.BOLD, 30));
		label.setForeground(new Color(0, 150, 255, 100));
		label.setBounds(40, 30, 500, 50);
		panels[0].add(label);

		JButton button1 = new JButton("One Shift");
		button1.setBounds(40, 40, 340, 220);
		button1.setFont(new Font("Serif", Font.BOLD, 24));
		panels[1].add(button1);
		JButton button2 = new JButton("Two Shifts");
		button2.setBounds(420, 40, 340, 220);
		button2.setFont(new Font("Serif", Font.BOLD, 24));
		panels[1].add(button2);

		button1.addActionListener(e -> availabilityLoop(1));
		button2.addActionListener(e -> availabilityLoop(2));
	}

	public void availabilityLoop(int shifts) {
		panels[1].removeAll();
		noOfShifts = shifts;

		int[] dayAvailability = new int[noOfDays];
		int[] nightAvailability = new int[noOfDays];

		int[] dayPreferences = new int[noOfDays];
		int[] nightPreferences = new int[noOfDays];

		String[] dayNames = new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		String[] timeType = new String[] { "Day", "Night" };
		JButton[][] buttons = new JButton[shifts][noOfDays];
		JTextField name = new JTextField();
		name.setBounds(300, 10, 200, 40);
		name.setFont(new Font("Arial", Font.BOLD, 20));
		panels[1].add(name);
		for (int i = 0; i < shifts; i++) {
			for (int j = 0; j < noOfDays; j++) {
				buttons[i][j] = new JButton((j + 1) + ". " + dayNames[j] + " " + timeType[i]);
				buttons[i][j].setBounds(20 + (j * 110), 60 + (i * 110), 100, 100);
				buttons[i][j].setBackground(new Color(255, 0, 0, 255));
				buttons[i][j].setFont(new Font("Arial", Font.BOLD, 10));
				panels[1].add(buttons[i][j]);
			}
			for (JButton button : buttons[i]) {
				if (i == 0) {
					button.addActionListener(e -> {
						if (button.getBackground().equals(new Color(255, 0, 0, 255))) {
							button.setBackground(new Color(0, 255, 0, 255));
							dayAvailability[Character.getNumericValue(button.getText().charAt(0)) - 1] = 1;
						} else if (button.getBackground().equals(new Color(0, 255, 0, 255))) {
							button.setBackground(new Color(0, 0, 255, 255));
							dayPreferences[Character.getNumericValue(button.getText().charAt(0)) - 1] = 1;
						} else {
							button.setBackground(new Color(255, 0, 0, 255));
							dayAvailability[Character.getNumericValue(button.getText().charAt(0)) - 1] = 0;
							dayPreferences[Character.getNumericValue(button.getText().charAt(0)) - 1] = 0;
						}

					});
				} else {
					button.addActionListener(e -> {
						if (button.getBackground().equals(new Color(255, 0, 0, 255))) {
							button.setBackground(new Color(0, 255, 0, 255));
							nightAvailability[Character.getNumericValue(button.getText().charAt(0)) - 1] = 1;
						} else if (button.getBackground().equals(new Color(0, 255, 0, 255))) {
							button.setBackground(new Color(0, 0, 255, 255));
							nightPreferences[Character.getNumericValue(button.getText().charAt(0)) - 1] = 1;
						} else {
							button.setBackground(new Color(255, 0, 0, 255));
							nightAvailability[Character.getNumericValue(button.getText().charAt(0)) - 1] = 0;
							nightPreferences[Character.getNumericValue(button.getText().charAt(0)) - 1] = 0;
						}
					});
				}
			}
		}
		int[][] availability = new int[][] { dayAvailability, nightAvailability };
		int[][] preferences = new int[][] { dayPreferences, nightPreferences };
		JButton submitButton = new JButton("Add");
		submitButton.setBounds(300, 280, 100, 30);
		JButton doneButton = new JButton("Finish");
		doneButton.setBounds(400, 280, 100, 30);

		submitButton.addActionListener(e -> {
			people.add(new Person(name.getText(), availability, preferences));
			availabilityLoop(shifts);
		});
		doneButton.addActionListener(e -> showRota());

		panels[1].add(submitButton);
		panels[1].add(doneButton);
		panels[1].repaint();
	}

	public void showRota() {
		panels[1].removeAll();
		RotaAlgorithm rota = new RotaAlgorithm(people, noOfDays, noOfShifts);
		Person[][] finalRota = rota.getFinalRota();
		for (int i = 0; i < finalRota.length; i++) {
			for (int j = 0; j < finalRota[i].length; j++) {
				JLabel label = new JLabel(finalRota[i][j].getName());
				label.setBounds(100 + (j * 75), 100 + (i * 100), 100, 50);
				panels[1].add(label);
			}
		}
		panels[1].repaint();
	}

	public static void main(String[] args) {
		Main m = new Main();
	}
}