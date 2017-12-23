package edu.bracketly.backend.model.entity.user;

public enum K_FACTOR {
    BEGGINER(40),
    AVERAGE(20),
    PRO(10);

    public final int value;

    K_FACTOR(int value) {
        this.value = value;
    }
}
