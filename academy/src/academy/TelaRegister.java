package academy;

import java.awt.EventQueue;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaRegister extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldCPF;
    private JTextField textFieldNome;
    private JTextField textFieldIdade;
    private JTextField textFieldEmail;
    private JTextField textFieldPlanoId;
    private JTextField textFieldDataInicioPlano;
    private JTextField textFieldValorPago;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaRegister frame = new TelaRegister();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaRegister() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 200, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(32, 245, 76, 23);
        contentPane.add(btnVoltar);

        btnVoltar.addActionListener(e -> {
            TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
            telaBoasVindas.setVisible(true);
            dispose();
        });

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(35, 31, 46, 14);
        contentPane.add(lblCpf);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(35, 56, 46, 14);
        contentPane.add(lblNome);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(35, 81, 46, 14);
        contentPane.add(lblIdade);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(35, 106, 46, 14);
        contentPane.add(lblEmail);

        JLabel lblPlanoId = new JLabel("Plano ID:");
        lblPlanoId.setBounds(35, 131, 63, 14);
        contentPane.add(lblPlanoId);

        JLabel lblDataInicioPlano = new JLabel("Data Início Plano:");
        lblDataInicioPlano.setBounds(35, 156, 104, 14);
        contentPane.add(lblDataInicioPlano);

        JLabel lblValorPago = new JLabel("Valor Pago:");
        lblValorPago.setBounds(35, 181, 76, 14);
        contentPane.add(lblValorPago);

        textFieldCPF = new JTextField();
        textFieldCPF.setBounds(149, 28, 215, 20);
        contentPane.add(textFieldCPF);
        textFieldCPF.setColumns(10);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(149, 53, 215, 20);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);

        textFieldIdade = new JTextField();
        textFieldIdade.setBounds(149, 78, 215, 20);
        contentPane.add(textFieldIdade);
        textFieldIdade.setColumns(10);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(149, 103, 215, 20);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        textFieldPlanoId = new JTextField();
        textFieldPlanoId.setBounds(149, 128, 215, 20);
        contentPane.add(textFieldPlanoId);
        textFieldPlanoId.setColumns(10);

        textFieldDataInicioPlano = new JTextField();
        textFieldDataInicioPlano.setBounds(149, 153, 215, 20);
        contentPane.add(textFieldDataInicioPlano);
        textFieldDataInicioPlano.setColumns(10);

        textFieldValorPago = new JTextField();
        textFieldValorPago.setBounds(149, 178, 215, 20);
        contentPane.add(textFieldValorPago);
        textFieldValorPago.setColumns(10);

        JButton btnRegistrarAluno = new JButton("Registrar Aluno");
        btnRegistrarAluno.setBounds(149, 215, 163, 23);
        contentPane.add(btnRegistrarAluno);

        btnRegistrarAluno.addActionListener(e -> {
            boolean cpfValido = false;
            boolean idadeValida = false;

            String cpf = textFieldCPF.getText();
            if (cpf.matches("\\d{11}")) {
                cpfValido = true;
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, insira um CPF válido com 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                textFieldCPF.setText("");
            }

            int idade = 0;
            String idadeStr = textFieldIdade.getText();
            try {
                idade = Integer.parseInt(idadeStr);
                if (idade > 0) {
                    idadeValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira uma idade válida maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    textFieldIdade.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
                textFieldIdade.setText("");
            }

            if (!cpfValido || !idadeValida) {
                return;
            }

            String tipoPlano = textFieldPlanoId.getText();
            LocalDate dataInicio = LocalDate.parse(textFieldDataInicioPlano.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            LocalDate dataTermino;
            switch (tipoPlano) {
                case "1":
                    dataTermino = dataInicio.plusDays(31);
                    break;
                case "2":
                    dataTermino = dataInicio.plusDays(93);
                    break;
                case "3":
                    dataTermino = dataInicio.plusDays(186);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo de plano inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            String nome = textFieldNome.getText();
            String email = textFieldEmail.getText();
            int planoId = Integer.parseInt(textFieldPlanoId.getText());

            Date dataInicioPlanoSQL = Date.valueOf(dataInicio);
            Date dataTerminoPlanoSQL = Date.valueOf(dataTermino);

            double valorPago = 0.0;

            try {
                valorPago = Double.parseDouble(textFieldValorPago.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um valor de pagamento válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            RegistrarAluno registrarAluno = new RegistrarAluno();
            registrarAluno.registrarAluno(cpf, nome, idade, email, planoId, dataInicioPlanoSQL, dataTerminoPlanoSQL, valorPago);
            
            TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
            telaBoasVindas.setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
