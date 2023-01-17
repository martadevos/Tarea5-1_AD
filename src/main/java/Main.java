import Entidades.MedicamentosEntity;
import Entidades.PacientesEntity;
import Entidades.RecetasEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int resp;
        boolean salir = false;
        while (!salir) {
            System.out.println("1.- Create\n" +
                    "2.- Read\n" +
                    "3.- Update\n" +
                    "4.- Delete\n" +
                    "5.- Salir");
            resp = s.nextInt();
            CRUD.open();
            salir = menu(resp);
            CRUD.close();
        }
    }

    public static boolean menu(int resp) {
        Scanner s = new Scanner(System.in);
        boolean salir = false;
        int tipo = pedirTipo();
        switch (resp) {
            case 1 -> {
                System.out.println("Se van a pedir los datos datos para insertar");
                Object objeto = rellenarObjeto(tipo);
                CRUD.create(objeto);
            }
            case 2 -> {
                System.out.println("introduce el id del objeto que deseas buscar");
                CRUD.read(s.nextInt(),1);
            }
            case 3 -> {
                System.out.println("Se van a pedir los datos datos para actualizar");
                Object objeto = rellenarObjeto(tipo);
                CRUD.update(objeto);
            }
            case 4 -> {
                System.out.println("Se van a pedir los datos datos para borrar");
                Object objeto = rellenarObjeto(tipo);
                CRUD.delete(objeto);
            }
            default -> salir = true;
        }
        return salir;
    }

    public static int pedirTipo() {
        Scanner s = new Scanner(System.in);
        int opc = 0;
        boolean correcto = false;
        while (!correcto) {
            System.out.println("Introduce el número correspondiente al tipo de ojeto con el que deseas interactuar: \n" +
                    "1.- Medicamento\n" +
                    "2.- Paciente\n" +
                    "3.- Receta");
            opc = s.nextInt();
            if (opc < 1 || opc > 3){
                correcto = false;
                System.out.println("Error: Elija un número entre 1 y 3");
            }else correcto = true;
        }
        return opc;
    }

    public static Object rellenarObjeto(int tipo) {
        Scanner s = new Scanner(System.in);
        Object objeto = new Object();
        switch (tipo) {
            case 1 -> {
                int idMedicamento;
                String composicion;
                System.out.println("introduce el id del medicamento");
                idMedicamento = s.nextInt();
                System.out.println("introduce la composición del medicamento");
                composicion = s.next();
                objeto = new MedicamentosEntity(idMedicamento, composicion);
            }
            case 2 -> {
                int idPaciente;
                String nombre, apellidos, nss;
                System.out.println("introduce el id del paciente");
                idPaciente = s.nextInt();
                System.out.println("introduce el nombre del paciente");
                nombre = s.next();
                System.out.println("introduce los apellidos del paciente");
                apellidos = s.next();
                System.out.println("introduce el nss del paciente");
                nss = s.next();
                objeto = new PacientesEntity(idPaciente, nombre, apellidos, nss);
            }
            case 3 -> {
                int idReceta, idMedicamento, idPaciente;
                Date fechaFin = new Date();
                boolean seguir = false;
                System.out.println("introduce el id de la receta");
                idReceta = s.nextInt();
                System.out.println("introduce el id del medicamento a recetar");
                idMedicamento = s.nextInt();
                System.out.println("introduce el id del paciente que recibirá la receta");
                idPaciente = s.nextInt();
                do {
                    System.out.println("introduce el la fecha de fin de validez de la receta con el siguiente formato: YY/MM/DD");
                    try {
                        fechaFin = new SimpleDateFormat("yy/MM/dd").parse(s.next());
                    } catch (ParseException e) {
                        seguir = true;
                        System.out.println("Error al introducir la fecha, vuelva a intentarlo");
                    }
                }while(seguir);
                objeto = new RecetasEntity(idReceta, (MedicamentosEntity) CRUD.read(idMedicamento, 1), (PacientesEntity) CRUD.read(idPaciente, 2), (java.sql.Date) fechaFin);
            }
        }
        return objeto;
    }
}
