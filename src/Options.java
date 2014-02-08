/**
 * 
 */

/**
 *Put here a description of what this class does.
 *
 * @author doolansr.
 *         Created Feb 8, 2014.
 */
public class Options {
	private String difficult;
	private int bright;
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public Options(){
		this.difficult = "easy";
		this.bright = 5;
		
	}
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param diff
	 * @param brgt
	 */
	public void setOptions(int diff, String brgt){
		
		this.difficult = brgt;
		
		this.bright = diff;
		
	}
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public Object[] getOptions(){
		Object[] ops = new Object[2];
		ops[0] = this.bright;
		ops[1] = this.difficult;
		return ops;
	}
}
