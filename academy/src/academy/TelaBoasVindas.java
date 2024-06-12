package academy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import java.sql.*;

public class TelaBoasVindas extends JFrame {

    private JPanel contentPane;

    private static final String URL = "jdbc:postgresql://localhost:5432/academy";
    private static final String USER = "db_admin";
    private static final String PASSWORD = "sua_senha_aqui";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaBoasVindas frame = new TelaBoasVindas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaBoasVindas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(160, 50, 120, 30);
        contentPane.add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(160, 100, 120, 30);
        contentPane.add(btnConsultar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(160, 150, 120, 30);
        contentPane.add(btnAtualizar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(334, 220, 90, 30);
        contentPane.add(btnVoltar);

        btnRegistrar.addActionListener(e -> {
            TelaRegister telaRegister = new TelaRegister();
            telaRegister.setVisible(true);
            dispose();
        });

        btnConsultar.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog("Digite o CPF:");
            if (cpf != null && !cpf.isEmpty()) {
                consultarAluno(cpf);
            }
        });

        btnAtualizar.addActionListener(e -> {
            TelaAtualizar telaAtualizar = new TelaAtualizar();
            telaAtualizar.setVisible(true);
            dispose();
        });

        btnVoltar.addActionListener(e -> {
            TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
            telaMenuPrincipal.setVisible(true);
            dispose();
        });
    }

    private void consultarAluno(String cpf) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT tipo_plano, data_inicio_plano, data_termino_plano FROM alunos WHERE cpf = ?")) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipoPlanoStr = rs.getString("tipo_plano");
                    String dataInicioPlano = rs.getString("data_inicio_plano");
                    String dataTerminoPlano = rs.getString("data_termino_plano");

                    JOptionPane.showMessageDialog(null, "Tipo de Plano: " + tipoPlanoStr + "\nData de início do plano: " + dataInicioPlano + "\nData de término do plano: " + dataTerminoPlano, "Consulta de Aluno", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
