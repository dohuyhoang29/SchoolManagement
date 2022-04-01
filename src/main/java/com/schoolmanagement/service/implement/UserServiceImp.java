package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.request.ResetPasswordRequest;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import com.schoolmanagement.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepositories repo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User getUserByUsername(String username) {
		return repo.findUserByUsername(username);
	}

	@Override
	public User getUserByEmail(String email) {
		return repo.findAllByEmail(email);
	}

	@Override
	public User findByUserId(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void resetPassword(ResetPasswordRequest resetPasswordRequest, Integer id) {
		Optional<User> user = repo.findById(id);

		if (user.isPresent()) {
			modelMapper.map(resetPasswordRequest, user.get());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.get().setPassword(encoder.encode(user.get().getPassword()));
			repo.save(user.get());
		} else {
			System.out.println("Could not found user with id : " + id);
		}
	}
}
