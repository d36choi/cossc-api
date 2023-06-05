package com.api.cossc.service.user;


import com.api.cossc.dto.tag.TagRequest;
import com.api.cossc.dto.user.UserMainResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {


    UserMainResponse getUserMe(UserDetails userDetails) throws Exception;

    boolean insertUserTag(UserDetails userDetails, TagRequest tagRequest) throws Exception;
}
