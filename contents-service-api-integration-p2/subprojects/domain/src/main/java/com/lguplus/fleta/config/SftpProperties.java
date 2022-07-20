package com.lguplus.fleta.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SftpProperties {

    private String name;
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean disable;
}
