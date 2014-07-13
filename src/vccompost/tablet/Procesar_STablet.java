package vccompost.tablet;

import java.util.List;

import javax.swing.SwingWorker;

public class Procesar_STablet extends SwingWorker<Integer, Integer> {

	String tarea = "";
	boolean[] valTablet = new boolean[32];

	OnTProcessListener onTPListener;
	public interface OnTProcessListener {
		public void onTProcessStarted(int avance);
		public void onTProcessFinished();
	}
	public void setOnTempProcessListener(OnTProcessListener otpl) {
		onTPListener = otpl;
	}
	public Procesar_STablet(String datos) {
		tarea = datos;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		// DB#<String hora1>;... <String hora32>;/
		for(int i = 0; i < 32; i++)
			valTablet[i] = false;
		String dat = tarea.split("#")[1];
		String[] data = dat.split(";");
		int l = data.length - 1;
		String debug = "";
		for(int i = 0; i < l; i++) {
			if(data[i].equals(Actualizar_Tablet.horas[i]/*"dvwubduawhwadhqw"*/))
				valTablet[i] = true;
			debug += "\r\nVagon"+(i+1)+" = "+valTablet[i];
		}
		System.out.println(debug+" "+valTablet.length);
		return null;
	}
	
	@Override
	protected void done() {
		if (onTPListener != null)
			onTPListener.onTProcessFinished();
		super.done();
	}

	@Override
	protected void process(List<Integer> upd) {
		if (onTPListener != null)
			onTPListener.onTProcessStarted(upd.get(0));
		super.process(upd);
	}
}