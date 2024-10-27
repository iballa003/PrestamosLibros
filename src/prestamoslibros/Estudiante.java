/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prestamoslibros;

import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Estudiante implements Runnable {
    private final int id;
    private final Libro[] libros;
    private final Random random = new Random();

    public Estudiante(int id, Libro[] libros) {
        this.id = id;
        this.libros = libros;
    }

    private void utilizarLibro(Libro libro) throws InterruptedException {
        System.out.println("Estudiante " + id + " está utilizando " + libro);
        int tiempoUso = (random.nextInt(3) + 1) * 60;  // Tiempo de uso en minutos (1 a 3 horas)
        Thread.sleep(tiempoUso);  // Simula el uso del libro
        System.out.println("Estudiante " + id + " ha terminado de usar " + libro);
    }

    private void descansar() throws InterruptedException {
        int tiempoDescanso = (random.nextInt(2) + 1) * 60;  // Tiempo de descanso en minutos (1 a 2 horas)
        System.out.println("Estudiante " + id + " está descansando por " + tiempoDescanso + " minutos.");
        Thread.sleep(tiempoDescanso);
    }

    @Override
    public void run() {
        while (true) {
            int primerLibroIndex = random.nextInt(libros.length);
            int segundoLibroIndex = random.nextInt(libros.length);

            // Aseguramos que los índices de los libros sean diferentes
            while (primerLibroIndex == segundoLibroIndex) {
                segundoLibroIndex = random.nextInt(libros.length);
            }

            Libro primerLibro = libros[primerLibroIndex];
            Libro segundoLibro = libros[segundoLibroIndex];

            try {
                synchronized (primerLibro) {
                    System.out.println("Estudiante " + id + " ha tomado " + primerLibro);
                    synchronized (segundoLibro) {
                        System.out.println("Estudiante " + id + " ha tomado " + segundoLibro);
                        utilizarLibro(primerLibro);
                        utilizarLibro(segundoLibro);
                    }
                }
                descansar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
