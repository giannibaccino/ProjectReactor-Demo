package com.gianni.operador.filtrado;

import com.gianni.DemoReactorApplication;
import com.gianni.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {

    private static final Logger log = LoggerFactory.getLogger(Filtrado.class);

    //Filtra los elementos del flujo segun los parametros que le indiquemos
    public void filter(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(2,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .filter(p -> p.getEdad() > 22)
                .subscribe(p -> log.info(p.toString()));
    }

    /* Distingue los elementos repetidos y solo deja el primero de ese tipo,
    puede hacer falta sobreescribir los metodos equals() y hashCode() en la clase del obejeto
    si no es un tipo de dato primitivo */
    public void distinct(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(1,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .distinct()
                .subscribe(p -> log.info(p.toString()));
    }

    //Solamente toma la cantidad de elementos DESDE EL INICIO que le indiquemos
    public void take(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(1,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .take(2)
                .subscribe(p -> log.info(p.toString()));
    }

    //Solamente toma la cantidad de elementos DESDE EL FINAL que le indiquemos
    public void takeLast(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(1,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .takeLast(2)
                .subscribe(p -> log.info(p.toString()));
    }

    //Saltea la cantidad de elementos DESDE EL INICO que le indiquemos
    public void skip(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(1,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .skip(1)
                .subscribe(p -> log.info(p.toString()));
    }

    //Saltea la cantidad de elementos DESDE EL FINAL que le indiquemos
    public void skipLast(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Pepe", 23));
        personas.add(new Persona(1,"Gianni", 22));
        personas.add(new Persona(3,"Juan", 21));

        Flux.fromIterable(personas)
                .skipLast(1)
                .subscribe(p -> log.info(p.toString()));
    }
}
