package jogo;
import javax.swing.*;
import java.awt.*;

public class Jogindavelha extends JFrame {

   private JButton[] casas = new JButton[9];
   private boolean vezDoX = true;

   public Jogindavelha() {
      setTitle("Jogo da velha");
      setSize(600, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

        JPanel tabuleiro = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < casas.length; i++) {
            int posicao = i;

            casas[i] = new JButton();
            casas[i].setFont(new Font("Arial", Font.BOLD, 70));

            casas[i].addActionListener(e -> jogar(posicao));

            tabuleiro.add(casas[i]);
        }

        add(tabuleiro);
        setVisible(true);
    }
    private void jogar(int posicao) {

        if (!casas[posicao].getText().isEmpty()) {
            return;
        }

        if (vezDoX) {
            casas[posicao].setText("X");
            casas[posicao].setForeground(Color.RED);
        } else {
            casas[posicao].setText("O");
            casas[posicao].setForeground(Color.BLUE);
        }
        verificarResultado();

        vezDoX = !vezDoX;
    }

    private void verificarResultado() {

        int[][] combinacoes = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] combinacao : combinacoes) {

            String primeiro = casas[combinacao[0]].getText();
            String segundo = casas[combinacao[1]].getText();
            String terceiro = casas[combinacao[2]].getText();
            if (!primeiro.isEmpty()
                    && primeiro.equals(segundo)
                    && segundo.equals(terceiro)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Jogador " + primeiro + " venceu"
                );

                novoJogo();
                return;
            }
        }
boolean tabuleiroCheio = true;

        for (JButton casa : casas) {
            if (casa.getText().isEmpty()) {
                tabuleiroCheio = false;
                break;
            }
        }

        if (tabuleiroCheio) {
            JOptionPane.showMessageDialog(this, "Deu empate");
            novoJogo();
        }
    }

    private void novoJogo() {

        for (JButton casa : casas) {
            casa.setText("");
            casa.setForeground(Color.BLACK);
        }

        vezDoX = true;
    }
}

