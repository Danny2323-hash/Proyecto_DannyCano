import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EliminarDocenteFrame extends JFrame {
    private JTextField txtCedula;

    public EliminarDocenteFrame() {
        setTitle("Eliminar Docente");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creación de etiquetas y campos de texto
        JLabel lblCedula = new JLabel("Cédula del Docente:");
        txtCedula = new JTextField(20);

        // Botón para eliminar docente
        JButton btnEliminar = new JButton("Eliminar");

        // Acción para el botón de eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDocente();
            }
        });

        // Configuración del panel con GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  // Usar GridBagLayout para más control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Márgenes entre componentes

        // Agregar componentes al panel con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblCedula, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtCedula, gbc);

        // Botón de eliminar
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;  // El botón ocupa ambas columnas
        gbc.anchor = GridBagConstraints.CENTER;  // Centrado del botón
        panel.add(btnEliminar, gbc);

        // Estilo de la ventana
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añadir bordes alrededor
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void eliminarDocente() {
        // Validación de campos
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la cédula del docente.");
            return;
        }

        // Extracción de datos del campo
        String cedula = txtCedula.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "{CALL eliminar_docente(?)}";  // Llamada al procedimiento almacenado
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cedula);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Docente eliminado exitosamente.");
            dispose();  // Cerrar ventana
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar docente: " + e.getMessage());
        }
    }
}

