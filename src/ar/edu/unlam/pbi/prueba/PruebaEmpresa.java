package ar.edu.unlam.pbi.prueba;

import java.util.Scanner;

import ar.edu.unlam.pbi.domain.Empresa;
import ar.edu.unlam.pbi.domain.Operario;

public class PruebaEmpresa {

	private static Empresa empresa;
	private static Scanner teclado = new Scanner(System.in);
	private static final int CANTIDAD_OPERARIOS = 2;
	private static final int CANTIDAD_DIAS_LABORABLES = 2;
	
	private static final int INGRESAR_OPERARIOS = 1;
	private static final int BUSCAR_EMPLEADO_CON_MAYOR_ANTIGUEDAD = 2;
	private static final int CALCULAR_HORAS_DE_TRABAJO_PROMEDIO = 3;
	private static final int SALIR = 99;
	
	public static void main(String[] args) {
		
		empresa = new Empresa(CANTIDAD_OPERARIOS, CANTIDAD_DIAS_LABORABLES);
		
		manejadorInterfaz();
	}
	
	private static void manejadorInterfaz() {
		int opcionIngresada=0;
		
		do {
			mostrarMenuOpciones();
			opcionIngresada = teclado.nextInt();
			ejecutarMenu(opcionIngresada);
		}while(opcionIngresada!=SALIR);
	}
	
	private static void mostrarMenuOpciones() {
		System.out.println("Menu de opciones");
		System.out.println("1. Ingresar operarios");
		System.out.println("2. Buscar operario con mayor antiguedad");
		System.out.println("3. Calcular horas de trabajo promedio");
		System.out.println("99. Salir");
	}

	private static void ejecutarMenu(int opcionSeleccionada) {
		switch(opcionSeleccionada){
		case INGRESAR_OPERARIOS:
			ingresarOperarios();
			break;
		case BUSCAR_EMPLEADO_CON_MAYOR_ANTIGUEDAD:
			buscarOperarioConMayorAntiguedad();
			break;
		case CALCULAR_HORAS_DE_TRABAJO_PROMEDIO:
			calcularHorasDeTrabajoPromedio();
			break;
		default:
			break;
		}
	}
	
	private static void ingresarOperarios() {
		Operario nuevoOperario;
		
		int legajo;
		String valorIngresado, nombre, apellido, fechaIngreso;
		
		for(int i = 0; i<CANTIDAD_OPERARIOS; i++) {
			do {
				System.out.println("Ingrese legajo");
				valorIngresado = teclado.next();
			}while(valorIngresado=="");
			
			legajo = Integer.parseInt(valorIngresado);
			
			do {
				System.out.println("Ingrese nombre");
				valorIngresado = teclado.next();
			}while(valorIngresado=="");
			
			nombre = valorIngresado;
			
			do {
				System.out.println("Ingrese apellido");
				valorIngresado = teclado.next();
			}while(valorIngresado=="");
			
			apellido = valorIngresado;
			
			System.out.println("Ingrese fecha ingreso");
			valorIngresado = teclado.next();
			
			fechaIngreso = valorIngresado;
			
			if(fechaIngreso == "") {
				nuevoOperario = new Operario(legajo, nombre, apellido, CANTIDAD_DIAS_LABORABLES);
			}
			else {
				nuevoOperario = new Operario(legajo, nombre, apellido, CANTIDAD_DIAS_LABORABLES, fechaIngreso);
			}
			
			String fecha = "";
			String horaIngresada = "";

			for(int j=0; j<CANTIDAD_DIAS_LABORABLES; j++) {
				System.out.println("Ingrese el día (dd/mm/aaaa): ");
				fecha = teclado.next();
				for(int k=0; k<Operario.getCantidadOperarios(); k++) {
					nuevoOperario.setFechaLaboral(j, fecha);
					System.out.println("Ingrese hora ingreso de " + nuevoOperario.toString() + ":");
					horaIngresada = teclado.next();
					nuevoOperario.setHoraIngreso(j, horaIngresada);
					System.out.println("Ingrese hora salida de " + nuevoOperario.toString() + ":");
					horaIngresada = teclado.next();
					nuevoOperario.setHoraSalida(j, horaIngresada);
				}
			}
			
			empresa.nuevoOperario(nuevoOperario);
		}
	}

	private static void buscarOperarioConMayorAntiguedad() {
		String fechaActual = "";
		Operario operarioConMayorAntiguedad;
		
		do {
			System.out.println("Ingrese fecha actual:");
			fechaActual = teclado.next();
		}while(fechaActual=="");
		
		operarioConMayorAntiguedad = empresa.buscarOperarioConMayorAntiguedad(fechaActual);
		
		System.out.println("El operario con mas antiguedad es: " + operarioConMayorAntiguedad.toString() + " con " + operarioConMayorAntiguedad.calcularAntiguedad(fechaActual) + " años.");
	}
	
	private static void calcularHorasDeTrabajoPromedio() {
		
		int horasDeTrabajoPromedio = empresa.calcularHorasDeTrabajoPromedio();

		System.out.println("El promedio de horas trabajadas es: " + horasDeTrabajoPromedio);
	}
}
