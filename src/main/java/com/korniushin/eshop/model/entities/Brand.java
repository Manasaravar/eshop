package com.korniushin.eshop.model.entities;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//
//@Table(name = "brands")
//public class Brand {
//    public static final String SEQ_NAME = "brand_seq";
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
//    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
//    private long id;
//    private String title;
//    @OneToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//}
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.Setter;

        import java.util.HashSet;
        import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@Table(name = "brands")

public class Brand {
    public static final String SEQ_NAME = "brand_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Brand() {
        products = new HashSet<>();
    }
}
