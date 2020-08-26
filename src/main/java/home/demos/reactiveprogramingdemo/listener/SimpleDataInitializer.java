package home.demos.reactiveprogramingdemo.listener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import home.demos.reactiveprogramingdemo.dao.ReservationRepos;
import home.demos.reactiveprogramingdemo.entitymodel.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Log4j2
public class SimpleDataInitializer {
    private final ReservationRepos reservetionRepos;
    
    @EventListener(ApplicationReadyEvent.class)
    public void ready(){
        Flux<String> names = Flux.just("zakaria","aymane","saksouka","test");
        Flux<Reservation> reservations = names.map(name -> new Reservation(null,name));
        // this line will return a Flux<Mono<Reservation>>, so to not avoid
        //that and to get just a Flux<Reserevation> we use the flatMap operator line 26
        //instead The map operator line 25
        //reservations.map(r -> this.reservetionRepos.save(r));
        Flux<Reservation> saved = reservations.flatMap(r -> this.reservetionRepos.save(r));
        //saved.subscribe(reservation -> log.info(reservation));

        // a clean code
        // var saved = Flux.just("zakaria","aymane","saksouka","test")
        // .map(name -> new Reservation(null,name))
        // .flatMap(r -> this.reservetionRepos.save(r));

        // creating a pipeline now that make sure that all the data in our 
        //database will be deleted after that we save the data created up
        //in tha var saved, see below
        this.reservetionRepos
        .deleteAll()
        .thenMany(saved)
        .thenMany(this.reservetionRepos.findAll())
        .subscribe(log::info);
    }
}
