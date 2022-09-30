package com.lguplus.fleta.data.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class TestSubscriberDto implements Serializable {

    private static final long serialVersionUID = 0L;

    private String saId;
    private String macAddr;
}
