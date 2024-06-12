package academy;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

public class TelaRegistrarTreino extends JFrame {

    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtDuracao;
    private int funcionarioId;
    private String funcionarioNome;
    private String funcionarioCPF;

    public TelaRegistrarTreino(int funcionarioId, String funcionarioNome, String funcionarioCPF) {
        setTitle("Registrar Treino");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.funcionarioId = funcionarioId;
        this.funcionarioNome = funcionarioNome;
        this.funcionarioCPF = funcionarioCPF;

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblFuncionarioNome = new JLabel("Nome do Funcionário:");
        lblFuncionarioNome.setBounds(10, 10, 150, 25);
        panel.add(lblFuncionarioNome);

        JTextField txtFuncionarioNome = new JTextField();
        txtFuncionarioNome.setBounds(160, 10, 200, 25);
        txtFuncionarioNome.setEditable(false);
        txtFuncionarioNome.setText(funcionarioNome);
        panel.add(txtFuncionarioNome);

        JLabel lblFuncionarioCPF = new JLabel("CPF do Funcionário:");
        lblFuncionarioCPF.setBounds(10, 40, 150, 25);
        panel.add(lblFuncionarioCPF);

        JTextField txtFuncionarioCPF = new JTextField();
        txtFuncionarioCPF.setBounds(160, 40, 200, 25);
        txtFuncionarioCPF.setEditable(false);
        txtFuncionarioCPF.setText(funcionarioCPF);
        panel.add(txtFuncionarioCPF);

        JLabel lblCpf = new JLabel("CPF do Aluno:");
        lblCpf.setBounds(10, 80, 100, 25);
        panel.add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(100, 80, 150, 25);
        panel.add(txtCpf);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(260, 80, 100, 25);
        panel.add(btnBuscar);

        JLabel lblNome = new JLabel("Nome do Aluno:");
        lblNome.setBounds(10, 110, 100, 25);
        panel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 110, 250, 25);
        txtNome.setEditable(false);
        panel.add(txtNome);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 140, 80, 25);
        panel.add(lblDescricao);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(100, 140, 270, 65);
        panel.add(txtDescricao);

        JLabel lblDuracao = new JLabel("Duração:");
        lblDuracao.setBounds(10, 216, 80, 25);
        panel.add(lblDuracao);

        txtDuracao = new JTextField();
        txtDuracao.setBounds(100, 216, 270, 25);
        panel.add(txtDuracao);

        JButton btnRegistrarTreino = new JButton("Registrar Treino");
        btnRegistrarTreino.setBounds(100, 251, 150, 30);
        panel.add(btnRegistrarTreino);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(308, 277, 72, 23);
        panel.add(btnVoltar);  

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarAluno(txtCpf.getText());
            }
        });

        btnRegistrarTreino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarTreino(txtCpf.getText(), txtDescricao.getText(), Integer.parseInt(txtDuracao.getText()), funcionarioId);
            }
        });
        
        btnVoltar.addActionListener(e -> {
        	TelaLoginFuncionario telaLoginFuncionario = new TelaLoginFuncionario();
            telaLoginFuncionario.setVisible(true);
            dispose();
        });
    }

    private void buscarAluno(String cpf) {
        String url = "jdbc:postgresql://localhost:5432/academy";
        String user = "user_admin";
        String password = "sua_senha_aqui";

        String query = "SELECT nome FROM alunos WHERE cpf = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                txtNome.setText(resultSet.getString("nome"));
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void registrarTreino(String cpf, String descricao, int duracao, int funcionarioId) {
        String url = "jdbc:postgresql://localhost:5432/academy";
        String user = "user_admin";
        String password = "sua_senha_aqui";

        String query = "INSERT INTO treinos (aluno_id, funcionario_id, data, descricao, duracao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            int alunoId = obterAlunoId(cpf, connection);

            if (alunoId != -1) {
                statement.setInt(1, alunoId);
                statement.setInt(2, funcionarioId);
                statement.setDate(3, Date.valueOf(LocalDate.now()));
                statement.setString(4, descricao);
                statement.setInt(5, duracao);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Treino registrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar treino", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int obterAlunoId(String cpf, Connection connection) throws SQLException {
        String query = "SELECT id FROM alunos WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {

                String cpf = "12345678900";
                int funcionarioId = obterFuncionarioIdPorCPF(cpf);
                TelaRegistrarTreino frame = new TelaRegistrarTreino(funcionarioId, "Nome do Funcionário", cpf);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static int obterFuncionarioIdPorCPF(String cpf) {
        int funcionarioId = -1;
        String url = "jdbc:postgresql://localhost:5432/academy";
        String user = "user_admin";
        String password = "sua_senha_aqui";

        String query = "SELECT funcionario_id FROM dados_funcionarios WHERE cpf = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                funcionarioId = resultSet.getInt("funcionario_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarioId;
    }
}

