package com.rbkmoney.adapter.common.state.serializer;

public interface Serializer<TState> {

    byte[] writeByte(Object obj);

    String writeString(Object obj);

}
