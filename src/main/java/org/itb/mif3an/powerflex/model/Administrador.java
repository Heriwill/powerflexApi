package org.itb.mif3an.powerflex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "administrador")
public class Administrador  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 100)
    private String login;
    @Column(nullable = false, length = 1000)
    private String password;
    @Column(nullable = false, length = 100)
    private UserRole role;
    @Column(nullable = false, length = 10)
    private boolean codStatus;

    public Administrador(String nome,String login, String password, UserRole role){
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    // Atributos de apoio

    // @Transient -> representa um atributo que NÃO CORRESPONDE A UMA COLUNA DA TABELA

    @Transient
    @JsonIgnore
    private String mensagemErro = "";
    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Administrador() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        else return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador administrador = (Administrador) o;
        return Objects.equals(id, administrador.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
