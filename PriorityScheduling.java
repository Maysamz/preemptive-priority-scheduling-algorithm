import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Scanner;
public class PriorityScheduling {
   int Pnum; 
   int n;
   int st,FinishedPr,SelectedPr=0 ; 
   ArrayList<PCB> ReadyQueue = new ArrayList<>();



   public static void main(String[] args) throws IOException {
      PriorityScheduling programm = new PriorityScheduling();
      programm.Operations();
   }
   
   

   public void Operations() throws IOException {
      System.out.println("1. Enter processes information. ");
      System.out.println("2. Report detailed information about each process and different scheduling criteria. ");
      System.out.println("3. Exit the program ");
   
      System.out.print("Choose operation from menu: ");
      Scanner input = new Scanner(System.in); 
      n = input.nextInt(); 
   
   
      while (n > 3 || n < 1) {
         System.out.print("you should Choose from menu: "); 
         n = input.nextInt();
      }
      switch (n) {
         case 1:
            ReadyQueue.clear();
            st=0;FinishedPr=0;SelectedPr=0;
            Start();
            Operations();
            break;
         case 2:
            System.out.print(" \n ##### Scheduale order (chart) ##### \n"); 
            PrioScheduling();
            Write();
            Operations();
            break;
         case 3:
            System.out.print("Thank you ... Good bye!!");
            break;
         default:
            System.out.print("Invalid choice!! ");
      }
   }
 
 
 
   public void Start() {
      int local_P=0 , tempB;
      Scanner input = new Scanner(System.in); 
      System.out.print("Enter the number of processes: ");
      Pnum = input.nextInt();  
      for (int i = 1; i <= Pnum; i++) { 
         PCB PCBobject=new PCB(); 
      
         PCBobject.setPID(i);
      
         System.out.printf("Enter the Arrival time of process (%d) in milliseconds : ", i);
         PCBobject.setArrival_t( input.nextInt());
         System.out.printf("Enter the Priority (from 1 to 5) : ", i);
         local_P = input.nextInt();
         while (local_P > 5 || local_P < 1) {
         
            System.out.printf("Priority must between (from 1 to 5) : ", i);
            local_P = input.nextInt();
         
         }
         PCBobject.setPriority( local_P);
      
      
      
         System.out.printf("Enter the CPU burst time of process (%d) in milliseconds : ", i);
         tempB =input.nextInt();
         PCBobject.setburst( tempB); 
         PCBobject.setRMT(tempB);
      
         PCBobject.setStartTime(-1);
         PCBobject.setEndTime(-1);
         PCBobject.setTurnTime(0);
         PCBobject.setWaitTime(0); 
         PCBobject.setResponseTime(0); 
         ReadyQueue.add(PCBobject); 
      }
   }
 
 
 
 
   public void PrioScheduling() {
      int L_ARRIVAL_TIME , loc=0;
      String PID_LAST=""; 
      int first_p = 0;
   
      while(FinishedPr<ReadyQueue.size()){ 
         int min = 9; 
      
         SelectedPr=-1;
      
         for (int icounter = 0; icounter < ReadyQueue.size(); icounter++) {
            if ((ReadyQueue.get(icounter).getArrival_t() <= st )&& (ReadyQueue.get(icounter).getPriority()<min)&& (ReadyQueue.get(icounter).getRMT()>0)) 
            {
            
               min=ReadyQueue.get(icounter).getPriority();
               L_ARRIVAL_TIME=ReadyQueue.get(icounter).getArrival_t();
               SelectedPr = icounter;
            
            
            
               for (int icounter2 = 0; icounter2 < ReadyQueue.size(); icounter2++) {
                  if (min==ReadyQueue.get(icounter2).getPriority()&&ReadyQueue.get(icounter2).getArrival_t()<L_ARRIVAL_TIME&& (ReadyQueue.get(icounter2).getRMT()>0)){
                     SelectedPr=icounter2;
                  }
               
               
               }
            }
         }
         if (SelectedPr!=-1){ 
            if(ReadyQueue.get(SelectedPr).getStartTime() ==-1 ){
               st=max(st,ReadyQueue.get(SelectedPr).getArrival_t());
            
            
               if (first_p==0) {
                  ReadyQueue.get(SelectedPr).setStartTime(st) ; 
                  first_p++;
               }
               else if (first_p!=0) {
                  ReadyQueue.get(SelectedPr).setStartTime(st+1) ;
               
               }
            
            } 
         
         
         
            if ( loc!=0){
               if ( PID_LAST!=ReadyQueue.get(SelectedPr).getPID()) {
                  System.out.print(" | CS");
                  st++; 
                  PID_LAST=ReadyQueue.get(SelectedPr).getPID();
               
               }
            }
            else if ( loc==0){
               loc=2; 
               PID_LAST=ReadyQueue.get(SelectedPr).getPID(); 
            }
         
            ReadyQueue.get(SelectedPr).setRMT(ReadyQueue.get(SelectedPr).getRMT()-1); 
            st++; 
            System.out.print(" | "+ ReadyQueue.get(SelectedPr).getPID());
         
         
         
         
            if (ReadyQueue.get(SelectedPr).getRMT()==0 ){
               FinishedPr++;
               ReadyQueue.get(SelectedPr).setEndTime(st) ;
            
            }
         } 
         
         else if (SelectedPr==-1){
            //System.out.print(" | "); 
            st++; 
         }
      
      
      
      }
      System.out.print(" | "); 
      for (int pro = 0; pro < ReadyQueue.size(); pro++) {
         ReadyQueue.get(pro).setTurnTime(ReadyQueue.get(pro).getEndTime()-ReadyQueue.get(pro).getArrival_t());
         ReadyQueue.get(pro).setWaitTime(ReadyQueue.get(pro).getTurnTime()-ReadyQueue.get(pro).getburst());
         ReadyQueue.get(pro).setResponseTime(ReadyQueue.get(pro).getStartTime()-ReadyQueue.get(pro).getArrival_t());
      }
   }
   
   
   
