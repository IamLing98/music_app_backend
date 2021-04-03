package com.music.backend.repository;

import com.music.backend.model.Account;
import com.music.backend.repository.custom.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

    Account findFirstByEmail(String email);

    boolean existsAccountByEmail(String email);
}
