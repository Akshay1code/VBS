package com.vbs.demo.controller;

import com.vbs.demo.dto.TransactionDto;
import com.vbs.demo.models.Transaction;
import com.vbs.demo.models.User;
import com.vbs.demo.repositories.TransactionRepo;
import com.vbs.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class TransactionController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TransactionRepo transactionRepo;
    @PostMapping("/deposit")
    public String deposit(@RequestBody TransactionDto atm){
        User user=userRepo.findById(atm.getId()).orElseThrow(()->new RuntimeException("User not found"));
        double newBalance=user.getBalance()+atm.getAmount();
        user.setBalance(newBalance);
        System.out.println(user.getBalance());
        userRepo.save(user);
        Transaction t=new Transaction();
        t.setUserId(user.getId());
        t.setAmount(atm.getAmount());
        t.setCurrBalance(newBalance);
        t.setDescription("Rs "+atm.getAmount()+"Mode: Deposit");
        transactionRepo.save(t);
        return "Deposit Successfully";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody TransactionDto atm ){
        User user=userRepo.findById(atm.getId()).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getBalance()<atm.getAmount() || user.getBalance()==0){
            return "No money please go for sponser";
        }
        double newBalance=user.getBalance()-atm.getAmount();
        user.setBalance(newBalance);
        userRepo.save(user);
        Transaction t=new Transaction();
        t.setUserId(atm.getId() );
        t.setAmount(atm.getAmount());
        t.setCurrBalance(newBalance);
        t.setDescription("Rs "+atm.getAmount()+" | Mode: Withdraw ");
        transactionRepo.save(t);
        return " Withdraw Sucessfull";
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransactionDto atm ){
        User user1=userRepo.findByUsername(atm.getUsername());
        User user2=userRepo.findById(atm.getId()).orElseThrow(()->new RuntimeException("User not found"));
        if(user1==null){
            return "Receipient user not found";
        }
        else if(user1.getUsername().equals(user2.getUsername())){
            return "Lmao tujhe kya laga khudko bhejke 25 din me paisa double noooooob!!!";
        }
        else if(user2.getBalance()<atm.getAmount() || user2.getBalance()==0){
            return "No money please go for sponser";
        }
        double repUserBalance=user1.getBalance()+atm.getAmount();
        double sendUserBalance=user2.getBalance()-atm.getAmount();
        user1.setBalance(repUserBalance);
        user2.setBalance(sendUserBalance);
        userRepo.save(user1);
        userRepo.save(user2);

        Transaction t1=new Transaction();
        t1.setUserId(user1.getId());
        t1.setAmount(atm.getAmount());
        t1.setCurrBalance(repUserBalance);
        t1.setDescription("Rs "+atm.getAmount()+" | Mode:Received"+" From "+user2.getUsername());
        transactionRepo.save(t1);

        Transaction t2=new Transaction();
        t2.setUserId(atm.getId() );
        t2.setAmount(atm.getAmount());
        t2.setCurrBalance(sendUserBalance);
        t2.setDescription("Rs "+atm.getAmount()+" | Mode:Transfered"+" to "+atm.getUsername());
        transactionRepo.save(t2);

        return "Transfer Successfully";
    }
    @GetMapping("/passbook/{id}")
    public List<Transaction> passbook(@PathVariable Integer id){
        return transactionRepo.findByUserId(id);
    }
}
