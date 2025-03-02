package parcial1.parcial1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiControlador {

    @GetMapping("/publico")
    public String publico() {
        return "Este es un contenido público.";
    }

    @GetMapping("/privado")
    public String privado() {
        return "Este es un contenido privado, solo accesible para usuarios autenticados.";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Este contenido es solo para administradores.";
    }
}
