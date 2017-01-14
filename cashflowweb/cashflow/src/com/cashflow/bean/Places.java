package com.cashflow.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase en la que mapearemos los sitios con la dirección a la cual apuntarán,
 * así si se hace un cambio en la ubicación del xhtml, no sea necesario
 * cambiar la dirección en el código desde donde se hace el include, 
 * sino que modificando esta clase, se hará el respectivo redireccionamiento
 * @author fabio
 *
 */
public class Places {
	
	public static Places instance;
	private Map<String, String> mapPlaces;
	
	public static Places getInstance() {
		if (instance == null) {
			instance = new Places();
		}
		return instance;
	}
	 
	public Places() {
		mapPlaces = new HashMap<String, String>();
		mapPlaces.put("movimientos", "/views/movimiento/movimientos.xhtml");
		mapPlaces.put("nuevomovimiento", "/views/movimiento/nuevomovimiento.xhtml");
		mapPlaces.put("movimientosList", "/views/movimiento/movimientosList.xhtml");
		mapPlaces.put("modal", "/widgets/popups/modal.xhtml");
		
	}

	public Map<String, String> getMapPlaces() {
		return mapPlaces;
	}

	public void setMapPlaces(Map<String, String> mapPlaces) {
		this.mapPlaces = mapPlaces;
	}

	public String get(String page) {
		return mapPlaces.get(page);
	}
} 
