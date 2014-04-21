package Viviendas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.InitializingException;

public class ViviendasConnector implements Connector {

	@Override
	public void initFromXMLfile(URL file) throws InitializingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub
		try{
			ArrayList<String> lines = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader("src/Viviendas/viviendas2"));
			if (reader == null) return;
			String line = null;
			Iterator<CBRCase> it = cases.iterator();
			int id = (it.hasNext()) ? ((DescripcionVivienda)it.next().getDescription()).getId() : -1; 
			if (id == -1) return;
			while ((line=reader.readLine())!=null){
				String[] s = line.split("#");
				if(Integer.valueOf(s[0]) == id){
					int v = Integer.valueOf(s[19]) + 1;
					s[19] = "" + v;
					String cadena = s[0];
					for(int i=1;i<s.length;i++)
						cadena = cadena + "#" + s[i];
					lines.add(cadena);
				}else{
					lines.add(line);
				}
			}
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/Viviendas/viviendas2"));
			if (writer == null) return;
			for(Iterator<String> ite=lines.iterator();ite.hasNext();){
				String s = ite.next();
				writer.write(s);
				writer.newLine();
			}
			writer.close();
		}catch(Exception e){
			 
		}

	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		ArrayList<CBRCase> cases = new ArrayList<CBRCase>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("src/Viviendas/viviendas"));
			String line = null;
			while ((line=reader.readLine())!=null)
			{
				DescripcionVivienda vivienda = new DescripcionVivienda(line);
				SolucionVivienda solucion = new SolucionVivienda();
				solucion.setId(vivienda.getId());
				solucion.setPrecio(vivienda.getPrecio());
				
				CBRCase _case = new CBRCase();
				_case.setDescription(vivienda);
				_case.setSolution(solucion);
				cases.add(_case);
				
			}
			reader.close();

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}	
	/**
	 * Testing method
	 * @param args
	 */
	public static void main(String[] args){
		ViviendasConnector vc = new ViviendasConnector();
		Collection<CBRCase> cases = vc.retrieveAllCases();
		for(CBRCase c : cases){
			System.out.println(c);
			break;
		}
	}

}
