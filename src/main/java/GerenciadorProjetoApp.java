import views.user.UserList;

import javax.swing.*;
import java.awt.*;

public class GerenciadorProjetoApp extends JFrame {

    private static final String EMPTY_SCREEN = "EMPTY_SCREEN";
    private static final String USER_LIST_SCREEN = "USER_LIST_SCREEN";
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GerenciadorProjetoApp(){
        setTitle("Gerenciador de Projetos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Empty Panel
        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.add(
                new JLabel("Bem-vindo! Use o menu para navegar!",
                        SwingConstants.CENTER), BorderLayout.CENTER);
        mainPanel.add(emptyPanel, EMPTY_SCREEN);

        // Screen User List
        UserList userList = new UserList();
        mainPanel.add(userList, USER_LIST_SCREEN);

        JMenu menu = new JMenu("Menu");
        JMenuItem listUsersItem = new JMenuItem("Listar Usuários");
        JMenuItem exitItem = new JMenuItem("Sair");

        menu.add(listUsersItem);
        menu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);

        listUsersItem.addActionListener(e -> {
            cardLayout.show(mainPanel, USER_LIST_SCREEN);
        });

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
