package com.life_manager.life_manager.admin;

import com.life_manager.life_manager.admin.domain.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Admin {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String businessNumber;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "admin")
    private List<Post> postList=new ArrayList<>();

    public Admin(String accountId, String password, String name, String companyName,
                 String businessName, String address, String phoneNumber,
                 String businessNumber, String category, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.companyName = companyName;
        this.businessName = businessName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.category = category;
        this.role = role;
    }

    public void addPostList(Post post) {
        postList.add(post);
    }
}