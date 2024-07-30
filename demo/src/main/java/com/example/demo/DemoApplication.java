package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.ProjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.demo.service.TimeSheetService;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

//		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//        UserEntity user = new UserEntity();
//		user.setLogin("u2");
//		user.setPassword("$2a$12$obTrd3NExQDJYPYsSTnmHe/MhBCdNIcG56Loea42zERHMHxyqxjUa");
//		UserEntity admin = new UserEntity();
//		admin.setLogin("a2");
//		admin.setPassword("$2a$12$obTrd3NExQDJYPYsSTnmHe/MhBCdNIcG56Loea42zERHMHxyqxjUa");
//		userRepository.save(user);
//		userRepository.save(admin);

//		UserRoleRepository userRoleRepository = applicationContext.getBean(UserRoleRepository.class);
//		UserRole userRole = new UserRole();
//		userRole.setRoleId(1L);
//		userRole.setUserId(5L);
//		UserRole userRole2 = new UserRole();
//		userRole2.setRoleId(2L);
//		userRole2.setUserId(6L);
//		UserRole userRole3 = new UserRole();
//		userRole3.setRoleId(1L);
//		userRole3.setUserId(6L);
//		UserRole userRole4 = new UserRole();
//		userRole4.setRoleId(3L);
//		userRole4.setUserId(6L);
//		userRoleRepository.save(userRole);
//		userRoleRepository.save(userRole2);
//		userRoleRepository.save(userRole3);
//		userRoleRepository.save(userRole4);

//		RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
//		RoleName roleName = new RoleName();
//		roleName.setName(Role.USER.getName());
//		RoleName roleName2 = new RoleName();
//		roleName2.setName(Role.ADMIN.getName());
//		RoleName roleName3 = new RoleName();
//		roleName3.setName(Role.REST.getName());
//		roleRepository.save(roleName);
//		roleRepository.save(roleName2);
//		roleRepository.save(roleName3);

		TimeSheetService timeSheetService = applicationContext.getBean(TimeSheetService.class);
		LocalDate localDate = LocalDate.now();
		for (int i = 1; i < 15; i++) {
			Long id = (long) i;
			Long project = ThreadLocalRandom.current().nextLong(1, 10);
			Integer minutes = ThreadLocalRandom.current().nextInt(1, 1000);
			TimeSheet timeSheet = new TimeSheet(id, project, minutes, localDate);
			timeSheetService.addTimeSheet(timeSheet);
		}

		ProjectService projectService = applicationContext.getBean(ProjectService.class);
		for (int i = 1; i < 11; i++) {
			Long id = (long) i;
			Long name = ThreadLocalRandom.current().nextLong(1, 1000);
			Project project = new Project(id, "Project #" + name.toString());
			projectService.addProject(project);
		}
	}
}
