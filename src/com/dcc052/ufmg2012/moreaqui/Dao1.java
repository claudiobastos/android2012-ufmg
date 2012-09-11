package com.dcc052.ufmg2012.moreaqui;

import java.io.*;
import java.net.Socket;
import java.net.Socket;

import com.dcc052.ufmg2012.EstateServer.*;
import com.dcc052.ufmg2012.EstateServer.src.server.DAO;

public class Dao1 implements DAO<Long, Estate> {
private ObjectOutputStream out;
private ObjectInputStream in;
public void setChannels
(ObjectOutputStream out, ObjectInputStream in) {
this.out = out;
this.in = in;
}

public Estate get(Long key) {
Estate e = null;
try {
out.writeObject(new Integer(DAO.GET));
out.writeObject(new Long(key));
e = (Estate) in.readObject();
out.flush();
} catch (Exception exc) {
exc.printStackTrace();
}
return e;
}


public void add(Long key, Estate value) {
try {
out.writeObject(new Integer(DAO.ADD));
out.writeObject(new Long(key));
out.writeObject(value);
out.flush();
} catch (Exception e) {
e.printStackTrace();
}
}


public void update(Long key, Estate value) {
try {
out.writeObject(new Integer(DAO.UPDATE));
out.writeObject(new Long(key));
out.writeObject(value);
out.flush();
} catch (Exception e) {
e.printStackTrace();
}
}


public void delete(Long key) {
try {
out.writeObject(new Integer(DAO.DELETE));
out.writeObject(new Long(key));
out.flush();
} catch (Exception e) {
e.printStackTrace();
}
}



}