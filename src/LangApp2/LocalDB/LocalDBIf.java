package LangApp2.LocalDB;

import LangApp2.Vocab.Dictionary.Dictionary;

public interface LocalDBIf {

		// TODO get list of saved dictionaries
		public String[] GetAllDictionariesNames();
	
		// TODO get basic info about selected dictionary (optional)
	
		// TODO save dictionary under given name
		public boolean SaveDictionary(Dictionary saved);
	
		// TODO load dictionary by its name
		public Dictionary LoadDictionary(String name);
	
		// TODO remove dictionary with name
		public boolean RemoveDictionary(String name);
	
}
