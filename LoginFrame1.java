import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame1 extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public LoginFrame1() {
        // Configuración básica de la ventana
        setTitle("Login");
        setSize(400, 300); // Tamaño ajustado para que el formulario sea más compacto
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal con GridBagLayout para centrado
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir márgenes

        // Configurar restricciones para el centrado
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Crear los campos de entrada
        userField = new JTextField(15);
        passField = new JPasswordField(15);

        // Crear el botón de login con icono
        ImageIcon icono = new ImageIcon(getClass().getResource(".vscode\\libs\\ingresar.png"));
        loginButton = new JButton("Iniciar sesión", icono);
        loginButton.setText(""); // Eliminar el texto del botón
        loginButton.setContentAreaFilled(false); // Hacer que el botón tenga fondo transparente
        loginButton.setFocusPainted(false); // Quitar el borde de enfoque al hacer clic
        loginButton.setBorderPainted(false);

        // Establecer un tamaño preferido para el botón (opcional)
        loginButton.setPreferredSize(new Dimension(30, 30));

        // Añadir la etiqueta y el campo de usuario
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Usuario:"), gbc);

        gbc.gridy = 1;
        mainPanel.add(userField, gbc);

        // Añadir la etiqueta y el campo de contraseña
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridy = 3;
        mainPanel.add(passField, gbc);

        // Añadir el botón de login
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE; // Evitar que el botón se expanda
        mainPanel.add(loginButton, gbc);

        // Añadir el panel principal a la ventana
        add(mainPanel);

              userField.setToolTipText("Ingresa tu nombre de usuario");
        passField.setToolTipText("Ingresa tu contraseña");
        loginButton.setToolTipText("Haz clic para iniciar sesión");

        // Acción del botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String contraseña = new String(passField.getPassword());

                // Verificar si el usuario y la contraseña son correctos
                if (autenticarUsuario(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(LoginFrame1.this, "Autenticación exitosa");
                    abrirMenuPrincipal();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame1.this, "Usuario o contraseña incorrectos");
                }
            }
        });

        setVisible(true);
    }

    // Método para autenticar el usuario (utilizando el Stored Procedure)
    private boolean autenticarUsuario(String usuario, String contraseña) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establecer la conexión a la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_inscripciones", "root", "root");

            // Consulta para verificar las credenciales del usuario
            String sql = "SELECT * FROM usuarios_nuevos WHERE usuario = ? AND contrasena = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);

            rs = stmt.executeQuery();

            // Verificar si se obtuvo un resultado
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para abrir el menú principal después del login exitoso
    private void abrirMenuPrincipal() {
        // Aquí abrirías el JFrame del menú principal (puede ser otra clase como
        // MainMenu)
        new MainMenu();
        dispose(); // Cierra la ventana de login
    }

    public static void main(String[] args) {
        // Iniciar la ventana de login cuando se ejecute el programa
        new LoginFrame1();
    }
}
