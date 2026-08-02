    package jogo;

    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;

    public class Jogindavelha extends JFrame implements ActionListener {

        JButton[][] botoes = new JButton[3][3];
        JLabel lblVez;
        JButton btnNovo, btnSair;

        char jogador = 'X';
        int jogadas = 0;

        public Jogindavelha() {

            setTitle("Jogo da velha");
            setSize(400, 450);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JPanel painelTabuleiro = new JPanel();
            painelTabuleiro.setLayout(new GridLayout(3, 3));

            Font fonte = new Font("Arial", Font.BOLD, 40);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botoes[i][j] = new JButton("");
                    botoes[i][j].setFont(fonte);
                    botoes[i][j].addActionListener(this);
                    painelTabuleiro.add(botoes[i][j]);

                }
            }

            lblVez = new JLabel("Vez do jogador: X", SwingConstants.CENTER);

            JPanel painelBotoes = new JPanel();

            btnNovo = new JButton("Novo jogo");
            btnSair = new JButton("Sair");
            btnNovo.addActionListener(this);
            btnSair.addActionListener(this);
            painelBotoes.add(btnNovo);
            painelBotoes.add(btnSair);

            add(lblVez, BorderLayout.NORTH);
            add(painelTabuleiro, BorderLayout.CENTER);
            add(painelBotoes, BorderLayout.SOUTH);

            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnNovo) {
                novoJogo();
                return;
            }
            if (e.getSource() == btnSair) {
                System.exit(0);
            }
            for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    if (e.getSource() == botoes[i][j]) {

                        botoes[i][j].setText(String.valueOf(jogador));
                        botoes[i][j].setEnabled(false);
                        jogadas++;

                        if (verificarVencedor()) {

                            JOptionPane.showMessageDialog(this,
                                    "Parabéns!\nJogador " + jogador + " venceu!");

                            bloquearTabuleiro();
                            return;
                        }

                        if (verificarEmpate()) {

                            JOptionPane.showMessageDialog(this,
                                    "Empate!\nNenhum jogador venceu.");

                            return;
                        }

                        alternarJogador();

                    }

                }
            }

        }

        public void alternarJogador() {

            if (jogador == 'X') {
                jogador = 'O';
            } else {
                jogador = 'X';
            }

            lblVez.setText("Vez do jogador: " + jogador);

        }
        public boolean verificarVencedor() {

            for (int i = 0; i < 3; i++) {

                if (!botoes[i][0].getText().equals("") &&
                    botoes[i][0].getText().equals(botoes[i][1].getText()) &&
                    botoes[i][1].getText().equals(botoes[i][2].getText())) {
                    return true;
                }

                if (!botoes[0][i].getText().equals("") &&
                    botoes[0][i].getText().equals(botoes[1][i].getText()) &&
                    botoes[1][i].getText().equals(botoes[2][i].getText())) {
                    return true;
                }
            }

            if (!botoes[0][0].getText().equals("") &&
                botoes[0][0].getText().equals(botoes[1][1].getText()) &&
                botoes[1][1].getText().equals(botoes[2][2].getText())) {
                return true;
            }

            if (!botoes[0][2].getText().equals("") &&
                botoes[0][2].getText().equals(botoes[1][1].getText()) &&
                botoes[1][1].getText().equals(botoes[2][0].getText())) {
                return true;
            }

            return false;
        }

        public boolean verificarEmpate() {

            if (jogadas == 9) {
                return true;
            }

            return false;
        }

        public void novoJogo() {

            jogador = 'X';
            jogadas = 0;

            lblVez.setText("Vez do jogador: X");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botoes[i][j].setText("");
                    botoes[i][j].setEnabled(true);

                }
            }

        }

        public void bloquearTabuleiro() {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botoes[i][j].setEnabled(false);

                }
            }

        }

    }