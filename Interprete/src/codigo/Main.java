package codigo;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String ruta="C:/Users/sergi/IdeaProjects/Interprete/src/codigo/Lexer.flex";//Aqui se pone el directorio donde esta el proyecto
        generarLexer(ruta);

    }
    public static void generarLexer(String ruta){
        File archivo= new File(ruta);
        JFlex.Main.generate(archivo);
    }

}
