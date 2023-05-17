package com.api.cossc.service.user;

import com.api.cossc.dto.user.UserMainResponse;
import com.api.cossc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserMainResponse getUserMe(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return new UserMainResponse(username);
    }
}
