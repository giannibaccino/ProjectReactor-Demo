package com.gianni.operador.matematico;

import com.gianni.DemoReactorApplication;
import com.gianni.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Matematico {

    private static final Logger log = LoggerFactory.getLogger(Matematico.class);

    //Calcula el promedio de los datos dados
    public void average(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(p -> log.info(p.toString()));
    }

    //Calcula la cantidad de elementos
    public void count(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .count()
                .subscribe(p -> log.info("Cantidad: " + p));
    }

    //Calcula el minimo de los elementos
    public void min(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(p -> log.info(p.get().toString()));
    }

    //Suma los elmentos dados
    public void sum(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(p -> log.info("Suma :" + p));
    }

    //Resume varias operaciones aritmeticas (count, sum, min, average, max)
    public void summarizing(){

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(p -> log.info("Resumen: " + p));
    }
}
