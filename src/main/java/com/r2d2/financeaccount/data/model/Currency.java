package com.r2d2.financeaccount.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Currency {
    @Id
    @Column(length = 3)
    private String code;

    @Column(length = 40)
    private String humanReadableName;

}
