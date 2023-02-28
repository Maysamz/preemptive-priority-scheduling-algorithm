public class PCB {

 String PID;
 int CPuBurst;
 int CPUArrival;
 int CPUPriority;
 int StartTime;
 int EndTime;
 int TurnTime;
 int WaitTime;
 int ResponseTime;
 int RMT;
 
 
 public String getPID() {
 return this.PID;
 }
 
 public void setPID(int i) {
 this.PID = "P" +i;
 }
 public int getburst() {
 return this.CPuBurst;
 }
 public void setburst( int burst) {
 this.CPuBurst = burst;
 }
 public int getArrival_t() {
 return this.CPUArrival;
 } 
 public void setArrival_t( int arr) {
 this.CPUArrival = arr;
 } 
 
 public int getPriority() {
 return this.CPUPriority;
 } 
 public void setPriority( int Prio) {
 this.CPUPriority = Prio;
 } 
 
 public int getStartTime() {
 return this.StartTime;
 }
 
 public void setStartTime(int st) {
 this.StartTime = st;
 }
 
 public int getEndTime() {
 return this.EndTime;
 }
 
 public void setEndTime( int en) {
 this.EndTime = en;
 }
 
 public int getTurnTime() {
 return this.TurnTime;
 }
 
 public void setTurnTime( int tat) {
 this.TurnTime = tat;
 }
 
 public int getWaitTime() {
 return this.WaitTime;
 }
 
 public void setWaitTime( int wt) {
 this.WaitTime =wt;
 }
 
 public int getResponseTime() {
 return this.ResponseTime;
 }
 public void setResponseTime( int rt) {
 this.ResponseTime = rt;
 }
 
 public int getRMT() {
 return this.RMT;
 }
 
 public void setRMT( int rmt) {
 this.RMT = rmt;
 }
}