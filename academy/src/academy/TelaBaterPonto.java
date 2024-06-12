package academy;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class TelaBaterPonto extends JFrame {

    private String cpf;
    private String nome;

    public TelaBaterPonto(final String cpf, final String nome) {
        this.cpf = cpf;
        this.nome = nome;

        setTitle("Bater Ponto");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblInfo = new JLabel("Registrando ponto para: " + nome);
        panel.add(lblInfo);
        lblInfo.setBounds(10, 11, 384, 33);

        JButton btnBaterPonto = new JButton("Bater Ponto");
        panel.add(btnBaterPonto);
        btnBaterPonto.setBounds(130, 55, 110, 64);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(302, 127, 72, 23);
        panel.add(btnVoltar);

        btnBaterPonto.addActionListener(e -> {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String horario = agora.format(formatter);
            String data = agora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (baterPontoNoBancoDeDados(data, horario)) {
                JOptionPane.showMessageDialog(null, "Ponto registrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar ponto", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnVoltar.addActionListener(e -> {
        	TelaLoginFuncionario telaLoginFuncionario = new TelaLoginFuncionario();
            telaLoginFuncionario.setVisible(true);
            dispose();
        });

        adicionarBotaoRegistrarPonto();
    }

    public void adicionarBotaoRegistrarPonto() {
    }

    private boolean baterPontoNoBancoDeDados(String data, String horario) {
        String url = "jdbc:postgresql://localhost:5432/academy";
        String user = "user_admin";
        String password = "sua_senha_aqui";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String queryCheck = "SELECT * FROM registros_horarios WHERE cpf = ? AND data = ?";
            try (PreparedStatement statementCheck = connection.prepareStatement(queryCheck)) {
                statementCheck.setString(1, cpf);
                Date date = Date.valueOf(data);
                statementCheck.setDate(2, date);
                ResultSet resultSet = statementCheck.executeQuery();
                if (resultSet.next()) {
                    Timestamp saida = resultSet.getTimestamp("saida");
                    if (saida == null) {
                        String queryUpdate = "UPDATE registros_horarios SET saida = ? WHERE cpf = ? AND data = ?";
                        try (PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate)) {
                            statementUpdate.setTimestamp(1, Timestamp.valueOf(horario));
                            statementUpdate.setString(2, cpf);
                            statementUpdate.setDate(3, date);
                            statementUpdate.executeUpdate();
                            return true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Você já bateu o ponto de saída hoje!");
                        return false;
                    }
                } else {
                    String queryInsert = "INSERT INTO registros_horarios (cpf, nome, entrada, data) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement statementInsert = connection.prepareStatement(queryInsert)) {
                        statementInsert.setString(1, cpf);
                        statementInsert.setString(2, nome);
                        statementInsert.setTimestamp(3, Timestamp.valueOf(horario));
                        statementInsert.setDate(4, date);
                        statementInsert.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
