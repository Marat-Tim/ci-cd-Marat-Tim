package ru.marattim.accounts.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class EmptyResponse {
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final EmptyResponse INSTANCE = new EmptyResponse();

    private EmptyResponse() {
    }
}
