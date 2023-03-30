package es.um.atica.umubus_pruebas.users.domain.factory;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_pruebas.users.domain.model.UsuarioId;

public class UsuarioFactory {

    public static Usuario createUser(UsuarioId id, String name, int age) {
        return Usuario.of(id, name, age);
    }
}
