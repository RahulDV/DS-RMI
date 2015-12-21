
public class Server1TimerTask extends AbstractServerTimerTask{
	
	public Server1TimerTask(){
		super.feServer="FE Server1";
	}
	
	public long berkeleyAlgorithm(long timeInNanoSecs){
		long correction = 0;
		long currServer1Time = System.nanoTime();
		long avg = (long)(timeInNanoSecs+currServer1Time)/2;
		super.correction=avg-currServer1Time;
		correction=avg-timeInNanoSecs;
		System.out.println("Correction in system time calculated is: "+super.correction);
		return correction;
	}

}
