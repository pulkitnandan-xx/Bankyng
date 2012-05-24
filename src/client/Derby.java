 package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.tiling.scheduling.examples.iterators.DailyIterator;


public class Derby {
        
    public Double srate=0.0,frate=0.0,crate=0.0,trate1=0.0,trate2=0.0;
    
    public Double stime=1.0,ftime=1.0,ctime=1.0,ttime1=0.0,ttime2=0.0;
      private static String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
      private static String driver="oracle.jdbc.driver.OracleDriver";
      private static String user="system";
      private static String pass="system_pass";
      private  Connection conn = null;
      
      public  Statement stmt = null,stmt2=null,stmt3=null,stmt4=null,stmt5 = null,stmt6=null,stmt7=null,stmt8=null,stmt9=null,stmt10=null,stmt11=null,stmt12=null;
      private  Statement s = null;
      private  PreparedStatement psInsert=null;
      public  ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null;
    public int id=0;
    public String start;
    public String username="username";
    private String password;
    private String name;
    public Boolean status;
    private ArrayList qwe;
    public int num=0;
    public Object dataswa[][]=new Object[5][3];
    private String tableo;
    private String colomneo;
    Derby() {
        this.setData();
    }
    
    Derby(int idoss,Double dobs,Double sobs) throws SQLException {
        this.connectionDb();
        this.num=stmt.executeUpdate("update constants set value="+dobs+" , constants.time="+sobs+" where c_id="+idoss+"");
        
    }
     
              public void calculateInterest() throws SQLException
    {
        num++;
        connectionDb();
        rs=stmt.executeQuery("select * from account");
        while(rs.next())
        {
            Double interest=0.0;
            Double rate=0.0;
            Double tax1=0.0;
            Double tax2=0.0;
            Double time;
            int ac_no=rs.getInt("acc_no");
            String ac_type=rs.getString("type");
            Double ac_balance=rs.getDouble("balance");
            if("s".equals(ac_type))
            {
                rate=srate;
                time=stime;
                interest = ac_balance* (rate/ (100*time));
            }
            else if("f".equals(ac_type))
            {
                rate=frate;
                time=stime;
                interest = ac_balance* (rate/ (100*time));
            }
            else 
            {
                rate=crate;
                time=ctime;
                interest = ac_balance* (rate/ (100*time));
            }
                tax1 = interest* (this.trate1/ (100*this.ttime1));
                tax2= interest* (this.trate2/ (100*this.ttime2));
               String dateaaj=new Date().toString(); 
            DecimalFormat form = new DecimalFormat(".00");
            stmt2.executeUpdate("insert into acc_"+ac_no+" values ('"+dateaaj+"', "+form.format(interest)+","+form.format(tax1)+","+form.format(tax2)+")");
            num++;
            
        }
        
    }
        
              public void update() throws SQLException
    {
        num++;
        int ar_id=100;
        connectionDb();
        rs2=stmt3.executeQuery("select * from account");
        while(rs2.next())
        {
            Double interest=0.0;
            Double rate=0.0;
            Double tax1=0.0;
            Double tax2=0.0;
            Double comuInte=0.0;
            Double time;
            int ac_no=rs2.getInt("acc_no");
            String ac_type=rs2.getString("type");
            Double ac_balance=rs2.getDouble("balance");
            rs3=stmt5.executeQuery("select sum(interest) from acc_"+ac_no+"");
            rs3.next();
            comuInte=rs3.getDouble(1);
            
            rs4=stmt6.executeQuery("select sum(tax) from acc_"+ac_no+"");
            rs4.next();
            tax1=rs4.getDouble(1);
            
            
            rs5=stmt7.executeQuery("select sum(tax2) from acc_"+ac_no+"");
            rs5.next();
            tax2=rs5.getDouble(1);
            
            comuInte=comuInte-tax1-tax2;
            ac_balance=ac_balance+comuInte;
            
            DecimalFormat form = new DecimalFormat(".00");
            stmt8.executeUpdate("update account set balance="+form.format(ac_balance)+" where acc_no="+ac_no+"");
 
            String dateaaj=new Date().toString();
            ar_id=ar_id+1;
            
            stmt10.executeUpdate("insert into accountreport values("+ar_id+","+ac_no+",'"+dateaaj+"',"+form.format(comuInte)+","+form.format(ac_balance)+")");
            stmt11.executeUpdate("drop table acc_"+ac_no+"");
            
            stmt12.executeUpdate("create table acc_"+ac_no+" (dated varchar(100),interest decimal(19,6),tax decimal(19,6),tax2 decimal(19,6))");
                num++;
            
        }
        
       // for(int )
    }
     
