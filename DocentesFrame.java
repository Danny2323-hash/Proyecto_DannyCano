import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DocentesFrame extends JFrame {

    public DocentesFrame() {
        setTitle("Gestión de Docentes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear la fuente común para los botones
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        // Crear botones para cada acción
        JButton btnAgregar = new JButton("Agregar Docente");
        btnAgregar.setFont(buttonFont); // Aplicar la fuente al botón

        JButton btnEliminar = new JButton("Eliminar Docente");
        btnEliminar.setFont(buttonFont); // Aplicar la fuente al botón

        JButton btnActualizar = new JButton("Actualizar Docente");
        btnActualizar.setFont(buttonFont); // Aplicar la fuente al botón

        JButton btnMostrar = new JButton("Mostrar Docentes");
        btnMostrar.setFont(buttonFont); // Aplicar la fuente al botón

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
}


