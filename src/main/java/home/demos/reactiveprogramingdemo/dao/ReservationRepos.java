package home.demos.reactiveprogramingdemo.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import home.demos.reactiveprogramingdemo.entitymodel.Reservation;

public interface ReservationRepos extends ReactiveCrudRepository<Reservation,String>{
    
}