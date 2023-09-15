package com.example.ib_proektna_2023.service.impl;
import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.repository.UserRepository;
import com.example.ib_proektna_2023.service.UserService;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void makeWithdrawal(BigDecimal Amount) {

    }

    @Override
    public void makeDeposit(BigDecimal Amount) {

    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findById(Long.parseLong(username)).orElseThrow(Exception::new);
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(" ")
                .roles(u.getType().name())
                .build();
    }

    @Override
    public User getUserByBankAccountNumber(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllCustomers() {
        return userRepository.findAllByType(UserType.CUSTOMER);
    }


}