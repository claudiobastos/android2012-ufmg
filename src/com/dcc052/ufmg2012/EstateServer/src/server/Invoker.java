package com.dcc052.ufmg2012.EstateServer.src.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class implemented the invoker of the Command design pattern. An
 * invoker is an object that receives a command, establishes a connection with
 * a server through a DAO, and executed the actions of the command on the
 * server.
 * @author Fernando
 *
 */
public class Invoker {

  /** The constant that indicates to the server that communication is over. */
  private static final int EOF = -1;

  /** The host where the server is waiting for connections. */
  private String host;

  /** The port in which the server is waiting for connections. */
  private int port;

  /** The socket to establish the connection with the server. */
  private Socket socket = null;

  /** The channel to send information to the server. */
  private ObjectOutputStream out = null;

  /** The channel to receive information from the server. */
  private ObjectInputStream in = null;

  /**
   * Constructor method.
   * @param newHost the host where a connection must be established.
   * @param newPort the port in the server host where connection must be made.
   */
  public Invoker(final String newHost, final int newPort) {
    this.host = newHost;
    this.port = newPort;
  }

  /**
   * Creates a new connection with the remote server.
   */
  private void openConnection() {
    // Open a new connection:
    try {
      socket = new Socket(host, port);
      out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Closes the connection with the remote server.
   */
  private void closeConnection() {
    try {
      out.writeObject(new Integer(EOF));
      out.flush();
      socket.close();
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method invokes a command over a DAO.
   * @param d the DAO that will interface communication with the server.
   * @param c the command that contains the actions that will be executed.
   */
  public final void invoke(final DaoImpl d, final Command c) {
    openConnection();
    d.setChannels(out, in);
    c.execute(d);
    closeConnection();
  }
}
