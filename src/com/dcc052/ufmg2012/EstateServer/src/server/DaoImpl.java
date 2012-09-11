package com.dcc052.ufmg2012.EstateServer.src.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the client side of a DAO. This DAO is general enough
 * to deal with any object that is serializable. The client DAO is not in
 * charge of opening the communication channel. Instead, it receives the
 * in and out channels from an Invoker. In this way, the users of the DAO
 * do not have to worry about closing this channel.
 * @author Fernando
 *
 */
public class DaoImpl implements DAO<Long, Serializable> {

  /** The channel through which information can be sent to the server. */
  private ObjectOutputStream out;

  /** The channel through which information can be received from the server. */
  private ObjectInputStream in;

  /**
   * This method establishes the channels that the DAO will use to communicate
   * with the server side of the application.
   * @param newOut channel to send data.
   * @param newIn channel to receive data.
   */
  public final void setChannels(final ObjectOutputStream newOut,
      final ObjectInputStream newIn) {
    this.out = newOut;
    this.in = newIn;
  }

  /**
   * Returns a value, given a key.
   * @param key the id of the object that must be found.
   * @return V the value associated with the key K.
   */
  public final Serializable get(final Long key) {
    Serializable s = null;
    try {
      out.writeObject(new Integer(DAO.GET));
      out.writeObject(new Long(key));
      s = (Serializable) in.readObject();
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return s;
  }

  /**
   * Adds a new object into the database.
   * @param key the id of the object that will be inserted into the database.
   * @param value the value associated with the key K.
   */
  public final void add(final Long key, final Serializable value) {
    try {
      out.writeObject(new Integer(DAO.ADD));
      out.writeObject(new Long(key));
      out.writeObject(value);
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Removes an object from the database.
   * @param key the id of the object that will be removed.
   */
  public final void delete(final Long key) {
    try {
      out.writeObject(new Integer(DAO.DELETE));
      out.writeObject(new Long(key));
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates an object in the database.
   * @param key the id of the object that will be replaced.
   * @param value the new value of the object associated with the given key.
   */
  public final void update(final Long key, final Serializable value) {
    try {
      out.writeObject(new Integer(DAO.UPDATE));
      out.writeObject(new Long(key));
      out.writeObject(value);
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Informs the number of elements stored in the database.
   * @return an integer number that tells how many objects are currently
   * stored in the database.
   */
  public final int size() {
    Integer size = null;
    try {
      out.writeObject(new Integer(DAO.SIZE));
      size = (Integer) in.readObject();
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return size;
  }

}
