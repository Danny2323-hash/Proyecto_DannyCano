import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ActualizarEstudianteFrame extends JFrame {
    private JTextField txtBuscarCarnet, txtCarnet, txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JButton btnBuscar, btnActualizar;

    public ActualizarEstudianteFrame() {
        setTitle("Actualizar ");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear etiquetas y campos de texto con menos borde interno
        JLabel lblBuscarCarnet = new JLabel("Buscar por Carnet:");
        txtBuscarCarnet = new JTextField(15);
        txtBuscarCarnet.setBorder(new EmptyBorder(3, 3, 3, 3)); // Espacio interno reducido

        btnBuscar = new JButton("Buscar");

        JLabel lblCarnet = new JLabel("Carnet:");
        txtCarnet = new JTextField(15);
        txtCarnet.setBorder(new EmptyBorder(3, 3, 3, 3));
        txtCarnet.setEditable(false);

        JLabel lblNombre1 = new JLabel("Nombre 1:");
        txtNombre1 = new JTextField(15);
        txtNombre1.setBorder(new EmptyBorder(3, 3, 3, 3));

        JLabel lblNombre2 = new JLabel("Nombre 2:");
        txtNombre2 = new JTextField(15);
        txtNombre2.setBorder(new EmptyBorder(3, 3, 3, 3));

        JLabel lblApellido1 = new JLabel("Apellido 1:");
        txtApellido1 = new JTextField(15);
        txtApellido1.setBorder(new EmptyBorder(3, 3, 3, 3));

        JLabel lblApellido2 = new JLabel("Apellido 2:");
        txtApellido2 = new JTextField(15);
        txtApellido2.setBorder(new EmptyBorder(3, 3, 3, 3));

        btnActualizar = new JButton("Actualizar ");
        btnActualizar.setPreferredSize(new Dimension(150, 25)); // Tamaño más pequeño
        btnActualizar.setEnabled(false);

        // Acción del botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });

        // Acción del botón Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });

        // Panel para buscar
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.add(lblBuscarCarnet);
        panelBuscar.add(txtBuscarCarnet);
        panelBuscar.add(btnBuscar);

        // Panel para los campos de texto con márgenes ajustados
        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5)); // Menos espacio entre filas y columnas
        panelCampos.setBorder(new EmptyBorder(5, 5, 5, 5)); // Reducido margen externo
        panelCampos.add(lblCarnet); panelCampos.add(txtCarnet);
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

    private void buscarEstudiante() {
        String carnet = txtBuscarCarnet.getText();
        if (carnet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el carnet a buscar.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM estudiantes WHERE carnet = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, carnet);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                txtCarnet.setText(rs.getString("carnet"));
                txtNombre1.setText(rs.getString("nombre1"));
                txtNombre2.setText(rs.getString("nombre2"));
                txtApellido1.setText(rs.getString("apellido1"));
                txtApellido2.setText(rs.getString("apellido2"));
                btnActualizar.setEnabled(true); 
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
                btnActualizar.setEnabled(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar estudiante: " + e.getMessage());
        }
    }

    private void actualizarEstudiante() {
        String carnet = txtCarnet.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "{CALL actualizar_estudiante(?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, carnet);
            stmt.setString(2, nombre1);
            stmt.setString(3, nombre2);
            stmt.setString(4, apellido1);
            stmt.setString(5, apellido2);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Estudiante actualizado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar estudiante: " + e.getMessage());
        }
    }
}
