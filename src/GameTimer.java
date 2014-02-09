/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author doolansr.
 *         Created Feb 9, 2014.
 */
public class GameTimer implements Runnable {
	
	private long finalTime;
	private Timed caller;

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (System.currentTimeMillis() < this.finalTime);
		this.stop();
	}

	private void stop() {
		this.caller.timeUp();
	}

	public void start(long ms, Timed caller) {
		this.caller = caller;
		this.finalTime = System.currentTimeMillis() + ms;
		Thread runThread = new Thread(this);
		runThread.start();
	}
	
	public long getRemaining() {
		return (this.finalTime-System.currentTimeMillis());
	}

}
