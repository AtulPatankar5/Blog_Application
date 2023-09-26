package com.project.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void ReposTest() {
		String name = userRepo.getClass().getName();
		Package package1 = userRepo.getClass().getPackage();

		System.out.println(name);
		System.out.println(package1);
	}

}
