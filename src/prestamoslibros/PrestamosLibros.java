/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prestamoslibros;

/**
 *
 * @author Usuario
 */
public class PrestamosLibros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Libro[] libros = new Libro[9];
        for (int i = 0; i < libros.length; i++) {
            libros[i] = new Libro(i + 1);
        }

        Thread[] estudiantes = new Thread[4];
        for (int i = 0; i < estudiantes.length; i++) {
            estudiantes[i] = new Thread(new Estudiante(i + 1, libros));
            estudiantes[i].start();
        }
    }
    
}
