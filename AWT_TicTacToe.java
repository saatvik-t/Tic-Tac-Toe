import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AWT_TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel label = new JLabel();
    JButton[] buttons = new JButton [3 * 3];
    boolean playerTurn;
    boolean gameOver;

    AWT_TicTacToe () {
        ImageIcon icon = new ImageIcon("TicTacToe_Logo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        label.setText("Tic Tac Toe : X turn");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        label.setBackground(new Color(25, 25, 25));
        label.setForeground(new Color(250, 250, 0));
        label.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(label);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(100, 100, 100));
        for (int i = 0; i < 9; i++) {
            buttons [i] = new JButton();
            buttonPanel.add(buttons [i]);
            buttons [i].setBorder(new LineBorder(Color.BLACK));
            buttons[i].setFont(new Font("Calibri", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        playerTurn = true;
        gameOver = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver)
            return;
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons [i]) {
                if (buttons[i].getText().isEmpty()) {
                    if (playerTurn) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        label.setText("Tic Tac Toe : O Turn");
                    }
                    else {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        label.setText("Tic Tac Toe : X Turn");
                    }
                    playerTurn = ! playerTurn;
                    check();
                }
            }
        }
    }

    public void check () {
        if (Objects.equals(buttons[0].getText(), buttons[1].getText()) && Objects.equals(buttons[1].getText(), buttons[2].getText())) {
            if (Objects.equals(buttons[0].getText(), "X") || Objects.equals(buttons[0].getText(), "O"))
                winner(0, 1, 2);
        }
        if (Objects.equals(buttons[3].getText(), buttons[4].getText()) && Objects.equals(buttons[4].getText(), buttons[5].getText())) {
            if (Objects.equals(buttons[3].getText(), "X") || Objects.equals(buttons[3].getText(), "O"))
                winner(3, 4, 5);
        }
        if (Objects.equals(buttons[6].getText(), buttons[7].getText()) && Objects.equals(buttons[7].getText(), buttons[8].getText())) {
            if (Objects.equals(buttons[6].getText(), "X") || Objects.equals(buttons[6].getText(), "O"))
                winner(6, 7, 8);
        }
        if (Objects.equals(buttons[0].getText(), buttons[3].getText()) && Objects.equals(buttons[3].getText(), buttons[6].getText())) {
            if (Objects.equals(buttons[0].getText(), "X") || Objects.equals(buttons[0].getText(), "O"))
                winner(0, 3, 6);
        }
        if (Objects.equals(buttons[1].getText(), buttons[4].getText()) && Objects.equals(buttons[4].getText(), buttons[7].getText())) {
            if (Objects.equals(buttons[1].getText(), "X") || Objects.equals(buttons[1].getText(), "O"))
                winner(1, 4, 7);
        }
        if (Objects.equals(buttons[2].getText(), buttons[5].getText()) && Objects.equals(buttons[5].getText(), buttons[8].getText())) {
            if (Objects.equals(buttons[2].getText(), "X") || Objects.equals(buttons[2].getText(), "O"))
                winner(2, 5, 8);
        }
        if (Objects.equals(buttons[0].getText(), buttons[4].getText()) && Objects.equals(buttons[4].getText(), buttons[8].getText())) {
            if (Objects.equals(buttons[0].getText(), "X") || Objects.equals(buttons[0].getText(), "O"))
                winner(0, 4, 8);
        }
        if (Objects.equals(buttons[2].getText(), buttons[4].getText()) && Objects.equals(buttons[4].getText(), buttons[6].getText())) {
            if (Objects.equals(buttons[2].getText(), "X") || Objects.equals(buttons[2].getText(), "O"))
                winner(2, 4, 6);
        }
        boolean allButtonsFilled = true;
        for (int i = 0; i < 9; i++) {
            if (Objects.equals(buttons[i].getText(), "")) {
                allButtonsFilled = false;
                break;
            }
        }
        if (allButtonsFilled && ! gameOver) {
            label.setText("It's a draw!");
            gameOver = true;
            disableButtons();
        }
    }
    public void winner (int p, int q, int r) {
        buttons[p].setBackground(new Color(0, 255, 0));
        buttons[q].setBackground(new Color(0, 255, 0));
        buttons[r].setBackground(new Color(0, 255, 0));
        label.setForeground(new Color(25, 255, 0));
        if (Objects.equals(buttons[p].getText(), "X"))
            label.setText("X Wins !");
        else
            label.setText("O Wins !");
        gameOver = true;
        disableButtons();
    }

    public void disableButtons () {
        if (gameOver) {
            for (int i = 0; i < 9; i++)
                buttons[i].setEnabled(false);
        }
    }

    public static void main(String[] args) {
        AWT_TicTacToe game = new AWT_TicTacToe();
    }
}
