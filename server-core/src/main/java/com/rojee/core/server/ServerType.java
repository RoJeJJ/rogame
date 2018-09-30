package com.rojee.core.server;

import java.io.Serializable;

public enum ServerType implements Serializable {
    Login(0),Lobby(1),Game(2);

    private static final long serialVersionUID = 1L;

    private int type;

    public int getType(){
        return type;
    }
    ServerType(int i) {
        type = i;
    }
}
