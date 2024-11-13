import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class AgregarDocenteFrame extends JFrame {
    private JTextField txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2;

    public AgregarDocenteFrame() {
        setTitle("Agregar Docente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creación de etiquetas y campos de texto
        JLabel lblCedula = new JLabel("Cédula:");
        txtCedula = new JTextField(20);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        txtNombre1 = new JTextField(20);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        txtNombre2 = new JTextField(20);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        txtApellido1 = new JTextField(20);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        txtApellido2 = new JTextField(20);

        JButton btnAgregar = new JButton("Agregar");

        // Acción para el botón de agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDocente();
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

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblNombre1, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtNombre1, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblNombre2, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtNombre2, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblApellido1, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(txtApellido1, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(lblApellido2, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(txtApellido2, gbc);

        // Botón de agregar
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; // El botón ocupa ambas columnas
        gbc.anchor = GridBagConstraints.CENTER;  // Centrado del botón
        panel.add(btnAgregar, gbc);

        // Estilo de la ventana
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añadir bordes alrededor
    

        // Estilo del botón
        btnAgregar.setBackground(new Color(0, 123, 255));  // Color de fondo del botón
        btnAgregar.setForeground(Color.WHITE);  // Color del texto del botón
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));  // Estilo de fuente

        // Estilo de las etiquetas
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        lblCedula.setFont(labelFont);
        lblNombre1.setFont(labelFont);
        lblNombre2.setFont(labelFont);
        lblApellido1.setFont(labelFont);
        lblApellido2.setFont(labelFont);

        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void agregarDocente() {
        // Validación de campos
        if (txtCedula.getText().isEmpty() || txtNombre1.getText().isEmpty() || txtApellido1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete los campos obligatorios.");
            return;
        }

        // Extracción de datos de los campos
        String cedula = txtCedula.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "{CALL insertar_docente(?, ?, ?, ?, ?)}";  // Llamada al SP
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cedula);
            stmt.setString(2, nombre1);
            stmt.setString(3, nombre2);
            stmt.setString(4, apellido1);
            stmt.setString(5, apellido2);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Docente agregado exitosamente.");
            dispose();  // Cerrar ventana
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar docente: " + e.getMessage());
        }
    }
}

