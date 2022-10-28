package com.example.ob03restdatajpa;

import com.example.ob03restdatajpa.entities.Laptop;
import com.example.ob03restdatajpa.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Ob03RestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Ob03RestDatajpaApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// CrUD
		// Crear Laptop
		Laptop a001 = new Laptop(null, "usr98", LocalDate.now(), 1, "IntelCore5", true);
		Laptop a002 = new Laptop(null, "usr99", LocalDate.now(), 1, "IntelCore7", false);
		System.out.println("Contando usuarios: " + repository.count());
		System.out.println("Tamaño de la base de datos: " + repository.findAll().size());
		// Almacenar Laptop
		repository.save(a001);
		repository.save(a002);
		// Recuperar Laptop
		System.out.println("Contando usuarios: " + repository.count());
		System.out.println("Tamaño de la base de datos: " + repository.findAll().size());
		// Borrar Laptop
		//repository.deleteById(1L);
		System.out.println("Contando usuarios: " + repository.count());
		System.out.println("Tamaño de la base de datos: " + repository.findAll().size());


	}

}
