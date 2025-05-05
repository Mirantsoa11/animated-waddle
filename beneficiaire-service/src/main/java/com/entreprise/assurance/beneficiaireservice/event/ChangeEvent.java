package com.entreprise.assurance.beneficiaireservice.event;

import com.entreprise.assurance.beneficiaireservice.dto.ChangeDTO;

public class ChangeEvent {
    private ChangeDTO change;
    public ChangeEvent(ChangeDTO change) { this.change = change; }
    public ChangeDTO getChange() { return change; }
}