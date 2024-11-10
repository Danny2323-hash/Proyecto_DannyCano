import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class EliminarEstudianteFrame extends JFrame {
    private JTextField txtCarnet;

    public EliminarEstudianteFrame() {
        setTitle("Eliminar Estudiante");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblCarnet = new JLabel("Carnet:");
        txtCarnet = new JTextField(15);

        JButton btnEliminar = new JButton("Eliminar ");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
                dispose();
            }
        });

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblCarnet, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtCarnet, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnEliminar, gbc);

        add(panel);
        setVisible(true);
    }

    private void eliminarEstudiante() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "CALL eliminar_estudiante(?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, txtCarnet.getText());
            stmt.execute();
            JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar estudiante: " + e.getMessage());
        }
    }
}
