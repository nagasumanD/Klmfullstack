package com.klm.client.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OauthConfiguration {


	@Bean
	ReactiveClientRegistrationRepository clientRegistrations(
			@Value("${spring.security.oauth2.client.provider.klm.token-uri}") String token_uri,
			@Value("${spring.security.oauth2.client.registration.klm.client-id}") String client_id,
			@Value("${spring.security.oauth2.client.registration.klm.client-secret}") String client_secret,
			@Value("${spring.security.oauth2.client.registration.klm.authorization-grant-type}") String authorizationGrantType,
			@Value("${spring.security.oauth2.client.registration.klm.scope}") String scope

	) {
		ClientRegistration registration = ClientRegistration.withRegistrationId("klm").tokenUri(token_uri)
				.clientId(client_id).clientSecret(client_secret)
				.authorizationGrantType(new AuthorizationGrantType(authorizationGrantType)).build();
		return new InMemoryReactiveClientRegistrationRepository(registration);
	}

	@Bean
	WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
		InMemoryReactiveOAuth2AuthorizedClientService clientService = new InMemoryReactiveOAuth2AuthorizedClientService(
				clientRegistrations);
		AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
				clientRegistrations, clientService);
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				authorizedClientManager);
		oauth.setDefaultClientRegistrationId("klm");

		return WebClient.builder().filter(oauth).build();
	}

}
