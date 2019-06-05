package com.rbkmoney.adapter.common.handler;

import com.rbkmoney.adapter.common.processor.Processor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.function.Function;


@Slf4j
@RequiredArgsConstructor
public abstract class CommonHandlerImpl<T, P, R, E> implements CommonHandler<T, E> {

    private final Function<P, R> requestFunction;
    private final Converter<E, P> converter;
    private final Processor<T, E, R> processor;

    @Override
    public T handle(E entryStateModel) {
        P request = converter.convert(entryStateModel);
        R response = requestFunction.apply(request);
        return processor.process(response, entryStateModel);
    }

}
