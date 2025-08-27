package com.example.Anorak.API;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface StationRepository extends FirestoreReactiveRepository<Station> {
    Flux<Station> findByName(String name);
}
