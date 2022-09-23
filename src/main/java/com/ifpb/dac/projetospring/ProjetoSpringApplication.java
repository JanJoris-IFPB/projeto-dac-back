package com.ifpb.dac.projetospring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifpb.dac.projetospring.controller.PersonController;
import com.ifpb.dac.projetospring.model.Person;

@SpringBootApplication
public class ProjetoSpringApplication implements CommandLineRunner {

	@Autowired
	private PersonController personController;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		showMenu(new BufferedReader(new InputStreamReader(System.in)));
	}

	private void showMenu(BufferedReader reader) throws IOException {
		System.out.print("1 - Adicionar pessoa \n2 - Mostrar pessoas cadastradas \n3 - Sair \nEscolha uma opção: ");

		switch (reader.readLine()) {
			default:
			case "1":
				addPerson(reader);
				break;
			case "2":
				showAllPerson();
				break;
			case "5":
				System.exit(0);
		}

		showMenu(reader);

	}

	public void addPerson(BufferedReader reader) {
		try {
			System.out.print("CPF: ");
			String cpf = reader.readLine();
			System.out.print("Nome: ");
			String name = reader.readLine();
			System.out.print("Idade: ");
			int age = Integer.parseInt(reader.readLine());
			System.out.print("Email: ");
			String email = reader.readLine();
			System.out.print("Telefone: ");
			String phone = reader.readLine();

			personController.savePerson(cpf, name, age, email, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showAllPerson() {
		System.out.print("Pessoas cadastradas: ");
		try {
			for (Person person : personController.getAll())
				System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
