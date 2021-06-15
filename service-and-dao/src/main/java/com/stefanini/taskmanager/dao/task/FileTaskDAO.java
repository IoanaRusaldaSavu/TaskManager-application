package com.stefanini.taskmanager.dao.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dto.Task;

public class FileTaskDAO implements TaskDAO{
	private HashMap<String, Task> tasks;
	private String file = "C:\\Users\\ISAVU1\\Desktop\\Tasks\\TaskManager\\usersTasks.txt";

  @Override
  /*	public Task addTask(Task task, String userName) {
  	this.tasks.put(userName, task);
  	try{
  		FileOutputStream file = new FileOutputStream(this.file);
  		ObjectOutputStream out = new ObjectOutputStream(file);
  		out.writeObject(tasks.get(userName));
  		out.close();
  		file.close();
  	}catch(IOException ex) {
  		System.out.println("IOException is caught");
  		}
  	return task;
  }*/
  public ArrayList<Task> getTasks() {
		 ArrayList<Task> tasks = new ArrayList<Task>();
		 try {
			 FileInputStream file = new FileInputStream (this.file);
			 ObjectInputStream in = new ObjectInputStream (file); 
			 Object oa = in.readObject();
			 while(oa != null) {
				 Task task = (Task) oa;
		   		// System.out.println(task);
				 tasks.add(task);
		         oa = in.readObject();
		     }
			 in.close(); 
			 file.close(); 
		} catch (IOException ex) { 
			System.out.println("IOException is caught"); 
		}catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException" + " is caught"); }
		 return tasks;
	  }

  @Override
  public Task createTask(Task task) {
    // TODO Auto-generated method stub
    return null;
  }

  /* @Override
  public boolean isUniqueTask(String username) {
    // TODO Auto-generated method stub
    return false;
  }*/

  /*  @Override
    public int findTaskByTitle(String title) { // TODO Auto-generated method stub
      return 0;
    }
  */
  //  @Override
  /*  public void setConnection(Connection connection) { // TODO Auto-generated method stub
  }*/
}
