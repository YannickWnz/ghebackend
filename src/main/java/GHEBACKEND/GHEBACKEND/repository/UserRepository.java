package GHEBACKEND.GHEBACKEND.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import GHEBACKEND.GHEBACKEND.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
