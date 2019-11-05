package model;

import java.io.Serializable;

public class ServerMessage<T> implements Serializable {
    private String action;
    private T data;

    public ServerMessage(String action, T data) {
        this.action = action;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getAction() {
        return action;
    }
}
