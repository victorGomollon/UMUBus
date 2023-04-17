package es.um.atica.umubus_pruebas.users.domain.factory;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;

public class UsuarioFactory {

    public static Usuario createUser(String id, String name, int age) {
        return Usuario.of(id, name, age);
    }
}
