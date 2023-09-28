package com.project.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.blog.config.AppConstants;
import com.project.blog.entities.Role;
import com.project.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("12345"));

		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN);
			role.setName("ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL);
			role1.setName("NORMAL");

			List<Role> roles = List.of(role, role1);

			List<Role> result = roleRepo.saveAll(roles);

			result.forEach(r -> System.out.println(r.getName()));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
