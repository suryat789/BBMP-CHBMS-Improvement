package com.infy.stg.api;

@javax.annotation.Generated(value = "io.stg.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T13:15:22.100Z[GMT]")
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
