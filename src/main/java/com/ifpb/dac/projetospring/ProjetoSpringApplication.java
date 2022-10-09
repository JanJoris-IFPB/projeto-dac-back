package com.ifpb.dac.projetospring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifpb.dac.projetospring.controller.PersonController;
import com.ifpb.dac.projetospring.controller.VehicleController;
import com.ifpb.dac.projetospring.model.entity.Person;
import com.ifpb.dac.projetospring.model.entity.Vehicle;

@SpringBootApplication
public class ProjetoSpringApplication implements CommandLineRunner {

	@Autowired
	private PersonController personController;
	@Autowired
	private VehicleController vehicleController;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		showMenu(new BufferedReader(new InputStreamReader(System.in)));
	}

	private void showMenu(BufferedReader reader) throws IOException {
		System.out.print("1 - Adicionar pessoa " +
		"\n2 - Mostrar pessoas cadastradas " +
		"\n3 - Adicionar veículo " +
		"\n4 - Mostrar veículos cadastrados " +
		"\n5 - Associar veículo com pessoa " +
		"\n6 - Sair " +
		"\nEscolha uma opção: ");

		switch (reader.readLine()) {
			case "1":
				addPerson(reader);
				break;
			case "2":
				showAllPerson();
				break;
			case "3":
				addVehicle(reader);
				break;
			case "4":
				showAllVehicle();
				break;
			case "5":
				vehicleToOwner(reader);
				break;
			case "6":
				System.exit(0);
				break;
			default:
				break;
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

	private void addVehicle(BufferedReader reader) {
		try {
			System.out.print("Placa: ");
			String plate = reader.readLine();
			System.out.print("Marca: ");
			String make = reader.readLine();
			System.out.print("Modelo: ");
			String model = reader.readLine();
			System.out.print("Cor: ");
			String color = reader.readLine();

			vehicleController.saveVehicle(plate, make, model, color);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showAllVehicle() {
		System.out.print("Veículos cadastrados: ");
		try {
			for (Vehicle vehicle : vehicleController.getAll())
				System.out.println(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void vehicleToOwner(BufferedReader reader) {
		try {
			System.out.print("CPF do dono: ");
			String cpf = reader.readLine();
			System.out.print("Placa do veículo: ");
			String plate = reader.readLine();

			personController.associateVehicle(cpf, plate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
