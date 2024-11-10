import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MostrarEstudiantesFrame extends JFrame {
    private JTable tablaEstudiantes;

    public MostrarEstudiantesFrame() {
        setTitle("Mostrar Estudiantes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Definir las columnas de la tabla
        String[] columnas = {"Carnet", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tablaEstudiantes = new JTable(modelo);

        // Centramos los datos de cada columna
        centrarColumnas(tablaEstudiantes);

        // JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        // Añadir un borde para el scrollPane para que no se pegue a los bordes
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes internos
        panel.add(scrollPane, BorderLayout.CENTER);

        // Cargar los datos de los estudiantes
        cargarEstudiantes(modelo);

        // Botón de regresar con tamaño preferido ajustado
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(100, 30)); // Establece el tamaño del botón
        btnRegresar.addActionListener(e -> dispose());  // Cerrar la ventana actual

        // Utilizar FlowLayout para que el botón no ocupe todo el ancho
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnRegresar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel a la ventana
        add(panel);

        setVisible(true);
    }

    private void centrarColumnas(JTable tabla) {
        // Establecer el alineamiento de las celdas a centrado para todas las columnas
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableCellRenderer renderer = tabla.getDefaultRenderer(Object.class);
            ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER); // Centra los datos
            tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void cargarEstudiantes(DefaultTableModel modelo) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "CALL mostrar_estudiantes()";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] fila = {
                    rs.getString("Carnet"),
                    rs.getString("Nombre1"),
                    rs.getString("Nombre2"),
                    rs.getString("Apellido1"),
                    rs.getString("Apellido2")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes: " + e.getMessage());
        }
    }
}