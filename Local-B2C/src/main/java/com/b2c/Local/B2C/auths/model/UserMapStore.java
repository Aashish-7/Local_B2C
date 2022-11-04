package com.b2c.Local.B2C.auths.model;

import com.b2c.Local.B2C.products.electronic.model.LocalStore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_map_store")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMapStore {

    @Id
    private String userStoreId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    private LocalStore localStores;

}