   public void Write() throws IOException {
    
      System.out.println("\n **** Console Report **** ");
      System.out.printf( "PID " + " " + 
         "Burst" + " " +
         "Priority" + " " +
         "Arrival" + " " +
         "ST " + " " + 
         "EN " + " " + 
         "TurnTime " + " " +
         "WaitTime " + " " +
         "ResponseTime "+ "\n"); 
   
      ReadyQueue.forEach(
         (n) -> {
            System.out.printf("%s" + " "+ "%5d" + " "+ "%6d" + " "+ "%7d" + " "+ "%6d" + " "+ "%4d" + " "+ "%6d" + " "+ "%7d" + " "+ "%8d" + " " + "\n",
               n.getPID(),
               n.getburst(),
               n.getPriority(),
               n.getArrival_t(),
               n.getStartTime(),
               n.getEndTime(),
               n.getTurnTime(),
               n.getWaitTime(),
               n.getResponseTime()
               );
         }); 
   
   
   
      float avgWaitTime=0,avgTurnTime=0, avgResponseTime=0;
      float Twt=0,Ttat=0,Trt=0;
   
      BufferedWriter outputWriter;
      outputWriter = new BufferedWriter(new FileWriter("Report.txt"));
   
      outputWriter.write(
         "PID " + " " + 
         "burst" + " " +
         "Prio" + " " +
         "Atime" + " " +
         "ST " + " " + 
         "EN " + " " + 
         "TurnTime " + " " +
         "WaitTime " + " " +
         "ResponseTime "
         );
   
      for (PCB n:ReadyQueue) { 
      
         Twt+=n.getWaitTime();
         Ttat+=n.getTurnTime();
         Trt+=n.getResponseTime();
         outputWriter.newLine();
         
         outputWriter.write(
            n.getPID() + " " +
            n.getburst()+ " " +
            n.getPriority()+ " " +
            n.getArrival_t()+ " " +
            n.getStartTime() + " " +
            n.getEndTime() + " " + 
            n.getTurnTime() + " "+
            n.getWaitTime() + " "+
            n.getResponseTime() 
            );
         outputWriter.newLine();
      
      }
   
   
      avgWaitTime=Twt/ReadyQueue.size();
      avgTurnTime=Ttat/ReadyQueue.size();
      avgResponseTime=Trt/ReadyQueue.size();
      
      outputWriter.newLine(); 
      outputWriter.write("**** average WaitTime: " +avgWaitTime );
      outputWriter.write("**** average TurnTime: " +avgTurnTime );
      outputWriter.write("**** average ResponseTime: " +avgResponseTime );
      outputWriter.flush();
      outputWriter.close();
   
      System.out.printf("\n**** average WaitTime: %.2f" ,avgWaitTime );
      
      System.out.printf("\n**** average TurnTime: %.2f " , avgTurnTime );
      
      System.out.printf("\n**** average ResponseTime: %.2f " , avgResponseTime);
      System.out.println("");
   
   }
   
   
}