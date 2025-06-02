import javax.swing.*;
import java.awt.*;

public class GerenciadorProjetoApp extends JFrame {

    private static final String EMPTY_SCREEN = "EMPTY_SCREEN";
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GerenciadorProjetoApp(){
        setTitle("Gerenciador de Projetos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.add(
                new JLabel("Bem-vindo! Use o menu para navegar!",
                        SwingConstants.CENTER), BorderLayout.CENTER);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(emptyPanel, EMPTY_SCREEN);

        JMenu menu = new JMenu("Menu");
        JMenuItem listUsersItem = new JMenuItem("Listar Usuários");
        JMenuItem exitItem = new JMenuItem("Sair");

        menu.add(listUsersItem);
        menu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);

        exitItem.addActionListener(event -> {
            dispose();
        });

        add(mainPanel);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "2.0");

        SwingUtilities.invokeLater(() -> {
            new GerenciadorProjetoApp().setVisible(true);
        });

    }
}
