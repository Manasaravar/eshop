package com.korniushin.eshop.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_position")
public class OrderPosition {
    public static final String SEQ_NAME = "order_position_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "order_id")
    private Order order;

    @Column (name = "order_quantity")
    private Integer orderQuantity;
}
