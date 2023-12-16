package ex2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Shell {

    private static List<String> readFile(String filename ) {
        List<String> lines = null;

        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get( filename );
            lines = Files.readAllLines(path, ENCODING);
        }
        catch (IOException ex) {
            System.out.println("Erro no acesso ao ficheiro...");
            System.exit( 0 );
        }
/*
		for (int i = 0; i < lines.size(); i++) {
			System.err.println("Linha n." + i + ": " + lines.get(i));
		}
*/
        return lines;
    }

    public static void main(String[] args) throws IOException {
        if ( args.length != 1 ) {
            System.err.println( "Modo de uso: java ex2.Shell <diretório do ficheiro com comandos>" );
            System.exit( 0 );
        }

        List<String> lines = readFile( args[0] );

        Process[] process = new Process[ lines.size() ];

        for (int i = 0; i < lines.size(); i++) {
            try {
                ProcessBuilder pb = new ProcessBuilder( lines.get(i) );	// public Process start() throws IOException
                pb.inheritIO();
                process[i] = pb.start();	// public Process start() throws IOException

                System.out.println( "Início do comando \"" + lines.get(i) + "\"." );

            } catch (IOException iex) {

            }
        }

        for (int i = 0; i < lines.size(); i++) {
            try {
                process[i].waitFor();	// throws InterruptedException
                System.out.println( "Fim do comando \"" + lines.get(i) + "\"." );
            } catch ( InterruptedException iex ) {

            }
        }
    }
}
