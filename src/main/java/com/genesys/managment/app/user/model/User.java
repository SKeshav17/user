package com.genesys.managment.app.user.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "users",uniqueConstraints= {@UniqueConstraint(columnNames={"email"})})
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotBlank(message = "First Name is mandatory")
	@Size(min = 3, max=20)
    private String firstName;
    @Column
    @NotBlank(message = "Last Name is mandatory")
	@Size(min = 3, max=20)
    private String lastName; 
    @Column
    @NotBlank(message = "Email is mandatory")
	@Size(min = 3, max=20)
    private String email;
    @Column
	@JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    @Column
    @JsonIgnore
    private boolean active;
    
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;
    @Column
    private Date lastLoggedInDate;

    }
