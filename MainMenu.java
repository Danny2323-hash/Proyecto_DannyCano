import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Menú Principal");
        setSize(600, 400); // Tamaño más compacto para centrar el contenido
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definir la fuente común
        Font commonFont = new Font("Arial", Font.BOLD, 14);

        // Crear los botones
        JButton btnEstudiantes = new JButton("Gestionar Estudiantes");
        JButton btnDocentes = new JButton("Gestionar Docentes");

        // Aplicar la fuente común a los botones
        btnEstudiantes.setFont(commonFont);
        btnDocentes.setFont(commonFont);

        // Modificar el color de fondo y texto de los botones
        btnEstudiantes.setBackground(new Color(0, 123, 255));  // Azul
        btnDocentes.setBackground(new Color(0, 123, 255));  // Azul
        btnEstudiantes.setForeground(Color.WHITE);  // Texto blanco
        btnDocentes.setForeground(Color.WHITE);  // Texto blanco

        // Establecer un tamaño uniforme para los botones
        btnEstudiantes.setPreferredSize(new Dimension(200, 50));
        btnDocentes.setPreferredSize(new Dimension(200, 50));

        // Crear el panel principal con GridBagLayout para centrado
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes

        // Configurar y agregar el botón de "Gestionar Estudiantes"
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnEstudiantes, gbc);

        // Configurar y agregar el botón de "Gestionar Docentes"
        gbc.gridy = 1;
        panel.add(btnDocentes, gbc);

        // Agregar el panel centrado a la ventana
        add(panel);

        // Añadir acciones a los botones
        btnEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana para gestionar estudiantes
                new EstudiantesFrame();
            }
        });

        btnDocentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana para gestionar docentes
                new DocentesFrame();
            }
        });

        // Hacer visible la ventana
        setVisible(true);
    }
}
