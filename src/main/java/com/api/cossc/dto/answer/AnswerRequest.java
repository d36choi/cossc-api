package com.api.cossc.dto.answer;

import java.util.List;

public class AnswerRequest {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
