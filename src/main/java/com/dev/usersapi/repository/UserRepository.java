package com.dev.usersapi.repository;

import com.dev.usersapi.entity.Summary;
import com.dev.usersapi.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT new com.dev.usersapi.entity.Summary(UPPER(u.uf), COUNT(u)) FROM User u GROUP BY UPPER(u.uf)")
    List<Summary> getSummary();
    
    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);
}
