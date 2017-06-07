package app.repository;


import java.util.Date;

public class Pair<T,V> {
    private T value;
    private V time;

    public Pair(T value, V time) {
        this.value = value;
        this.time = time;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public V getTime() {
        return time;
    }

    public void setTime(V time) {
        this.time = time;
    }
}
