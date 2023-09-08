package com.CRUDOPERATION.CRUDOPERATION.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CRUDOPERATION.CRUDOPERATION.Controller.Respository.UserRepository;
import com.CRUDOPERATION.CRUDOPERATION.Model.User;
@RestController
@RequestMapping("/api")
public class UserContoller {
	//update variable
	@Autowired
    private UserRepository userRepository;
	@Autowired
	UserRepository userrepository;	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		 return userrepository.save(user);	  
	}
	@GetMapping("/Allregisteruser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> userlist=new ArrayList<>();
		userrepository.findAll().forEach(userlist::add);
		return new ResponseEntity<List<User>>(userlist,HttpStatus.OK);
	}
	 // Use @PutMapping for updates
	@PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        // Retrieve the existing user by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // Update the user's information with the new data
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        // Save the updated user to the database
        User updated = userRepository.save(existingUser);
        return ResponseEntity.ok(updated);
    }
	//deleting the user from database
	// Use @DeleteMapping for delete
	@DeleteMapping("/deleteUser/{userId}") 
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
	
}
