Proyecto: Seguridad en Spring Boot con Spring Security

Este proyecto implementa autenticación y autorización en una aplicación Spring Boot utilizando Spring Security. Se han configurado rutas protegidas, usuarios con roles y seguridad con BCryptPasswordEncoder.

Tecnologías Utilizadas

Java 17/21 (Evitar Java 23 por incompatibilidad)

Spring Boot 3.4.2

Spring Security

Maven

Instalación y Configuración

1️⃣ Clonar el Repositorio

git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>

2️⃣ Configurar Java 17 o 21

Asegúrate de tener Java 17 o 21 instalado y configurado:

java -version

Si aparece Java 23, cambia a Java 17/21.

3️⃣ Ejecutar la Aplicación

mvn spring-boot:run

La aplicación se iniciará en http://localhost:8080.

Endpoints Disponibles

Ruta

Tipo

Autenticación

Usuario

Contraseña

/publico

GET

No requerida

-

-

/privado

GET

Requerida

usuario

contraseña

/admin

GET

Solo Admin

admin

admin123

Pruebas en el Navegador

Abrir http://localhost:8080/publico (Debe mostrar contenido sin login).

Ir a http://localhost:8080/privado, ingresar usuario / contraseña.

Ir a http://localhost:8080/admin, ingresar admin / admin123.

Pruebas con Postman

Método: GET

URL: http://localhost:8080/privado o http://localhost:8080/admin

Authorization: Basic Auth

Ingresar credenciales según la ruta.

Pruebas con cURL

curl -u usuario:contraseña http://localhost:8080/privado
curl -u admin:admin123 http://localhost:8080/admin

Configuración de Seguridad

1️⃣ Definición de Usuarios en Memoria

Los usuarios están definidos en SeguridadConfig.java:

@Bean
public UserDetailsService userDetailsService() {
    UserDetails usuario = User.withUsername("usuario")
        .password(passwordEncoder().encode("contraseña"))
        .roles("USER")
        .build();

    UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder().encode("admin123"))
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(usuario, admin);
}

2️⃣ Rutas Protegidas y Autorización

http
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/publico").permitAll()
        .requestMatchers("/privado").hasRole("USER")
        .requestMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated()
    )
    .formLogin()
    .and()
    .httpBasic()
    .and()
    .csrf().disable(); // Deshabilitado para pruebas API

Deslogueo y Cambio de Usuario

En Navegador:

Modo incógnito para probar otro usuario.

Cerrar sesión manualmente en la configuración del navegador.

En Postman:

Ir a Cookies y eliminar las de localhost.

Cambiar las credenciales en Basic Auth y reenviar la solicitud.

En cURL:

curl -u nuevo_usuario:nueva_contraseña http://localhost:8080/privado

Contribuir

Hacer un fork del repositorio.

Crear una nueva rama (git checkout -b feature-nueva).

Hacer commit y push (git push origin feature-nueva).

Crear un Pull Request.

📜 Licencia

Este proyecto es de código abierto y puede ser utilizado y modificado libremente.
