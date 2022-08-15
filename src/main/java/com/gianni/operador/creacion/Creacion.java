package com.gianni.operador.creacion;

import com.gianni.DemoReactorApplication;
import com.gianni.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {

    private static final Logger log = LoggerFactory.getLogger(Creacion.class);

    //Crea un flujo a partir del elemento dado
    public void justFrom(){
        Mono.just(new Persona(1,"Gianni", 29));
       // Flux.fromIterable(collecion);
       // Observable.just(new Persona(1,"Gianni", 29));
    }

    //Crea un flujo vacio
    public void empty(){
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    //Crea un flujo de un rango de numeros que le indiquemos
    public void range(){
        Flux.range(0,3)
                .doOnNext(i -> log.info("i : " + i))
                .subscribe();
    }

    //Repite el flujo la cantidad de veces que le indiquemos
    public void repeat(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .repeat(2)
                .subscribe(p -> log.info(p.toString()));

        Mono.just(new Persona(1,"Pepito", 25))
                .repeat(2)
                .subscribe(p -> log.info(p.toString()));

    }
}
