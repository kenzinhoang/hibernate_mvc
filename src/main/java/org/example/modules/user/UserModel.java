package org.example.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserModel {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) throws SQLException {
        return userRepository.save(user);
    }

    public List<User> findAll() throws SQLException {
        return userRepository.findAll();
    }
    
}
