package mwo.lab.repositories;

import mwo.lab.models.Client;
import mwo.lab.models.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Query("select c from Client c " +
            "where c.name = :name")
    List<Client> getClient(@Param("name") String guestName);
}