
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class MemoryMatchingGame {
    private JFrame frame;
    private JPanel panel, topPanel;
    private JButton[] buttons;
    private JLabel movesLabel, timerLabel;
    private JButton restartButton;
    private List<Integer> cardValues;
    private int firstIndex = -1, secondIndex = -1;
    private boolean checking = false;
    private int matchedPairs = 0;
    private int moves = 0;
    private Timer gameTimer;
    private int elapsedTime = 0;
    

    public MemoryMatchingGame() {
        frame = new JFrame("Memory Matching Game");
        panel = new JPanel(new GridLayout(4, 4, 5, 5)); // 4x4 Grid with spacing
        topPanel = new JPanel(new FlowLayout());

        buttons = new JButton[16];
        cardValues = new ArrayList<>();
        movesLabel = new JLabel("Moves: 0");
        timerLabel = new JLabel("Time: 0s");
        restartButton = new JButton("Restart");

        restartButton.addActionListener(e -> resetGame());

        // Add labels and restart button to the top panel
        topPanel.add(movesLabel);
        topPanel.add(timerLabel);
        topPanel.add(restartButton);

        initializeGame();

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void initializeGame() {
        panel.removeAll(); // Clear the panel for restart
        cardValues.clear();
        matchedPairs = 0;
        moves = 0;
        elapsedTime = 0;
        movesLabel.setText("Moves: 0");
        timerLabel.setText("Time: 0s");

        // Initialize pairs of card values (1-8, twice)
        for (int i = 1; i <= 8; i++) {
            cardValues.add(i);
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);

        // Create buttons
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 24));
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setPreferredSize(new Dimension(80, 80)); // Set button size
            buttons[i].addActionListener(new CardClickListener(i));
            panel.add(buttons[i]);
        }

        // Restart timer
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                elapsedTime++;
                timerLabel.setText("Time: " + elapsedTime + "s");
            }
        }, 1000, 1000);

        panel.revalidate();
        panel.repaint();
    }

    private class CardClickListener implements ActionListener {
        private int index;

        public CardClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checking || buttons[index].getText().length() > 0) return;

            buttons[index].setText(String.valueOf(cardValues.get(index)));

            if (firstIndex == -1) {
                firstIndex = index;
            } else {
                secondIndex = index;
                checking = true;
                moves++;
                movesLabel.setText("Moves: " + moves);

                Timer delayTimer = new Timer();
                delayTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        checkMatch();
                    }
                }, 500);
            }
        }
    }

    private void checkMatch() {
        if (!cardValues.get(firstIndex).equals(cardValues.get(secondIndex))) {
            buttons[firstIndex].setText("");
            buttons[secondIndex].setText("");
        } else {
            buttons[firstIndex].setEnabled(false);
            buttons[secondIndex].setEnabled(false);
            buttons[firstIndex].setBackground(Color.GREEN);
            buttons[secondIndex].setBackground(Color.GREEN);
            matchedPairs++;
        }
        firstIndex = -1;
        secondIndex = -1;
        checking = false;

        if (matchedPairs == 8) {
            gameTimer.cancel();
            JOptionPane.showMessageDialog(frame, 
                "Congratulations! You won in " + moves + " moves and " + elapsedTime + " seconds.");
            resetGame();
        }
    }

    private void resetGame() {
        initializeGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoryMatchingGame::new);
    }
}
