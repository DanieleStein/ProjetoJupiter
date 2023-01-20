package br.com.projetojupiter.security

import br.com.projetojupiter.model.Criador
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CriadorDetails (criador: Criador): UserDetails {

    val email: String = criador.email
    val senha: String = criador.senha

    private val serialVersionUID = 1L

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String? {
        return this.senha
    }

    override fun getUsername(): String? {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}