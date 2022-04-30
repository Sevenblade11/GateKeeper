package services;

import hash.HashUtils;
import mvc.model.Account;
import mvc.model.EncryptionDecryption;
import mvc.model.Session;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.AccountRepository;
import repository.AccountSearchRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    private AccountSearchRepository accountSearchRepository;

    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
        public ResponseEntity<ArrayList<Account>> getAccounts(@RequestHeader Map<String, String> header, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false) String keyword){
            if(SessionController.isValidSession(header.get("authorization"), Long.parseLong(header.get("userid")))){
                ArrayList<Account> accounts = accountSearchRepository.searchUserAccounts(Long.parseLong(header.get("userid")));
                return new ResponseEntity<>(accounts, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/accounts/{id}")
        public ResponseEntity<Account> getAccount(@PathVariable long id, @RequestHeader Map<String, String> header){
            if(SessionController.isValidSession(header.get("authorization"), Long.parseLong(header.get("userid")))) {
                Optional<Account> account = accountRepository.findById(id);
                if(account.isPresent()) {
                    if (account.get().getUserId() == Long.parseLong(header.get("userid")))
                        return new ResponseEntity<>(account.get(), HttpStatus.OK);
                    else
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> addAccount(@RequestHeader Map<String, String> header, @RequestBody Map<String, String> body){
        Account account = new Account(body.get("username"), body.get("accountname"), EncryptionDecryption.encrypt(body.get("password")), body.get("website"), body.get("email"));
        if(SessionController.isValidSession(header.get("authorization"), Long.parseLong(header.get("userid")))){
           if(validateAccount(account)){
                account.setUserId(Long.parseLong(header.get("userid")));
                accountRepository.save(account);
                return new ResponseEntity<>(HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
       }
       return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestHeader Map<String, String> header, @RequestBody Map<String, String> body){
        Account updatedAccount = new Account(body.get("username"), body.get("accountname"), EncryptionDecryption.encrypt(body.get("password")), body.get("website"), body.get("email"));
        if(SessionController.isValidSession(header.get("authorization"), Long.parseLong(header.get("userid")))) {
            Optional<Account> account = accountRepository.findById(id);
            if(account.isPresent())
                if (validateAccount(updatedAccount)) {
                    updatedAccount.setUserId(account.get().getUserId());
                    updatedAccount.setId(account.get().getId());
                    accountRepository.save(updatedAccount);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable long id, @RequestHeader Map <String, String> header){
        if(SessionController.isValidSession(header.get("authorization"), Long.parseLong(header.get("userid")))){
            Optional<Account> account = accountRepository.findById(id);
            if(account.isPresent()){
                if(account.get().getUserId() == Long.parseLong(header.get("userid"))) {
                    accountRepository.delete(account.get());
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean validateAccount(Account account){
        return !(account.getAccountName().equals("")) && !(account.getEncryptPassword().equals(""));
    }
}
