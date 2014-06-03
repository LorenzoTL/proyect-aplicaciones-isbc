package OWL;

import java.util.ArrayList;
import java.util.Iterator;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;

public class FamiliaReal {
	
	private static FamiliaReal _instance = null;
	
	OntoBridge ob;
	
	public static FamiliaReal getInstance(){
		if (_instance == null)
			_instance = new FamiliaReal();
		return _instance;
	}
	
	public FamiliaReal(){
		ob = new OntoBridge();
		ob.initWithPelletReasoner();
		OntologyDocument mainOnto = new OntologyDocument("http://www.semanticweb.org/j/ontologies/2014/4/FamiliaReal.owl","file:owl/familia.owl");
		ob.loadOntology(mainOnto, new ArrayList<OntologyDocument>(), false); 
	}
	
	public void printAllClases(){
		System.out.println("\nALL CLASSES OF http://www.semanticweb.org/j/ontologies/2014/4/FamiliaReal.owl");
		for(Iterator<String> iter = ob.listAllClasses(); iter.hasNext();)
			System.out.println(iter.next());
	}
	
	public void printSubClasesOf(String s){
		System.out.println("\nDIRECT SUBCLASSES OF http://www.semanticweb.org/j/ontologies/2014/4/FamiliaReal.owl#" + s);
		for(Iterator<String> iter = ob.listSubClasses(s, true); iter.hasNext();)
			System.out.println(iter.next());
	}
	
	public void createInstanceFamiliaReal(){
		System.out.println("\n CREACION DE INSTANCIAS DE LA FAMILIA REAL");
		
		/** Instancias **/
		//Rey Juan Carlos y la Reina Sofia
		ob.createInstance("Hombre", "Juan_Carlos");
		ob.createInstance("Mujer", "Sofia");
		//Felipe y Leticia : Infata_Leonor y Infanta_Sofia
		ob.createInstance("Hombre", "Felipe");
		ob.createInstance("Mujer", "Leticia");
		ob.createInstance("Mujer", "Infanta_Leonor");
		ob.createInstance("Mujer", "Infanta_Sofia");
		//Cristina y Urdangarin: Irene, Miguel, Pablo y Juan
		ob.createInstance("Mujer", "Cristina");
		ob.createInstance("Hombre", "Urdangarin");
		ob.createInstance("Mujer", "Irene");
		ob.createInstance("Hombre", "Miguel");
		ob.createInstance("Hombre", "Pablo");
		ob.createInstance("Hombre", "Juan");
		//Elena y Marichalar: Froilan y Victoria
		ob.createInstance("Mujer", "Elena");
		ob.createInstance("Hombre", "Marichalar");
		ob.createInstance("Hombre", "Froilan");
		ob.createInstance("Mujer", "Victoria");
		
		
	}
	
