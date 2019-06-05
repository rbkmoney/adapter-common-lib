package com.rbkmoney.adapter.common.processor;

public interface Processor<T, E, R> {

    T process(R response, E context);

}
