package com.projekt.event_scheduling_application.mailConfirmation;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.User;
import lombok.Getter;

@Getter
public class EmailConst {

    String APPROVAL_SUBJECT = "ESA: Manager approval required";
    String APPROVAL_MESSAGE = "please accept participation in an event";

    public String insertApprovalSubject() {
        String APPROVAL_SUBJECT = "ESA: Manager approval required";
        return APPROVAL_SUBJECT;
    }

}
