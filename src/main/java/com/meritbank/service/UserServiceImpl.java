package com.meritbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.model.User;
import com.meritbank.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(int id) {
		return userRepository.getOne(id);
	}
	
	@Override
	public User getUserByUserName(String username) {
		return userRepository.findByUserName(username).orElse(null);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		User oldUser = userRepository.getOne(user.getId());
		oldUser = user;
		return userRepository.save(oldUser);
	}

	@Override
	public User deleteUser(User user) {
		 userRepository.delete(user);
		 return user;
	}
	
	
}
