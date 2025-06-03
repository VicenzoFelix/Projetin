package views.user;

import models.Usuario;
import models.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class UserList extends JPanel {
    private UserListTableModel tableModel;
    private JTable table;
    private UsuarioDAO usarioDAO = new UsuarioDAO();

    public UserList(){
        this.initComponents();
        this.loadUsers();
    }

    private void loadUsers() {
        List<Usuario> users = usarioDAO.findAll();
        tableModel.setUsers(users);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        tableModel = new UserListTableModel(List.of());
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);



        add(scrollPane, BorderLayout.CENTER);

    }

}
