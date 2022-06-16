package com.loginregister.logincodejavanet.restaurantdetails;

import com.loginregister.logincodejavanet.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
