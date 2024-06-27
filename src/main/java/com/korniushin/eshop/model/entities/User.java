package com.korniushin.eshop.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "users")
public class User implements UserDetails {
    public static final String SEQ_NAME = "user_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean archive;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    //@JoinColumn(name = "order_id")
    private Set<Order> orders;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.orders = new HashSet<>();
        Order order = Order.builder()
                .user(this)
                .address("")
                .created(LocalDateTime.now())
                .orderStatus(OrderStatus.CART)
                .updated(LocalDateTime.now())
                .totalPrice(0.0)
                .totalQuantity(0)
                .productsPositions(new HashSet<>())
                .build();
        orders.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>() {{add(new SimpleGrantedAuthority(role.name()));}};
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
