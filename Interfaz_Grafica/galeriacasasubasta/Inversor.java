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
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.compras.OfertaVenta;
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
import net.miginfocom.swing.MigLayout;

public class Inversor extends javax.swing.JFrame {

    private JFrame frame = (JFrame) this;
    
    public Inversor(String tipo, String usu, LoginMain inicio, Galeria galeria, String inversorUsu) {
        initComponents();
        
        //Clases Iniciales
        RegistroInicio sesion = galeria.getRegistro();
		OperacionSubasta subasta = galeria.getClaseSubasta();
		Inventario inventario = galeria.getInventario();
		ProcesoCompra cajero = galeria.getCajero();
		CentroOfertas centroOferta = galeria.getCentroOfertas();
		CentroAutores cenAutores = galeria.getCentroAutores();
        
        //Persistencia
		inicio.persistenciaCargarInventario();
		inicio.persistenciaCargarVentas();
		inicio.persistenciaCargarAutores();
		
		//Historial Usuario
		HistorialInversor inversor = null;
        for(HistorialInversor dueño : galeria.getListaHistorial()) {
        	String usuP = dueño.getInversor().getUsuario();
        	if(usuP.equals(inversorUsu)) {
        		inversor = dueño;
        		inversor.asignarGaleria(galeria);
        	}
        }
        
        itemsMenu(tipo, inversor, inventario, cenAutores, inicio, galeria);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        
        //Panel principal
        paneles.setOpaque(false);
        //Panel Menu Botones
        items.setOpaque(false);
        //Texto Header
        rol.setText(tipo);
        usuario.setText(usu);
        nombre.setVisible(true);
        inicioCrear.setVisible(false);
        arteDigital.setVisible(false);
        arteTridi.setVisible(false);
        arteVisual.setVisible(false);
        tablas.setVisible(false);
        blogo.setVisible(true);
        bienvenida.setVisible(true);
        bienvenida.setText("<html>Bienvenido, " + tipo + " a nuestra" + " Galería y Casa de Subastas<br></html>");
    }
    
