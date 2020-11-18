package com.projekt.event_scheduling_application.mailConfirmation;

import com.projekt.event_scheduling_application.dao.Event;
import lombok.Getter;

@Getter
public class EmailConst {

    String APPROVAL_SUBJECT = "ESA: Manager approval required";
    String APPROVAL_MESSAGE = "please accept participation in an event";
    public String  insertApprovalMessage(String eventName, String userName) {
        String APPROVAL_MESSAGE = String
                .format("please accept participation of %s in an event: %s"
                        ,userName, eventName);

        return APPROVAL_MESSAGE;
    }
}
