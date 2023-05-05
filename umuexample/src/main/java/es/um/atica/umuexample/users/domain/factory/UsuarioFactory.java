package es.um.atica.umuexample.users.domain.factory;

import es.um.atica.umuexample.users.domain.model.Usuario;

public class UsuarioFactory {

    public static Usuario createUser(String id, String name, int age) {
        return Usuario.of(id, name, age);
    }
}
