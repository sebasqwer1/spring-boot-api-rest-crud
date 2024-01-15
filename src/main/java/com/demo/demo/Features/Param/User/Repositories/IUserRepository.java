package com.demo.demo.Features.Param.User.Repositories;

import com.demo.demo.Entities.UserModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findAll(Specification<UserModel> spec);
}
