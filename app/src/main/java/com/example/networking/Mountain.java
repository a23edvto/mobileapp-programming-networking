package com.example.networking;
import androidx.annotation.NonNull;
public class Mountain {
    private String name;

    public Mountain(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
    @NonNull

    @Override
    public String toString() {
        return name;
    }
}
