package com.dcc052.ufmg2012.EstateServer.src.server;
/**
 * The Data Object Interface (DAO) works as a layer between the application
 * logic and the database.
 * @author Fernando
 *
 * @param <K> The type of the key of the elements stored in the database.
 * @param <V> The values stored in the database.
 */
public interface DAO<K, V> {

  /** The constant that denotes the operation of getting an element from the
     database. */
  int GET = 1;

  /** Constant for the operation of adding a new element in the database. */
  int ADD = 2;

  /** Constant for the operation of deleting a new operation from the
     database. */
  int DELETE = 3;

  /** Constant for the operation of putting a new element in the database. */
  int UPDATE = 4;

  /** Constant for the operation of asking for the size of the database. */
  int SIZE = 5;

  /** The port in which the server will be waiting for connections. */
  int PORT = 4444;

  /** The address of the host that we must use, whenever we are testing the
     client and server applications on the same machine. */
  String HOST = "10.0.2.2";

  /**
   * Returns a value, given a key.
   * @param key the id of the object that must be found.
   * @return V the value associated with the key K.
   */
  V get(K key);

  /**
   * Adds a new object into the database.
   * @param key the id of the object that will be inserted into the database.
   * @param value the value associated with the key K.
   */
  void add(K key, V value);

  /**
   * Removes an object from the database.
   * @param key the id of the object that will be removed.
   */
  void delete(K key);

  /**
   * Updates an object in the database.
   * @param key the id of the object that will be replaced.
   * @param value the new value of the object associated with the given key.
   */
  void update(K key, V value);

  /**
   * Informs the number of elements stored in the database.
   * @return an integer number that tells how many objects are currently
   * stored in the database.
   */
  //int size();
}
