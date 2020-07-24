package com.Sachin.Repository;

import com.Sachin.Model.Url;
import com.Sachin.Starter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UrlRepository extends JpaRepository<Url,Integer> {
    //    void delete(Optional<Url> obj);
}
