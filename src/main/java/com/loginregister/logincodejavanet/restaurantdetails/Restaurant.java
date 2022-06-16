package com.loginregister.logincodejavanet.restaurantdetails;

import com.loginregister.logincodejavanet.RestaurantDishes.Menu;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Menu> menuSet;

    @Column(name = "name")
    private String name;

//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "menu",
//            joinColumns = @JoinColumn(name = "restaurant_id"),
//            inverseJoinColumns = @JoinColumn(name = "menu_id"))

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "restaurant_id")

//    @OneToOne(mappedBy = "restaurant")
//    private Menu menu;
//
//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    private Menu restaurant_id;
//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinColumn(name="restaurant_id")
//    private Set<Menu> menuSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
