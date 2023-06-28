import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class NewWindow implements ActionListener{
	JFrame frame=new JFrame();
	private JTable table;
	private JTable table_1;
	public NewWindow(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 99, -57, -23);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBounds(123, 181, 60, -71);
		frame.getContentPane().add(table);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(25, 132, 186, 25);
		frame.getContentPane().add(progressBar);
		
		table_1 = new JTable();
		table_1.setBounds(52, 35, 37, 0);
		frame.getContentPane().add(table_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(25, 23, 45, 0);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FIle 1");
		lblNewLabel_1.setBounds(25, 17, 122, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(25, 149, 186, -7);
		frame.getContentPane().add(progressBar_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(25, 46, 186, 25);
		frame.getContentPane().add(progressBar_2);

		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e){
	}
}
