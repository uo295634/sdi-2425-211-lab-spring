package com.uniovi.sdi2425211spring.repositories;

import com.uniovi.sdi2425211spring.entities.*;
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<User, Long>{
}
