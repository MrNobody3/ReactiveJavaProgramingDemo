package home.demos.reactiveprogramingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import io.r2dbc.spi.ConnectionFactory;

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
	public static void main(String[] args) {
		SpringApplication.run(ReactiveprogramingdemoApplication.class, args);
	}

}
