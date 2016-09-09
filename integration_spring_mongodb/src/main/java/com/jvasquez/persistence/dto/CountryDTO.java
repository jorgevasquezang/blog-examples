package com.jvasquez.persistence.dto;

/**
 * Created by Ricco on 26/08/2015.
 */
public class CountryDTO {

    private String id;
    private String name;
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
