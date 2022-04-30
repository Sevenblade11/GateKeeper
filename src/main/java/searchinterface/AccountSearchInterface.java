package searchinterface;

import mvc.model.Account;

import java.util.ArrayList;

public interface AccountSearchInterface {
    public ArrayList<Account> searchUserAccounts(Long id);
}
