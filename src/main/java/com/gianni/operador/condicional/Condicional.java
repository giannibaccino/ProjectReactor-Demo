package com.gianni.operador.condicional;

import com.gianni.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Condicional {

    private final Logger log = LoggerFactory.getLogger(Condicional.class);

    //Retorna un elemento por defecto que le asignemos cuando el flujo viene vacio
    public void defaultIsEmpty(){
        Mono.empty()
                .defaultIfEmpty(new Persona(0, "DEFAULT", 99))
                .subscribe(o -> log.info(o.toString()));
    }

    //Emite los valores de flujo hasta que cumpla la condicion indicada
    public void takeUntil(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .takeUntil(p -> p.getEdad() > 22)
                .subscribe(p -> log.info(p.toString()));
    }

    //Devuelve una exepcion si no se cumple antes del tiempo indicado
    //(delayElements retrasa el flujo de elementos el tiempo indicado)
    public void timeout() throws InterruptedException {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2))
                .subscribe(p -> log.info(p.toString()));

        //Indica el tiempo en que el flujo se cierra
        Thread.sleep(10000);
    }
}
