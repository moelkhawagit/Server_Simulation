 package compsim;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mohamedelkhawaga
 */
public class CompSim  {
    // Declare Queue
    static Queue<Integer> q = new LinkedList<>();
    
 static class GenrateTasks implements Runnable {
     
    // Generate random tasks of max  duration = sec in Milliseconds
     int getRandMillsec(int sec) {
        int x = (int) (Math.random() * 1000 * sec);
        return x;
    }
    
     int getexpMillsec(double lambda){  // lambda is the avergae time between tasks in sec
    double r1 = Math.random(); // URV from 0 to 1 millisec
    double x =  ((-1*(lambda*1000))*(Math.log(r1))) ;
    return  (int) x;
    }
    
    int taskMaxSec = 4;
    double expAvgsec = 2.0;
    
    
    @Override
    public void run() {
    while(true){
    q.add(getRandMillsec(taskMaxSec));
    
        try {
            Thread.sleep(getexpMillsec(expAvgsec));
        } catch (InterruptedException ex) {
            Logger.getLogger(GenrateTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println(q.toString());
    }
    
    }
}
 
 
 static class CompModel implements Runnable {
    
    int task = 0;
    
    public void setTask(int task) {
        this.task = task;
    }
    
    // new THREAD(CLASSNAME).start();
    @Override
    public void run() {
       while(true){
        
        if( !(q.isEmpty())) {
            System.out.print("IN");
            task = q.poll();
            try {
                Thread.sleep(task);
            } catch (InterruptedException ex) {
                Logger.getLogger(CompModel.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
        }
        else
            System.out.print("EMPTY");
            
       }
    }
  
    
    boolean compIsAvailble( CompModel c){
    return this.task == 0;
    }
}

    
    
    public static void main(String[] args) throws InterruptedException {
        
//        // Declare to computer objects
//         CompModel comp1 = new CompModel();
//         CompModel comp2 = new CompModel();
        
         Runnable runnable1 = new GenrateTasks();
         Runnable runnable2 = new CompModel();
         Runnable runnable3 = new CompModel();
         Thread thread1 = new Thread(runnable1);
         Thread thread2= new Thread(runnable2);
         Thread thread3 = new Thread(runnable3);
         thread1.start();
         thread2.start();
         thread3.start();
         //System.out.println(q.toString());
        
       
    }

}
