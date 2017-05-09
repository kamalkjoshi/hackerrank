org.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {
	private static final String FILE = "tempdata.ser";
	
	public  static void main(String[] args){
		serializeToDisk();
	}
	
	static void serializeToDisk() {
		// Write to file
		try {
			Person ted = new Person("Ted", "Neward", 39);
			Person charl = new Person("Charlotte", "Neward", 38);
			ted.setSpouse(charl);
			charl.setSpouse(ted);
			FileOutputStream fos = new FileOutputStream(FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ted);
			oos.close();
		} catch (Exception ex) {
			System.err
					.println("Exception thrown during test: " + ex.toString());
		}

		// Read from file
		try {
			FileInputStream fis = new FileInputStream(FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Person ted = (Person) ois.readObject();
			ois.close();
			System.out.println(ted);
			// Delete file
			new File(FILE).delete();
		} catch (Exception e) {
			System.err.println("Exception thrown during test: " + e.toString());
		}

	}
}