package com.project.blog.DTO;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDTO {

	private Integer id;

	@NotEmpty
	@Size(min=4, message="UserName must be of minimum 4 characters")
	private String name;

	@Email(message = "EMail address is invalid")
	private String email;


	@NotEmpty()
	@Size(min=3, max=10, message = "size should be within 3-10")
//	@Pattern(regexp = "")
	private String password;

	@NotEmpty
	@Size(min=10,message = "Length should be minumum 10 character")
	private String about;

	
	private Set<RoleDTO> roles = new HashSet<>();
}
