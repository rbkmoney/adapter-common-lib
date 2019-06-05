package com.rbkmoney.adapter.common.flow;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.enums.TargetStatus;

public interface StateResolver {

    Step resolveEntry(TargetStatus targetStatus, Step step);

    Step resolveExit(Step step, boolean isThreeDsOperation);

}
