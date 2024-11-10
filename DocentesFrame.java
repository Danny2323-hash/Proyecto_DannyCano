import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocentesFrame extends JFrame {

    public DocentesFrame() {
        setTitle("Gestión de Docentes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear botones para cada acción
        JButton btnAgregar = new JButton("Agregar Docente");
        JButton btnEliminar = new JButton("Eliminar Docente");
        JButton btnActualizar = new JButton("Actualizar Docente");
        JButton btnMostrar = new JButton("Mostrar Docentes");

        // Añadir ActionListeners para abrir las ventanas correspondientes
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarDocenteFrame();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarDocenteFrame();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ActualizarDocenteFrame();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MostrarDocentesFrame();
            }
        });

        // Crear panel y configurar el layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Espacio entre los botones
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegura que los botones mantengan su tamaño preferido

        // Agregar botones al panel de manera vertical
        gbc.gridy = 0;
        panel.add(btnAgregar, gbc);
        gbc.gridy = 1;
        panel.add(btnEliminar, gbc);
        gbc.gridy = 2;
        panel.add(btnActualizar, gbc);
        gbc.gridy = 3;
        panel.add(btnMostrar, gbc);

        // Añadir el panel centrado al frame
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Iniciar la ventana de gestión de docentes
        new DocentesFrame();
    }
}

