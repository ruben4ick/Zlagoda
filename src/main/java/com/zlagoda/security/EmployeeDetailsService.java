package com.zlagoda.security;

import com.zlagoda.dao.EmployeeDao;
import com.zlagoda.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {
    private EmployeeDao dao;

    @Autowired
    public EmployeeDetailsService(EmployeeDao dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = dao.findByUsername(username);
        if (employee.isPresent()) {
            User authUser = new User(
                    employee.get().getUsername(),
                    employee.get().getPassword(),
                    List.of(new SimpleGrantedAuthority(employee.get().getRole().name()))
            );
            return authUser;
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }
}
