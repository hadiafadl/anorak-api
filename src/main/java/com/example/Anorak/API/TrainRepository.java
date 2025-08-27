package com.example.Anorak.API;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface TrainRepository extends FirestoreReactiveRepository<Train> {
    Flux<Train> findByTrainNumber(String trainNumber);
}
