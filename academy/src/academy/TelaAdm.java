package academy;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TelaAdm extends JFrame {

    public TelaAdm() {
        setTitle("Administração");
        setSize(400, 263);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnQuantidadeAlunos = new JButton("Quantidade de Alunos");
        btnQuantidadeAlunos.setBounds(107, 38, 180, 30);
        panel.add(btnQuantidadeAlunos);

        JButton btnQuantidadeFuncionarios = new JButton("Quantidade de Funcionários");
        btnQuantidadeFuncionarios.setBounds(107, 93, 180, 30);
        panel.add(btnQuantidadeFuncionarios);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(302, 190, 72, 23);
        panel.add(btnVoltar);

        btnQuantidadeAlunos.addActionListener(e -> {
            int quantidadeAlunos = DatabaseHelper.obterQuantidade("alunos");
            JOptionPane.showMessageDialog(null, "Total de Alunos: " + quantidadeAlunos);
        });

        btnQuantidadeFuncionarios.addActionListener(e -> {
            int quantidadeFuncionarios = DatabaseHelper.obterQuantidade("dados_funcionarios");
            JOptionPane.showMessageDialog(null, "Total de Funcionários: " + quantidadeFuncionarios);
        });

        btnVoltar.addActionListener(e -> {
            TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
            telaMenuPrincipal.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        TelaAdm frame = new TelaAdm();
        frame.setVisible(true);
    }
}

class DatabaseHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/academy";
    private static final String USER = "user_admin";
    private static final String PASSWORD = "sua_senha_aqui";

    public static int obterQuantidade(String tabela) {
        int quantidade = 0;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM public." + tabela)) {

            if (rs.next()) {
                quantidade = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return quantidade;
    }
}
