import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class NotesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JFileChooser chooser;
	private static JTextArea NotesTextBox;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotesGUI frame = new NotesGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NotesGUI() {
		
		chooser =  new JFileChooser();
		NotesTextBox  = new JTextArea();		
		setResizable(false);
		setTitle("Projectile\u2122 Flight Notes ALPHA V 1.00");
		setBounds(100, 100, 471, 785);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NotesTextBox = new JTextArea();
		NotesTextBox.setBounds(10, 11, 431, 685);
		contentPane.add(NotesTextBox);
		
		// Saving text file
		
		JButton btnSaveNotes = new JButton("Save Notes");
		btnSaveNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = chooser.showSaveDialog(null);
				File file = chooser.getSelectedFile();
				BufferedWriter writer = null;
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
				    try
				    {
				    writer = new BufferedWriter( new FileWriter( file.getAbsolutePath() + ".txt" ));
				    writer.write( NotesTextBox.getText() );
				    writer.close( );
				    }
				    catch (IOException e1)
				    {
				    JOptionPane.showMessageDialog(null, "ERROR: Could not be saved!", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				    dispose();
				    }
				}
			}
		});
		btnSaveNotes.setBounds(316, 707, 109, 23);
		contentPane.add(btnSaveNotes);
		
		// Loading text file
		
		JButton btnLoadNotes = new JButton("Load Notes");
		btnLoadNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotesTextBox.setText(" ");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null); //replace null with your swing container
				File file = null;
				String line = null;
				BufferedReader in = null;
				if(returnVal == JFileChooser.APPROVE_OPTION)  
				  file = chooser.getSelectedFile();    

				try {
					in = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e3) {
					e3.printStackTrace();
				}
				
				try {
					line = in.readLine();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					}
				
				while(line != null)
				{
				  NotesTextBox.append(line + "\n");
				  try {
					line = in.readLine();
				  } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				  }
				}
			}
		});
		btnLoadNotes.setBounds(156, 707, 119, 23);
		contentPane.add(btnLoadNotes);
		
		// Cancel
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 707, 109, 23);
		contentPane.add(btnCancel);
		

	}
}