	public void createPropertiesFamilia(){
		System.out.println("\n CREACION DE LAS PROPIEDADES DE LA FAMILIA");
		
		/** Propiedades **/
		ob.createDataTypeProperty("Juan_Carlos", "es_Rey", true);
		ob.createOntProperty("Juan_Carlos", "esta_casado_con", "Sofia");
		ob.createOntProperty("Juan_Carlos", "es_progenitor", "Felipe");
		ob.createOntProperty("Juan_Carlos", "es_progenitor", "Elena");
		ob.createOntProperty("Juan_Carlos", "es_progenitor", "Cristina");
		ob.createOntProperty("Sofia", "es_progenitor", "Felipe");
		ob.createOntProperty("Sofia", "es_progenitor", "Elena");
		ob.createOntProperty("Sofia", "es_progenitor", "Cristina");
		
		ob.createOntProperty("Felipe", "esta_casado_con", "Leticia");
		ob.createOntProperty("Felipe", "es_progenitor", "Infanta_Leonor");
		ob.createOntProperty("Felipe", "es_progenitor", "Infanta_Sofia");
		ob.createOntProperty("Leticia", "es_progenitor", "Infanta_Leonor");
		ob.createOntProperty("Leticia", "es_progenitor", "Infanta_Sofia");
		
		ob.createOntProperty("Cristina", "esta_casado_con", "Urdangarin");
		ob.createOntProperty("Cristina", "es_progenitor", "Irene");
		ob.createOntProperty("Cristina", "es_progenitor", "Miguel");
		ob.createOntProperty("Cristina", "es_progenitor", "Pablo");
		ob.createOntProperty("Cristina", "es_progenitor", "Juan");
		ob.createOntProperty("Urdangarin", "es_progenitor", "Irene");
		ob.createOntProperty("Urdangarin", "es_progenitor", "Miguel");
		ob.createOntProperty("Urdangarin", "es_progenitor", "Pablo");
		ob.createOntProperty("Urdangarin", "es_progenitor", "Juan");
		
		ob.createOntProperty("Elena", "esta_divorciado_con", "Marichalar");
		ob.createOntProperty("Elena", "es_progenitor", "Froilan");
		ob.createOntProperty("Elena", "es_progenitor", "Victoria");
		ob.createOntProperty("Marichalar", "es_progenitor", "Froilan");
		ob.createOntProperty("Marichalar", "es_progenitor", "Victoria");
		
		ob.createOntProperty("Felipe", "es_hermano", "Elena");
		ob.createOntProperty("Felipe", "es_hermano", "Cristina");
		ob.createOntProperty("Cristina", "es_hermano", "Elena");
		
		ob.createOntProperty("Infanta_Leonor", "es_hermano", "Infanta_Sofia");
		
		ob.createOntProperty("Irene", "es_hermano", "Miguel");
		ob.createOntProperty("Irene", "es_hermano", "Pablo");
		ob.createOntProperty("Irene", "es_hermano", "Juan");
		ob.createOntProperty("Miguel", "es_hermano", "Pablo");
		ob.createOntProperty("Miguel", "es_hermano", "Juan");
		ob.createOntProperty("Pablo", "es_hermano", "Juan");
		
		ob.createOntProperty("Froilan", "es_hermano", "Victoria");
	}
	
	public void createInstanceFoto(){
		System.out.println("\n CREACION DE INSTANCIAS DE LAS FOTOS");
		
		/** Instancias **/
		for(int i=1;i<=23;i++){
			ob.createInstance("Foto", "foto" + i);
		}
	}
	
