package com.rbkmoney.adapter.common.service;

import java.util.Map;

public interface ThreeDsPropertiesService<T> {

    Map<String, String> initProperties(T stateModel);

}
