package com.dcc052.ufmg2012.EstateServer.src.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Map;

/**
 * This class instantiates threads in charge of answering requests made via
 * socket channels. It is the server side of the implementation of a DAO.
 * @author Fernando
 *
 */
public class ConnectionHandlerThread extends Thread implements
    DAO<Long, Serializable> {

  /** The database where information is stored. */
  private Map<Long, Serializable> database;

  /** The socket used to establish communication. */
  private Socket socket = null;

  /** The channel used to send information to the client. */
  private ObjectOutputStream out;

  /** The channel used to receive information from the client. */
  private ObjectInputStream in;

  /**
   * Creates a new handler.
   *
   * @param newSocket
   *        the network connection where this handler will be listening.
   * @param newDatabase
   *        the database of students.
   */
  public ConnectionHandlerThread(final Socket newSocket,
      final Map<Long, Serializable> newDatabase) {
    this.socket = newSocket;
    this.database = newDatabase;
    System.out.println("Create new Thread");
    try {
      out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Runs the thread that is in charge of answering client requests.
   */
  public final void run() {
    try {
      Integer request = (Integer) in.readObject();
      boolean isWorking = true;
      while (isWorking) {
        switch (request.intValue()) {
        case DAO.GET:
          out.writeObject(get((Long) in.readObject()));
          break;
        case DAO.SIZE:
          out.writeObject(new Integer(size()));
          break;
        case DAO.DELETE:
          delete((Long) in.readObject());
          break;
        case DAO.UPDATE:
          update((Long) in.readObject(), (Serializable) in.readObject());
          break;
        case DAO.ADD:
          add((Long) in.readObject(), (Serializable) in.readObject());
          break;
        default:
          isWorking = false;
          return;
        }
        request = (Integer) in.readObject();
      }
      out.close();
      in.close();
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds a new Serializable object into the database.
   * @param key
   *        the key of the new object.
   * @param value
   *        the value that is going to be inserted into the database.
   */
  public final void add(final Long key, final Serializable value) {
    System.out.println("New object received: " + value
        + ", Current size of the database: " + database.size());
    this.database.put(key, value);
  }

  /**
   * Removes an object from the database.
   * @param key
   *        the id of the object that will be removed.
   */
  public final void delete(final Long key) {
    this.database.remove(key);
  }

  /**
   * Returns a reference to an object in the database.
   * @param key
   *        the id of the object that is being looked up.
   * @return a serializable element. The return value is marked serializable
   *         because it will be sent through a network connection.
   */
  public final Serializable get(final Long key) {
    return database.containsKey(key) ? database.get(key) : null;
  }

  /**
   * Updates an object in the database.
   * @param key
   *        the id of the object that will be replaced.
   * @param value
   *        the value of the new object.
   */
  public final void update(final Long key, final Serializable value) {
    add(key, value);
  }

  /**
   * Informs the number of elements currently stored in the database.
   * @return an integer number.
   */
  public final int size() {
    return database.size();
  }
}
