package com.vbs.demo.controller;


import com.vbs.demo.dto.DisplayDto;
import com.vbs.demo.dto.LoginDto;
import com.vbs.demo.dto.UpdateDto;
import com.vbs.demo.models.History;
import com.vbs.demo.models.Transaction;
import com.vbs.demo.models.User;
import com.vbs.demo.repositories.HistoryRepo;
import com.vbs.demo.repositories.TransactionRepo;
import com.vbs.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    HistoryRepo historyRepo;
    @Autowired
    TransactionRepo transactionRepo;
    @PostMapping("/register")
    public String register(@RequestBody User user){
        userRepo.save(user);
        return "Signup successfull!";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDto u){
        User user=userRepo.findByUsername(u.getUsername());
        if(user==null){
            return "User not found";
        }
        else if(!user.getPassword().equals(u.getPassword())){
            return "Incorrect password";
        }
        else if(!user.getRole().equals(u.getRole())){
            return "Incorrect role";
        }
        return String.valueOf(user.getId());
    }
    @GetMapping("/get-details/{id}")
    public DisplayDto display(@PathVariable Integer id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        DisplayDto displayDto=new DisplayDto();
        displayDto.setName(user.getName());
        displayDto.setBalance(user.getBalance());
        displayDto.setUsername(user.getUsername());
        System.out.println(displayDto.getName());
        return displayDto;
    }
    @PostMapping("/update")
    public String update(@RequestBody UpdateDto update){
        User user=userRepo.findById(update.getId()).orElseThrow(()->new RuntimeException("User not found"));
        System.out.println(user);
        if(update.getKey().equals("name")){
            if(update.getValue().equals("") || update.getValue().equals(user.getName())){
                return "Name is same";
            }
            user.setName(update.getValue());
            System.out.println(user);

        }
        else if(update.getKey().equals("password")){
            if(update.getValue().isEmpty() || update.getValue().equals(user.getPassword())){
                return "Password is same";
            }
            user.setPassword(update.getValue());

        }
        else if(update.getKey().equals("email")){
            if(update.getValue().isEmpty() || update.getValue().equals(user.getEmail())){
                return "Email is same";
            }
            else{
                User user1=userRepo.findByEmail(update.getValue());
                System.out.println(user1);
                if(user1!=null){
                    return "Email already exists";
                }
            }
            user.setEmail(update.getValue());
        }
        userRepo.save(user);
        return "Update successfull!";
    }
    @PostMapping("/add/{adminId}")
    public String add(@RequestBody User user,@PathVariable int adminId){
        History history=new History();
        User admin=userRepo.findById(adminId).orElseThrow(()->new RuntimeException("User not found"));
        userRepo.save(user);
        if(user.getBalance()>0){
            User temp=userRepo.findByUsername(user.getUsername());
            Transaction t=new Transaction();
            t.setUserId(temp.getId());
            t.setAmount(temp.getBalance());
            t.setCurrBalance(temp.getBalance());
            t.setDescription("Rs "+temp.getBalance()+"Mode: Deposit");
            transactionRepo.save(t);

        }
        history.setDescription("Admin "+adminId+" "+admin.getUsername()+" created user "+user.getUsername());
        historyRepo.save(history);

        return "Add successfull!";
    }
    @GetMapping("/users")
    public List<User> getUser(@RequestParam  String sortBy, @RequestParam String order){
        Sort sort;
        if(order.equals("desc")){
            sort=Sort.by(sortBy).descending();
        }
        else{
            sort=Sort.by(sortBy).ascending();
        }
        return userRepo.findByRole("customer",sort);
    }
    @GetMapping("/users/{keyword}")
    public List<User> getUsername(@PathVariable String keyword, @RequestParam String sortBy, @RequestParam String order){
        return userRepo.findByUsernameContainingIgnoreCaseAndRole(keyword,"customer");
    }
    @DeleteMapping("delete-user/{userId}/admin/{adminId}")
    public String delete(@PathVariable Integer userId, @PathVariable Integer adminId){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getBalance()>0){
            return "Account Khali kar bhai/behen";
        }
        History history=new History();
        User admin=userRepo.findById(adminId).orElseThrow(()->new RuntimeException("User not found"));
        history.setDescription("Admin #ID"+adminId+" "+admin.getUsername()+" deleted user "+user.getUsername());
        userRepo.delete(user);
        historyRepo.save(history);
        return " User Deletion successfully!";
    }

}
