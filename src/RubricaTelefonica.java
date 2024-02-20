import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RubricaTelefonica extends JFrame {
	
	private static ArrayList<Persona> rubrica = new ArrayList<>();
	private static DefaultTableModel tableModel;

    public RubricaTelefonica() {
    	
    	// Create window
        setTitle("Rubrica Telefonica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Create table
        String[] columns = {"Nome", "Cognome", "Telefono"};
        tableModel = new DefaultTableModel(columns, 0);
        
        // Buttons
        JButton newPersona = new JButton("Nuovo");
        JButton editPersona = new JButton("Modifica");
        JButton deletePersona = new JButton("Elimina");
        
        // Add to layout
        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newPersona);
        buttonPanel.add(editPersona);
        buttonPanel.add(deletePersona);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        // Fetch from DB
    	rubrica = Database.fetchPersoneFromDB();
    	Utils.reloadTable(tableModel, rubrica);
                     
        // Button listeners
        newPersona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditorPersona editor = new EditorPersona(null, rubrica, tableModel);
                editor.setVisible(true);
            }
        });

        editPersona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(RubricaTelefonica.this, "É necessario selezionare una persona da modificare.");
                } else {
                    Persona persona = rubrica.get(selectedRow);
                    EditorPersona editor = new EditorPersona(persona, rubrica, tableModel);
                    editor.setVisible(true);
                }
            }
        });

        deletePersona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(RubricaTelefonica.this, "É necessario selezionare una persona da eliminare.");
                } else {
                    Persona persona = rubrica.get(selectedRow);
                    int choice = JOptionPane.showConfirmDialog(RubricaTelefonica.this, "Eliminare dalla rubrica " + persona.getNome() + " " + persona.getCognome() + "?", "Elimina", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                    	Database.deletePersonaDB(persona.getId());
                        reloadList();
                    }
                }
            }
        });                
        
    } 
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new RubricaTelefonica().setVisible(true);
	        }
	    });
	}
	
	public static void reloadList() {
		// Fetch from DB
    	rubrica = Database.fetchPersoneFromDB();
    	Utils.reloadTable(tableModel, rubrica);
	}
}