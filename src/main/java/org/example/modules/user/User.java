package org.example.modules.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String gender;

    @Override
    public String toString() {
        return "User [id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", age=" + age +
                ", gender=" + gender + "]";
    }
}
