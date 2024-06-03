package galeriacasasubasta;

import items.Button;
import items.MyTextField;
import items.Notification;
import items.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Autores;
import galeria.modelo.inventario.CentroAutores;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.OperacionSubasta;
import galeria.modelo.usuarios.ProcesoCompra;
import galeria.modelo.usuarios.RegistroInicio;
import galeria.modelo.usuarios.Usuarios;
import net.miginfocom.swing.MigLayout;

public class Principal extends javax.swing.JFrame {

	private JFrame frame = (JFrame) this;

	public Principal(String tipo, String usu, LoginMain inicio, Galeria galeria) {

		// Clases
		RegistroInicio sesion = galeria.getRegistro();
		OperacionSubasta subasta = galeria.getClaseSubasta();
		Inventario inventario = galeria.getInventario();
		ProcesoCompra cajero = galeria.getCajero();
		CentroAutores autores = galeria.getCentroAutores();

		// Persistencia
		inicio.persistenciaCargarAutores();
		inicio.persistenciaCargarInventario();
		inicio.persistenciaCargarVentas();

		initComponents();
		itemsMenu(tipo, sesion, inicio, inventario, galeria, cajero, subasta);
		setLocationRelativeTo(null);

		// Panel principal
		paneles.setOpaque(false);
		// Panel Menu Botones
		items.setOpaque(false);
		// Texto Header
		rol.setText(tipo);
		usuario.setText(usu);
		nombre.setVisible(true);
		// Panel Con Tablas
		listaU.setOpaque(true);
		listaU.setVisible(false);
		// Mensaje Inicio
		bienvenida.setVisible(true);
		blogo.setVisible(true);
		bienvenida.setText("<html>Bienvenido, " + tipo + " a nuestra" + " Galería y Casa de Subastas<br></html>");
		// Tablas
		scrollTabla.setVisible(false);
		listaU.setVisible(false);
	}

