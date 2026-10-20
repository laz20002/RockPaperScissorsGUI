import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame { 
    private int playerScore = 0;
    private int computerScore = 0;
    private int ties = 0;

    private JLabel scoreLabel;
    private JLabel resultLabel;
    private JLabel choiceLabel;

    private String[] choices = {"Rock", "Paper", "Scissors"};
    private Random random = new Random();

    public RockPaperScissorsGUI() {
        setTitle("Rock, Paper, Scissors - Java Swing");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Rock, Paper, Scissors!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        scoreLabel = new JLabel("Score -> You: 0 | Computer: 0 | Ties: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        choiceLabel = new JLabel("Choose an option below to start playing.");
        choiceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        choiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(choiceLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(resultLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(scoreLabel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton resetButton = new JButton("Reset");

        Dimension btnSize = new Dimension(90, 35);
        rockButton.setPreferredSize(btnSize);
        paperButton.setPreferredSize(btnSize);
        scissorsButton.setPreferredSize(btnSize);
        resetButton.setPreferredSize(btnSize);

        rockButton.addActionListener(new GameAction("Rock"));
        paperButton.addActionListener(new GameAction("Paper"));
        scissorsButton.addActionListener(new GameAction("Scissors"));

        resetButton.addActionListener(e -> resetGame());

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class GameAction implements ActionListener {
        private String playerChoice;

        public GameAction(String playerChoice) {
            this.playerChoice = playerChoice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String computerChoice = choices[random.nextInt(3)];
            String resultText = "";

            if (playerChoice.equals(computerChoice)) {
                resultText = "It's a tie!";
                ties++;
            } else if (
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))
            ) {
                resultText = "You Win!";
                playerScore++;
            } else {
                resultText = "Computer wins!";
                computerScore++;
            }

            choiceLabel.setText("You: " + playerChoice + " | Computer: " + computerChoice);
            resultLabel.setText(resultText);
            scoreLabel.setText("Score -> You: " + playerScore + " | Computer: " + computerScore + " | Ties: " + ties);
        }
    }

    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        ties = 0;
        
        choiceLabel.setText("Choose an option below to start playing.");
        resultLabel.setText(" ");
        scoreLabel.setText("Score -> You: 0 | Computer: 0 | Ties: 0");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RockPaperScissorsGUI().setVisible(true);
        });
    }
}

