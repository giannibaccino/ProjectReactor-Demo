package com.gianni.operador.transformacion;

import com.gianni.model.Persona;
import com.gianni.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {

    private static final Logger log = LoggerFactory.getLogger(Transformacion.class);

    ////Espera un objeto como respuesta
    public void map() {
        /*List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepe", 23));
        personas.add(new Persona(2, "Gianni", 22));
        personas.add(new Persona(3, "Juan", 21));

        Flux.fromIterable(personas)
                .map(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return p;
                })
                .subscribe(p -> log.info(p.toString()));
        */

        Flux<Integer> fx = Flux.range(0, 10);
        fx.map(x -> x + 10)
                .subscribe(x -> log.info("X: " + x));
    }

    //Espera un flujo como respuesta
    public void flatMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepe", 23));
        personas.add(new Persona(2, "Gianni", 22));
        personas.add(new Persona(3, "Juan", 21));

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> log.info(p.toString()));
    }

    //Divide en flujos segun el identificador que le demos
    public void groupBy(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepe", 23));
        personas.add(new Persona(1, "Gianni", 22));
        personas.add(new Persona(3, "Juan", 21));

        Flux.fromIterable(personas)
                .groupBy(Persona::getIdPersona)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> log.info(x.toString()));
    }
}
