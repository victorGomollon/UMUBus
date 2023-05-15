package es.um.atica.umuexample.usuarios.domain.factory;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;

public class UsuarioFactory {

    public static Usuario createUsuario(String id, String name, int age) {
        return Usuario.of(id, name, age);
    }
}
