package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JPasswordField;


public class Manager extends Banker {
    
    private Statement stmt=null; 
    public String usertable="banker";
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
 final Object rowData[][] =
    { { "Database", "On", "Derby_Embedded" }, { "Last_Update", "03/04/2012", "View_Stats" }, { "Next_Update",
                                                                                               "08/04/2012",
                                                                                               "Change" },
      { "Total_Accounts", "3456", "with 2 Billion $$" }, { "Employees", "34", "5 connected " } };
    final Object constantData[] = { "Interest/Tax", "Rate (percent)", "Time (months)" };
    final Object realData[][] ={ { "Savings", "10.33", "Annual" }, { "Fixed", "11.23", "Quaterly" }, { "Senior_Citz", "12.98", "Monthly" },
      { "Savings_Tax", "1.2", "Annual" }, { "Fixed_Tax", "0.68", "Monthly" } };
     final Object constantDataw[] = { "Service", "Status", "Comments" };
    final Object logData[] = { "No.", "Username", "Time", "Session" };
    final Object logsData[][] =
    { { "1", "Rohit", "12 PM", "2 hours" }, { "2", "Bharat", "1 Pm", "1 minute" }, { "3", "ravi",
                                                                                          "12.98 pm", "3 months" },
      { "4", "Moxambique", "1.2 Pm", "2 Sec" }, { "5", "Maximus", "0.68 pm", "4 seconds" } };
                             Object datasw[][]=new Object[5][3]; 
                             private StatusUI jPanel1 = new StatusUI(rowData, constantDataw);
    private JPanel jPanel2 = new JPanel();
    private StatusUI jPanel3 ;
    private StatusUI jPanel4 = new StatusUI(logsData, logData);
    private JLabel jLabel1=new JLabel();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JButton jButton6 = new JButton();
    private JButton jButton7 = new JButton();
    private JPasswordField as=new JPasswordField();
    private Derby dara;
    public int id;
    private JPasswordField jPasswordField1 = new JPasswordField();

    public void getConstantData(String tableName)
 {
             Derby gcd =new Derby(tableName);
             datasw=gcd.dataswa;
             
        
    
}
    public Manager(int id,String username) {
        try {
            this.id = id;
            this.username=username.toUpperCase();
            this.getConstantData("constants");
            jPanel3 = new StatusUI(datasw,constantData);
            this.jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Manager() {
        
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(678, 370));
        jTabbedPane1.setBounds(new Rectangle(0, 0, 560, 315));
        jLabel1.setText(this.username);
        jLabel1.setBounds(new Rectangle(575, 40, 90, 60));
        jLabel1.setFont(new Font("Comic Sans MS", 1, 14));
        jButton1.setText("Managers");
        jButton2.setText("Clerks");
        jButton3.setText("Reports");
        jButton4.setText("Customer");
        jButton5.setText("Log Out");
                jButton7.setText("Constants");
        jButton5.setBounds(new Rectangle(575, 200, 75, 21));
        jButton5.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton5_mouseClicked(e);
                }
            });
        
         jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton3_mouseClicked(e);
                }
            });
        
        jButton7.setBounds(new Rectangle(555, 265, 75, 21));
        jButton7.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton7_mouseClicked(e);
                }
            });
        jButton6.setBounds(new Rectangle(565, 230, 105, 21));
        jButton6.setSize(new Dimension(105, 21));
        jButton6.setText("Change Password");
        jButton6.setFont(new Font("Tahoma", 0, 8));
        jButton6.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton6_mouseClicked(e);
                }
            });
        jPasswordField1.setPreferredSize(new Dimension(100, 19));
        jPasswordField1.setText("pa");
        jPasswordField1.setBounds(new Rectangle(288, 6, 100, 19));
        jPasswordField1.setAlignmentX((float) 1.5);
        jTabbedPane1.addTab("Welcome", jPanel1);
        jPanel2.add(jButton1, null);
        jPanel2.add(jButton2, null);
        jPanel2.add(jButton3, null);
        jPanel2.add(jButton4, null);
        jPanel2.add(jPasswordField1, null);
        jTabbedPane1.addTab("Genesis", jPanel2);
        jTabbedPane1.addTab("Constants", jPanel3);
        jTabbedPane1.addTab("Logs", jPanel4);
        this.getContentPane().add(jButton6, null);
        this.getContentPane().add(jButton7, null);
        this.getContentPane().add(jButton5, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jTabbedPane1, null);

        this.setVisible(true);
    }

    void setInterestRate() {
    }

    void createClerk() {
    }

    void setTaxrate() {
    }

    private void jButton5_mouseClicked(MouseEvent e) {
        
        this.dispose();
    }
    
    private void jButton6_mouseClicked(MouseEvent e)
        {
        // TODO add your handling code here:
        char[] b=jPasswordField1.getPassword();
        String pass=new String(b);
        int num=0;
         try
                      {
                              Class.forName("oracle.jdbc.driver.OracleDriver");
                              Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Pulkit.ini4Oracle%");
                               stmt= conn.createStatement();
                              String sql = "UPDATE banker SET password ='"+pass+"' where banker_id="+this.id+"";
      num=stmt.executeUpdate(sql);
}
                      catch(SQLException | ClassNotFoundException sqle)
                      {
                      }
        if(num>0)
        {
            JOptionPane.showMessageDialog(this," password Changed");
            //this.dispose();
        }
        else
             JOptionPane.showMessageDialog(this,"Wrong password");
        
    }
    
    private void jButton7_mouseClicked(MouseEvent e)
        {
           Constants c= new Constants(this,true);
    }
     private void jButton3_mouseClicked(MouseEvent e)
        {
           ManReports c= new ManReports(true);
    }
}
