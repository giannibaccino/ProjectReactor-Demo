package com.gianni;

import com.gianni.model.Persona;
import com.gianni.operador.combinacion.Combinacion;
import com.gianni.operador.condicional.Condicional;
import com.gianni.operador.matematico.Matematico;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoReactorApplication.class);

	public void reactor(){
		Mono.just(new Persona(1, "Gianni", 21))
				.doOnNext(p -> {
					p.setEdad(25);
					log.info("[Reactor] Persona:" + p + "#");
				})
				.subscribe(p -> log.info("[Reactor] Persona:" + p));
	}

	public void rxjava2(){
		Observable.just(new Persona(1, "Gianni", 21))
				.doOnNext(p -> log.info("[RxJava2] Persona:" + p + "#"))
				.subscribe(p -> log.info("[RxJava2] Persona:" + p));
	}

	public void mono(){
		Mono.just(new Persona(1, "Gianni", 21))
				.subscribe(p -> log.info(p.toString()));
	}

	public void flux(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Pepe", 23));
		personas.add(new Persona(2,"Gianni", 22));
		personas.add(new Persona(3,"Juan", 21));

		Flux.fromIterable(personas)
				.subscribe(p -> log.info(p.toString()));
	}

	public void fluxMono(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Pepe", 23));
		personas.add(new Persona(2,"Gianni", 22));
		personas.add(new Persona(3,"Juan", 21));

		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(lista -> log.info(lista.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoReactorApplication.class, args);
	}

	//PRUEBAS AQUI
	@Override
	public void run(String... args) throws Exception {
		Matematico app = new Matematico();
		app.summarizing();
	}
}
