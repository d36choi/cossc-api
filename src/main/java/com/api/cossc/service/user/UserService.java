package com.api.cossc.service.user;


import com.api.cossc.dto.user.UserMainResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {


    UserMainResponse getUserMe(UserDetails userDetails);
}
