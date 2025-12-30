package com.vbs.demo.repositories;

import com.vbs.demo.models.Transaction;
import com.vbs.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByUserId(Integer id);
}
