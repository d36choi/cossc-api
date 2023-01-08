package com.api.cossc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthRefreshRequestParam {
    private String accessToken;
}
