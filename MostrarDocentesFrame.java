import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MostrarDocentesFrame extends JFrame {
    private JTable tablaDocentes;

    public MostrarDocentesFrame() {
        setTitle("Mostrar Docentes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Definir la fuente común
        Font commonFont = new Font("Arial", Font.PLAIN, 14);

        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Definir las columnas de la tabla para los docentes
        String[] columnas = {"Cédula", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tablaDocentes = new JTable(modelo);
        tablaDocentes.setFont(commonFont);  // Aplicar la fuente a la tabla

        // Centramos los datos de cada columna
        centrarColumnas(tablaDocentes);

        // JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaDocentes);
        // Añadir un borde para el scrollPane para que no se pegue a los bordes
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes internos
        panel.add(scrollPane, BorderLayout.CENTER);

        // Cargar los datos de los docentes
        cargarDocentes(modelo);

        // Botón de regresar con tamaño preferido ajustado
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(100, 30)); // Establece el tamaño del botón
        btnRegresar.setFont(commonFont);  // Aplicar la fuente al botón
        btnRegresar.addActionListener(e -> dispose()); // Cerrar la ventana actual
        btnRegresar.setBackground(new Color(0, 123, 255));  // Color de fondo del botón
        btnRegresar.setForeground(Color.WHITE);  // Color del texto del botón

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

    private void cargarDocentes(DefaultTableModel modelo) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Llamamos al procedimiento almacenado 'mostrar_docentes'
            String sql = "{CALL mostrar_docentes()}";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] fila = {
                    rs.getString("Cedula"),
                    rs.getString("Nombre_1"),
                    rs.getString("Nombre_2"),
                    rs.getString("Apellido_1"),
                    rs.getString("Apellido_2")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar docentes: " + e.getMessage());
        }
    }
}
