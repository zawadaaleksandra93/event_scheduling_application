package com.projekt.event_scheduling_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApprovalForm {

    @NotNull
    private boolean approve;
}
