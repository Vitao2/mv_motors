package mv_motors.utility;

import javax.swing.JTable;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class TableUtility {
    
    public static void limpar(List<JTextField> textFields, JButton inserir, JButton remover, JButton alterar, Runnable metodo)
    {
        // para cada TextField na lista
        for (JTextField text : textFields)
            text.setText(""); // marcar o texto como vazio
        
        // atualizando a tabela...
        if (metodo != null)
            metodo.run();

        inserir.setEnabled(true);   // ativando o botao inserir
        remover.setEnabled(false);  // desativando o botao remover
        alterar.setEnabled(false);  // desativando o botao alterar
    }
    
    public static <T> void atualizarTabela(JTable tabela, List<T> list)
    {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        model.setRowCount(0); // limpa a tabela

        try {
            //List<Funcionario> funcs = fc.findFuncionarioEntities();

//            for (Funcionario func : funcs) {
//                String linha[] = {
//                    String.valueOf(func.getId()),
//                    String.valueOf(func.getDepartamentoId().getId()),
//                    func.getNome(),
//                    func.getFuncao(),
//                    func.getRegistro()
//                };
//
//                model.addRow(linha);
//            }

            //atualizarComboBox();

//            if (comboBox_idDep.getItemCount() > 0) {
//                button_inserir.setEnabled(true);
//            }

        } catch (Exception ex) {
            
        }
    }
}
