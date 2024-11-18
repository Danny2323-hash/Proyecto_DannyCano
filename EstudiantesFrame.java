import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstudiantesFrame extends JFrame {
    public EstudiantesFrame() {
        setTitle("Gestionar Estudiantes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear la fuente común para los botones
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        // Crear los botones para cada acción
        JButton btnAgregar = new JButton("Agregar Estudiante");
        btnAgregar.setFont(buttonFont);  // Aplicar la fuente al botón
        JButton btnEliminar = new JButton("Eliminar Estudiante");
        btnEliminar.setFont(buttonFont);  // Aplicar la fuente al botón
        JButton btnActualizar = new JButton("Actualizar Estudiante");
        btnActualizar.setFont(buttonFont);  // Aplicar la fuente al botón
        JButton btnMostrar = new JButton("Mostrar Estudiantes");
        btnMostrar.setFont(buttonFont);  // Aplicar la fuente al botón

        // Establecer el tamaño preferido de los botones
        btnAgregar.setPreferredSize(new Dimension(200, 50));
        btnEliminar.setPreferredSize(new Dimension(200, 50));
        btnActualizar.setPreferredSize(new Dimension(200, 50));
        btnMostrar.setPreferredSize(new Dimension(200, 50));

        // ActionListeners para abrir las ventanas correspondientes
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarEstudianteFrame();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarEstudianteFrame();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ActualizarEstudianteFrame();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MostrarEstudiantesFrame();
            }
        });

        // Crear panel y configurar el layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado entre los botones
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegura que los botones mantengan su tamaño preferido

        // Agregar los botones al panel de manera vertical
        gbc.gridy = 0;
        panel.add(btnAgregar, gbc);
        gbc.gridy = 1;
        panel.add(btnEliminar, gbc);
        gbc.gridy = 2;
        panel.add(btnActualizar, gbc);
        gbc.gridy = 3;
        panel.add(btnMostrar, gbc);

        // Añadir el panel al frame
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }


}

