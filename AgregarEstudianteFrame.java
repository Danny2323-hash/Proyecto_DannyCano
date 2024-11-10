import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class AgregarEstudianteFrame extends JFrame {
    private JTextField txtCarnet, txtNombre1, txtNombre2, txtApellido1, txtApellido2;

    public AgregarEstudianteFrame() {
        setTitle("Agregar Estudiante");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear etiquetas y campos de texto
        JLabel lblCarnet = new JLabel("Carnet:");
        txtCarnet = new JTextField(15);

        JLabel lblNombre1 = new JLabel("Nombre 1:");
        txtNombre1 = new JTextField(15);

        JLabel lblNombre2 = new JLabel("Nombre 2:");
        txtNombre2 = new JTextField(15);

        JLabel lblApellido1 = new JLabel("Apellido 1:");
        txtApellido1 = new JTextField(15);

        JLabel lblApellido2 = new JLabel("Apellido 2:");
        txtApellido2 = new JTextField(15);

        JButton btnAgregar = new JButton("Agregar Estudiante");

        // Agregar acción para el botón
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
                dispose();
            }
        });

        // Configurar diseño usando GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblCarnet, gbc);

        gbc.gridx = 1;
        panel.add(txtCarnet, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblNombre1, gbc);

        gbc.gridx = 1;
        panel.add(txtNombre1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNombre2, gbc);

        gbc.gridx = 1;
        panel.add(txtNombre2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblApellido1, gbc);

        gbc.gridx = 1;
        panel.add(txtApellido1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblApellido2, gbc);

        gbc.gridx = 1;
        panel.add(txtApellido2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAgregar, gbc);

        add(panel);
        setVisible(true);
    }

    private void agregarEstudiante() {
        // Validación de campos
        if (txtCarnet.getText().isEmpty() || txtNombre1.getText().isEmpty() || txtApellido1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete los campos obligatorios.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "CALL agregar_estudiante(?, ?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, txtCarnet.getText());
            stmt.setString(2, txtNombre1.getText());
            stmt.setString(3, txtNombre2.getText());
            stmt.setString(4, txtApellido1.getText());
            stmt.setString(5, txtApellido2.getText());
            stmt.execute();
            JOptionPane.showMessageDialog(this, "Estudiante agregado exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar estudiante: " + e.getMessage());
        }
    }
}

