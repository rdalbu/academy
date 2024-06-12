package academy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastroFuncionario extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldLogin;
    private JPasswordField passwordField;
    private JTextField textFieldNome;
    private JTextField textFieldCPF;
    private JTextField textFieldTelefone;
    private JTextField textFieldCargo;

    private static final String URL = "jdbc:postgresql://localhost:5432/academy";
    private static final String USER = "user_admin";
    private static final String PASSWORD = "sua_senha_aqui";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaCadastroFuncionario() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 200, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(35, 31, 46, 14);
        contentPane.add(lblLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(35, 56, 46, 14);
        contentPane.add(lblSenha);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(35, 81, 46, 14);
        contentPane.add(lblNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(35, 106, 46, 14);
        contentPane.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(35, 131, 63, 14);
        contentPane.add(lblTelefone);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(35, 156, 46, 14);
        contentPane.add(lblCargo);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(149, 28, 215, 20);
        contentPane.add(textFieldLogin);
        textFieldLogin.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(149, 53, 215, 20);
        contentPane.add(passwordField);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(149, 78, 215, 20);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);

        textFieldCPF = new JTextField();
        textFieldCPF.setBounds(149, 103, 215, 20);
        contentPane.add(textFieldCPF);
        textFieldCPF.setColumns(10);

        textFieldTelefone = new JTextField();
        textFieldTelefone.setBounds(149, 128, 215, 20);
        contentPane.add(textFieldTelefone);
        textFieldTelefone.setColumns(10);

        textFieldCargo = new JTextField();
        textFieldCargo.setBounds(149, 153, 215, 20);
        contentPane.add(textFieldCargo);
        textFieldCargo.setColumns(10);

        JButton btnRegistrarFuncionario = new JButton("Registrar Funcionário");
        btnRegistrarFuncionario.setBounds(149, 194, 164, 38);
        contentPane.add(btnRegistrarFuncionario);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(334, 320, 90, 30);
        contentPane.add(btnVoltar);

        btnRegistrarFuncionario.addActionListener(e -> registrarFuncionario());
        btnVoltar.addActionListener(e -> voltarParaLogin());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void registrarFuncionario() {
        String login = textFieldLogin.getText();
        String senha = new String(passwordField.getPassword());
        String nome = textFieldNome.getText();
        String cpf = textFieldCPF.getText();
        String telefone = textFieldTelefone.getText();
        String cargo = textFieldCargo.getText();

        if (login.isEmpty() || senha.isEmpty() || nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || cargo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (insertIntoDatabase(login, senha, nome, cpf, telefone, cargo)) {
            clearFields();
            JOptionPane.showMessageDialog(null, "Funcionário registrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao registrar funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean insertIntoDatabase(String login, String senha, String nome, String cpf, String telefone, String cargo) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO dados_funcionarios (login, senha, nome, cpf, telefone, cargo) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, login);
                statement.setString(2, senha);
                statement.setString(3, nome);
                statement.setString(4, cpf);
                statement.setString(5, telefone);
                statement.setString(6, cargo);
                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearFields() {
        textFieldLogin.setText("");
        passwordField.setText("");
        textFieldNome.setText("");
        textFieldCPF.setText("");
        textFieldTelefone.setText("");
        textFieldCargo.setText("");
    }

    private void voltarParaLogin() {
        TelaLoginFuncionario telaLoginFuncionario = new TelaLoginFuncionario();
        telaLoginFuncionario.setVisible(true);
        dispose();
    }
}
