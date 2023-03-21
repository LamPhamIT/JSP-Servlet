package com.shinn.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account extends AbstractModel {
    private String userName;
    private String password;
    private Long roleID;

}