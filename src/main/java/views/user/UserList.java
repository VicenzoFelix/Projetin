package views.user;

import models.Usuario;
import models.dao.UsuarioDAO;
import views.ButtonEditor;
import views.ButtonRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class UserList extends JPanel {
    private UserListTableModel tableModel;
    private JTable table;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private JButton btnNovo;

    public UserList(){
        this.initComponents();
        this.loadUsers();
    }

    private void loadUsers() {
        List<Usuario> users = usuarioDAO.findAll();
        tableModel.setUsers(users);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        tableModel = new UserListTableModel(List.of());
        table = new JTable(tableModel);

        // Render e editor dos botões
        table.getColumn("Editar").setCellRenderer(
                new ButtonRenderer());
        table.getColumn("Editar").setCellEditor(
                new ButtonEditor(table, row -> editarUsuario(row)));

        table.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
        table.getColumn("Excluir").setCellEditor(
                new ButtonEditor(table, row -> excluirUsuario(row)));

        JScrollPane scrollPane = new JScrollPane(table);

        btnNovo = new JButton("Incluir Novo Usuário");
        btnNovo.addActionListener(e -> incluirUsuario());

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBottom.add(btnNovo);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

    }

    private void incluirUsuario() {
        UsuarioForm form = new UsuarioForm((Frame) SwingUtilities.getWindowAncestor(this), null, new UsuarioForm.FormListener() {
            @Override
            public void onSave(Usuario usuario) {
                try {
                    usuarioDAO.save(usuario);
                    loadUsers();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(UserList.this, "Erro ao salvar: " + e.getMessage());
                }
            }
            @Override
            public void onCancel() {
                // nada a fazer
            }
        });
        form.setVisible(true);
    }

    private void editarUsuario(int row) {
        Usuario usuario = tableModel.getUsuarioAt(row);
        UsuarioForm form = new UsuarioForm(
                (Frame) SwingUtilities.getWindowAncestor(this),
                usuario, new UsuarioForm.FormListener() {
            @Override
            public void onSave(Usuario usuario) {
                try {
                    usuarioDAO.save(usuario);
                    loadUsers();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(UserList.this, "Erro ao salvar: " + e.getMessage());
                }
            }
            @Override
            public void onCancel() {
                // nada a fazer
            }
        });
        form.setVisible(true);
    }

    private void excluirUsuario(int row) {
        Usuario usuario = tableModel.getUsuarioAt(row);
        int opcao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o usuário " +
                        usuario.getNome() + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                usuarioDAO.delete(usuario);
                this.loadUsers();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao excluir: " + e.getMessage());
            }
        }
    }

}
