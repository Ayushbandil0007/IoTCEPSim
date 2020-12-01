package com.example;

import com.opencsv.bean.CsvBindByName;

public class Response {

    @CsvBindByName
    public long id;
    @CsvBindByName
    public String value;

    public String severity;

    public Response() {
    }

    public Response(long id, String value, String severity) {
        this.id = id;
        this.value = value;
        this.severity = severity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
