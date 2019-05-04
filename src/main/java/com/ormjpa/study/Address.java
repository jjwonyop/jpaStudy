/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
