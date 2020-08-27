package home.demos.reactiveprogramingdemo.dao;

//import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import home.demos.reactiveprogramingdemo.entitymodel.Reservation;
//import reactor.core.publisher.Flux;


public interface ReservationRepos extends ReactiveCrudRepository<Reservation,Integer>{
    // Keep searching until has matching we use tailable annotation just for mongo db
    // @Tailable
    // Flux<Reservation> findByName(String name);
}