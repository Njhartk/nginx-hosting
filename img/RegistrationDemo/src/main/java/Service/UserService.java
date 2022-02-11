package Service;



import edu.ilstu.lai.RegistrationDemo.entity.User;

import jdk.jshell.spi.ExecutionControl.UserException;

public interface UserService {
		  public void saveUser (User user) throws UserException;
		 // public boolean isUserAlreadyPresent(User user);
		  
		}

