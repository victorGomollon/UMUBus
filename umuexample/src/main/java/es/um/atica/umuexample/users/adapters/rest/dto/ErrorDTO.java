package es.um.atica.umuexample.users.adapters.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDTO {
    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String path;
    private String method;
}
