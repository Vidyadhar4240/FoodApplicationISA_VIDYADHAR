package com.loginregister.logincodejavanet.RestaurantDishes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
//    @Query("SELECT m FROM restaurant r join r.menu")
    public static final String FIND_MENU = "SELECT m.* FROM Menu m, Restaurant r WHERE m.restaurant_id = r.restaurant_id AND m.restaurant_id = ?";
    @Query(value = FIND_MENU, nativeQuery = true)
    public List<Menu> findAllByRestaurantId(Long restaurant_id);
}
