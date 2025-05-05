package com.entreprise.assurance.notificationservice.event;

import com.entreprise.assurance.notificationservice.model.ChangeDTO;

public class ChangeEvent {
    private ChangeDTO change;
    public ChangeEvent(ChangeDTO change) { this.change = change; }
    public ChangeDTO getChange() { return change; }
}