package home.demos.reactiveprogramingdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import home.demos.reactiveprogramingdemo.dao.ReservationRepos;
import home.demos.reactiveprogramingdemo.entitymodel.Reservation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepos reservationRepos;

    @GetMapping("/reservations")
    public Flux<Reservation> reservationFlux(){
        return this.reservationRepos.findAll();
    }
}