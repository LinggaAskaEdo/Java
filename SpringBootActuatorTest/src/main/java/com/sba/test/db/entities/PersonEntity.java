package com.sba.test.db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PERSON")
@NoArgsConstructor
public class PersonEntity implements Serializable
{
    private static final long serialVersionUID = -8003246612943943723L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int personId;

    private String firstName;
    private String lastName;
    private String email;

    public PersonEntity( String firstName, String lastName, String email)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public PersonEntity(int personId, String firstName, String lastName, String email)
    {
        super();
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getPersonId()
    {
        return personId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }
}