package academy;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class RegistrarAluno {

    public void registrarAluno(String cpf, String nome, int idade, String email, int planoId, Date dataInicioPlanoSQL, Date dataTerminoPlanoSQL, double valorPago) {

        String tipoPlano = mapearTipoPlano(planoId);

        if (tipoPlano == null) {
            JOptionPane.showMessageDialog(null, "Plano ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDataInicioPlano = formatter.format(dataInicioPlanoSQL);
        String formattedDataTerminoPlano = formatter.format(dataTerminoPlanoSQL);

        String url = "jdbc:postgresql://localhost:5432/academy";
        String user = "user_admin";
        String password = "sua_senha_aqui";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO alunos (cpf, nome, idade, email, plano_id, data_inicio_plano, data_termino_plano, valor_pago, tipo_plano) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setInt(3, idade);
            stmt.setString(4, email);
            stmt.setInt(5, planoId);
            stmt.setString(6, formattedDataInicioPlano);
            stmt.setString(7, formattedDataTerminoPlano);
            stmt.setDouble(8, valorPago);
            stmt.setString(9, tipoPlano);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Aluno registrado com sucesso!");
                JOptionPane.showMessageDialog(null, "Aluno registrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Falha ao registrar aluno.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar aluno: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao registrar aluno: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }


    private String mapearTipoPlano(int planoId) {
        switch (planoId) {
            case 1:
                return "Mensal";
            case 2:
                return "Trimestral";
            case 3:
                return "Semestral";
            default:
                return null;
        }
    }
}
