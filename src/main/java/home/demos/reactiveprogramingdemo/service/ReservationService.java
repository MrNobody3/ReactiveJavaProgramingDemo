package home.demos.reactiveprogramingdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import home.demos.reactiveprogramingdemo.dao.ReservationRepos;
import home.demos.reactiveprogramingdemo.entitymodel.Reservation;
import org.springframework.transaction.reactive.TransactionalOperator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepos reservationRepos;
    private final TransactionalOperator transactionalOperator;

    public Flux<Reservation> saveAll(String... names){
        
        Flux<String> stringFlux = Flux.fromArray(names);
        Flux<Reservation> map = stringFlux.map(name -> new Reservation(null,name));
        Flux<Reservation> fluxReservation = map.flatMap(r -> this.reservationRepos.save(r));
        //the probléme here is, the data would be writing after that he check validity, what if we had two records
        //from 10 writed without a capitale first letter,  so to resolve that we gonna work with reactive
        //transation manager 
        return this.transactionalOperator.transactional(
            fluxReservation.doOnNext(r -> Assert.isTrue(this.isValid(r), "name must start with capitale letter")));
    }

    boolean isValid(Reservation r){
        return Character.isUpperCase(r.getName().charAt(0)) ;
    }
}
