package org.johnmusic.service.pojo;

/**
 * Created by bankuru on 12/2/17.
 */
public enum InstrumentId {
    GUITAR(1),
    KEYBOARD(2),
    VOCALS(3);

    private int value;

    private InstrumentId(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
