package com.ibgeapi.ibge_consumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record City(Long id, String nome) { }
