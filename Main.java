import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	static JTextArea area = null;
	protected Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Main();
	}
	
	private Main() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1,8,10));
		JTextField uni = new JTextField(10);
		JTextField college = new JTextField(10);
		JTextField trade = new JTextField(10);
		JTextField prim = new JTextField(10);
		
		JTextField[] input = {uni, college, trade , prim};
		int i;
		
		for (i=0;i<4;i++) {
			final int j =i;
		input[i].addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == '\b') {
	            	input[j].setEditable(true);
	            } else {
	            	input[j].setEditable(false);
	            }
	         }
	      });
		}
		
		p.add(new JLabel("Please enter the amount of people with university degrees:\n"));
		p.add(input[0]);
		p.add(new JLabel("Please enter the amount of college graduates:\n"));
		p.add(input[1]);
		p.add(new JLabel("Please enter the amount of trade school graduates:\n"));
		p.add(input[2]);
		p.add(new JLabel("Please enter the amount of primary school graduates:\n"));
		p.add(input[3]);

		JOptionPane.showConfirmDialog(null, p, "Please Input Data", JOptionPane.OK_CANCEL_OPTION);
		
		for (i=0;i<4;i++) {  
			if (input[i].getText().equals("")) {
				System.out.println("One of the inputs was not entered! \nPlease enter all of them before pressing OK");
				System.exit(1);
			}
		}
		
		JButton jby = new JButton();
		MyComponent centre = new MyComponent(input);
		jby.add(centre);
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i;
				Slice storage= centre.slices[centre.slices.length-1];
				for (i=centre.slices.length-1;i>0;i--) {
				centre.slices[i] = centre.slices[i-1];				
				}
				centre.slices[0]=storage;
			}
		};

		jby.addActionListener(actionListener);
		centre.setLayout(new FlowLayout(0,200,250));
		add(jby,BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setBorder(
		BorderFactory.createTitledBorder("Buttons"));
		southPanel.setLayout(new GridLayout(2,1,0,10));
		JButton bby = new JButton("Press to input new data");
		JPanel legendgrid = new JPanel();
	    legendgrid.setBorder(
		BorderFactory.createTitledBorder("Legend"));
		legendgrid.setLayout(new GridLayout(1,4));
		legendgrid.add(new JLabel("Blue - University Graduates"));
		legendgrid.add(new JLabel("Green - College Graduates"));
		legendgrid.add(new JLabel("Red - Trade School Graduates"));
		legendgrid.add(new JLabel("Pink - Primary School Graduates"));
		
		southPanel.add(legendgrid);
		southPanel.add(bby);
		add(southPanel,BorderLayout.SOUTH);
		
		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(4,1,8,10));
				JTextField uni = new JTextField(10);
				JTextField college = new JTextField(10);
				JTextField trade = new JTextField(10);
				JTextField prim = new JTextField(10);
				
				JTextField[] input = {uni, college, trade , prim};
				int i;
				
				for (i=0;i<4;i++) {
					final int j =i;
				input[i].addKeyListener(new KeyAdapter() {
			         public void keyPressed(KeyEvent ke) {
			            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == '\b') {
			            	input[j].setEditable(true);
			            } else {
			            	input[j].setEditable(false);
			            }
			         }
			      });
				}
				
				p.add(new JLabel("Please enter the amount of people with university degrees:\n"));
				p.add(input[0]);
				p.add(new JLabel("Please enter the amount of college graduates:\n"));
				p.add(input[1]);
				p.add(new JLabel("Please enter the amount of trade school graduates:\n"));
				p.add(input[2]);
				p.add(new JLabel("Please enter the amount of primary school graduates:\n"));
				p.add(input[3]);
				
				JOptionPane.showConfirmDialog(null, p, "Please Input Data", JOptionPane.OK_CANCEL_OPTION);
				
				for (i=0;i<4;i++) {  
					if (input[i].getText().equals("")) {
						System.out.println("One of the inputs was not entered! \nPlease enter all of them before pressing OK");
						System.exit(1);
					}
				}
				
				for(i=0;i<4;i++) {
					sc = new Scanner(input[i].getText());
					switch(i) {
						case 0: 
							centre.slices[0] = new Slice(sc.nextLong(), Color.BLUE);
						break;
						case 1: 
							centre.slices[1] = new Slice(sc.nextLong(), Color.GREEN);
						break;
						case 2: 
							centre.slices[2] = new Slice(sc.nextLong(), Color.RED);
						break;
						case 3: 
							centre.slices[3] = new Slice(sc.nextLong(), Color.PINK);
						break;
					}
				}sc.close();
			}	
		};
		
		bby.addActionListener(actionListener1);

		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		}
}

class Slice {
   long value;
   Color color;
   public Slice(long value, Color color) {  
      this.value = value;
      this.color = color;
   }
}

class MyComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Slice[] slices;
	protected Scanner sc;
	
	
	
	MyComponent(JTextField[] txt) {
			this.slices = new Slice[4];
			int i;
			for(i=0;i<4;i++) {
				this.sc = new Scanner(txt[i].getText());
				switch(i) {
					case 0: 
						this.slices[0] = new Slice(sc.nextLong(), Color.BLUE);
					break;
					case 1: 
						this.slices[1] = new Slice(sc.nextLong(), Color.GREEN);
					break;
					case 2: 
						this.slices[2] = new Slice(sc.nextLong(), Color.RED);
					break;
					case 3: 
						this.slices[3] = new Slice(sc.nextLong(), Color.PINK);
					break;
				}
			}
		}
	
	
	public void paint(Graphics g) {
		
		((Graphics2D) g).setRenderingHint(
				RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		((Graphics2D) g).setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		Rectangle r = new  Rectangle(getWidth(),getHeight());
		
		drawPie((Graphics2D) g, r , slices);
	}
	
   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
	   
      double total = 0.0D;
      
      for (int i = 0; i < slices.length; i++) {
         total += slices[i].value;
      }
      
      double curValue = 0.0D;
      int startAngle;
      int arcAngle = 0;
      for (int i = 0; i < slices.length; i++) {
         startAngle = 90 + (int) (curValue * 360 / total);
         arcAngle = -(int) Math.ceil((slices[i].value * 360 / total));
         g.setColor(slices[i].color);
         g.fillArc(area.width/2-(area.width*2/3)/2, area.height/2-(area.height*2/3)/2, area.width*2/3, area.height*2/3, startAngle, arcAngle);
         curValue -= slices[i].value;
      }
   }
}
