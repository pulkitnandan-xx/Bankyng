/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author devil
 */
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.awt.print.PrinterException; 
 
import javax.swing.JDialog; 
import javax.swing.JLabel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import java.util.*; 
import java.sql.*; 
import javax.swing.*; 
public class CustReports extends JDialog { 
 
   
private JTable table;
    private int fabel;
public Vector rows() { 
 
    Vector data = new Vector(); 
    String sql= null; 
    Connection C; 
 
 
        try { 
 
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
            C = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
                    "system", "Pulkit.ini4Oracle%"); 
 
            Statement stmt = C.createStatement();
            
            Statement stmt2 = C.createStatement();
            Statement stmt3 = C.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT accno from acccust where cust_id="+fabel+""); 
 
 
            while (rset.next()) { 
                int accno=rset.getInt(1);
                          
                ResultSet rset2 = stmt2.executeQuery("SELECT * from accountreport where accno="+accno+"");
                ResultSetMetaData md = rset2.getMetaData(); 
            int columns = md.getColumnCount(); 
                while (rset2.next()) { 
                Vector row = new Vector(columns); 
 
                for (int i = 1; i <= columns; i++) { 
                    row.addElement(rset2.getObject(i)); 
                } 
 
                data.addElement(row); 
 
            } 
  
        } 
        }catch (Exception e) { 
            java.lang.System.out.println(e.getMessage()); 
            java.lang.System.out.println(e.getStackTrace()); 
        }
 
        //System.out.println("lignes :  "+data); 
        return data; 
} 
 
 
   public  Vector columns() 
    {   Connection c ; 
        Vector cols = new Vector (); 
        String sql2=null;
        String sql3=null;
        String sql4=null;
        try {  
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
                    "system", "Pulkit.ini4Oracle%"); 
 
            sql2 = "select * from accountreport"; 
            Statement stmt = c.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql2); 
            ResultSetMetaData md = rs.getMetaData(); 
            int columns = md.getColumnCount(); 
 
              //récupération des noms des colonnes dans la table site 
 
            for (int i = 1; i <= columns; i++) { 
            cols.addElement(md.getColumnName(i).toUpperCase()); 
            } 
           } catch (Exception e) { 
            java.lang.System.out.println(e.getMessage()); 
            java.lang.System.out.println(e.getStackTrace()); 
        } 
        //System.out.println("colonnes ::: "+cols); 
        return cols; 
    } 
 
 
public CustReports(Boolean s,int ids) { 
    super(); 
    this.fabel=ids;
            setVisible(true); 
    getContentPane().setLayout(null); 
    setTitle("Accounts Report"); 
    setBounds(100, 100, 500, 363); 
    setResizable(false); 
 
    final JLabel laTablesiteLabel = new JLabel(); 
    laTablesiteLabel.setText(""); 
    laTablesiteLabel.setBounds(10, 34, 274, 16); 
    getContentPane().add(laTablesiteLabel); 
 
    final JLabel label = new JLabel(); 
    //label.setIcon(SwingResourceManager.getIcon(ManReports.class, "/pictures/130.png")); 
    label.setBounds(402, 18, 49, 48); 
    getContentPane().add(label); 
 
    final JButton okButton = new JButton(); 
    okButton.addActionListener(new ActionListener() { 
        public void actionPerformed(final ActionEvent arg0) { 
            dispose(); 
            setVisible(false); 
        } 
 
 
    }); 
    //okButton.setIcon(SwingResourceManager.getIcon(listing.class, "/pictures/33-32x32.png")); 
    okButton.setText("Ok"); 
    okButton.setBounds(10, 291, 148, 32); 
    getContentPane().add(okButton); 
 
    final JButton refeshButton_1 = new JButton(); 
//    refeshButton_1.setIcon(SwingResourceManager.getIcon(listing.class, "/pictures/48-32x32.png")); 
    refeshButton_1.setText("Refresh"); 
    refeshButton_1.setBounds(171, 291, 148, 32); 
    getContentPane().add(refeshButton_1); 
 
    final JScrollPane scrollPane = new JScrollPane(); 
    scrollPane.setBounds(10, 85, 474, 187); 
    getContentPane().add(scrollPane); 
 
    table = new JTable(this.rows(), this.columns()); // chargement de JTable  
    scrollPane.setViewportView(table); 
 
    final JButton printButton_1_1 = new JButton(); 
    printButton_1_1.addActionListener(new ActionListener() { 
        public void actionPerformed(final ActionEvent arg0) { 
            try { 
                table.print(); 
            } catch (PrinterException e) { 
 
                e.printStackTrace(); 
            } 
        } 
    }); 
  //  printButton_1_1.setIcon(SwingResourceManager.getIcon(listing.class, "/pictures/Printer_h.png")); 
    printButton_1_1.setText("Print Report"); 
    printButton_1_1.setBounds(336, 291, 148, 32); 
    getContentPane().add(printButton_1_1); 
 
 
 
 
    } 
 
}