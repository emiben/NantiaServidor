package com.nantia.model;

public class Vendedor extends Usuario {

	private static final long serialVersionUID = 5588303785690284901L;
	
	private Caja caja;
	
	protected Vendedor() {
		super();
	}

	public Vendedor(Caja caja) {
		super();
		this.caja = caja;
	}
	
//	public Vendedor(String usuario, String nombre, String apellido, int rol, String contrasenia, Caja caja) {
//		super.setUsuario(usuario);
//		super.
//	}
	
	public long getId() {
		return super.getId();
	}

	public void setId(long id) {
		super.setId(id);
	}

	public String getUsuario() {
		return super.getUsuario();
	}

	public void setUsuario(String usuario) {
		super.setUsuario(usuario);
	}

	public String getNombre() {
		return super.getNombre();
	}

	public void setNombre(String nombre) {
		super.setNombre(nombre);
	}

	public String getApellido() {
		return super.getApellido();
	}

	public void setApellido(String apellido) {
		super.setApellido(apellido);
	}

	public int getRol() {
		return super.getRol();
	}

	public void setRol(int rol) {
		super.setRol(rol);
	}

	public String getContrasenia() {
		return super.getContrasenia();
	}

	public void setContrasenia(String contrasenia) {
		super.setContrasenia(contrasenia);
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	

}
