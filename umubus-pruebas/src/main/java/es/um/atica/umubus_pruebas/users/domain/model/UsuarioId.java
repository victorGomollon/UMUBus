package es.um.atica.umubus_pruebas.users.domain.model;

import java.util.Objects;
import java.util.UUID;

public class UsuarioId {
    private UUID value;
    private UsuarioId(UUID value) { this.value = value; }
    public static UsuarioId of(String value) {
        Objects.requireNonNull(value, "User Id could not be null!");
        return new UsuarioId(UUID.fromString(value));
    }
    public static UsuarioId randomId() {
        return UsuarioId.of(UUID.randomUUID().toString());
    }
    public String getValue() { return value.toString(); }    
}
