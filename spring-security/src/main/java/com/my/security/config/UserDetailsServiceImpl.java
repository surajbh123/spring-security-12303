package com.my.security.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.security.model.Citizen;
import com.my.security.model.Employee;
import com.my.security.repository.CitizenRepository;
import com.my.security.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final EmployeeRepository employeeRepository;
	private final CitizenRepository citizenRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Citizen> citizen = this.citizenRepository.findByEmail(username);
		if (citizen.isPresent()) {
			       Citizen user = citizen.get();
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(), 
					user.getPassword(), 
					true, 
					true, 
					true, 
					true, 
					getGrantedAuthorities(user.getRole()));
		} else {
			Optional<Employee> employee = this.employeeRepository.findByEmail(username);
			if (employee.isPresent()) {
				Employee user = employee.get();
				System.out.println(user);
				return new org.springframework.security.core.userdetails.User(
						user.getEmail(), 
						user.getPassword(), 
						true, 
						true, 
						true, 
						true, 
						getGrantedAuthorities(user.getRole()));
			}
			else {
				throw new UsernameNotFoundException("No user " +
		                "Found with username : " + username);
			}
		}
	}

	public Collection<? extends GrantedAuthority> getGrantedAuthorities(String role) {
	    final String ROLE_PREFIX = "ROLE_";
		return Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX+role));
	}

}
