package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PvReq {

    private String date;
    private String heure;
    private String patrouille;
    private String idacc;
    private String nous;
    private String assiste;
    private String constate;
    private String circulation;

}
