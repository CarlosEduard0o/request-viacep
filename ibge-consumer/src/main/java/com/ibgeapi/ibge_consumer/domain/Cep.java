package com.ibgeapi.ibge_consumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Cep(String logradouro, String bairro, String localidade, String uf, String ddd) {
}
