package com.graduate.IRLEnglishcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
