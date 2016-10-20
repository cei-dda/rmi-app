package net.franciscovillegas.cei.obligatorio.server.motor_reglas;

import net.franciscovillegas.cei.obligatorio.server.motor_reglas.ReglasFactory.Opcion;

public interface Regla {
	public void accion(OpcionesParaJugador opcionesParaJugador, Opcion jugadaDelCliente);
}
