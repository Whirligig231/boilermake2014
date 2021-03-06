import java.io.IOException;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author Steven.
 *         Created Feb 9, 2014.
 */
public class NetClient {
	
	private static String ip;
	private static int port = 54555;
	private static Client client = null;
	private static WaitingFrame wait;
	private static WorldManager world;

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param hostIp
	 */
	public static void create(String hostIp) {
		NetClient.ip = hostIp;
		client = new Client();
	    client.start();
	    try {
			client.connect(5000,hostIp,54555);
		} catch (IOException e) {
			client = null;
			JOptionPane.showMessageDialog(null,"IP does not lead anywhere useful!");
			return;
		}
	    Kryo kryo = client.getKryo();
	    kryo.register(MessageCmd.class);
	    kryo.register(MessageLevel.class);
	    kryo.register(MessageScore.class);
	    wait = new WaitingFrame();
	    client.addListener(new Listener() {
	    	@Override
			public void received(Connection connection, Object object) {
	    		if (object instanceof MessageLevel)
	    			wait.play((MessageLevel)object);
	    		else if (object instanceof MessageScore) {
	    			NetClient.world.setOtherPoints(((MessageScore)object).score);
	    		}
	    	}
	    });
	    client.sendTCP(MessageCmd.MESSAGE_CONNECT);
	}
	
	public static boolean isNet() {
		return client != null;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param points
	 */
	public static void pushScore(long points) {
		client.sendTCP(new MessageScore((int) points));
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param worldManager
	 */
	public static void setWorld(WorldManager worldManager) {
		world = worldManager;
	}

}
