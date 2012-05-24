package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class StatusUI extends JPanel implements ActionListener {

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable jTable1 = new JTable();
   
    

    public StatusUI(Object[][] rowData, Object[] columnNames) {
        super();

        jTable1 = new JTable(rowData, columnNames);
        jTable1.setEnabled(true);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.getViewport().add(jTable1, null);
        this.add(jScrollPane1, null);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {

        jScrollPane1.getViewport().add(jTable1, null);
        this.add(jScrollPane1, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
