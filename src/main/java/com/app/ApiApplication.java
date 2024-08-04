package com.app;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.entities.Role;
import com.app.entities.RoleEnum;
import com.app.repository.RoleRepository;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {
	@Value("${file.profile.upload.location}")
	private String profilePictureFolderPath;
	
	@Autowired
	private RoleRepository roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper()
	{
		 ModelMapper modelMapper = new ModelMapper();
		 modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 return modelMapper;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... args) throws Exception {
		File dir = new File(profilePictureFolderPath);
		if (!dir.exists())
			dir.mkdirs();
		Role role1 = new Role();
		role1.setRoleName(RoleEnum.ROLE_ADMIN);
		Role role2 = new Role();
		role2.setRoleName(RoleEnum.ROLE_USER);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		for(Role role : roles) {
			Optional<Role> existingUser = roleRepo.findByRoleName(role.getRoleName());

            if (existingUser.isEmpty()) {
                // If the user doesn't exist, insert them
                roleRepo.save(role);
            }
		}
	}
	

}
