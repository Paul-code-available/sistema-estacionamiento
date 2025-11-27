package interfaz;

import logica.vehiculos.Vehiculo;

public class Estacionamiento {

    private int filas;
    private int columnas;
    private String vistaEstacionamiento[][];
    private double espacioEstacionamiento[][];

    public Estacionamiento(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        vistaEstacionamiento = new String[filas][columnas];
        espacioEstacionamiento = new double[filas][columnas];
    }

    public void inicializarEstacionamiento() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                vistaEstacionamiento[i][j] = " ";
                espacioEstacionamiento[i][j] = 0;
            }
        }
    }
    // falta ver si puedo ingresar dos objetos en la misma posicion
    public String estacionarVehiculo(Vehiculo vehiculo, int fila, int columna) {
        /*
            si el espacio requerido por el vehiculo es 0.5 y el espacio disponible del estacionamiento es
            menor o igual a 0.5 sumanos y asignamos el espacio requerido por el vehiculo al espacio total
            del estacionamiento.
            el espacio maximo por lugar de estacionamiento es 1, es decir; 1 carro o 2 motos.
            si el espacio total del estacionamiento es 0.5 asignamos una 'M' de moto a la vista
            del estacionamiento que significa que aún hay espacio para otra moto y si es igual a
            1 una 'X' que significa que el espacio ya esta lleno.
         */

        if (espacioEstacionamiento[fila][columna] == 1) {
            throw new IllegalStateException("El espacio ya esta ocupado.");
        }

        if (vehiculo.getEspacioRequerido() <= 0.5 && espacioEstacionamiento[fila][columna] <= 0.5) {
            espacioEstacionamiento[fila][columna] += vehiculo.getEspacioRequerido();

            if (espacioEstacionamiento[fila][columna] == 0.5) {
                vistaEstacionamiento[fila][columna] = "M";
                return "Moto estacionada, hay espacio para una más";
            }
            if (espacioEstacionamiento[fila][columna] == 1) {
                vistaEstacionamiento[fila][columna] = "X";
                return "Vehiculo estacionado, espacio lleno";
            }
        }
        return "No se pudo estacionar e vehiculo.";
    }

    public void verVistaEstacionamiento() {
        System.out.println("\t\t----------Estacionamiento----------");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(i + "," +  j);
                System.out.print("[" + vistaEstacionamiento[i][j] + "] ");
            }
            System.out.println();
        }
    }

    public void verEspacioEstacionamiento() {
        System.out.println("\t\t----------Estacionamiento----------");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(i + "," +  j);
                System.out.print("[" + espacioEstacionamiento[i][j] + "] ");
            }
            System.out.println();
        }
    }

}
