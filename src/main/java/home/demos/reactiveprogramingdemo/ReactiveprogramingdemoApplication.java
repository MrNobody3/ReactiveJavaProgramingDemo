package home.demos.reactiveprogramingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.RouteMatcher.Route;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import home.demos.reactiveprogramingdemo.dao.ReservationRepos;
import home.demos.reactiveprogramingdemo.entitymodel.Reservation;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveprogramingdemoApplication {
	@Bean
	ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory cf){
		return new R2dbcTransactionManager(cf);
	}
	@Bean
	TransactionalOperator transactionalOperator(ReactiveTransactionManager transactionManager){
		return TransactionalOperator.create(transactionManager);
	}
	// making first functional reactive end point
	@Bean
	RouterFunction <ServerResponse> routes(ReservationRepos rr){
		//return route()
		// .GET("reservations", new HandlerFunction<ServerResponse>(){
		// 	@Override
		// 	public Mono<ServerResponse> handle(ServerRequest request){
		// 		return ServerResponse.ok().body(rr.findAll(), Reservation.class);
		// 	}
		// })
		//.GET("reservations", request -> ok().body(rr.findAll(), Reservation.class))
		//.build();
		return null;
		
	}
	public static void main(String[] args) {
		SpringApplication.run(ReactiveprogramingdemoApplication.class, args);
	}

}
