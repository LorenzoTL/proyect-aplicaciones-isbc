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

	}

	@Override
	public void close() {

	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		
	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		
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
		return null;
	}	
	
	public static void storeCases(int id){
		try{
			ArrayList<String> lines = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader("src/Viviendas/viviendas"));
			String line = null;
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
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/Viviendas/viviendas"));
			for(Iterator<String> ite=lines.iterator();ite.hasNext();){
				String s = ite.next();
				writer.write(s);
				writer.newLine();
			}
			writer.close();
		}catch(Exception e){
			 
		}
	}

	public static void main(String[] args){
		for (int i=0;i<10;i++)
			ViviendasConnector.storeCases(i);
	}
	
}
