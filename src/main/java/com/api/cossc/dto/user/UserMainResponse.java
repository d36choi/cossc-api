package com.api.cossc.dto.user;

public record UserMainResponse(String uid, int solved,
                               int correct, String profile, String tagName) {

}