    public void setData()
    {
        connectionDb();
         try {
            
            int i=0;
            rs4=stmt4.executeQuery("select * from constants");
           rs4.next();
               
               srate=rs4.getDouble("value");
               stime=rs4.getDouble("time");
           rs4.next();
               
               frate=rs4.getDouble("value");
               ftime=rs4.getDouble("time");    
               
            rs4.next();
               
               crate=rs4.getDouble("value");
               ctime=rs4.getDouble("time");
                
               rs4.next();
               trate1=rs4.getDouble("value");
               ttime1=rs4.getDouble("time");
               rs4.next();
                       trate2=rs4.getDouble("value");
               ttime2=rs4.getDouble("time");
               
        
        } catch (SQLException ex) {
            Logger.getLogger(Derby.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public Derby(String tablename)
     {
         connectionDb();
         try {
            
            int i=0;
            rs4=stmt4.executeQuery("select * from "+tablename+"");
           while(rs4.next())
           {
               
               dataswa[i][0]=rs4.getObject("c_type");
               dataswa[i][1]=rs4.getObject("value");
               dataswa[i][2]=rs4.getObject("time");
               i++;
               
           }   
                
        
        } catch (SQLException ex) {
            Logger.getLogger(Derby.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     

     public Derby(String string, char[] cs,String table,String coloumn) {
         this.tableo=table;
         this.colomneo=coloumn;
          Calendar currentDate = Calendar.getInstance();
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
          this.start = formatter.format(currentDate.getTime());
          this.username = string;
          this.password = new String(cs);
          try
          {
              this.connectionDb();
              ResultSet results = stmt.executeQuery("select "+colomneo+" from "+tableo+" where username='"+this.username+"' and password='"+this.password+"'");
              if(results.next())
              this.id=results.getInt(colomneo);
              this.shutdown();
              }
          catch (SQLException sqlExcept)
          {
              sqlExcept.printStackTrace();
          }

      }
      public void connectionDb()
              {
                      try
                      {
                              Class.forName(driver);
                              conn=DriverManager.getConnection(dbURL,user,pass);
                              stmt=conn.createStatement();
                              stmt2=conn.createStatement();
                              stmt3=conn.createStatement();
                              stmt4=conn.createStatement();
                              stmt5=conn.createStatement();
                              stmt6=conn.createStatement();
                              stmt7=conn.createStatement();
                              stmt8=conn.createStatement();
                              
                              stmt9=conn.createStatement();
                              stmt10=conn.createStatement();
                              stmt11=conn.createStatement();
                              stmt12=conn.createStatement();
                      }
                      catch(SQLException | ClassNotFoundException sqle)
                      {
                      }
              }
      public  void shutdown()
      {
          try
          {
              if (this.stmt != null)
              {
                  this.stmt.close();
              }
              if (this.rs != null)
              {
                  this.rs.close();
              }
              if (this.conn != null)
              {
                  this.conn.close();
              }           
          }
          catch (SQLException sqlExcept)
          {
              
          }

      }
      public int authorise() {


          return this.id;
      }





     public Boolean checkDB() throws ClassNotFoundException {
              try
              {
                  connectionDb();
                  rs=stmt.executeQuery("select * from banker_details");
                  if(rs!=null)
                      return false;
                  else {
                      
                      return true;
                  }
                  }
              catch (SQLException sqle)
              {
                  return true;
              }
              
          }

      public String[] getStatus(String tablename) {
                  String[] s=null;
                  
          try{
              this.connectionDb();
              ResultSet results = this.stmt.executeQuery("select *  from systemstatus");
              ResultSetMetaData rsmd = results.getMetaData();
              int numberCols = rsmd.getColumnCount();
                  for (int i=1; i<=numberCols; i++)
                  {
                      //print Column Names
                      s[i]=rsmd.getColumnLabel(i)+"\t";
                  }
          }
                  catch (SQLException sqlExcept)
                  {
                      
                  }
          
              return s;
              }

   
}