    private void itemsMenu(String tipo, HistorialInversor inversor, Inventario inventario, CentroAutores cenAutores, LoginMain inicio, Galeria galeria){
        
        if(tipo.equals("Inversor")){
            
            items.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]push"));
            Button cmd2 = new Button();
            cmd2.setBackground(new Color(72,125,171));
            cmd2.setForeground(new Color(250, 250, 250));
            cmd2.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd2.setText("Crear Pieza");
            items.add(cmd2, "w 202, h 40");
            
            cmd2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                   crearPieza(inversor, inventario, cenAutores, inicio, galeria);
                }
            });
        
            Button cmd3 = new Button();
            cmd3.setBackground(new Color(176,132,139));
            cmd3.setForeground(new Color(250, 250, 250));
            cmd3.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd3.setText("Ofertar En Subasta");
            items.add(cmd3, "w 202, h 40");
            
            cmd3.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    ofertarSubasta(inicio, galeria, inversor);
                }
            });
            
            Button cmd1 = new Button();
            cmd1.setBackground(new Color(72,125,171));
            cmd1.setForeground(new Color(250, 250, 250));
            cmd1.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd1.setText("Ofertar En Venta");
            items.add(cmd1, "w 202, h 40");
            
            cmd1.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    ofertarVenta(inicio, galeria, inversor, inventario);
                }
            });
        
            Button cmd5 = new Button();
            cmd5.setBackground(new Color(176,132,139));
            cmd5.setForeground(new Color(250, 250, 250));
            cmd5.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd5.setText("Historiales Piezas");
            items.add(cmd5, "w 202, h 40");
            
            cmd5.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    historialPieza(galeria);
                }
            });
        
            Button cmd6 = new Button();
            cmd6.setBackground(new Color(72,125,171));
            cmd6.setForeground(new Color(250, 250, 250));
            cmd6.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd6.setText("Historiales Artistas");
            items.add(cmd6, "w 202, h 40");
            
            cmd6.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    historialArtista(galeria);
                }
            });
        
            Button cmd7 = new Button();
            cmd7.setBackground(new Color(176,132,139));
            cmd7.setForeground(new Color(250, 250, 250));
            cmd7.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd7.setText("Solicitud Ampliar Monto");
            items.add(cmd7, "w 202, h 40");
            
            cmd7.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                   solicitudMonto(inversor, inicio);
                }
            });
        }
    }
    
    private void incializar(){
        
        blogo.setVisible(false);
        bienvenida.setVisible(false);
        tablas.setVisible(false);
        inicioCrear.setVisible(false);
        inicioCrear1.setVisible(false);
        arteTridi.setVisible(false);
        arteDigital.setVisible(false);
        arteVisual.setVisible(false);
        scrollTabla.setVisible(false);
        scrollTabla.setVerticalScrollBar(new ScrollBar());
        scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollTabla.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrollTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        ingresoTipo.setText("");
        ingresoTipo1.setText("");
    }
    
    private void solicitudMonto(HistorialInversor inversor, LoginMain inicio){
        
        incializar();
        inicioCrear.setVisible(true);
        opcion1.setText("¿Deseas ampliar tu monto?");
        botonAccion1.setText("Enviar Solicitud");
        ingresoTipo.setVisible(false);
        botonAccion1.setIcon(new ImageIcon(getClass().getResource("/icon/solicitud.png")));
        botonAccion1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	inversor.crearSolicitudMonto();
            	inicio.persistenciaSalvar();
                Notification alertUsu = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
                alertUsu.showNotification();
            }
        });
    }
    
    private void crearPieza(HistorialInversor inversor, Inventario inventario, CentroAutores cenAutores, LoginMain inicio, Galeria galeria){
        
        incializar();
        inicioCrear.setVisible(true);
        opcion1.setText("Ingrese el tipo de arte");
        ingresoTipo.setVisible(true);
        botonAccion1.setText("Generar");
        botonAccion1.setIcon(new ImageIcon(getClass().getResource("/icon/genera.png")));
        botonAccion1.addActionListener(new ActionListener(){
            String texto;
            @Override
            public void actionPerformed(ActionEvent e){
                texto = ingresoTipo.getText();
                if(texto.equals("Arte Digital")){
                    inicioCrear.setVisible(false);
                    arteDigital.setVisible(true);
                }
                else if(texto.equals("Arte Tridimensional")){
                    inicioCrear.setVisible(false);
                    arteTridi.setVisible(true);
                }
                else if(texto.equals("Arte Cuadros")){
                    inicioCrear.setVisible(false);
                    arteVisual.setVisible(true);
                }
            }
        });
        
        botonDigital.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tituloDigital = tituloD.getText();
				String autorDigital = autoresD.getText();
				
				String propoDigital = propositoD.getText();
				String[] partes = propoDigital.split(", ");
				int valorCliente = 0;
				int valorMinimo = 0;
				if(partes[0].equals("Vender")) {
					valorCliente = Integer.parseInt(partes[1]);
					valorMinimo = 0;
				}
				else if(partes[0].equals("Subastar")) {
					valorCliente = Integer.parseInt(partes[1]);
					valorMinimo = Integer.parseInt(partes[2]);
				}
				else if(partes[0].equals("Exhibir")) {
					valorCliente = 0;
					valorMinimo = 0;
				}
				String lugarDigital = lugarAñoD.getText();
				String consignaDigital = consignaD.getText();
				String[] partesPrestamo = consignaDigital.split(", ");
				boolean deposito = Boolean.parseBoolean(partesPrestamo[0]);
				int dias = 0;
				if(partesPrestamo[0].equals("true")) {
					dias = Integer.parseInt(partesPrestamo[1]);
				}
				String arteDigital = tipoArteD.getText();
				String archivoDigital = tipoArchivoD.getText();
				
				ConsignacionPieza pieza = inversor.CrearPiezaDigital(dias, tituloDigital, valorCliente, valorMinimo, propoDigital, lugarDigital, deposito, inversor.getInversor(), autorDigital, arteDigital, archivoDigital);
                inventario.añadirPiezasSolicitud(pieza);
                
                String[] partesAutores = autorDigital.split(", ");
				for(String autoresNombres : partesAutores) {
					Autores auto = cenAutores.crearAutor(autoresNombres);
					auto.agregarPiezas(pieza.getPieza());
					inicio.persistenciaSalvarAutores();
				}
				inicio.persistenciaSalvarInventario();
				Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "A esperar de aprobación");
                panel.showNotification();
			}
		});
        
        botonVisual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tituloVisual = visualD.getText();
				String autorVisual = autoresV.getText();
				String propoVisual = propositoV.getText();
				String[] partes = propoVisual.split(", ");
				int valorCliente = 0;
				int valorMinimo = 0;
				if(partes[0].equals("Vender")) {
					valorCliente = Integer.parseInt(partes[1]);
					valorMinimo = 0;
				}
				else if(partes[0].equals("Subastar")) {
					valorCliente = Integer.parseInt(partes[1]);
					valorMinimo = Integer.parseInt(partes[2]);
				}
				else if(partes[0].equals("Exhibir")) {
					valorCliente = 0;
					valorMinimo = 0;
				}
				String lugarVisual = lugarAñoV.getText();
				String consignaVisual = consignacionV.getText();
				String[] partesPrestamo = consignaVisual.split(", ");
				boolean deposito = Boolean.parseBoolean(partesPrestamo[0]);
				int dias = 0;
				if(partesPrestamo[0].equals("true")) {
					dias = Integer.parseInt(partesPrestamo[1]);
				}
				String anchoVisual = anchoV.getText();
				String tecnicaVisual = tecnicaV.getText();
				
				ConsignacionPieza pieza = inversor.CrearPiezaPintura(dias, tituloVisual, valorCliente, valorMinimo, partes[0], lugarVisual, deposito, inversor.getInversor(),autorVisual, anchoVisual, tecnicaVisual);
                inventario.añadirPiezasSolicitud(pieza);
				
				String[] partesAutores = autorVisual.split(", ");
				for(String autoresNombres : partesAutores) {
					Autores auto = cenAutores.crearAutor(autoresNombres);
					auto.agregarPiezas(pieza.getPieza());
					inicio.persistenciaSalvarAutores();
				}
				inicio.persistenciaSalvarInventario();
				Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "A esperar de aprobación");
                panel.showNotification();
			}
		});
        
        botonDigital.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tituloTridi = tituloT.getText();
				String autorTridi = autoresT.getText();
				String propoTridi = propositoT.getText();
				String[] partes = propoTridi.split(", ");
				int valorCliente = 0;
				int valorMinimo = 0;
				if(partes[0].equals("Vender")) {
					valorCliente = Integer.parseInt(partes[1]);
					valorMinimo = 0;
				}
				else if(partes[0].equals("Subastar")) {
					valorCliente = Integer.parseInt(partes[1]);
					valorMinimo = Integer.parseInt(partes[2]);
				}
				else if(partes[0].equals("Exhibir")) {
					valorCliente = 0;
					valorMinimo = 0;
				}
				String lugarTridi = lugarT.getText();
				String consignaTridi = consignaT.getText();
				String[] partesPrestamo = consignaTridi.split(", ");
				boolean deposito = Boolean.parseBoolean(partesPrestamo[0]);
				int dias = 0;
				if(partesPrestamo[0].equals("true")) {
					dias = Integer.parseInt(partesPrestamo[1]);
				}
				int pesoTridi = Integer.parseInt(pesoT.getText());
				boolean electriTridi = Boolean.parseBoolean(electriT.getText());
				String tecnicaTridi = tecnicaT.getText();
				String dimensionTridi = dimensionT.getText();
				ConsignacionPieza pieza = inversor.CrearPiezaTridimensional(dias, tituloTridi, valorCliente, valorMinimo, partes[0], lugarTridi, deposito, inversor.getInversor(), autorTridi, dimensionTridi, tecnicaTridi, pesoTridi, electriTridi);
				inventario.añadirPiezasSolicitud(pieza);
				
				String[] partesAutores = autorTridi.split(", ");
				for(String autoresNombres : partesAutores) {
					Autores auto = cenAutores.crearAutor(autoresNombres);
					auto.agregarPiezas(pieza.getPieza());
					inicio.persistenciaSalvarAutores();
				}
				inicio.persistenciaSalvarInventario();
				Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "A esperar de aprobación");
                panel.showNotification();
			}
		});
    }
    
    private void ofertarSubasta(LoginMain inicio, Galeria galeria, HistorialInversor inversor){
        
        incializar();
        tablas.setVisible(true);
        scrollTabla.setVisible(true);
        inicioCrear1.setVisible(true);
        opcion2.setText("Hacer puja por pieza");
        descripcion.setText("Ingresa el titulo de la pieza y el monto, con comas");
        botonAccion2.setText("Ofertar");
        botonAccion2.setIcon(new ImageIcon(getClass().getResource("/icon/aprobar3.png")));
        //Filas Tabla Usuario
        tabla.removeRowAll();
        tabla.removeColumnAll();
        tabla.addColumn("Pieza");
        tabla.addColumn("Autor");
        tabla.addColumn("Puja Actual");
        Set<Piezas> piezasSubasta = galeria.getMapaSubastas().keySet();
        for(Piezas pieza : piezasSubasta){
        	List<Ofertas> oferta = galeria.getMapaSubastas().get(pieza);
        	if(oferta.size() > 0) {
        		tabla.addRow(new Object []{pieza.getTitulo(), pieza.getAutores(), oferta.get(oferta.size()-1).getMonto()});
    		}
        }
        botonAccion2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	String[] partes = ingresoTipo1.getText().split(", ");
            	String indice = partes[0];
            	int valor = Integer.parseInt(partes[1]);
            	Piezas seleccionada = null;
            	for(Piezas piezas : piezasSubasta) {
            		if(piezas.getTitulo().equals(indice)) {
            			seleccionada = piezas;
            		}
            	}
            	if(seleccionada != null) {
            		OfertaSubasta ofertaNuevas = inversor.crearOfertaSubasta(seleccionada, inversor, valor);
                	galeria.getCentroOfertas().agregarOfertas(ofertaNuevas);
                    inicio.persistenciaSalvarVentas();
                    Notification alertUsu = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
                    alertUsu.showNotification();
            	}else {
            		Notification alertUsu = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Dato Inválido");
                    alertUsu.showNotification();
            	}
            }
        });
    }
    
    private void ofertarVenta(LoginMain inicio, Galeria galeria, HistorialInversor inversor, Inventario inventario){
        
        incializar();
        tablas.setVisible(true);
        scrollTabla.setVisible(true);
        inicioCrear1.setVisible(true);
        opcion2.setText("Hacer oferta por pieza");
        descripcion.setText("Ingresa el titulo de la pieza");
        botonAccion2.setText("Ofertar");
        botonAccion2.setIcon(new ImageIcon(getClass().getResource("/icon/aprobar3.png")));
        //Filas Tabla Usuario
        tabla.removeRowAll();
        tabla.removeColumnAll();
        tabla.addColumn("Pieza");
        tabla.addColumn("Autor");
        tabla.addColumn("Valor Fijo");
        List<Piezas> piezasVenta = inventario.getVenta();
        int contador = -1;
        for(Piezas pieza : piezasVenta){
        	contador++;
        	tabla.addRow(new Object []{pieza.getTitulo(), pieza.getAutores(), pieza.getGaleriaOferta().getMontoCliente()});
        }
        botonAccion2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	int partes = Integer.parseInt(ingresoTipo1.getText());
            	if(partes < 0 | partes > piezasVenta.size()-1) {
            		Notification alertUsu = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Dato Inválido");
                    alertUsu.showNotification();
            	}else {
            		Piezas piezaSelec = piezasVenta.get(partes);
            		OfertaVenta ofertaNuevas = inversor.crearOfertaVenta(piezaSelec, inversor, piezaSelec.getGaleriaOferta().getMontoCliente());
                	galeria.getCentroOfertas().agregarOfertas(ofertaNuevas);
                    inicio.persistenciaSalvarVentas();
                    Notification alertUsu = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
                    alertUsu.showNotification();
            	}
            }
        });
    }
    
    private void historialPieza(Galeria galeria){
        
        incializar();
        //Items Tabla
        tablas.setVisible(true);
        scrollTabla.setVisible(true);
        inicioCrear1.setVisible(true);
        opcion2.setText("Consultar por pieza");
        descripcion.setText("Ingresa el titulo de la obra");
        botonAccion2.setText("Consultar");
        botonAccion2.setIcon(new ImageIcon(getClass().getResource("/icon/buscar.png")));
        tabla.removeRowAll();
        tabla.removeColumnAll();
        tabla.addColumn("Titulo");
        tabla.addColumn("Tipo");
        tabla.addColumn("Dueño");
        tabla.addColumn("Fecha Venta");
        tabla.addColumn("Precio Venta");
        
        botonAccion2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	List<ConsignacionPieza> piezas = galeria.getPiezasSolicitud();
                ConsignacionPieza arte = null;
                String tituloArte = ingresoTipo1.getText();
                for(ConsignacionPieza arteB : piezas) {
            		if(arte.getPieza().getTitulo().equals(tituloArte)) {
            			arte = arteB;
            		}
            	}
                if(arte != null) {
                	if (arte.getPieza().getProposito().equals("Vender")) {
						tabla.addRow(new Object[] {arte.getPieza().getTitulo(), arte.getPieza().getTipo(), arte.getPieza().getPropietario(), arte.getPieza().getFechaVendida(), arte.getPieza().getGaleriaOferta().getMontoCliente() });
					} else if (arte.getPieza().getProposito().equals("Subastar")) {
						tabla.addRow(new Object[] {arte.getPieza().getTitulo(), arte.getPieza().getTipo(), arte.getPieza().getPropietario(), arte.getPieza().getFechaVendida(), arte.getPieza().getGaleriaOferta().getMontoMinimo() });
					} else {
						tabla.addRow(new Object[] {arte.getPieza().getTitulo(), arte.getPieza().getTipo(), arte.getPieza().getPropietario(), arte.getPieza().getFechaVendida(), 0 });
					}
                	Notification alertUsu = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
                    alertUsu.showNotification();
                }else {
                	Notification alertUsu = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Dato Inválido");
                    alertUsu.showNotification();
                }
            }
        });
    }
    
    private void historialArtista(Galeria galeria){
        
        incializar();
        //Items Tabla
        tablas.setVisible(true);
        scrollTabla.setVisible(true);
        inicioCrear1.setVisible(true);
        opcion2.setText("Consultar por artista");
        descripcion.setText("Ingresa el nombre del artista");
        botonAccion2.setText("Consultar");
        botonAccion2.setIcon(new ImageIcon(getClass().getResource("/icon/buscar.png")));
        tabla.removeRowAll();
        tabla.removeColumnAll();
        tabla.addColumn("Pieza");
        tabla.addColumn("Fecha Creación");
        tabla.addColumn("Fecha Venta");
        tabla.addColumn("Monto");
        List<Autores> autoresDisponibles = galeria.getCentroAutores().getListaAutores();
		botonAccion2.addActionListener(new ActionListener() {
			String autor = ingresoTipo1.getText();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        tablas = new javax.swing.JPanel();
        inicioCrear1 = new javax.swing.JPanel();
        opcion2 = new javax.swing.JLabel();
        descripcion = new javax.swing.JLabel();
        ingresoTipo1 = new items.MyTextField();
        botonAccion2 = new items.Button();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new galeriacasasubasta.Tabla();
        arteTridi = new javax.swing.JPanel();
        tituloTridi = new javax.swing.JLabel();
        tituloT = new items.MyTextField();
        autoresTridi = new javax.swing.JLabel();
        autoresT = new items.MyTextField();
        propositoTridi = new javax.swing.JLabel();
        propositoT = new items.MyTextField();
        lugarTridi = new javax.swing.JLabel();
        lugarT = new items.MyTextField();
        consignaTridi = new javax.swing.JLabel();
        consignaT = new items.MyTextField();
        pesoTridi = new javax.swing.JLabel();
        pesoT = new items.MyTextField();
        electriTridi = new javax.swing.JLabel();
        electriT = new items.MyTextField();
        tecnicaTridi = new javax.swing.JLabel();
        tecnicaT = new items.MyTextField();
        dimensionTridi = new javax.swing.JLabel();
        dimensionT = new items.MyTextField();
        botonTridi = new items.Button();
        arteDigital = new javax.swing.JPanel();
        tituloDigital = new javax.swing.JLabel();
        tituloD = new items.MyTextField();
        autoresDigital = new javax.swing.JLabel();
        autoresD = new items.MyTextField();
        propositoDigital = new javax.swing.JLabel();
        propositoD = new items.MyTextField();
        lugarAñoDigital = new javax.swing.JLabel();
        lugarAñoD = new items.MyTextField();
        consignaDigital = new javax.swing.JLabel();
        consignaD = new items.MyTextField();
        tipoArteDigital = new javax.swing.JLabel();
        tipoArteD = new items.MyTextField();
        tipoArchivoDigital = new javax.swing.JLabel();
        tipoArchivoD = new items.MyTextField();
        botonDigital = new items.Button();
        arteVisual = new javax.swing.JPanel();
        tituloVisual = new javax.swing.JLabel();
        visualD = new items.MyTextField();
        autoresVisual = new javax.swing.JLabel();
        autoresV = new items.MyTextField();
        propositoVisual = new javax.swing.JLabel();
        propositoV = new items.MyTextField();
        lugarAñoVisual = new javax.swing.JLabel();
        lugarAñoV = new items.MyTextField();
        consignaVisual = new javax.swing.JLabel();
        consignacionV = new items.MyTextField();
        anchoVisual = new javax.swing.JLabel();
        anchoV = new items.MyTextField();
        tecnicaVisual = new javax.swing.JLabel();
        tecnicaV = new items.MyTextField();
        botonVisual = new items.Button();
        inicioCrear = new javax.swing.JPanel();
        opcion1 = new javax.swing.JLabel();
        ingresoTipo = new items.MyTextField();
        botonAccion1 = new items.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        paneles.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(53, 92, 125));
        menu.setPreferredSize(new java.awt.Dimension(237, 628));

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
                .addContainerGap(28, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
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

        tablas.setBackground(new java.awt.Color(255, 255, 255));

        inicioCrear1.setBackground(new java.awt.Color(248, 247, 247));
        inicioCrear1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        inicioCrear1.setForeground(new java.awt.Color(246, 239, 239));

        opcion2.setBackground(new java.awt.Color(9, 13, 118));
        opcion2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        opcion2.setForeground(new java.awt.Color(9, 13, 118));
        opcion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opcion2.setText("Titulo\n");

        descripcion.setBackground(new java.awt.Color(83, 103, 169));
        descripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        descripcion.setForeground(new java.awt.Color(83, 103, 169));
        descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descripcion.setText("Descripcion");

        ingresoTipo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonAccion2.setText("Eliminar");
        botonAccion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAccion2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inicioCrear1Layout = new javax.swing.GroupLayout(inicioCrear1);
        inicioCrear1.setLayout(inicioCrear1Layout);
        inicioCrear1Layout.setHorizontalGroup(
            inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrear1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingresoTipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(opcion2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(botonAccion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(220, Short.MAX_VALUE))
            .addGroup(inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inicioCrear1Layout.createSequentialGroup()
                    .addGap(126, 126, 126)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );
        inicioCrear1Layout.setVerticalGroup(
            inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrear1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(opcion2)
                .addGap(37, 37, 37)
                .addComponent(ingresoTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAccion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inicioCrear1Layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(descripcion)
                    .addContainerGap(100, Short.MAX_VALUE)))
        );

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

        javax.swing.GroupLayout tablasLayout = new javax.swing.GroupLayout(tablas);
        tablas.setLayout(tablasLayout);
        tablasLayout.setHorizontalGroup(
            tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(inicioCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
        tablasLayout.setVerticalGroup(
            tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(inicioCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(432, Short.MAX_VALUE)))
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(235, 235, 235)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );

        arteTridi.setBackground(new java.awt.Color(255, 255, 255));

        tituloTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tituloTridi.setForeground(new java.awt.Color(97, 42, 104));
        tituloTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloTridi.setText("Ingrese el titulo");

        tituloT.setBackground(new java.awt.Color(227, 228, 234));
        tituloT.setForeground(new java.awt.Color(122, 40, 124));
        tituloT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        autoresTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        autoresTridi.setForeground(new java.awt.Color(97, 42, 104));
        autoresTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autoresTridi.setText("Ingrese los autores, separados por comas");

        autoresT.setBackground(new java.awt.Color(227, 228, 234));
        autoresT.setForeground(new java.awt.Color(122, 40, 124));
        autoresT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        propositoTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        propositoTridi.setForeground(new java.awt.Color(97, 42, 104));
        propositoTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        propositoTridi.setText("Ingrese el propósito seguido de sus precios con comas (si aplica)");

        propositoT.setBackground(new java.awt.Color(227, 228, 234));
        propositoT.setForeground(new java.awt.Color(122, 40, 124));
        propositoT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lugarTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lugarTridi.setForeground(new java.awt.Color(97, 42, 104));
        lugarTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lugarTridi.setText("Ingrese el lugar de creación y el año (lugar-año)");

        lugarT.setBackground(new java.awt.Color(227, 228, 234));
        lugarT.setForeground(new java.awt.Color(122, 40, 124));
        lugarT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        consignaTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        consignaTridi.setForeground(new java.awt.Color(97, 42, 104));
        consignaTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consignaTridi.setText("Prestamo: true, dias. No prestamos: false");

        consignaT.setBackground(new java.awt.Color(227, 228, 234));
        consignaT.setForeground(new java.awt.Color(122, 40, 124));
        consignaT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pesoTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        pesoTridi.setForeground(new java.awt.Color(97, 42, 104));
        pesoTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pesoTridi.setText("Ingrese el peso");

        pesoT.setBackground(new java.awt.Color(227, 228, 234));
        pesoT.setForeground(new java.awt.Color(122, 40, 124));
        pesoT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        electriTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        electriTridi.setForeground(new java.awt.Color(97, 42, 104));
        electriTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        electriTridi.setText("Ingrese true o false si necesita electricidad");

        electriT.setBackground(new java.awt.Color(227, 228, 234));
        electriT.setForeground(new java.awt.Color(122, 40, 124));
        electriT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tecnicaTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tecnicaTridi.setForeground(new java.awt.Color(97, 42, 104));
        tecnicaTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tecnicaTridi.setText("Ingrese la técnica");

        tecnicaT.setBackground(new java.awt.Color(227, 228, 234));
        tecnicaT.setForeground(new java.awt.Color(122, 40, 124));
        tecnicaT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        dimensionTridi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        dimensionTridi.setForeground(new java.awt.Color(97, 42, 104));
        dimensionTridi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dimensionTridi.setText("Ingrese sus dimensiones (anchoxlargoxprofundidad)");

        dimensionT.setBackground(new java.awt.Color(227, 228, 234));
        dimensionT.setForeground(new java.awt.Color(122, 40, 124));
        dimensionT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonTridi.setBackground(new java.awt.Color(53, 92, 125));
        botonTridi.setForeground(new java.awt.Color(255, 255, 255));
        botonTridi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arteTridi.png"))); // NOI18N
        botonTridi.setText("Crear Pieza De Arte");

        javax.swing.GroupLayout arteTridiLayout = new javax.swing.GroupLayout(arteTridi);
        arteTridi.setLayout(arteTridiLayout);
        arteTridiLayout.setHorizontalGroup(
            arteTridiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, arteTridiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(arteTridiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propositoT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugarT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consignaT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propositoTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugarTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consignaTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(arteTridiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(electriTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesoTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesoT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(electriT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tecnicaTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tecnicaT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dimensionTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dimensionT, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        arteTridiLayout.setVerticalGroup(
            arteTridiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arteTridiLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(arteTridiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(arteTridiLayout.createSequentialGroup()
                        .addComponent(pesoTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(electriTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(electriT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tecnicaTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tecnicaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dimensionTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dimensionT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonTridi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(arteTridiLayout.createSequentialGroup()
                        .addComponent(tituloTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tituloT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoresTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoresT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(propositoTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(propositoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lugarTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lugarT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consignaTridi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consignaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        arteDigital.setBackground(new java.awt.Color(255, 255, 255));

        tituloDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tituloDigital.setForeground(new java.awt.Color(97, 42, 104));
        tituloDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloDigital.setText("Ingrese el titulo");

        tituloD.setBackground(new java.awt.Color(190, 196, 233));
        tituloD.setForeground(new java.awt.Color(122, 40, 124));
        tituloD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        autoresDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        autoresDigital.setForeground(new java.awt.Color(97, 42, 104));
        autoresDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autoresDigital.setText("Ingrese los autores, separados por comas");

        autoresD.setBackground(new java.awt.Color(190, 196, 233));
        autoresD.setForeground(new java.awt.Color(122, 40, 124));
        autoresD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        propositoDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        propositoDigital.setForeground(new java.awt.Color(97, 42, 104));
        propositoDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        propositoDigital.setText("Ingrese el propósito, seguido de sus precios separados por comas (si aplica)");

        propositoD.setBackground(new java.awt.Color(190, 196, 233));
        propositoD.setForeground(new java.awt.Color(122, 40, 124));
        propositoD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lugarAñoDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lugarAñoDigital.setForeground(new java.awt.Color(97, 42, 104));
        lugarAñoDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lugarAñoDigital.setText("Ingrese el lugar de creación y el año (lugar-año)");

        lugarAñoD.setBackground(new java.awt.Color(190, 196, 233));
        lugarAñoD.setForeground(new java.awt.Color(122, 40, 124));
        lugarAñoD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        consignaDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        consignaDigital.setForeground(new java.awt.Color(97, 42, 104));
        consignaDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consignaDigital.setText("En consignacion: true, dias. No consignacion: false");

        consignaD.setBackground(new java.awt.Color(190, 196, 233));
        consignaD.setForeground(new java.awt.Color(122, 40, 124));
        consignaD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tipoArteDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tipoArteDigital.setForeground(new java.awt.Color(97, 42, 104));
        tipoArteDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipoArteDigital.setText("Ingrese el tipo de arte");

        tipoArteD.setBackground(new java.awt.Color(190, 196, 233));
        tipoArteD.setForeground(new java.awt.Color(122, 40, 124));
        tipoArteD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tipoArchivoDigital.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tipoArchivoDigital.setForeground(new java.awt.Color(97, 42, 104));
        tipoArchivoDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipoArchivoDigital.setText("Ingrese el tipo de archivo");

        tipoArchivoD.setBackground(new java.awt.Color(190, 196, 233));
        tipoArchivoD.setForeground(new java.awt.Color(122, 40, 124));
        tipoArchivoD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonDigital.setBackground(new java.awt.Color(53, 92, 125));
        botonDigital.setForeground(new java.awt.Color(255, 255, 255));
        botonDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arteDigital.png"))); // NOI18N
        botonDigital.setText("Crear Pieza De Arte");

        javax.swing.GroupLayout arteDigitalLayout = new javax.swing.GroupLayout(arteDigital);
        arteDigital.setLayout(arteDigitalLayout);
        arteDigitalLayout.setHorizontalGroup(
            arteDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arteDigitalLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(arteDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoArchivoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoArteDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propositoD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugarAñoD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consignaD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoArteD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoArchivoD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propositoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugarAñoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consignaDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        arteDigitalLayout.setVerticalGroup(
            arteDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arteDigitalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tituloDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tituloD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoresDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoresD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propositoDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propositoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lugarAñoDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lugarAñoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consignaDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consignaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoArteDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoArteD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoArchivoDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoArchivoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        arteVisual.setBackground(new java.awt.Color(255, 255, 255));

        tituloVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tituloVisual.setForeground(new java.awt.Color(97, 42, 104));
        tituloVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloVisual.setText("Ingrese el titulo");

        visualD.setBackground(new java.awt.Color(190, 196, 233));
        visualD.setForeground(new java.awt.Color(122, 40, 124));
        visualD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        autoresVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        autoresVisual.setForeground(new java.awt.Color(97, 42, 104));
        autoresVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autoresVisual.setText("Ingrese los autores, separados por comas");

        autoresV.setBackground(new java.awt.Color(190, 196, 233));
        autoresV.setForeground(new java.awt.Color(122, 40, 124));
        autoresV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        propositoVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        propositoVisual.setForeground(new java.awt.Color(97, 42, 104));
        propositoVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        propositoVisual.setText("Ingrese el propósito, seguido de sus precios con comas (si aplica)");

        propositoV.setBackground(new java.awt.Color(190, 196, 233));
        propositoV.setForeground(new java.awt.Color(122, 40, 124));
        propositoV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lugarAñoVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lugarAñoVisual.setForeground(new java.awt.Color(97, 42, 104));
        lugarAñoVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lugarAñoVisual.setText("Ingrese el lugar de creación y el año (lugar-año)");

        lugarAñoV.setBackground(new java.awt.Color(190, 196, 233));
        lugarAñoV.setForeground(new java.awt.Color(122, 40, 124));
        lugarAñoV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        consignaVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        consignaVisual.setForeground(new java.awt.Color(97, 42, 104));
        consignaVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consignaVisual.setText("Prestamo: true, dias. No prestamos: false");

        consignacionV.setBackground(new java.awt.Color(190, 196, 233));
        consignacionV.setForeground(new java.awt.Color(122, 40, 124));
        consignacionV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        anchoVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        anchoVisual.setForeground(new java.awt.Color(97, 42, 104));
        anchoVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anchoVisual.setText("Ingrese el ancho y largo (anchoxlargo)");

        anchoV.setBackground(new java.awt.Color(190, 196, 233));
        anchoV.setForeground(new java.awt.Color(122, 40, 124));
        anchoV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tecnicaVisual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tecnicaVisual.setForeground(new java.awt.Color(97, 42, 104));
        tecnicaVisual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tecnicaVisual.setText("Ingrese la técnica");

        tecnicaV.setBackground(new java.awt.Color(190, 196, 233));
        tecnicaV.setForeground(new java.awt.Color(122, 40, 124));
        tecnicaV.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonVisual.setBackground(new java.awt.Color(53, 92, 125));
        botonVisual.setForeground(new java.awt.Color(255, 255, 255));
        botonVisual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arteVisual.png"))); // NOI18N
        botonVisual.setText("Crear Pieza De Arte");

        javax.swing.GroupLayout arteVisualLayout = new javax.swing.GroupLayout(arteVisual);
        arteVisual.setLayout(arteVisualLayout);
        arteVisualLayout.setHorizontalGroup(
            arteVisualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arteVisualLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(arteVisualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tecnicaVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anchoVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(visualD, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresV, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propositoV, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugarAñoV, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consignacionV, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anchoV, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tecnicaV, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoresVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propositoVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugarAñoVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consignaVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        arteVisualLayout.setVerticalGroup(
            arteVisualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arteVisualLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tituloVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visualD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoresVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoresV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propositoVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propositoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lugarAñoVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lugarAñoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consignaVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consignacionV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(anchoVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(anchoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tecnicaVisual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tecnicaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        inicioCrear.setBackground(new java.awt.Color(238, 238, 238));
        inicioCrear.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        inicioCrear.setForeground(new java.awt.Color(246, 239, 239));

        opcion1.setBackground(new java.awt.Color(9, 13, 118));
        opcion1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        opcion1.setForeground(new java.awt.Color(9, 13, 118));
        opcion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opcion1.setText("Titulo\n");

        ingresoTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonAccion1.setText("Eliminar");
        botonAccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAccion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inicioCrearLayout = new javax.swing.GroupLayout(inicioCrear);
        inicioCrear.setLayout(inicioCrearLayout);
        inicioCrearLayout.setHorizontalGroup(
            inicioCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrearLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(inicioCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingresoTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(opcion1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(botonAccion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        inicioCrearLayout.setVerticalGroup(
            inicioCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrearLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(opcion1)
                .addGap(37, 37, 37)
                .addComponent(ingresoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bannerLayout = new javax.swing.GroupLayout(banner);
        banner.setLayout(bannerLayout);
        bannerLayout.setHorizontalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(arteTridi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(10, 10, 10)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(arteDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(arteVisual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(inicioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bannerLayout.createSequentialGroup()
                    .addContainerGap(179, Short.MAX_VALUE)
                    .addComponent(blogo, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bannerLayout.setVerticalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(arteTridi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(arteDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(arteVisual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(inicioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bannerLayout.createSequentialGroup()
                    .addContainerGap(179, Short.MAX_VALUE)
                    .addComponent(blogo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(350, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
            .addGroup(panelesLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void botonAccion1ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void botonAccion2ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    // Variables declaration - do not modify                     
    private items.MyTextField anchoV;
    private javax.swing.JLabel anchoVisual;
    private javax.swing.JPanel arteDigital;
    private javax.swing.JPanel arteTridi;
    private javax.swing.JPanel arteVisual;
    private items.MyTextField autoresD;
    private javax.swing.JLabel autoresDigital;
    private items.MyTextField autoresT;
    private javax.swing.JLabel autoresTridi;
    private items.MyTextField autoresV;
    private javax.swing.JLabel autoresVisual;
    private javax.swing.JPanel banner;
    private javax.swing.JLabel bienvenida;
    private javax.swing.JLabel blogo;
    private items.Button botonAccion1;
    private items.Button botonAccion2;
    private items.Button botonDigital;
    private items.Button botonTridi;
    private items.Button botonVisual;
    private items.MyTextField consignaD;
    private javax.swing.JLabel consignaDigital;
    private items.MyTextField consignaT;
    private javax.swing.JLabel consignaTridi;
    private javax.swing.JLabel consignaVisual;
    private items.MyTextField consignacionV;
    private javax.swing.JLabel descripcion;
    private items.MyTextField dimensionT;
    private javax.swing.JLabel dimensionTridi;
    private items.MyTextField electriT;
    private javax.swing.JLabel electriTridi;
    private javax.swing.JPanel header;
    private javax.swing.JLabel img;
    private items.MyTextField ingresoTipo;
    private items.MyTextField ingresoTipo1;
    private javax.swing.JPanel inicioCrear;
    private javax.swing.JPanel inicioCrear1;
    private javax.swing.JPanel items;
    private galeriacasasubasta.LogoMenu logoMenu1;
    private items.MyTextField lugarAñoD;
    private javax.swing.JLabel lugarAñoDigital;
    private items.MyTextField lugarAñoV;
    private javax.swing.JLabel lugarAñoVisual;
    private items.MyTextField lugarT;
    private javax.swing.JLabel lugarTridi;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel opcion1;
    private javax.swing.JLabel opcion2;
    private javax.swing.JPanel paneles;
    private items.MyTextField pesoT;
    private javax.swing.JLabel pesoTridi;
    private items.MyTextField propositoD;
    private javax.swing.JLabel propositoDigital;
    private items.MyTextField propositoT;
    private javax.swing.JLabel propositoTridi;
    private items.MyTextField propositoV;
    private javax.swing.JLabel propositoVisual;
    private javax.swing.JLabel rol;
    private javax.swing.JScrollPane scrollTabla;
    private galeriacasasubasta.Tabla tabla;
    private javax.swing.JPanel tablas;
    private items.MyTextField tecnicaT;
    private javax.swing.JLabel tecnicaTridi;
    private items.MyTextField tecnicaV;
    private javax.swing.JLabel tecnicaVisual;
    private items.MyTextField tipoArchivoD;
    private javax.swing.JLabel tipoArchivoDigital;
    private items.MyTextField tipoArteD;
    private javax.swing.JLabel tipoArteDigital;
    private items.MyTextField tituloD;
    private javax.swing.JLabel tituloDigital;
    private items.MyTextField tituloT;
    private javax.swing.JLabel tituloTridi;
    private javax.swing.JLabel tituloVisual;
    private javax.swing.JLabel usuario;
    private items.MyTextField visualD;
    // End of variables declaration
}
