package academy;

import javax.swing.*;

public class TelaMenuPrincipal extends JFrame {

    public TelaMenuPrincipal() {
        setTitle("Menu Principal");
        setSize(400, 263);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnAlunos = new JButton("Alunos");
        btnAlunos.setBounds(124, 145, 120, 30);
        panel.add(btnAlunos);

        JButton btnFuncionarios = new JButton("Funcionários");
        btnFuncionarios.setBounds(124, 93, 120, 30);
        panel.add(btnFuncionarios);

        JButton btnAdministracao = new JButton("Administração");
        btnAdministracao.setBounds(124, 38, 120, 30);
        panel.add(btnAdministracao);

        btnAlunos.addActionListener(e -> {
            TelaBoasVindas telaBoasVindasAlunos = new TelaBoasVindas();
            telaBoasVindasAlunos.setVisible(true);
            dispose();
        });

        btnFuncionarios.addActionListener(e -> {
            TelaLoginFuncionario telaLoginFuncionario = new TelaLoginFuncionario();
            telaLoginFuncionario.setVisible(true);
            dispose();
        });

        btnAdministracao.addActionListener(e -> {
            TelaAdm telaAdm = new TelaAdm();
            telaAdm.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        TelaMenuPrincipal frame = new TelaMenuPrincipal();
        frame.setVisible(true);
    }
}
