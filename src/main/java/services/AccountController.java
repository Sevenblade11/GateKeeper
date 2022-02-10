package services;

import org.springframework.web.bind.annotation.RestController;
import repository.AccountRepository;

@RestController
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
}
