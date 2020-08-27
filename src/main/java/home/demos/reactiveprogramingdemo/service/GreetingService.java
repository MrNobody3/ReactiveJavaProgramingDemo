package home.demos.reactiveprogramingdemo.service;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class GreetingService {
    //generating a non stop data
    public Flux<GreetingResponse> greet(GreetingRequest request){
        return Flux.fromStream(
            Stream.generate(new Supplier<GreetingResponse>(){
                public GreetingResponse get(){
                    return new GreetingResponse("Hello "+request.getName()+" @ "+Instant.now()+" !");
                }
            })
            ).delayElements(Duration.ofSeconds(1));
    }
}
