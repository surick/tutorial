package com.jinaiya.utils;

import com.jinaiya.utils.service.impl.WebSocketServer;
import com.jinaiya.utils.updateshandlers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;
import org.telegram.telegrambots.meta.logging.BotsFileHandler;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

/**
 * @author Jin
 * @date 2018/11/20
 */
@SpringBootApplication
@EnableScheduling
public class UtilsApplication {
	private static final String LOGTAG = "MAIN";

	public static void main(String[] args) {
		SpringApplication.run(UtilsApplication.class, args);

		BotLogger.setLevel(Level.ALL);
		BotLogger.registerLogger(new ConsoleHandler());
		try {
			BotLogger.registerLogger(new BotsFileHandler());
		} catch (IOException e) {
			BotLogger.severe(LOGTAG, e);
		}

		try {
			ApiContextInitializer.init();
			TelegramBotsApi telegramBotsApi = createTelegramBotsApi();
			try {
				// Register long polling bots. They work regardless type of TelegramBotsApi we are creating
//				telegramBotsApi.registerBot(new ChannelHandlers());
//				telegramBotsApi.registerBot(new DirectionsHandlers());
//				telegramBotsApi.registerBot(new RaeHandlers());
//				telegramBotsApi.registerBot(new WeatherHandlers());
//				telegramBotsApi.registerBot(new TransifexHandlers());
				telegramBotsApi.registerBot(new FilesHandlers());
//				telegramBotsApi.registerBot(new CommandsHandler(BotConfig.COMMANDS_USER));
//				telegramBotsApi.registerBot(new ElektrollArtFanHandler());
			} catch (TelegramApiException e) {
				BotLogger.error(LOGTAG, e);
			}
		} catch (Exception e) {
			BotLogger.error(LOGTAG, e);
		}

		new WebSocketServer("127.0.0.1", 9999).start();
	}

	private static TelegramBotsApi createTelegramBotsApi() throws TelegramApiException {
		TelegramBotsApi telegramBotsApi;
		if (!BuildVars.useWebHook) {
			// Default (long polling only)
			telegramBotsApi = createLongPollingTelegramBotsApi();
		} else if (!BuildVars.pathToCertificatePublicKey.isEmpty()) {
			// Filled a path to a pem file ? looks like you're going for the self signed option then, invoke with store and pem file to supply.
			telegramBotsApi = createSelfSignedTelegramBotsApi();
			telegramBotsApi.registerBot(new WebHookExampleHandlers());
		} else {
			// Non self signed, make sure you've added private/public and if needed intermediate to your cert-store.
			telegramBotsApi = createNoSelfSignedTelegramBotsApi();
			telegramBotsApi.registerBot(new WebHookExampleHandlers());
		}
		return telegramBotsApi;
	}

	/**
	 * @brief Creates a Telegram Bots Api to use Long Polling (getUpdates) bots.
	 * @return TelegramBotsApi to register the bots.
	 */
	private static TelegramBotsApi createLongPollingTelegramBotsApi() {
		return new TelegramBotsApi();
	}

	/**
	 * @brief Creates a Telegram Bots Api to use Long Polling bots and webhooks bots with self-signed certificates.
	 * @return TelegramBotsApi to register the bots.
	 *
	 * @note https://core.telegram.org/bots/self-signed#java-keystore for generating a keypair in store and exporting the pem.
	 *  @note Don't forget to split the pem bundle (begin/end), use only the public key as input!
	 */
	private static TelegramBotsApi createSelfSignedTelegramBotsApi() throws TelegramApiException {
		return new TelegramBotsApi(BuildVars.pathToCertificateStore, BuildVars.certificateStorePassword, BuildVars.EXTERNALWEBHOOKURL, BuildVars.INTERNALWEBHOOKURL, BuildVars.pathToCertificatePublicKey);
	}

	/**
	 * @brief Creates a Telegram Bots Api to use Long Polling bots and webhooks bots with no-self-signed certificates.
	 * @return TelegramBotsApi to register the bots.
	 *
	 * @note Coming from a set of pem files here's one way to do it:
	 * @code{.sh}
	 * openssl pkcs12 -export -in public.pem -inkey private.pem > keypair.p12
	 * keytool -importkeystore -srckeystore keypair.p12 -destkeystore server.jks -srcstoretype pkcs12
	 * #have (an) intermediate(s) to supply? first:
	 * cat public.pem intermediate.pem > set.pem (use set.pem as -in)
	 * @endcode
	 */
	private static TelegramBotsApi createNoSelfSignedTelegramBotsApi() throws TelegramApiException {
		return new TelegramBotsApi(BuildVars.pathToCertificateStore, BuildVars.certificateStorePassword, BuildVars.EXTERNALWEBHOOKURL, BuildVars.INTERNALWEBHOOKURL);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}
}
