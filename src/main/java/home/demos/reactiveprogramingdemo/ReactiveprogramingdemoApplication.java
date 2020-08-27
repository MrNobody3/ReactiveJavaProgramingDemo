package home.demos.reactiveprogramingdemo;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.RouteMatcher.Route;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import home.demos.reactiveprogramingdemo.dao.ReservationRepos;
import home.demos.reactiveprogramingdemo.entitymodel.Reservation;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;
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

@Configuration
class GreetingWebSocketConfiguration{
	@Bean
	WebSocketHandlerAdapter webSocketHandlerAdapter(){
		return new WebSocketHandlerAdapter();
	}

	//this function for busniss logic
	@Bean
	WebSocketHandler webSocketHandler(){
		return new WebSocketHandler(){
			@Override
			public Mono<Void> handle(WebSocketSession session) {
				//ask for incomming data
				Flux<WebSocketMessage> receive = session.receive();
				Flux<String> names = receive.map(wsm -> wsm.getPayloadAsText());
				names.map(name -> new GreetingR)
				return null;
			}
		};
	}

	//Tell Spring to mount our websocket endpoint to an http url, the reason we need to do that is because
	//websocket is a baniry protocol but when a websocket client connects to the websocket service it upgrades
	//to binary protocol so it s connect to http first and then there a handshake and then it upgrade, so we need
	// to tell the framework map this endpoint
	@Bean
	SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler){
		return new SimpleUrlHandlerMapping(Map.of("ws/greetings",webSocketHandler),10);
	}
}