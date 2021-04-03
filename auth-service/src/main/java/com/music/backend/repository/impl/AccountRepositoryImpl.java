package com.music.backend.repository.impl;

import com.music.backend.model.Account;
import com.music.backend.repository.custom.AccountRepositoryCustom;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
@Data
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Account> getAll() {
        TypedQuery<Account> query =
                entityManager.createQuery("SELECT c FROM Account c", Account.class);
        List<Account> results = query.getResultList();
        return results;
    }
}
