package com.rbkmoney.adapter.common.state.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.common.model.RecToken;

public class RecTokenSerializer extends StateSerializer<RecToken> {

    public RecTokenSerializer(ObjectMapper mapper) {
        super(mapper);
    }

}
