package model;

public class GameData<T> {
    protected String type;
    protected T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
