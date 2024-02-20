import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Utils {
	
	public static void reloadTable(DefaultTableModel table, ArrayList<Persona> list) {
    	table.setRowCount(0);
    	for (Persona value : list) {
    		table.addRow(PersonaToObject(value));
    	}
    }
    
    private static Object[] PersonaToObject(Persona persona) {
    	return new Object[]{persona.getNome(), persona.getCognome(), persona.getIndirizzo()};
    }
}
