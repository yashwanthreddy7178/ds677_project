package com.sprintgether.otserver.model.payload;

import lombok.Data;

@Data
public class ExceptionMessage {
    private String date;
    private String path;
    private String className;
    private String message;
}
