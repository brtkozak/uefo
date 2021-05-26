package com.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class GatewayApplication {
	private static final String UID = "some-uid";

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, FirebaseAuthException {
		SpringApplication.run(GatewayApplication.class, args);

		FileInputStream serviceAccount = new FileInputStream("gateway/uefo-key.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);
	}

}
