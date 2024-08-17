package com.ibgeapi.ibge_consumer;

import com.ibgeapi.ibge_consumer.domain.Cep;
import com.ibgeapi.ibge_consumer.domain.City;
import com.ibgeapi.ibge_consumer.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class IbgeConsumerApplication {

	private static final Logger log = LoggerFactory.getLogger(IbgeConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IbgeConsumerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			String urlState = "https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome";
			String urlCity = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/25/municipios?orderBy=nome";
			String urlCep = "https://viacep.com.br/ws/37538756/json/";

			//Coletando os estados do Brasil
			List<State> states = restTemplate.exchange(
					urlState,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<State>>() {}
			).getBody();
			if(states != null){
				//states.forEach(state -> log.info((state.toString())));
			} else {
				//log.info("Not state found");
			}

			//Coletando cidades de acordo com o estado
			List<City> cities = restTemplate.exchange(
					urlCity,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<City>>() {}
			).getBody();
			if(cities != null){
				//cities.forEach(city -> log.info((city.toString())));
			} else {
				//log.info("No city found");
			}
			//Coletando dados de acordo com o cep
			Cep cep = restTemplate.getForObject(
					urlCep,
					Cep.class
			);
			//log.info(cep.toString());
		};
	}
	@GetMapping("/home")
	public String LocationController(@RequestParam(value = "name", defaultValue = "World") String name) {
		RestTemplate restTemplate = new RestTemplate();
		String urlState = "https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome";
		String urlCity = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/25/municipios?orderBy=nome";
		String urlCep = "https://viacep.com.br/ws/37538756/json/";
		List<State> states = restTemplate.exchange(
				urlState,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<State>>() {}
		).getBody();
		if(states != null){
			states.forEach(state -> System.out.println(state.toString()));
		} else {
			//log.info("Not state found");
		}

		return String.format("Hello %s!", name);
	}

}