	public void createPropertiesFotos(){
		System.out.println("\n CREACION DE PROPIEDADES DE LAS FOTOS");
		
		/** Propiedades **/
		ob.createDataTypeProperty("foto1", "url_Foto", "file:Fotos/foto1.png#");
		ob.createOntProperty("foto1", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto1", "aparece", "Sofia");
		ob.createOntProperty("foto1", "aparece", "Felipe");
		ob.createOntProperty("foto1", "aparece", "Leticia");
		ob.createOntProperty("foto1", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto1", "aparece", "Infanta_Sofia");
		ob.createOntProperty("foto1", "aparece", "Cristina");
		ob.createOntProperty("foto1", "aparece", "Urdangarin");
		ob.createOntProperty("foto1", "aparece", "Irene");
		ob.createOntProperty("foto1", "aparece", "Miguel");
		ob.createOntProperty("foto1", "aparece", "Pablo");
		ob.createOntProperty("foto1", "aparece", "Juan");
		ob.createOntProperty("foto1", "aparece", "Elena");
		ob.createOntProperty("foto1", "aparece", "Marichalar");
		ob.createOntProperty("foto1", "aparece", "Froilan");
		ob.createOntProperty("foto1", "aparece", "Victoria");
		
		ob.createDataTypeProperty("foto2", "url_Foto", "file:Fotos/foto2.png#");
		ob.createOntProperty("foto2", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto2", "aparece", "Sofia");
		ob.createOntProperty("foto2", "aparece", "Felipe");
		ob.createOntProperty("foto2", "aparece", "Leticia");
		ob.createOntProperty("foto2", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto2", "aparece", "Infanta_Sofia");
		ob.createOntProperty("foto2", "aparece", "Cristina");
		ob.createOntProperty("foto2", "aparece", "Urdangarin");
		ob.createOntProperty("foto2", "aparece", "Irene");
		ob.createOntProperty("foto2", "aparece", "Miguel");
		ob.createOntProperty("foto2", "aparece", "Pablo");
		ob.createOntProperty("foto2", "aparece", "Juan");
		ob.createOntProperty("foto2", "aparece", "Elena");
		ob.createOntProperty("foto2", "aparece", "Marichalar");
		ob.createOntProperty("foto2", "aparece", "Froilan");
		ob.createOntProperty("foto2", "aparece", "Victoria");
		
		ob.createDataTypeProperty("foto3", "url_Foto", "file:Fotos/foto3.jpg#");
		ob.createOntProperty("foto3", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto3", "aparece", "Sofia");
		ob.createOntProperty("foto3", "aparece", "Felipe");
		ob.createOntProperty("foto3", "aparece", "Leticia");
		ob.createOntProperty("foto3", "aparece", "Cristina");
		ob.createOntProperty("foto3", "aparece", "Urdangarin");
		ob.createOntProperty("foto3", "aparece", "Elena");
		
		ob.createDataTypeProperty("foto4", "url_Foto", "file:Fotos/foto4.jpg#");
		ob.createOntProperty("foto4", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto4", "aparece", "Sofia");
		ob.createOntProperty("foto4", "aparece", "Felipe");
		ob.createOntProperty("foto4", "aparece", "Leticia");
		ob.createOntProperty("foto4", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto4", "aparece", "Infanta_Sofia");
		
		ob.createDataTypeProperty("foto5", "url_Foto", "file:Fotos/foto5.jpg#");
		ob.createOntProperty("foto5", "aparece", "Felipe");
		ob.createOntProperty("foto5", "aparece", "Leticia");
		ob.createOntProperty("foto5", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto5", "aparece", "Infanta_Sofia");
		
		ob.createDataTypeProperty("foto6", "url_Foto", "file:Fotos/foto6.jpg#");;
		ob.createOntProperty("foto6", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto6", "aparece", "Infanta_Sofia");
		ob.createOntProperty("foto6", "aparece", "Irene");
		ob.createOntProperty("foto6", "aparece", "Miguel");
		ob.createOntProperty("foto6", "aparece", "Pablo");
		ob.createOntProperty("foto6", "aparece", "Juan");
		ob.createOntProperty("foto6", "aparece", "Froilan");
		ob.createOntProperty("foto6", "aparece", "Victoria");
		
		ob.createDataTypeProperty("foto7", "url_Foto", "file:Fotos/foto7.png#");
		ob.createOntProperty("foto7", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto7", "aparece", "Sofia");
		ob.createOntProperty("foto7", "aparece", "Felipe");
		ob.createOntProperty("foto7", "aparece", "Leticia");
		
		ob.createDataTypeProperty("foto8", "url_Foto", "file:Fotos/foto8.jpg#");
		ob.createOntProperty("foto8", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto8", "aparece", "Sofia");
		ob.createOntProperty("foto8", "aparece", "Elena");
		ob.createOntProperty("foto8", "aparece", "Marichalar");
		ob.createOntProperty("foto8", "aparece", "Froilan");
		ob.createOntProperty("foto8", "aparece", "Victoria");
		
		ob.createDataTypeProperty("foto9", "url_Foto", "file:Fotos/foto9.png#");
		ob.createOntProperty("foto9", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto9", "aparece", "Felipe");
		
		ob.createDataTypeProperty("foto10", "url_Foto", "file:Fotos/foto10.png#");
		ob.createOntProperty("foto10", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto10", "aparece", "Felipe");
		
		ob.createDataTypeProperty("foto11", "url_Foto", "file:Fotos/foto11.png#");
		ob.createOntProperty("foto11", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto11", "aparece", "Felipe");
		
		ob.createDataTypeProperty("foto12", "url_Foto", "file:Fotos/foto12.png#");
		ob.createOntProperty("foto12", "aparece", "Juan_Carlos");
		
		ob.createDataTypeProperty("foto13", "url_Foto", "file:Fotos/foto13.png#");
		ob.createOntProperty("foto13", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto13", "aparece", "Infanta_Sofia");
		
		ob.createDataTypeProperty("foto14", "url_Foto", "file:Fotos/foto14.jpg#");
		ob.createOntProperty("foto14", "aparece", "Elena");
		
		ob.createDataTypeProperty("foto15", "url_Foto", "file:Fotos/foto15.jpg#");
		ob.createOntProperty("foto15", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto15", "aparece", "Sofia");
		ob.createOntProperty("foto15", "aparece", "Felipe");
		ob.createOntProperty("foto15", "aparece", "Leticia");
		ob.createOntProperty("foto15", "aparece", "Cristina");
		ob.createOntProperty("foto15", "aparece", "Urdangarin");
		ob.createOntProperty("foto15", "aparece", "Elena");
		
		ob.createDataTypeProperty("foto16", "url_Foto", "file:Fotos/foto16.jpg#");
		ob.createOntProperty("foto16", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto16", "aparece", "Sofia");
		ob.createOntProperty("foto16", "aparece", "Felipe");
		
		ob.createDataTypeProperty("foto17", "url_Foto", "file:Fotos/foto17.jpg#");
		ob.createOntProperty("foto17", "aparece", "Juan_Carlos");
		
		ob.createDataTypeProperty("foto18", "url_Foto", "file:Fotos/foto18.jpg#");
		ob.createOntProperty("foto18", "aparece", "Cristina");
		ob.createOntProperty("foto18", "aparece", "Urdangarin");
		ob.createOntProperty("foto18", "aparece", "Irene");
		ob.createOntProperty("foto18", "aparece", "Miguel");
		ob.createOntProperty("foto18", "aparece", "Pablo");
		ob.createOntProperty("foto18", "aparece", "Juan");
		
		ob.createDataTypeProperty("foto19", "url_Foto", "file:Fotos/foto19.JPG#");
		ob.createOntProperty("foto19", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto19", "aparece", "Sofia");
		ob.createOntProperty("foto19", "aparece", "Felipe");
		ob.createOntProperty("foto19", "aparece", "Leticia");
		ob.createOntProperty("foto19", "aparece", "Infanta_Leonor");
		ob.createOntProperty("foto19", "aparece", "Infanta_Sofia");
		ob.createOntProperty("foto19", "aparece", "Cristina");
		ob.createOntProperty("foto19", "aparece", "Urdangarin");
		ob.createOntProperty("foto19", "aparece", "Irene");
		ob.createOntProperty("foto19", "aparece", "Miguel");
		ob.createOntProperty("foto19", "aparece", "Pablo");
		ob.createOntProperty("foto19", "aparece", "Juan");
		ob.createOntProperty("foto19", "aparece", "Elena");
		ob.createOntProperty("foto19", "aparece", "Marichalar");
		ob.createOntProperty("foto19", "aparece", "Froilan");
		ob.createOntProperty("foto19", "aparece", "Victoria");
		
		ob.createDataTypeProperty("foto20", "url_Foto", "file:Fotos/foto20.jpg#");
		ob.createOntProperty("foto20", "aparece", "Felipe");
		ob.createOntProperty("foto20", "aparece", "Leticia");
		
		ob.createDataTypeProperty("foto21", "url_Foto", "file:Fotos/foto21.jpg#");
		ob.createOntProperty("foto21", "aparece", "Juan_Carlos");
		
		ob.createDataTypeProperty("foto22", "url_Foto", "file:Fotos/foto22.jpg#");
		ob.createOntProperty("foto22", "aparece", "Juan_Carlos");
		
		ob.createDataTypeProperty("foto23", "url_Foto", "file:Fotos/foto23.jpg#");
		ob.createOntProperty("foto23", "aparece", "Juan_Carlos");
		ob.createOntProperty("foto23", "aparece", "Sofia");
		ob.createOntProperty("foto23", "aparece", "Felipe");
		ob.createOntProperty("foto23", "aparece", "Leticia");
	}
	
	public ArrayList<String> instanceInfered(String s){
		System.out.println("\n INSTANCIAS INFERIDAS PARA " + s.toUpperCase());
		ArrayList<String> list = new ArrayList<String>();
		Iterator<String> fotos = ob.listInstances(s);
		while(fotos.hasNext())
		{
			String instance = fotos.next();
			ArrayList<String> properties = new ArrayList<String>();
			ArrayList<String> values = new ArrayList<String>();
			ob.listInstancePropertiesValues(instance, properties, values);
			for(int i=0; i<properties.size(); i++){
				if(properties.get(i).contains("url_Foto")){
					String[] value = values.get(i).split("#");
					list.add(value[0]);
				}
			}
		}
		return list;
	}
	
}
