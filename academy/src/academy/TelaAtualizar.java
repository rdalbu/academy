package academy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class TelaAtualizar extends JFrame {

    private JPanel contentPane;
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextField emailField;
    private JTextField planoIdField;
    private JTextField dataInicioField;
    private JTextField dataTerminoField;
    private JTextField valorPagoField;
    private JTextField tipoPlanoField;

    private static final String URL = "jdbc:postgresql://localhost:5432/academy";
    private static final String USER = "db_admin";
    private static final String PASSWORD = "sua_senha_aqui";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public TelaAtualizar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(30, 30, 80, 25);
        contentPane.add(lblCpf);

        cpfField = new JTextField();
        cpfField.setBounds(120, 30, 200, 25);
        contentPane.add(cpfField);
        cpfField.setColumns(10);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 70, 80, 25);
        contentPane.add(lblNome);

        nomeField = new JTextField();
        nomeField.setBounds(120, 70, 200, 25);
        contentPane.add(nomeField);
        nomeField.setColumns(10);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(30, 110, 80, 25);
        contentPane.add(lblIdade);

        idadeField = new JTextField();
        idadeField.setBounds(120, 110, 200, 25);
        contentPane.add(idadeField);
        idadeField.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 150, 80, 25);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(120, 150, 200, 25);
        contentPane.add(emailField);
        emailField.setColumns(10);

        JLabel lblPlanoId = new JLabel("Plano ID:");
        lblPlanoId.setBounds(30, 190, 80, 25);
        contentPane.add(lblPlanoId);

        planoIdField = new JTextField();
        planoIdField.setBounds(120, 190, 200, 25);
        contentPane.add(planoIdField);
        planoIdField.setColumns(10);

        JLabel lblDataInicio = new JLabel("Data Início:");
        lblDataInicio.setBounds(30, 230, 80, 25);
        contentPane.add(lblDataInicio);

        dataInicioField = new JTextField();
        dataInicioField.setBounds(120, 230, 200, 25);
        contentPane.add(dataInicioField);
        dataInicioField.setColumns(10);

        JLabel lblDataTermino = new JLabel("Data Término:");
        lblDataTermino.setBounds(30, 270, 80, 25);
        contentPane.add(lblDataTermino);

        dataTerminoField = new JTextField();
        dataTerminoField.setBounds(120, 270, 200, 25);
        contentPane.add(dataTerminoField);
        dataTerminoField.setColumns(10);

        JLabel lblValorPago = new JLabel("Valor Pago:");
        lblValorPago.setBounds(30, 310, 80, 25);
        contentPane.add(lblValorPago);

        valorPagoField = new JTextField();
        valorPagoField.setBounds(120, 310, 200, 25);
        contentPane.add(valorPagoField);
        valorPagoField.setColumns(10);

        JLabel lblTipoPlano = new JLabel("Tipo Plano:");
        lblTipoPlano.setBounds(30, 350, 80, 25);
        contentPane.add(lblTipoPlano);

        tipoPlanoField = new JTextField();
        tipoPlanoField.setBounds(120, 350, 200, 25);
        contentPane.add(tipoPlanoField);
        tipoPlanoField.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(330, 30, 90, 25);
        contentPane.add(btnBuscar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(160, 390, 120, 30);
        contentPane.add(btnAtualizar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(346, 425, 74, 25);
        contentPane.add(btnVoltar);

        btnBuscar.addActionListener(e -> {
            String cpf = cpfField.getText();
            if (!cpf.isEmpty()) {
                buscarAluno(cpf);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, insira um CPF válido.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAtualizar.addActionListener(e -> atualizarAluno());

        btnVoltar.addActionListener(e -> {
            TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
            telaBoasVindas.setVisible(true);
            dispose();
        });
    }

    private void buscarAluno(String cpf) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT nome, idade, email, plano_id, data_inicio_plano, data_termino_plano, valor_pago, tipo_plano FROM alunos WHERE cpf = ?")) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeField.setText(rs.getString("nome"));
                    idadeField.setText(rs.getString("idade"));
                    emailField.setText(rs.getString("email"));
                    planoIdField.setText(rs.getString("plano_id"));
                    dataInicioField.setText(rs.getString("data_inicio_plano"));
                    dataTerminoField.setText(rs.getString("data_termino_plano"));
                    valorPagoField.setText(rs.getString("valor_pago"));
                    tipoPlanoField.setText(rs.getString("tipo_plano"));
                } else {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarAluno() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE alunos SET nome = ?, idade = ?, email = ?, plano_id = ?, data_inicio_plano = ?, data_termino_plano = ?, valor_pago = ?, tipo_plano = ? WHERE cpf = ?")) {

            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String email = emailField.getText();
            int planoId = Integer.parseInt(planoIdField.getText());
            String dataInicio = dataInicioField.getText();
            String dataTermino = dataTerminoField.getText();
            double valorPago = Double.parseDouble(valorPagoField.getText());
            String tipoPlano = tipoPlanoField.getText();
            String cpf = cpfField.getText();

            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, email);
            stmt.setInt(4, planoId);
            stmt.setString(5, dataInicio);
            stmt.setString(6, dataTermino);
            stmt.setDouble(7, valorPago);
            stmt.setString(8, tipoPlano);
            stmt.setString(9, cpf);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
                telaBoasVindas.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAtualizar frame = new TelaAtualizar();
            frame.setVisible(true);
        });
    }
}


