package views.user;

import models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UsuarioForm extends JFrame {
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JButton btnSalvar;
    private JButton btnCancelar;

    private Usuario usuario;
    private FormListener listener;

    public interface FormListener {
        void onSave(Usuario usuario);
        void onCancel();
    }

    public UsuarioForm(Frame parent, Usuario usuario, FormListener listener) {
        super(usuario == null ? "Novo Usuário" : "Editar Usuário");
        this.usuario = usuario == null ? new Usuario() : usuario;
        this.listener = listener;
        initComponents();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblId = new JLabel("ID:");
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");

        txtId = new JTextField(10);
        txtNome = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSenha = new JTextField(20);

        if (usuario.getId() != 0) {
            txtId.setText(String.valueOf(usuario.getId()));
            txtId.setEditable(false);  // não edita ID existente
        }

        txtNome.setText(usuario.getNome());
        txtEmail.setText(usuario.getEmail());
        txtSenha.setText(usuario.getSenha());

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0; gbc.gridy = 0; add(lblId, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(lblNome, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(lblSenha, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(txtSenha, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(btnSalvar, gbc);
        gbc.gridx = 1; gbc.gridy = 4; add(btnCancelar, gbc);

        btnSalvar.addActionListener(e -> onSalvar());
        btnCancelar.addActionListener(e -> onCancelar());
    }

    private void onSalvar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            listener.onSave(usuario);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancelar() {
        listener.onCancel();
        dispose();
    }
}