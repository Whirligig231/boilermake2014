import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryo.Kryo;
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
public class NetServer {

	private static Server server = null;
	private static Connection connection = null;
	private static int port = 54555;
	private static WaitingFrame wait;
	private static WorldManager world;
	public static void create() {
		System.out.println("CREATE");
		server = new Server();
		server.start();
		try {
			server.bind(port);
		} catch (IOException e) {
			server = null;
			JOptionPane.showMessageDialog(null,"Unknown error occurred while creating the socket.");
			return;
		}
		Kryo kryo = server.getKryo();
	    kryo.register(MessageCmd.class);
	    kryo.register(MessageLevel.class);
	    kryo.register(MessageScore.class);
	    wait = new WaitingFrame();
	    server.addListener(new Listener() {
	    	@Override
			public void received(Connection connection, Object object) {
	    		NetServer.connection = connection;
	    		System.out.println(object.getClass());
	    		if (object == MessageCmd.MESSAGE_CONNECT)
	    			wait.enable();
	    		else if (object instanceof MessageScore) {
	    			NetServer.world.setOtherPoints(((MessageScore)object).score);
	    		}
	    	}
	    });
	}
	
	public static boolean isNet() {
		return server != null;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param f
	 */
	public static void sendLevel(String f) {
		connection.sendTCP(new MessageLevel(f));
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param points
	 */
	public static void pushScore(long points) {
		connection.sendTCP(new MessageScore((int) points));
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
