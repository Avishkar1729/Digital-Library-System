package com.DiditalLibrarySystem;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DigitalLibrarySystem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Starting Digital Library Book Management System...");
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
		SpringApplication.run(DigitalLibrarySystem.class, args);

		while (true) {
			System.out.println("Enter 'exit' to close the application.");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("exit")) {
				System.out.println("Shutting down Digital Library Book Management System...");
				System.exit(0);
			}
		}
	}

}
