package repository;

import mvc.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AccountSearchRepository extends CrudRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.userId = 1")
    public ArrayList<Account> searchUserAccounts(@Param("1") long userId);
}
