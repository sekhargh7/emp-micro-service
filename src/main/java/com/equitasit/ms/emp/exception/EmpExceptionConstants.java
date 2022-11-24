package com.equitasit.ms.emp.exception;

public enum EmpExceptionConstants {

    EMP_NOT_FOUND("001");

    EmpExceptionConstants(String code) {
        value = code;
    }

    public String getValue() {
        return value;
    }

    private String value;



}
