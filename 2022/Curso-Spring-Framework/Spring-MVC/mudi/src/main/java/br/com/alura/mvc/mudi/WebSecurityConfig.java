package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// A forma mais atual de se fazer config. Configuration denota que esta classe irá criar beans de definições. Também pode ser usada para configurar um dataSource.
@Configuration
@EnableWebSecurity // Junto com a annotation Configuration, essa annotation diz para o Spring que esta é uma classe de configuração de segurança web, conforme os códigos abaixo
public class WebSecurityConfig{

    @Autowired
    private DataSource dataSource;

    @Autowired // Autowired para funções não retornáveis, Bean para funções retornaveis. Função para acessar o banco de dados (Parte comentada serve para adicionar um usuário)
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // UserDetails user = User
        //         .withUsername("maria")
        //         .password(encoder.encode("maria"))
        //         .roles("USER")
        //         .build();

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(encoder);
           // .withUser(user);
    }

    @Bean // Função para permitir apenas usuários autenticados e mapear a tela de login, que deve ser criada nos templates ( também tem o httpBasuc que não necessita de criação, porém é muito básico). Também, há um endereço de logout que não necessita de página
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/home/**")
                .permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin((form) -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/usuario/pedido", true)
                    .permitAll()
            )
            .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home")
            )
            .csrf().disable();
            return http.build();
    }

    // @Bean // Criando usuário e salvando ele em memória. Precisa ter todos os campos preenchidos no build abaixo e é obrigatório ter um password encoder. Forma mais atual criando um bean
	// public UserDetailsService userDetailsService() {
	// 	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //     UserDetails user = User
    //         .withUsername("joao")
    //         .password(passwordEncoder.encode("joao"))
    //         .roles("USER")
    //         .build();

	// 	return new InMemoryUserDetailsManager(user);
	// }

    // @Bean // Até esta função precisa ser um bean do Spring
    // public PasswordEncoder PasswordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}
