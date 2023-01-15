package com.api.cossc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyQuizRequest {

    private Long userId;
    private Long tagId;
}
