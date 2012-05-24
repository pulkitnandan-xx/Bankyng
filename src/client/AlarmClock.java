package client;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;
import org.tiling.scheduling.examples.iterators.DailyIterator;

public class AlarmClock {
    

        private Derby f ;
    public AlarmClock() {
        
        f=new Derby();
    }

    
   

    public static void main(String[] args) throws SQLException {
        //alarmClock.Intereststart();
        final AlarmClock g= new AlarmClock(); 
        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable(){ 
    @Override 
    public void run() 
    {  {
                    
        new System();
                } 
    }}, 1,TimeUnit.SECONDS);
        
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable(){ 
    @Override 
    public void run() 
    { 
                try {
                    
        g.update();
                } catch (SQLException ex) {
                    Logger.getLogger(AlarmClock.class.getName()).log(Level.SEVERE, null, ex);
                }
    }}, 5, 30, TimeUnit.DAYS);
        
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable(){ 
    @Override 
    public void run() 
    { 
                try {
                    
        g.calculateInterest();
                } catch (SQLException ex) {
                    Logger.getLogger(AlarmClock.class.getName()).log(Level.SEVERE, null, ex);
                }
    }},1, 10, TimeUnit.DAYS);
    }

    private void calculateInterest() throws SQLException {
        this.f.calculateInterest();
    }

    private void update() throws SQLException {
        this.f.update();
    }
    
   

}
