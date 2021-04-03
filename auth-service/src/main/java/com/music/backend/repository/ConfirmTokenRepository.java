package com.music.backend.repository;

import com.music.backend.model.ConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {

}
