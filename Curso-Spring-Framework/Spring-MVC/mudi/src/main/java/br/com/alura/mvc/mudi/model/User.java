package br.com.alura.mvc.mudi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true) // Serve para mapear o lado fraco do relacionamento e também fazer uma relação bidirecional
    private List<Pedido> pedidos;

    

    public User(String username, String password, Boolean enabled, List<Pedido> pedidos) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.pedidos = pedidos;
    }
    
    public User() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
