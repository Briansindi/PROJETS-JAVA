package Model;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Authentification extends JFrame {
    private JButton confirmerButton;
    private JButton annulerButton;
    private JTextField textField1;
    private JPasswordField textField2;

    public Authentification() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Authentification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel label1 = new JLabel("Login:");
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(label1, c);

        textField1 = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(textField1, c);

        JLabel label2 = new JLabel("Mot de passe:");
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(label2, c);

        textField2 = new JPasswordField(20);
        c.gridx = 1;
        c.gridy = 1;
        mainPanel.add(textField2, c);

        confirmerButton = new JButton("Confirmer");
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(confirmerButton, c);

        annulerButton = new JButton("Annuler");
        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(annulerButton, c);

        getContentPane().add(mainPanel);

        pack();
        setLocationRelativeTo(null);

        confirmerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = textField1.getText();
                String password = new String(textField2.getPassword());

                try {
                    String url = "jdbc:mysql://localhost:3306/bddgraph";
                    String user = "myusername";
                    String pass = "mypassword";
                    Connection conn = DriverManager.getConnection(url, user, pass);

                    String query = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, login);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // L'authentification a réussi
                        System.out.println("Authentification réussie");
                    } else {
                        // L'authentification a échoué
                        System.out.println("Login  ou Mot de passe incorrect");
                    }

                    rs.close();
                    stmt.close();
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
            }
        });
    }

    public static void main(String[] args) {
        Authentification auth = new Authentification();
        auth.setVisible(true);
    }
}


















