package com.widson.chalengeinc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widson.chalengeinc.models.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer>{

}
