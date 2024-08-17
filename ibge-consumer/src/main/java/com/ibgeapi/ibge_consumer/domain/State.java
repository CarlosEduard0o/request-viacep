package com.ibgeapi.ibge_consumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record State(Long id, String sigla, String nome) { }
