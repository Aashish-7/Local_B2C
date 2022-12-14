package com.b2c.Local.B2C.common.model;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wishlist_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishlistProduct {
    
    @Id
    private UUID id = UUID.randomUUID();
    
    private Boolean deleted = false;
    
    private LocalDateTime createDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private ProductEnum product;

    private Long productId;
    
    @ManyToOne @JsonIgnore
    private User user;

    @Transient
    private Object products;

}
