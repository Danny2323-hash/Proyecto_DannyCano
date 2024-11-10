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

        // Definir la fuente común
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Crear los componentes con la fuente aplicada
        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setFont(labelFont);  // Aplicar la fuente a la etiqueta

        txtCarnet = new JTextField(15);
        txtCarnet.setFont(labelFont);  // Aplicar la fuente al campo de texto

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(buttonFont);  // Aplicar la fuente al botón
        btnEliminar.setBackground(new Color(0, 123, 255));  // Color de fondo del botón
        btnEliminar.setForeground(Color.WHITE);  // Color del texto del botón

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

        // Agregar componentes al panel
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
