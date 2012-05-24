package client;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class Frame1 extends JFrame {

    private JTabbedPane jTabbedPane1 = new JTabbedPane();
 
    final Object rowData[][] =
    { { "Database", "On", "Derby_Embedded" }, { "Last_Update", "03/04/2012", "View_Stats" }, { "Next_Update",
                                                                                               "08/04/2012",
                                                                                               "Change" },
      { "Total_Accounts", "3456", "with 2 Billion $$" }, { "Employees", "34", "5 connected " } };
    final Object constantData[] = { "Interest/Tax", "Rate (percent)", "Time (months)" };
     final Object constantDataw[] = { "Service", "Status", "Comments" };
    final Object realData[][] ={ { "Savings", "10.33", "Annual" }, { "Fixed", "11.23", "Quaterly" }, { "Senior_Citz", "12.98", "Monthly" },
      { "Savings_Tax", "1.2", "Annual" }, { "Fixed_Tax", "0.68", "Monthly" } };

    final Object logData[] = { "No.", "Username", "Time", "Session" };
    final Object logsData[][] =
    { { "1", "Rohit", "12 PM", "2 hours" }, { "2", "Bharat", "1 Pm", "1 minute" }, { "3", "ravi",
                                                                                          "12.98 pm", "3 months" },
      { "4", "Moxambique", "1.2 Pm", "2 Sec" }, { "5", "Maximus", "0.68 pm", "4 seconds" } };
                             Object datasw[][]=new Object[5][3]; 
   
    private StatusUI jPanel1 = new StatusUI(rowData, constantDataw);
    private LoginUI jPanel2 = new LoginUI("banker","banker_id");
    private StatusUI jPanel3;
    private StatusUI jPanel4 = new StatusUI(logsData, logData);
    private LoginUI jPanel5 = new LoginUI("cust_login","cust_id");
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private void getConstantData(String tableName)
 {
             Derby gcd =new Derby(tableName);
             datasw=gcd.dataswa;
             
        
    
}
 
    
    public Frame1() {
        try {
            getConstantData("constants");
            jPanel3=new StatusUI(datasw,constantData );
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String[] getStatusC()
    {
        String[] s;
        
            s=new Derby().getStatus("status_table");
        return s;
        }

    private void jbInit() throws Exception {
        
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(600, 300));
        jTabbedPane1.setBounds(new Rectangle(0, 0, 600, 300));
        jButton1.setText("Recheck");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jButton1_mouseClicked(e);
            }
        });
        jButton2.setText("jButton2");
        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jButton2_mouseClicked(e);
            }
        });
        jPanel1.add(jButton1, null);
        jTabbedPane1.addTab("Status", jPanel1);
        jTabbedPane1.addTab("Login", jPanel2);
        jPanel3.add(jButton2, null);
        jTabbedPane1.addTab("Constants", jPanel3);
        jTabbedPane1.addTab("Logs", jPanel4);
        
                jTabbedPane1.addTab("CustLogin", jPanel5);
        this.getContentPane().add(jTabbedPane1, null);
    }


    private void jButton1_mouseClicked(MouseEvent e) {
        jTabbedPane1.remove(0);
        //jPanel1 = new StatusUI(rowData, columnData);
        jPanel1.add(jButton1, null);
        jTabbedPane1.add(jPanel1, "Status", 0);
        jTabbedPane1.setSelectedIndex(0);

    }

    private void jButton2_mouseClicked(MouseEvent e) {
        jTabbedPane1.remove(2);
        //jPanel3 = new StatusUI(rowData, columnData);
        jPanel3.add(jButton2, null);
        jTabbedPane1.add(jPanel3, "Constants", 2);
        jTabbedPane1.setSelectedIndex(2);
    }
}
