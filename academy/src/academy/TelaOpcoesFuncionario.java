package academy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class TelaOpcoesFuncionario extends JFrame {
	
	private int funcionarioId;
    private String cpf;
    private String nome;

    public TelaOpcoesFuncionario(int funcionarioId, String cpf, String nome) {
    	this.funcionarioId = funcionarioId;
        this.cpf = cpf;
        this.nome = nome;

        setTitle("Opções do Funcionário");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnBaterPonto = new JButton("Bater Ponto");
        btnBaterPonto.setBounds(116, 34, 150, 30);
        panel.add(btnBaterPonto);

        JButton btnRegistrarTreino = new JButton("Registrar Treino");
        btnRegistrarTreino.setBounds(116, 86, 150, 30);
        panel.add(btnRegistrarTreino);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(302, 127, 72, 23);
        panel.add(btnVoltar);  

        btnBaterPonto.addActionListener(e -> {
        	TelaBaterPonto telaBaterPonto = new TelaBaterPonto(cpf, nome);
            telaBaterPonto.setVisible(true);
            dispose();
        });

        btnRegistrarTreino.addActionListener(e -> {
        	TelaRegistrarTreino telaRegistrarTreino = new TelaRegistrarTreino(funcionarioId, cpf, nome);
            telaRegistrarTreino.setVisible(true);
            dispose();
        });
        
        btnVoltar.addActionListener(e -> {
        	TelaLoginFuncionario telaLoginFuncionario = new TelaLoginFuncionario();
            telaLoginFuncionario.setVisible(true);
            dispose();
        });

    }

    public static void main(String[] args) {
        TelaLoginFuncionario frame = new TelaLoginFuncionario();
        frame.setVisible(true);
    }
}
