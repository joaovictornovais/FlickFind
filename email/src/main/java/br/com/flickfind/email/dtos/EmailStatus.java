package br.com.flickfind.email.dtos;

import lombok.Getter;

@Getter
public enum EmailStatus {

    SENT("sent"),
    ERROR("error");

    private final String code;


    private EmailStatus(String code) {
        this.code = code;
    }

}
