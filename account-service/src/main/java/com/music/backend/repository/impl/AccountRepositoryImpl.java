package com.music.backend.repository.impl;

import com.music.backend.model.Account;
import com.music.backend.repository.custom.AccountRepositoryCustom;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
@Data
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;
}
