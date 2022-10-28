package com.example.ob03restdatajpa.controller;

import com.example.ob03restdatajpa.entities.Laptop;
import com.example.ob03restdatajpa.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository repository;


    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    // CrUD sobre la entidad Laptop
    // Buscar Laptops
    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        // recuperar y devolver laptops
        return repository.findAll();
    }
    // Buscar laptop
    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOpt = repository.findById(id);
        //opcion1
        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
        //opcion2
/*        return laptopOpt.orElse(null);*/

    }

    // Crear Laptop
    @PostMapping("/api/laptops")
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){

        System.out.println(headers.get("User-Agent"));
        return repository.save(laptop);
    }

    // Actualizar Laptop
    @PutMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if((laptop.getId() == null) || (!repository.existsById(laptop.getId()))){ //el Id no existe? o no es correcto?
            log.warn("Trying to update Pacient without Id");
            System.out.println("Trying to update Pacient without Id");
            return ResponseEntity.badRequest().build();
        }
        else{
            Laptop result = repository.save(laptop);
            return ResponseEntity.ok(result);
        }
    }

    // Borrar Laptop
    @ApiIgnore // ignorar este metodo para que no aparezca en la documentación de la Api Swagger
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!repository.existsById(id)){ //el id no es correcto?
            log.warn("Trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        else{
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }

    //Borrar todas
    @ApiIgnore // ignorar este metodo para que no aparezca en la documentación de la Api Swagger
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Rquest deleting all Laptops");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
