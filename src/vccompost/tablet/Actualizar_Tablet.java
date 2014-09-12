package vccompost.tablet;

import javafx.animation.Timeline;
import vccompost.tablet.Procesar_STablet.OnTProcessListener;
import vclibs.communication.Eventos.OnComunicationListener;
import vclibs.communication.Eventos.OnConnectionListener;
import vclibs.communication.Eventos.OnTimeOutListener;
import vclibs.communication.javafx.Comunic;
import vclibs.communication.javafx.TimeOut;

public class Actualizar_Tablet implements OnComunicationListener {

	public Comunic comunic;
	TimeOut timeout;
	Procesar_STablet procTablet;
	public boolean[] valTablet = new boolean[32];
	
	Timeline timer;
	int vcont = 0;

	Thread th;
	Thread toth;
	String serverip;
	int serverport;
	String tarea = "";
	boolean timeOutEnabled = false;
	String[] vagones = new String[32];
	public static String[] horas = new String[32];
	boolean Actualizando = false;

	public Actualizar_Tablet(String sIP, int sPort) {
		serverip = sIP;
		serverport = sPort;
		/*
		 * <tip>#<n vag>#<n muestras>#
		 * <x>;<y>;<hora>;<d1>;<d2>;<d3>;<d4>&
		 * <x>;<y>;<hora>;<d1>;<d2>;<d3>;<d4>&
		 * <x>;<y>;<hora>;<d1>;<d2>;<d3>;<d4>&
		 * <x>;<y>;<hora>;<d1>;<d2>;<d3>;<d4>&/
		 */
		for(int i = 0; i < 32;i++) {
			horas[i] = "01-01-2014 12:30:20";
			valTablet[i] = false;
		}
		iniciarServer();
	}
	
	public void updHora(int nR, String nHora) {
		horas[nR] = nHora;
	}

	public void consultar() {
		if(!Actualizando)
			enviarDatos("T%/");
	}
	
	public void enviarDatos(String data) {
		if (comunic.estado != comunic.CONNECTED) {
			comunic.Detener_Actividad();
			comunic = new Comunic(serverip, serverport);
			comunic.edebug = false;
//			comunic.idebug = false;
			comunic.setConnectionListener(new OnConnectionListener() {

				@Override
				public void onConnectionstablished() {
					comunic.enviar(data);
				}

				@Override
				public void onConnectionfinished() {
					iniciarServer();
				}
			});
			th = new Thread(comunic);
			th.setDaemon(true);
			th.start();
		}
	}
	
	public void enviarPGru(int nvag, int vagActual) {
		/*
		 * <tip>#<a donde>;<desde>;/
		 */
		if(!Actualizando)
			enviarDatos("2#"+nvag+";"+vagActual+";/");
	}
	
	public void Actualizar_IP(String IP, int port) {
		comunic.Detener_Actividad();
		serverip = IP;
		serverport = port;
		iniciarServer();
	}

	private void iniciarServer() {
		comunic = new Comunic(serverport);
		comunic.edebug = false;
//		comunic.idebug = false;
		comunic.setComunicationListener(this);
		comunic.setConnectionListener(new OnConnectionListener() {

			@Override
			public void onConnectionstablished() {
				timeout = new TimeOut(8000);
				timeout.edebug = false;
				timeout.setTimeOutListener(new OnTimeOutListener() {

					@Override
					public void onTimeOutEnabled() {
						
					}

					@Override
					public void onTimeOutCancelled() {
						
					}

					@Override
					public void onTimeOut() {
						comunic.Detener_Actividad();
					}
				});
				toth = new Thread(timeout);
				toth.setDaemon(true);
				toth.start();
			}

			@Override
			public void onConnectionfinished() {

			}
		});
		th = new Thread(comunic);
		th.setDaemon(true);
		th.start();
	}

	@Override
	public void onDataReceived(String dato) {
		tarea += dato;
		if (dato.endsWith("/")) {
			timeout.cancel();
			procesar(tarea);
			comunic.Detener_Actividad();
			tarea = "";
		}
	}
	
	private void procesar(String data) {
		procTablet = new Procesar_STablet(data);
		procTablet.setOnTempProcessListener(new OnTProcessListener() {
			
			@Override
			public void onTProcessStarted(int avance) {
				
			}
			
			@Override
			public void onTProcessFinished() {
				valTablet = procTablet.valTablet;
				iniciarServer();
			}
		});
		procTablet.execute();
	}
}