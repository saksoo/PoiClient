package PoiServicesClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;

public class ControlPanel extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	private final int T;		// in ms
	private final Application application;
	
	private final JTabbedPane tabs = new JTabbedPane();
	private final JPanel panel1 = new JPanel();
	private final JPanel panel2 = new JPanel();
	private final JPanel panel1_center = new JPanel();
	private final JPanel panel2_center = new JPanel();
	private final JPanel panel2_west = new JPanel();
	
	private final JButton commitPOI = new JButton("Commit POI to database");
	private final JButton searchPoi = new JButton("Search for POI");
	
	private final JTextField textFieldX = new JTextField("0",5);
	private final JTextField textFieldY = new JTextField("0",5);
	private final JTextField textFieldName = new JTextField("",15);
	private final JComboBox<String> primary_types = new JComboBox<String>(new String[] {"Entertainment", "Education", "Food" });
	private final JComboBox<String> type1 = new JComboBox<String>(new String[] { "Drink", "Cinema", "Site seeing" });
	private final JComboBox<String> type2 = new JComboBox<String>(new String[] { "Library", "University" });
	private final JComboBox<String> type3 = new JComboBox<String>(new String[] { "Fast food restaurant", "Take away restaurant", "Typical restaurant" });
	
	private final JTextField textFieldX_s = new JTextField("0",5);
	private final JTextField textFieldY_s = new JTextField("0",5);
	private final JTextField textFieldR_s = new JTextField("3",5);
	
	private final JToggleButton t1 = new JToggleButton("Drink");
	private final JToggleButton t2 = new JToggleButton("Cinema");
	private final JToggleButton t3 = new JToggleButton("Site seeing");
	private final JToggleButton t4 = new JToggleButton("Library");
	private final JToggleButton t5 = new JToggleButton("University");
	private final JToggleButton t6 = new JToggleButton("Fast food restaurant");
	private final JToggleButton t7 = new JToggleButton("Take away restaurant");
	private final JToggleButton t8 = new JToggleButton("Typical restaurant");
	
	private DefaultListModel<String> dlm = new DefaultListModel<>();
	private JList<String> poiList = new JList<String>(dlm);
	
	private int screen_size = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();	
	private final int WIDTH = 450;
	private final int HEIGHT = 400;
	
	public ControlPanel(Application a) {
		super("Client Control Panel : " + a.regInformation.username);		
		this.application = a;
		this.T = application.cpInformation.T;
		
		setSize(WIDTH,HEIGHT);
		setLocation(screen_size/2-WIDTH/2,HEIGHT/12);
		setLayout(new BorderLayout());
		addWindowListener(this);
		add(tabs);		
		
		tabs.addTab("Insert a new POI", null, panel1, "Define a poi and insert it");
		tabs.setMnemonicAt(0, KeyEvent.VK_1);
		tabs.addTab("Search for a POI", null, panel2, "Search for known pois");
		tabs.setMnemonicAt(1, KeyEvent.VK_2);
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		
		panel1_center.setLayout(new FlowLayout());
		
		panel1.add(new JLabel("Please fill the following fields ..."), BorderLayout.NORTH);
		panel1.add(panel1_center, BorderLayout.CENTER);
		panel1.add(commitPOI, BorderLayout.SOUTH);
		panel1_center.add(new JLabel("X:"));
		panel1_center.add(textFieldX);		
		panel1_center.add(new JLabel("Y:"));
		panel1_center.add(textFieldY);		
		panel1_center.add(new JLabel("name:"));
		panel1_center.add(textFieldName);
		panel1_center.add(new JLabel("Primary type:"));
		panel1_center.add(primary_types);
		panel1_center.add(new JLabel("Subtypes:"));
		panel1_center.add(type1);
		panel1_center.add(type2);
		panel1_center.add(type3);
		panel1_center.add(poiList);
		
		type2.setVisible(false);
		type3.setVisible(false);
		
		
		panel2.add(new JLabel("Please fill the following fields ..."), BorderLayout.NORTH);
		panel2.add(searchPoi, BorderLayout.SOUTH);
		panel2.add(panel2_center, BorderLayout.CENTER);
		panel2_center.add(new JLabel("X:"));
		panel2_center.add(textFieldX_s);		
		panel2_center.add(new JLabel("Y:"));
		panel2_center.add(textFieldY_s);
		panel2_center.add(new JLabel("R:"));
		panel2_center.add(textFieldR_s);
		panel2_center.add(poiList);
		panel2.add(panel2_west, BorderLayout.WEST);
		panel2_west.setLayout(new GridLayout(10,1));
		panel2_west.add(new JLabel("Known Types:"));
		panel2_west.add(t1);
		panel2_west.add(t2);
		panel2_west.add(t3);
		panel2_west.add(t4);
		panel2_west.add(t5);
		panel2_west.add(t6);
		panel2_west.add(t7);
		panel2_west.add(t8);
		
		dlm.addElement("Search results:                                ");
		
		primary_types.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				type1.setVisible(primary_types.getSelectedIndex() == 0);				
				type2.setVisible(primary_types.getSelectedIndex() == 1);
				type3.setVisible(primary_types.getSelectedIndex() == 2);
			}
		});
		
		commitPOI.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String username = application.regInformation.username;
					String password = application.regInformation.password;
					int x = Integer.parseInt(textFieldX.getText());
					int y = Integer.parseInt(textFieldY.getText());
					String type = null;
					int type_offset = primary_types.getSelectedIndex();
					if (type_offset == 0) {
						type = type1.getSelectedItem().toString();
					} else if (type_offset == 1) {
						type = type2.getSelectedItem().toString();
					} else if (type_offset == 2) {
						type = type3.getSelectedItem().toString();
					}
					String name = textFieldName.getText();
					
					String response = application.setMonitorData(username, password, x, y, type, name);
					JOptionPane.showMessageDialog(null, response);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		});
		
		searchPoi.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (updateGUI() == false) {
					JOptionPane.showMessageDialog(null, "Error while getting data");
				}
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		new GetSetWorker(T).execute();
	}
	
	boolean updateGUI() {
		try {
			String username = application.regInformation.username;
			String password = application.regInformation.password;
			int x = Integer.parseInt(textFieldX_s.getText());
			int y = Integer.parseInt(textFieldY_s.getText());
			int R = Integer.parseInt(textFieldR_s.getText());
			
			String response = application.getMapData(username, password, x, y, R);
			dlm.clear();
			dlm.addElement("Search results:                                ");
			
			String[] pois = response.split("[$]");
			for(String poi : pois) {						
				String[] f = poi.split("#");
				String t = f[2];
				System.out.println("t = " + t);
				if (t.equals("Drink") && t1.isSelected() ||
						t.equals("Cinema") && t2.isSelected() ||
						t.equals("Site seeing") && t3.isSelected() ||
						t.equals("Library") && t4.isSelected() ||
						t.equals("University") && t5.isSelected() ||
						t.equals("Fast food restaurant") && t6.isSelected() ||
						t.equals("Take away restaurant") && t7.isSelected() ||
						t.equals("Typical restaurant") && t8.isSelected() ||
						((t1.isSelected() == false &&
							t2.isSelected() == false &&
							t3.isSelected() == false &&
							t4.isSelected() == false &&
							t5.isSelected() == false &&
							t6.isSelected() == false &&
							t7.isSelected() == false &&
							t8.isSelected() == false))) {
					dlm.addElement(poi.replace("#", "     "));
				}
			}
		} catch (Exception ex) {
			return false;			
		}
		return true;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		application.stop();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	class GetSetWorker extends SwingWorker<Void, Integer> {
		private final int T;
		private String result;
		
	    public GetSetWorker(int t) {
			super();
			System.out.println("A new worker has been created in client! with T= " + t);
			T = t;
		}

		protected Void doInBackground() throws Exception {
	        // Do a time-consuming task.
			Thread.sleep(T);
			return null;
	    }

	    protected void done() {
	    	System.out.println("Update!");
	    	updateGUI();
	    	new GetSetWorker(T).execute();
	    }
	}
	
}

