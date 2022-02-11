package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ilstu.lai.RegistrationDemo.entity.User;
import edu.ilstu.lai.RegistrationDemo.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl.UserException;


@Service
	public class UserServiceImp implements UserService {

	    @Autowired
	    private UserRepository userRepo;


		@Override
	    public void saveUser(User user) throws UserException {
	        if (emailExists(user.getEmail())) {
	            throw new UserException("There is an account with that email address: "
	              + user.getEmail(), null, null);
	        }

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String encodedPassword = passwordEncoder.encode(user.getPassword());
	user.setPassword(encodedPassword);

	        userRepo.save(user);
	    }

	    private boolean emailExists(String email) {
	        return userRepo.findByEmail(email) != null;
	    }
	}


