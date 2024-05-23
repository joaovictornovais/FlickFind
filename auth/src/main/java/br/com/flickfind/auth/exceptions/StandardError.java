package br.com.flickfind.auth.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {

    private Instant moment;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
