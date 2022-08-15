package com.gianni.operador.combinacion;

import com.gianni.model.Persona;
import com.gianni.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {

    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    //Concatena los flujos que le indiquemos mas alla del tipo de elemento que lleva
    public void merge(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepe", 23));
        personas.add(new Persona(2, "Gianni", 22));
        personas.add(new Persona(3, "Juan", 21));

        List<Persona> personas2 = new ArrayList<>();
        personas.add(new Persona(4, "Pep", 23));
        personas.add(new Persona(5, "Gian", 22));
        personas.add(new Persona(6, "John", 21));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.merge(fx1, fx2, fx3)
                .subscribe(p -> log.info(p.toString()));
    }

    //Combina los flujos indicados y opera (opcional) una vez haya recibido
    //al menos un elemento de cada flujo
    public void zip(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepe", 23));
        personas.add(new Persona(2, "Gianni", 22));
        personas.add(new Persona(3, "Juan", 21));

        List<Persona> personas2 = new ArrayList<>();
        personas.add(new Persona(4, "Pep", 23));
        personas.add(new Persona(5, "Gian", 22));
        personas.add(new Persona(6, "John", 21));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        /*Flux.zip(fx1, fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2 ))
                .subscribe(s -> log.info(s));*/

        Flux.zip(fx1, fx2, fx3)
                .subscribe(p -> log.info(p.toString()));
    }

    //Igual que zip pero parte de un elemento de tipo flux en lugar de la clase Flux
    public void zipWith(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepe", 23));
        personas.add(new Persona(2, "Gianni", 22));
        personas.add(new Persona(3, "Juan", 21));

        List<Persona> personas2 = new ArrayList<>();
        personas.add(new Persona(4, "Pep", 23));
        personas.add(new Persona(5, "Gian", 22));
        personas.add(new Persona(6, "John", 21));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        fx1.zipWith(fx2)
                .subscribe(s -> log.info(s.toString()));
    }
}
