import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class LaunchPage implements ActionListener{
	JFrame frame=new JFrame();
	JButton myButton=new JButton("SCAN");
	public LaunchPage(){
		myButton.setBounds(100,160,200,40);
		myButton.setFocusable(false);
		myButton.addActionListener(this);

		frame.getContentPane().add(myButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==myButton){
			frame.dispose();//Close the frame window
			NewWindow secondWindow=new NewWindow();//Call the method to open the new window
		}
	}
}
