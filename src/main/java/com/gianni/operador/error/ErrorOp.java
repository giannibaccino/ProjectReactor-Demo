package com.gianni.operador.error;

import com.gianni.model.Persona;
import com.gianni.operador.combinacion.Combinacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ErrorOp {

    private static final Logger log = LoggerFactory.getLogger(ErrorOp.class);

    //Retry reintenta la accion del flujo tras un error
    public void retry(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .retry(1)
                .doOnNext(persona -> log.info(persona.toString()))
                .subscribe();
    }

    //Controla el error haciendo lo que le indiquemos
    public void errorReturn(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorReturn(new Persona(0, "XXX", 00))
                .subscribe(persona -> log.info(persona.toString()));
    }

    //Espera una funcion tras el erro/excepcion (retoma un flujo dado)
    public void errorResume(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorResume(e -> Mono.just(new Persona(0, "XXX", 00)))
                .subscribe(persona -> log.info(persona.toString()));
    }

    //Podemos lanzar otra excepcion personalizada al recibir el error
    public void errorMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(persona -> log.info(persona.toString()));
    }

}
