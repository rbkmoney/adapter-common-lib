package com.rbkmoney.adapter.common.flow;

import com.rbkmoney.adapter.common.enums.Step;

public interface StepResolver<T, R>  {

    Step resolveEntry(T stateModel);

    Step resolveExit(R stateModel);

}
