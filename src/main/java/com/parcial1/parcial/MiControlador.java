import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Indica que esta clase maneja rutas HTTP
@RequestMapping  // Opcional, se usa para organizar las rutas
public class MiControlador {

    @GetMapping("/publico") // Ruta accesible sin autenticación
    public String publico() {
        return "Este es un contenido público.";
    }

    @GetMapping("/privado") // Ruta que requerirá autenticación
    public String privado() {
        return "Este es un contenido privado. Solo puedes verlo si estás autenticado.";
    }
}