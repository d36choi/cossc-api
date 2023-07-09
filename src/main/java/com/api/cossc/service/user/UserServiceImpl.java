package com.api.cossc.service.user;

import com.api.cossc.domain.TagEntity;
import com.api.cossc.domain.UserEntity;
import com.api.cossc.dto.tag.TagRequest;
import com.api.cossc.dto.user.UserMainResponse;
import com.api.cossc.exception.CommonException;
import com.api.cossc.repository.TagRepository;
import com.api.cossc.repository.UserRepository;
import com.api.cossc.service.quiz.QuizService;
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
    private final TagRepository tagRepository;
    private final QuizService quizService;

    @Transactional(readOnly = true)
    @Override
    public UserMainResponse getUserMe(UserDetails userDetails) throws Exception {
        UserEntity userEntity = getUserEntityElseThrowException(userDetails);

        //TODO:: solved, correct 로직 구현
        if (Objects.isNull(userEntity.getTagEntity()))
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, "USER의 TAG가 없습니다.");

        return new UserMainResponse(userEntity.getName(), userEntity.getSolvedCount(), userEntity.getCorrectCount(), userEntity.getImg(), userEntity.getTagEntity().getName(), quizService.isAllSolved(userEntity));
    }

    @Transactional
    @Override
    public boolean insertUserTag(UserDetails userDetails, TagRequest tagRequest) throws Exception {
        UserEntity userEntity = getUserEntityElseThrowException(userDetails);

        TagEntity tagEntity = tagRepository.findByName(tagRequest.getTagName())
                .orElseThrow(() -> new Exception("Tag가 없습니다."));

        userEntity.addTagEntity(tagEntity);
        userRepository.save(userEntity);

        return true;
    }

    private UserEntity getUserEntityElseThrowException(UserDetails userDetails) throws Exception {
        String oauthKey = userDetails.getUsername();
        return userRepository.findByOauthKeyOrEmail(oauthKey, null)
                .orElseThrow(() -> new Exception("없는 USER입니다"));
    }
}



