package academy;

import javax.swing.*;
import java.sql.*;

public class TelaLoginFuncionario extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private String cpf;
    private String nome;
    private int funcionarioId;

    public TelaLoginFuncionario() {
        setTitle("Login de Funcionários");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(10, 30, 80, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(58, 30, 210, 25);
        panel.add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 66, 44, 25);
        panel.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(58, 66, 210, 25);
        panel.add(txtSenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(58, 106, 100, 30);
        panel.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(302, 127, 72, 23);
        panel.add(btnVoltar);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(168, 106, 100, 30);
        panel.add(btnLogin);

        btnCadastrar.addActionListener(e -> {
            TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario();
            telaCadastroFuncionario.setVisible(true);
            dispose();
        });

        btnVoltar.addActionListener(e -> {
            TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
            telaMenuPrincipal.setVisible(true);
            dispose();
        });

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (verificarCredenciais(usuario, senha)) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        TelaOpcoesFuncionario telaOpcoes = new TelaOpcoesFuncionario(funcionarioId, cpf, nome);
                        telaOpcoes.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private boolean verificarCredenciais(String usuario, String senha) {
        String url = "jdbc:postgresql://localhost:5432/academy";
        String user = "user_admin";
        String password = "sua_senha_aqui";

        String query = "SELECT * FROM dados_funcionarios WHERE login = ? AND senha = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cpf = resultSet.getString("cpf");
                nome = resultSet.getString("nome");
                funcionarioId = resultSet.getInt("funcionario_id");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        TelaLoginFuncionario frame = new TelaLoginFuncionario();
        frame.setVisible(true);
    }
}



