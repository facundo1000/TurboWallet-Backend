package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Debería encontrar usuario por email")
    void shouldFindUserByEmail() {
        // Given
        Usuario usuario = Usuario.builder()
                .nombre("Carlos")
                .apellido("García")
                .email("carlos@email.com")
                .contrasenia("password123")
                .build();

        entityManager.persistAndFlush(usuario);

        // When
        Optional<Usuario> found = usuarioRepository.findByEmail("carlos@email.com");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Carlos");
        assertThat(found.get().getApellido()).isEqualTo("García");
        assertThat(found.get().getEmail()).isEqualTo("carlos@email.com");
    }

    @Test
    @DisplayName("Debería retornar Optional vacío cuando email no existe")
    void shouldReturnEmptyOptionalWhenEmailNotExists() {
        // When
        Optional<Usuario> found = usuarioRepository.findByEmail("noexiste@email.com");

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Debería guardar usuario correctamente")
    void shouldSaveUserCorrectly() {
        // Given
        Usuario usuario = Usuario.builder()
                .nombre("Ana")
                .apellido("López")
                .email("ana@email.com")
                .contrasenia("password456")
                .build();

        // When
        Usuario saved = usuarioRepository.save(usuario);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getIdUsuario()).isNotNull();
        assertThat(saved.getNombre()).isEqualTo("Ana");
        assertThat(saved.getEmail()).isEqualTo("ana@email.com");
    }
}