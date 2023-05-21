package com.api.cossc.service.user;

import com.api.cossc.domain.UserEntity;
import com.api.cossc.dto.user.UserMainResponse;
import com.api.cossc.exception.CommonException;
import com.api.cossc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserMainResponse getUserMe(UserDetails userDetails) throws Exception {
        String oauthKey = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByOauthKeyOrEmail(oauthKey, null)
                .orElseThrow(() -> new Exception("없는 USER입니다"));

        //TODO:: solved, correct 로직 구현
        if (Objects.isNull(userEntity.getTagEntity()))
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, "USER의 TAG가 없습니다.");

        return new UserMainResponse(userEntity.getName(), userEntity.getSolvedCount(), userEntity.getCorrectCount(), userEntity.getImg(), userEntity.getTagEntity().getName());
    }
}



