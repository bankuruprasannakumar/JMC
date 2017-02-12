package org.johnmusic.service.request;


import org.johnmusic.service.Constants;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bankuru on 12/2/17.
 */
@XmlRootElement
public class GetBatchesRequest implements IRequest{

    private int userId;
    private int instrumentId;
    private int dayId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    @Override
    public boolean isValid() {
        if (userId == 0)
            return false;
        if (instrumentId == 0)
            return false;
        if (dayId == 0)
            return false;
        return true;
    }

    @Override
    public String errorMessage() {
        StringBuilder message = new StringBuilder();
        if (userId == 0)
            message.append(Constants.INVALID_USER_ID);
        if (instrumentId == 0)
            message.append(Constants.INVALID_INSTRUMENT_ID);
        if (dayId == 0)
            message.append(Constants.INVALID_DAY_ID);
        return message.toString();
    }
}
