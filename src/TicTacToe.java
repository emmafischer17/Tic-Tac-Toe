import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardwidth = 600;
    int boardHeight = 650; //50px for text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    JButton resetButton = new JButton();
    
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    Color bkgcolor = new Color(0, 128, 128);
    Color highlightColor = new Color(82, 181, 181);
    Color textColor = Color.white;
    Color winColor = new Color(144, 238, 144);
    Color tieColor = new Color (250,250,210);

    TicTacToe() {

    frame.setVisible(true);
    frame.setSize(boardwidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    textLabel.setBackground(bkgcolor);
    textLabel.setForeground(textColor);
    textLabel.setFont(new Font("Arial", Font.BOLD, 50));
    textLabel.setHorizontalAlignment(JLabel.CENTER);
    textLabel.setText("Tic-Tac-Toe");
    textLabel.setOpaque(true);

    textPanel.setLayout(new BorderLayout());
    textPanel.add(textLabel);
    frame.add(textPanel, BorderLayout.NORTH);

    resetButton.setText("Reset");
    resetButton.setPreferredSize(new Dimension(100, 50));
    resetButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            gameOver = false;
            currentPlayer = playerX;
            textLabel.setText("Tic-Tac-Toe");
            turns = 0;
            for (int r = 0; r < 3; r++){
                    for (int c = 0; c < 3; c++){
                        resetTile(board[r][c]);
                    }
                }

        }
    });
    bottomPanel.setLayout(new GridBagLayout());
    bottomPanel.add(resetButton);
    frame.add(bottomPanel, BorderLayout.SOUTH);


    boardPanel.setLayout(new GridLayout(3,3));
    boardPanel.setBackground(bkgcolor);
    frame.add(boardPanel);

    for (int r =0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
          JButton tile = new JButton(); 
          board[r][c] = tile;
          boardPanel.add(tile); 
          tile.setBackground(bkgcolor);
          tile.setForeground(Color.white);
          tile.setFont(new Font("Arial", Font.BOLD, 120));
          tile.setFocusable(false);
          

          tile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameOver) return;
                JButton tile = (JButton) e.getSource();
                if (tile.getText() == "") {
                    tile.setText(currentPlayer);
                    turns++;
                    checkWinner();
                    if (!gameOver){
                        currentPlayer = currentPlayer == playerX ? playerO : playerX;
                        textLabel.setText(currentPlayer + "'s turn.");
                    }
                    
                }
                



            }
          });

        }
    }
    }

    void checkWinner() {
        //horrizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()){
                for (int i = 0; i < 3; i++){
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            if (board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()){
                for (int i = 0; i < 3; i++){
                    setWinner(board[i][c]);
                }

                gameOver = true;
                return;
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
                for (int i = 0; i < 3; i++){
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
            }

        //antidiagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);    
            setWinner(board[2][0]);
            gameOver = true;
            return;
            }

            if (turns == 9) {
                for (int r = 0; r < 3; r++){
                    for (int c = 0; c < 3; c++){
                        setTie(board[r][c]);
                    }
                }
                gameOver = true;
            }

    }

    void setWinner(JButton tile) {
        tile.setForeground(winColor);
        tile.setBackground(highlightColor);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile){
        tile.setForeground(tieColor);
        tile.setBackground(highlightColor);
        textLabel.setText("Tie!");
    }

    void resetTile(JButton tile){
        tile.setForeground(textColor);
        tile.setBackground(bkgcolor);
        tile.setText("");
    }
}
