package com.doza.coffee.repository;

import com.doza.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
