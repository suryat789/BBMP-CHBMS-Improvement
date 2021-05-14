package com.infy.stg.api;

@javax.annotation.Generated(value = "io.stg.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T13:15:22.100Z[GMT]")
public class ApiException extends Exception {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
