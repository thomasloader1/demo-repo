package ar.edu.unlam.pbi.domain;

import java.util.Scanner;

import ar.edu.unlam.pbi.util.Fecha;
import ar.edu.unlam.pbi.util.Hora;

public class Empresa {
	
	private Operario operarios[];
	private int cantidadDiasLaborables;
	
	public Empresa(int cantidadOperarios, int cantidadDiasLaborables) {
		operarios = new Operario[cantidadOperarios];
		this.cantidadDiasLaborables = cantidadDiasLaborables; 
	}
	
	public void nuevoOperario(Operario nuevoOperario) {			
			operarios[Operario.getCantidadOperarios()-1] = nuevoOperario;
	}
	
	public Operario buscarOperarioConMayorAntiguedad(String fechaActual) {
		int mayorAntiguedad=0, posicionMayorAntiguedad=-1;
		
		for(int i = 0; i<Operario.getCantidadOperarios(); i++) {
			if(operarios[i].calcularAntiguedad(fechaActual)>mayorAntiguedad) {
				mayorAntiguedad = operarios[i].calcularAntiguedad(fechaActual);
				posicionMayorAntiguedad = i;
			}
		}
		
		return operarios[posicionMayorAntiguedad];
	}
	
	public int calcularHorasDeTrabajoPromedio() {
		int sumatoriaHoras = 0;
		
		for(int i=0; i<Operario.getCantidadOperarios(); i++) {
			sumatoriaHoras+=operarios[i].calcularHorasTrabajadas();
		}
		
		return sumatoriaHoras/Operario.getCantidadOperarios();
	}
}
