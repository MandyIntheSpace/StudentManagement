package com.example.studentmanagement.Repository;


import com.example.studentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = " SELECT * FROM SYSTEM_USER_LOGIN S WHERE S.USERNAME = :userName", nativeQuery = true)
    User findByUserName(String userName);

}
