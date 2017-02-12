package org.johnmusic.service.pojo;

/**
 * Created by bankuru on 12/2/17.
 */
public enum DayId {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private int value;

    private DayId(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