	private void itemsMenu(String tipo, RegistroInicio sesion, LoginMain inicio, Inventario inventario, Galeria galeria,
			ProcesoCompra cajero, OperacionSubasta subasta) {

		if (tipo.equals("Administrador")) {

			items.setLayout(
					new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]push"));
			Button cmd2 = new Button();
			cmd2.setBackground(new Color(176, 132, 139));
			cmd2.setForeground(new Color(250, 250, 250));
			cmd2.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd2.setText("Aprobar Usuarios");
			items.add(cmd2, "w 202, h 40");

			cmd2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					aprobarUsuarios(sesion, inicio);
				}
			});

			Button cmd3 = new Button();
			cmd3.setBackground(new Color(72, 125, 171));
			cmd3.setForeground(new Color(250, 250, 250));
			cmd3.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd3.setText("Aprobar Piezas");
			items.add(cmd3, "w 202, h 40");

			cmd3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					aprobarPiezas(inventario, inicio);
				}
			});

			Button cmd4 = new Button();
			cmd4.setBackground(new Color(176, 132, 139));
			cmd4.setForeground(new Color(250, 250, 250));
			cmd4.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd4.setText("Aprobar Ofertas Piezas");
			items.add(cmd4, "w 202, h 40");

			cmd4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					piezasOfertas(inicio, galeria, cajero);
				}
			});

			Button cmd6 = new Button();
			cmd6.setBackground(new Color(72, 125, 171));
			cmd6.setForeground(new Color(250, 250, 250));
			cmd6.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd6.setText("Finalizar Subasta");
			items.add(cmd6, "w 202, h 40");

			cmd6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					finalizarSubasta(inicio, inventario, subasta);
				}
			});

			Button cmd7 = new Button();
			cmd7.setBackground(new Color(176, 132, 139));
			cmd7.setForeground(new Color(250, 250, 250));
			cmd7.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd7.setText("Historiales Comprador");
			items.add(cmd7, "w 202, h 40");

			cmd7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					historialComprador(galeria);
				}
			});

			Button cmd8 = new Button();
			cmd8.setBackground(new Color(72, 125, 171));
			cmd8.setForeground(new Color(250, 250, 250));
			cmd8.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd8.setText("Solicitudes Inversores");
			items.add(cmd8, "w 202, h 40");

			cmd8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					solicitudInversor(inicio, galeria);
				}
			});

			Button cmd9 = new Button();
			cmd9.setBackground(new Color(176, 132, 139));
			cmd9.setForeground(new Color(250, 250, 250));
			cmd9.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd9.setText("Devolver Piezas");
			items.add(cmd9, "w 202, h 40");

			cmd9.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					devolverPieza(inicio, galeria);
				}
			});

			Button cmd1 = new Button();
			cmd1.setBackground(new Color(72, 125, 171));
			cmd1.setForeground(new Color(250, 250, 250));
			cmd1.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd1.setText("Historiales Piezas");
			items.add(cmd1, "w 202, h 40");

			cmd1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					historialesPiezas(galeria);
				}
			});

			Button cmd10 = new Button();
			cmd10.setBackground(new Color(176, 132, 139));
			cmd10.setForeground(new Color(250, 250, 250));
			cmd10.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd10.setText("Historiales Artistas");
			items.add(cmd10, "w 202, h 40");

			cmd10.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					historialesArtistas(galeria);
				}
			});
			
			Button cmd11 = new Button();
			cmd11.setBackground(new Color(72, 125, 171));
			cmd11.setForeground(new Color(250, 250, 250));
			cmd11.setFont(new Font("SansSerif", Font.BOLD, 15));
			cmd11.setText("Ventas Por Año");
			items.add(cmd11, "w 202, h 40");

			cmd11.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GraficoVentas grafico = new GraficoVentas(inicio, galeria);
					grafico.setVisible(true);
				}
			});
		}
	}

	private void inicializacion() {

		bienvenida.setVisible(false);
		blogo.setVisible(false);
		listaU.setVisible(true);
		eliminar.setVisible(true);
		scrollTabla.setVisible(true);
		scrollTabla.setVerticalScrollBar(new ScrollBar());
		scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);
		scrollTabla.getViewport().setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		scrollTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		ingresoIndice.setText("");
	}

	private void aprobarUsuarios(RegistroInicio sesion, LoginMain inicio) {

		inicializacion();
		mensajeTabla.setText("Estos son los usuarios en espera de aprobación");
		opcion.setText("Aprobar Nuevos Usuarios");
		descripcion.setText("Ingresa el índice el usuario, si es inversor ingresa monto sin espacios con coma");
		botonAccion.setText("Aprobar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/aprobar1.png")));
		// Filas Tabla Usuario
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Nombre");
		tabla.addColumn("Usuario");
		tabla.addColumn("Rol");
		tabla.addColumn("Índice Aprobación");
		List<Usuarios> listaSolicitud = sesion.getSolicitud();
		int indiceL = -1;
		for (Usuarios usuarioL : listaSolicitud) {
			indiceL++;
			tabla.addRow(new Object[] { usuarioL.getNombre(), usuarioL.getUsuario(), usuarioL.getRol(), indiceL });
		}
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String[] partes = ingresoIndice.getText().split(",");
					int usuApro = Integer.parseInt(partes[0]);
					try {
						Usuarios usuA = sesion.getSolicitud().get(usuApro);
						sesion.agregarNuevosAprobados(usuA);
						sesion.rechazarSolicitud();
						if (usuA.getRol().equals("Inversor")) {
							if (partes.length == 2) {
								int montoApro = Integer.parseInt(partes[1]);
								sesion.crearHistoriales(usuA, montoApro);
							} else {
								sesion.crearHistoriales(usuA, 0);
							}
						}
						inicio.persistenciaSalvar();
						Notification alertUsu = new Notification(frame, Notification.Type.INFO,
								Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
						alertUsu.showNotification();
					} catch (IndexOutOfBoundsException indexE) {
						Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
								Notification.Location.TOP_RIGHT, "Dato Inválido");
						alertUsu.showNotification();
					}
				} catch (NumberFormatException e1) {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void aprobarPiezas(Inventario inventario, LoginMain inicio) {

		inicializacion();
		mensajeTabla.setText("Estos son las piezas en espera de aprobación");
		opcion.setText("Aprobar Nuevas Piezas");
		descripcion.setText("Ingresa el índice de la pieza para aprobarla");
		botonAccion.setText("Aprobar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/aprobar2.png")));
		// Filas Tabla Piezas
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Titulo");
		tabla.addColumn("Dueño");
		tabla.addColumn("Propósito");
		tabla.addColumn("Consignación");
		tabla.addColumn("Vigencia");
		tabla.addColumn("Índice Aprobación");
		List<ConsignacionPieza> listaSolicitudPiezas = inventario.getPiezasSolicitud();
		int contador = -1;
		for (ConsignacionPieza pieza : listaSolicitudPiezas) {
			contador++;
			tabla.addRow(new Object[] { pieza.getPieza().getTitulo(), pieza.getPieza().getPropietario().getUsuario(),
					pieza.getPieza().getProposito(), pieza.getPieza().getDeposito(), pieza.getPieza().getVigencia(),
					contador });
		}
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int piezaApro = Integer.parseInt(ingresoIndice.getText());
					try {
						Piezas piezaA = listaSolicitudPiezas.get(piezaApro).getPieza();
						piezaA.asignarVigenica(true);
						piezaA.asignarAprobada(true);
						inicio.persistenciaSalvarInventario();
						Notification alertUsu = new Notification(frame, Notification.Type.INFO,
								Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
						alertUsu.showNotification();
					} catch (IndexOutOfBoundsException indexE) {
						Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
								Notification.Location.TOP_RIGHT, "Dato Inválido");
						alertUsu.showNotification();
					}
				} catch (NumberFormatException e1) {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void piezasOfertas(LoginMain inicio, Galeria galeria, ProcesoCompra cajero) {

		inicializacion();
		mensajeTabla.setText("Estos son las ofertas finales que esperan aprobación");
		opcion.setText("Aprobar Ofertas Finales");
		descripcion.setText("Ingresa el índice de la oferta, la fecha actual");
		botonAccion.setText("Aprobar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/aprobar3.png")));
		// Filas Tabla Ofertas Piezas
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Ofertador");
		tabla.addColumn("Pieza");
		tabla.addColumn("Autor");
		tabla.addColumn("Monto");
		tabla.addColumn("Valor");
		tabla.addColumn("Tipo");
		tabla.addColumn("Índice Aprobación");
		List<Ofertas> ofertasFinales = galeria.getListaOfertasFinales();
		int contador = -1;
		for (Ofertas oferta : ofertasFinales) {
			contador++;
			tabla.addRow(new Object[] { oferta.getComprador().getInversor().getUsuario(),
					oferta.getPiezas().getTitulo(), oferta.getPiezas().getAutores(), oferta.getMonto(),
					oferta.getPiezas().getGaleriaOferta().getMontoCliente(), oferta.tipoOferta(), contador });
		}
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String[] partes = ingresoIndice.getText().split(",");
					int ofertaApro = Integer.parseInt(partes[0]);
					String fechaVenta = partes[1];
					try {
						Ofertas ofertaA = ofertasFinales.get(ofertaApro);
						ofertaA.getPiezas().asignarFechaVendida(fechaVenta);
						ofertaA.getPiezas().asignarVenta(true);
						ofertaA.getPiezas().asignarVigenica(false);

						cajero.agregarOfertas(ofertaA);
						inicio.persistenciaSalvarInventario();
						inicio.persistenciaSalvarVentas();
						inicio.persistenciaSalvarVentas();

						Notification alertUsu = new Notification(frame, Notification.Type.INFO,
								Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
						alertUsu.showNotification();
					} catch (IndexOutOfBoundsException indexE) {
						Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
								Notification.Location.TOP_RIGHT, "Dato Inválido");
						alertUsu.showNotification();
					}
				} catch (NumberFormatException e1) {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void finalizarSubasta(LoginMain inicio, Inventario inventario, OperacionSubasta subasta) {

		inicializacion();
		// Items Tabla
		mensajeTabla.setText("Estos son las subastas activas");
		opcion.setText("Finalizar Subasta");
		descripcion.setText("Ingresa el índice de la subasta");
		botonAccion.setText("Finalizar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/eliminar.png")));
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Pieza");
		tabla.addColumn("Autor");
		tabla.addColumn("Valor");
		tabla.addColumn("Índice Aprobación");
		List<Piezas> piezasSubasta = inventario.getSubastar();
		int contador = -1;
		for (Piezas pieza : piezasSubasta) {
			contador++;
			tabla.addRow(new Object[] { pieza.getTitulo(), pieza.getAutores(),
					pieza.getGaleriaOferta().getMontoMinimo(), contador });
		}
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int subastaEli = Integer.parseInt(ingresoIndice.getText());
					try {
						Piezas subastaE = piezasSubasta.get(subastaEli);
						subasta.finalizarSubasta(subastaE);
						subastaE.asignarVigenica(false);
						inicio.persistenciaSalvarInventario();
						inicio.persistenciaSalvarVentas();
						Notification alertUsu = new Notification(frame, Notification.Type.INFO,
								Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
						alertUsu.showNotification();
					} catch (IndexOutOfBoundsException indexE) {
						Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
								Notification.Location.TOP_RIGHT, "Dato Inválido");
						alertUsu.showNotification();
					}
				} catch (NumberFormatException e1) {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void historialComprador(Galeria galeria) {

		inicializacion();
		// Items Tabla
		mensajeTabla.setText("Consulta las piezas de cada inversor");
		opcion.setText("Consultar por usuario");
		descripcion.setText("Ingresa el usuario del inversor");
		botonAccion.setText("Consultar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/buscar.png")));
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Pieza");
		tabla.addColumn("Tipo");
		tabla.addColumn("Autor");
		tabla.addColumn("Propiedad");
		tabla.addColumn("Fecha Creación");
		List<HistorialInversor> historiales = galeria.getListaHistorial();
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistorialInversor encontrado = null;
				for (HistorialInversor usuarioE : historiales) {
					if (usuarioE.getInversor().getUsuario().equals(ingresoIndice.getText())) {
						encontrado = usuarioE;
					}
				}
				if (encontrado != null) {
					Notification alertUsu = new Notification(frame, Notification.Type.INFO,
							Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
					alertUsu.showNotification();
					for (Piezas piezasC : encontrado.getPiezasCompradas()) {
						tabla.addRow(new Object[] { piezasC.getTitulo(), piezasC.getTipo(), piezasC.getAutores(),
								"Comprada", piezasC.getLugarCreacion() });
					}
					for (Piezas piezasC : encontrado.getPiezas()) {
						tabla.addRow(new Object[] { piezasC.getTitulo(), piezasC.getTipo(), piezasC.getAutores(),
								"Propia", piezasC.getLugarCreacion() });
					}
				} else {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void solicitudInversor(LoginMain inicio, Galeria galeria) {

		inicializacion();
		// Items Tabla
		mensajeTabla.setText("Aprobar solicitudes ampliar monto");
		opcion.setText("Aprobar solicitud");
		descripcion.setText("Ingresa el índice del usuario y su monto (índice, monto)");
		botonAccion.setText("Cambiar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/monto.png")));
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Inversor");
		tabla.addColumn("Monto Actual");
		tabla.addColumn("Índice Aprobación");
		List<HistorialInversor> solicitudMonto = galeria.getSolicitudMonto();
		int contador = -1;
		for (HistorialInversor historial : solicitudMonto) {
			contador++;
			tabla.addRow(new Object[] { historial.getInversor().getUsuario(), historial.getMontoMaximo(), contador });
		}
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						String[] partes = ingresoIndice.getText().split(",");
						int solicitudApro = Integer.parseInt(partes[0]);
						int montoNuevo = Integer.parseInt(partes[1]);
						HistorialInversor inverMontoA = solicitudMonto.get(solicitudApro);
						inverMontoA.modificarMontoMaximo(montoNuevo);
						inicio.persistenciaSalvar();
						Notification alertUsu = new Notification(frame, Notification.Type.INFO,
								Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
						alertUsu.showNotification();
					} catch (IndexOutOfBoundsException indexE) {
						Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
								Notification.Location.TOP_RIGHT, "Dato Inválido");
						alertUsu.showNotification();
					}
				} catch (NumberFormatException e1) {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void devolverPieza(LoginMain inicio, Galeria galeria) {

		inicializacion();
		// Items Tabla
		mensajeTabla.setText("Piezas en consignación");
		opcion.setText("Devolver piezas");
		descripcion.setText("Ingresa el índice de la pieza");
		botonAccion.setText("Devolver");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/devolver.png")));
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Pieza");
		tabla.addColumn("Propietario");
		tabla.addColumn("Tipo");
		tabla.addColumn("Propósito");
		tabla.addColumn("Índice Aprobación");
		List<ConsignacionPieza> listaPiezasDevolver = galeria.getDeposito();
		int contador = -1;
		for (ConsignacionPieza devolver : listaPiezasDevolver) {
			contador++;
			tabla.addRow(new Object[] { devolver.getPieza().getTitulo(), devolver.getPropietario().getUsuario(),
					devolver.getPieza().getTipo(), devolver.getPieza().getProposito(), contador });
		}
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int devPieza = Integer.parseInt(ingresoIndice.getText());
					try {
						ConsignacionPieza consignaDev = listaPiezasDevolver.get(devPieza);
						consignaDev.getPieza().asignarVigenica(false);
						inicio.persistenciaSalvarInventario();
						Notification alertUsu = new Notification(frame, Notification.Type.INFO,
								Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
						alertUsu.showNotification();
					} catch (IndexOutOfBoundsException indexE) {
						Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
								Notification.Location.TOP_RIGHT, "Dato Inválido");
						alertUsu.showNotification();
					}
				} catch (NumberFormatException e1) {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void historialesPiezas(Galeria galeria) {

		inicializacion();
		// Items Tabla
		mensajeTabla.setText("Consultar historial pieza");
		opcion.setText("Consultar por pieza");
		descripcion.setText("Ingresa el titulo y su autor (titulo,autor)");
		botonAccion.setText("Consultar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/buscar.png")));
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Titulo");
		tabla.addColumn("Tipo");
		tabla.addColumn("Dueño");
		tabla.addColumn("Fecha Venta");
		tabla.addColumn("Precio Venta");
		List<ConsignacionPieza> piezas = galeria.getPiezasSolicitud();
		botonAccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] partes = ingresoIndice.getText().split(",");
				String titulo = partes[0];
				String autores = partes[1];
				ConsignacionPieza pieza = null;
				for (ConsignacionPieza arte : piezas) {
					if (arte.getPieza().getTitulo().equals(titulo) && arte.getPieza().getAutores().equals(autores)) {
						pieza = arte;
					}
				}
				if (pieza != null) {
					Notification alertUsu = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
					alertUsu.showNotification();
					if (pieza.getPieza().getProposito().equals("Vender")) {
						tabla.addRow(new Object[] { pieza.getPieza().getTitulo(), pieza.getPieza().getTipo(),
								pieza.getPieza().getPropietario(), pieza.getPieza().getFechaVendida(),
								pieza.getPieza().getGaleriaOferta().getMontoCliente() });
					} else if (pieza.getPieza().getProposito().equals("Subastar")) {
						tabla.addRow(new Object[] { pieza.getPieza().getTitulo(), pieza.getPieza().getTipo(),
								pieza.getPieza().getPropietario(), pieza.getPieza().getFechaVendida(),
								pieza.getPieza().getGaleriaOferta().getMontoMinimo() });
					} else {
						tabla.addRow(new Object[] { pieza.getPieza().getTitulo(), pieza.getPieza().getTipo(),
								pieza.getPieza().getPropietario(), pieza.getPieza().getFechaVendida(), 0 });
					}
				} else {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	private void historialesArtistas(Galeria galeria) {

		inicializacion();
		// Items Tabla
		mensajeTabla.setText("Consultar historial del artista");
		opcion.setText("Consultar por artista");
		descripcion.setText("Ingresa el nombre del artista");
		botonAccion.setText("Consultar");
		botonAccion.setIcon(new ImageIcon(getClass().getResource("/icon/buscar.png")));
		tabla.removeRowAll();
		tabla.removeColumnAll();
		tabla.addColumn("Pieza");
		tabla.addColumn("Fecha Creación");
		tabla.addColumn("Fecha Venta");
		tabla.addColumn("Monto");
		List<Autores> autoresDisponibles = galeria.getCentroAutores().getListaAutores();
		botonAccion.addActionListener(new ActionListener() {
			String autor = ingresoIndice.getText();
			Autores autorEncontrado = null;

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Autores creador : autoresDisponibles) {
					if (creador.getNombre().equals(autor)) {
						autorEncontrado = creador;
					}
				}
				if (autorEncontrado != null) {
					for (Piezas pieza : autorEncontrado.getListaPiezas()) {
						if (pieza.getProposito().equals("Vender")) {
							tabla.addRow(new Object[] { pieza.getTitulo(), pieza.getLugarCreacion(),
									pieza.getFechaVendida(), pieza.getGaleriaOferta().getMontoCliente() });
						} else if (pieza.getProposito().equals("Subastar")) {
							tabla.addRow(new Object[] { pieza.getTitulo(), pieza.getLugarCreacion(),
									pieza.getFechaVendida(), pieza.getGaleriaOferta().getMontoMinimo() });
						} else {
							tabla.addRow(new Object[] { pieza.getTitulo(), pieza.getLugarCreacion(),
									pieza.getFechaVendida(), 0 });
						}
					}
					Notification alertUsu = new Notification(frame, Notification.Type.INFO,
							Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
					alertUsu.showNotification();
				} else {
					Notification alertUsu = new Notification(frame, Notification.Type.WARNING,
							Notification.Location.TOP_RIGHT, "Dato Inválido");
					alertUsu.showNotification();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

        paneles = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        items = new javax.swing.JPanel();
        logoMenu1 = new galeriacasasubasta.LogoMenu();
        header = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        rol = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        banner = new javax.swing.JPanel();
        blogo = new javax.swing.JLabel();
        bienvenida = new javax.swing.JLabel();
        listaU = new javax.swing.JPanel();
        eliminar = new javax.swing.JPanel();
        opcion = new javax.swing.JLabel();
        descripcion = new javax.swing.JLabel();
        ingresoIndice = new items.MyTextField();
        botonAccion = new items.Button();
        mensajeTabla = new javax.swing.JLabel();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new galeriacasasubasta.Tabla();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        paneles.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(53, 92, 125));

        javax.swing.GroupLayout itemsLayout = new javax.swing.GroupLayout(items);
        items.setLayout(itemsLayout);
        itemsLayout.setHorizontalGroup(
            itemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        itemsLayout.setVerticalGroup(
            itemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        header.setBackground(new java.awt.Color(122, 40, 124));

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile.png"))); // NOI18N

        rol.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        rol.setForeground(new java.awt.Color(255, 255, 255));
        rol.setText("Message");

        usuario.setForeground(new java.awt.Color(220, 220, 220));
        usuario.setText("Message Text");

        nombre.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setText("Galeria y Casa de Subastas");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rol, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usuario, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(img)
                .addGap(19, 19, 19))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuario))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(img))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(nombre)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        banner.setBackground(new java.awt.Color(255, 255, 255));

        blogo.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        blogo.setForeground(new java.awt.Color(122, 40, 124));
        blogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bienvenido.png"))); // NOI18N

        bienvenida.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        bienvenida.setForeground(new java.awt.Color(122, 40, 124));
        bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bienvenida.setText("Bienvenido\n");

        listaU.setBackground(new java.awt.Color(255, 255, 255));

        eliminar.setBackground(new java.awt.Color(238, 238, 238));
        eliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        eliminar.setForeground(new java.awt.Color(246, 239, 239));

        opcion.setBackground(new java.awt.Color(9, 13, 118));
        opcion.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        opcion.setForeground(new java.awt.Color(9, 13, 118));
        opcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opcion.setText("Titulo\n");

        descripcion.setBackground(new java.awt.Color(83, 103, 169));
        descripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        descripcion.setForeground(new java.awt.Color(83, 103, 169));
        descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descripcion.setText("Descripcion");

        ingresoIndice.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonAccion.setText("Eliminar");
        botonAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout eliminarLayout = new javax.swing.GroupLayout(eliminar);
        eliminar.setLayout(eliminarLayout);
        eliminarLayout.setHorizontalGroup(
            eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarLayout.createSequentialGroup()
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ingresoIndice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcion, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(botonAccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        eliminarLayout.setVerticalGroup(
            eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(opcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ingresoIndice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        mensajeTabla.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        mensajeTabla.setForeground(new java.awt.Color(122, 40, 124));
        mensajeTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensajeTabla.setText("Message");

        scrollTabla.setBorder(null);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTabla.setViewportView(tabla);

        javax.swing.GroupLayout listaULayout = new javax.swing.GroupLayout(listaU);
        listaU.setLayout(listaULayout);
        listaULayout.setHorizontalGroup(
            listaULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaULayout.createSequentialGroup()
                .addGroup(listaULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listaULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(listaULayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, listaULayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(mensajeTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(listaULayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        listaULayout.setVerticalGroup(
            listaULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensajeTabla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bannerLayout = new javax.swing.GroupLayout(banner);
        banner.setLayout(bannerLayout);
        bannerLayout.setHorizontalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listaU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(179, 179, 179)
                    .addComponent(bienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGap(179, 179, 179)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(189, 189, 189)
                    .addComponent(blogo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGap(169, 169, 169)))
        );
        bannerLayout.setVerticalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bannerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1364, Short.MAX_VALUE))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(313, 313, 313)
                    .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(160, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(208, 208, 208)
                    .addComponent(blogo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1652, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelesLayout = new javax.swing.GroupLayout(paneles);
        paneles.setLayout(panelesLayout);
        panelesLayout.setHorizontalGroup(
            panelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelesLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelesLayout.setVerticalGroup(
            panelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelesLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(banner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(paneles, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

	private void botonAccionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonAccionActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_botonAccionActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel banner;
	private javax.swing.JLabel bienvenida;
	private javax.swing.JLabel blogo;
	private items.Button botonAccion;
	private javax.swing.JLabel descripcion;
	private javax.swing.JPanel eliminar;
	private javax.swing.JPanel header;
	private javax.swing.JLabel img;
	private items.MyTextField ingresoIndice;
	private javax.swing.JPanel items;
	private javax.swing.JPanel listaU;
	private galeriacasasubasta.LogoMenu logoMenu1;
	private javax.swing.JLabel mensajeTabla;
	private javax.swing.JPanel menu;
	private javax.swing.JLabel nombre;
	private javax.swing.JLabel opcion;
	private javax.swing.JPanel paneles;
	private javax.swing.JLabel rol;
	private javax.swing.JScrollPane scrollTabla;
	private galeriacasasubasta.Tabla tabla;
	private javax.swing.JLabel usuario;
	// End of variables declaration//GEN-END:variables
}
