import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
class EditorPersona extends JFrame {
	private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JSpinner etaField;

    public EditorPersona(Persona persona, ArrayList<Persona> rubrica, DefaultTableModel tableModel, String user) {
	    setTitle("Editor");
	    setSize(300, 200);
	    setLocationRelativeTo(null);
	
	    JLabel nomeLabel = new JLabel("Nome:");
	    JLabel cognomeLabel = new JLabel("Cognome:");
	    JLabel indirizzoLabel = new JLabel("Indirizzo:");
	    JLabel telefonoLabel = new JLabel("Telefono:");
	    JLabel etaLabel = new JLabel("Età:");
	
	    nomeField = persona == null ? new JTextField(20) : new JTextField(persona.getNome());
	    cognomeField = persona == null ? new JTextField(20) : new JTextField(persona.getCognome());
	    indirizzoField = persona == null ? new JTextField(20) : new JTextField(persona.getIndirizzo());
	    telefonoField = persona == null ? new JTextField(20) : new JTextField(persona.getTelefono());
	
        SpinnerModel spinnerModel = persona == null ? new SpinnerNumberModel(0, 0, 100, 1) : new SpinnerNumberModel((int) persona.getEta(), 0, 100, 1);
        etaField = new JSpinner(spinnerModel);
	    
	    // Layout
	
	    JButton saveButton = new JButton("Salva");	
	    JButton cancelButton = new JButton("Annulla");
	   
	    JPanel panel = new JPanel(new GridLayout(6, 2));
	    panel.add(nomeLabel);
	    panel.add(nomeField);
	    panel.add(cognomeLabel);
	    panel.add(cognomeField);
	    panel.add(indirizzoLabel);
	    panel.add(indirizzoField);
	    panel.add(telefonoLabel);
	    panel.add(telefonoField);
	    panel.add(etaLabel);
	    panel.add(etaField);
	    panel.add(saveButton);
	    panel.add(cancelButton);

	    getContentPane().add(panel);
	    
	    // Button listeners
	    
	    saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nome = nomeField.getText();
	            String cognome = cognomeField.getText();
	            String indirizzo = indirizzoField.getText();
	            String telefono = telefonoField.getText();
	            Integer eta = (Integer) etaField.getValue();
	           
            	if (persona == null) {
            		Database.addPersonaDB(nome, cognome, indirizzo, telefono, eta, user);             
            	}
	            else {
	            	Database.editPersonaDB(persona.getId(), nome, cognome, indirizzo, telefono, eta);
	            }
	            
	            RubricaTelefonica.reloadList(user);
		        dispose();

	        }
	    });
	    
	    cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	        }
	    });
	
    }
}