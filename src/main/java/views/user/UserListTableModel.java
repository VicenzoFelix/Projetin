package views.user;

import models.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserListTableModel extends AbstractTableModel {
    private List<Usuario> users;
    private final String[] columns =
            {"ID", "Nome", "Email", "Senha", "Editar", "Excluir"};

    public UserListTableModel(List<Usuario> users) {
        this.users = users;
    }

    @Override
    public String getColumnName(int column) {
        return this.columns[column];
    }

    @Override
    public int getRowCount() {
        return this.users.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario user = this.users.get(rowIndex);
        switch (columnIndex){
            case 0: return user.getId();
            case 1: return user.getNome();
            case 2: return user.getEmail();
            case 3: return user.getSenha();
            case 4: return "Editar";
            case 5: return "Excluir";
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Só as colunas de botão serão "editáveis" para capturar cliques
        return columnIndex == 4 || columnIndex == 5;
    }

    public Usuario getUsuarioAt(int rowIndex) {
        return this.users.get(rowIndex);
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
        fireTableDataChanged();
    }
}
