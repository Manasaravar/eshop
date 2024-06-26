package com.korniushin.eshop.model.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    public static final String SEQ_NAME = "category_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private long id;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
        products = new HashSet<>();
    }
}