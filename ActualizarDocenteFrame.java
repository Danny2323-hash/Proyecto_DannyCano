import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ActualizarDocenteFrame extends JFrame {
    private JTextField txtBuscarCedula, txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JButton btnBuscar, btnActualizar;

    public ActualizarDocenteFrame() {
        setTitle("Actualizar Docente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Estilo de las etiquetas
        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        // Crear etiquetas y campos de texto para el docente
        JLabel lblBuscarCedula = new JLabel("Buscar por Cédula:");
        lblBuscarCedula.setFont(labelFont);
        txtBuscarCedula = new JTextField(15);
        txtBuscarCedula.setBorder(new EmptyBorder(3, 3, 3, 3)); // Espacio interno reducido

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(0, 123, 255));  // Color de fondo del botón
        btnBuscar.setForeground(Color.WHITE);  // Color del texto del botón
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));  // Estilo de fuente

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setFont(labelFont);
        txtCedula = new JTextField(15);
        txtCedula.setBorder(new EmptyBorder(3, 3, 3, 3));
        txtCedula.setEditable(false);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setFont(labelFont);
        txtNombre1 = new JTextField(15);
        txtNombre1.setBorder(new EmptyBorder(3, 3, 3, 3));

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setFont(labelFont);
        txtNombre2 = new JTextField(15);
        txtNombre2.setBorder(new EmptyBorder(3, 3, 3, 3));

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setFont(labelFont);
        txtApellido1 = new JTextField(15);
        txtApellido1.setBorder(new EmptyBorder(3, 3, 3, 3));

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setFont(labelFont);
        txtApellido2 = new JTextField(15);
        txtApellido2.setBorder(new EmptyBorder(3, 3, 3, 3));

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setPreferredSize(new Dimension(150, 25)); // Tamaño más pequeño
        btnActualizar.setEnabled(false);
        btnActualizar.setBackground(new Color(0, 123, 255));  // Color de fondo del botón
        btnActualizar.setForeground(Color.WHITE);  // Color del texto del botón
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));  // Estilo de fuente

        // Acción del botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDocente();
            }
        });

        // Acción del botón Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDocente();
                dispose();
            }
        });

        // Panel para buscar
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.add(lblBuscarCedula);
        panelBuscar.add(txtBuscarCedula);
        panelBuscar.add(btnBuscar);

        // Panel para los campos de texto con márgenes ajustados
        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5)); // Menos espacio entre filas y columnas
        panelCampos.setBorder(new EmptyBorder(5, 5, 5, 5)); // Reducido margen externo
        panelCampos.add(lblCedula); panelCampos.add(txtCedula);
        panelCampos.add(lblNombre1); panelCampos.add(txtNombre1);
        panelCampos.add(lblNombre2); panelCampos.add(txtNombre2);
        panelCampos.add(lblApellido1); panelCampos.add(txtApellido1);
        panelCampos.add(lblApellido2); panelCampos.add(txtApellido2);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelBuscar, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        // Panel para botón con alineación centrada
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private void buscarDocente() {
        String cedula = txtBuscarCedula.getText();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la cédula a buscar.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM docente WHERE Cedula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                txtCedula.setText(rs.getString("Cedula"));
                txtNombre1.setText(rs.getString("Nombre_1"));
                txtNombre2.setText(rs.getString("Nombre_2"));
                txtApellido1.setText(rs.getString("Apellido_1"));
                txtApellido2.setText(rs.getString("Apellido_2"));
                btnActualizar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Docente no encontrado.");
                btnActualizar.setEnabled(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar docente: " + e.getMessage());
        }
    }

    private void actualizarDocente() {
        String cedula = txtCedula.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "{CALL actualizar_docente(?, ?, ?, ?, ?)}"; // Llamada al SP
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cedula);
            stmt.setString(2, nombre1);
            stmt.setString(3, nombre2);
            stmt.setString(4, apellido1);
            stmt.setString(5, apellido2);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Docente actualizado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar docente: " + e.getMessage());
        }
    }
}
