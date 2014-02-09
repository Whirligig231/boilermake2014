import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author doolansr.
 *         Created Feb 9, 2014.
 */
public class WorldTimer implements Runnable {
	
	private WorldManager manage;
	
	public WorldTimer(WorldManager manage) {
		this.manage = manage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			manage.timer();
			try {
				Thread.sleep((long)(1000/WorldManager.PHYSICS_FPS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
