package com.rojee.core.server;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ServerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private ServerType type;

    @Getter
    private long id;

    @Getter
    @Setter
    private String ip;

    @Getter
    @Setter
    private int port;

    public void setId(int id) {
        this.id = id * 100000;
    }
}
