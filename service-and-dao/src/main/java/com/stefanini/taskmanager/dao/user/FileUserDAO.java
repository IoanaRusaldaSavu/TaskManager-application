package com.stefanini.taskmanager.dao.user;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dto.User;

public class FileUserDAO implements UserDAO {
  private ArrayList<User> users;
  private String file = "C:\\Users\\ISAVU1\\Desktop\\Tasks\\TaskManager\\users.txt";

  public FileUserDAO() {
    this.users = new ArrayList<User>();
  }

  public boolean isUnigueUsername(String username) {
    for (User u : this.users) {
      if (u.getUserName().equals(username)) return false;
    }
    return true;
  }

  public User createUser(User user) {
    if (isUnigueUsername(user.getUserName())) {

      try {
        FileOutputStream file = new FileOutputStream(this.file);
        // TODO Proprietati ca sa nu mai suprascriu
        ObjectOutputStream out = new ObjectOutputStream(file);
        for (User u : this.users) {
          out.writeObject(u);
        }
        out.close();
        file.close();
        this.users.add(user);
      } catch (IOException ex) {

        System.out.println("IOException is caught");
      }

    } else {

      System.out.println("UserName " + user.getUserName() + " already used");
    }
    return user;
  }

  public ArrayList<User> getUsers() {
    ArrayList<User> users = new ArrayList<User>();
    try {
      FileInputStream file = new FileInputStream(this.file);
      ObjectInputStream in = new ObjectInputStream(file);
      Object oa = in.readObject();
      while (oa != null) {
        User user = (User) oa;
        //  System.out.println(user);//TODO:in taskmanager
        users.add(user);
        oa = in.readObject();
      }
      in.close();
      file.close();
    } catch (EOFException ex) {
      return users;
    } catch (IOException ex) {
      ex.printStackTrace();
      System.out.println("IOException is caught");
    } catch (ClassNotFoundException ex) {
      System.out.println("ClassNotFoundException" + " is caught");
    }
    return users;
  }

  /*  @Override
    public int findUserByUserName(String userName) { // TODO Auto-generated method stub
      return 0;
    }



    @Override
    public void setConnection(Connection connection) { // TODO Auto-generated method stub
    }
  */
  @Override
  public boolean addTask(String userName, String title) { // TODO Auto-generated method stub
    return false;
  }


}
