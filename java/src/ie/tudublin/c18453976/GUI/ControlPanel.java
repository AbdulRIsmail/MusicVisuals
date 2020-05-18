package ie.tudublin.c18453976.GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ie.tudublin.c18453976.main.Music;
import ie.tudublin.c18453976.shapes.*;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;

public class ControlPanel extends Thread implements ActionListener {

	// frame
	private JFrame frame;

	// layered panes
	private JLayeredPane layeredPane;

	// panels
	private JPanel headerPanel;
	private JPanel mainPanel;
	private JPanel menuPanel;
	private JPanel wavePanel;
	private JPanel radialPanel;
	private JPanel tunnelPanel;

	// buttons
	private JButton mainBtn;
	private JButton waveBtn;
	private JButton radialBtn;
	private JButton tunnelBtn;

	// array list of panels
	List<JPanel> panels = new ArrayList<>();
	List<JButton> buttons = new ArrayList<>();

	// slider
	public static JSlider musicPlayer;

	// boolean
	public static boolean musicPaused = false;
	public static boolean rainbow = false;

	public static int colourReturned;

	// Launch the application.
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final ControlPanel window = new ControlPanel();
					window.frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public ControlPanel() {
		initialize();
		panels.addAll(Arrays.asList(mainPanel, wavePanel, radialPanel, tunnelPanel));
		buttons.addAll(Arrays.asList(mainBtn, waveBtn, radialBtn, tunnelBtn));
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frameSetup();

		// header panel
		headerPanelSetup();
		headerPanelUISetup();

		// main panel
		mainPanelSetup();
		mainPanelUISetup();

		// menu panel
		menuPanelSetup();
		menuPanelUISetup();

		// wave panel
		wavePanelSetup();
		wavePanelUISetup();

		// radial panel
		radialPanelSetup();
		radialPanelUISetup();

		// tunnel panel
		tunnelPanelSetup();
		tunnelPanelUISetup();

		// layer all the panels together
		layeredPaneSetup();
	}

	private void frameSetup() {
		frame = new JFrame("Music Visual");
		frame.setBounds(100, 100, 560, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void headerPanelSetup() {
		headerPanel = new JPanel();
		headerPanel.setBackground(new Color(30, 30, 30));
		headerPanel.setBounds(0, 0, 544, 57);
		headerPanel.setVisible(true);
		headerPanel.setLayout(null);
		frame.getContentPane().add(headerPanel);
	}

	private void layeredPaneSetup() {
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(112, 57, 432, 254);
		frame.getContentPane().add(layeredPane);

		layeredPane.add(mainPanel);
		layeredPane.add(wavePanel);
		layeredPane.add(radialPanel);
		layeredPane.add(tunnelPanel);
	}

	private void mainPanelSetup() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBounds(0, 0, 432, 254);
		mainPanel.setVisible(true);
		mainPanel.setLayout(null);
	}

	private void menuPanelSetup() {
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.LIGHT_GRAY);
		menuPanel.setBounds(0, 57, 112, 254);
		menuPanel.setVisible(true);
		menuPanel.setLayout(null);
		frame.getContentPane().add(menuPanel);
	}

	private void wavePanelSetup() {
		wavePanel = new JPanel();
		wavePanel.setBackground(Color.DARK_GRAY);
		wavePanel.setBounds(0, 0, 432, 254);
		wavePanel.setVisible(false);
		wavePanel.setLayout(null);
	}

	private void radialPanelSetup() {
		radialPanel = new JPanel();
		radialPanel.setBackground(Color.DARK_GRAY);
		radialPanel.setBounds(0, 0, 432, 254);
		radialPanel.setVisible(false);
		radialPanel.setLayout(null);
	}

	private void tunnelPanelSetup() {
		tunnelPanel = new JPanel();
		tunnelPanel.setBackground(Color.DARK_GRAY);
		tunnelPanel.setBounds(0, 0, 432, 254);
		tunnelPanel.setVisible(false);
		tunnelPanel.setLayout(null);
	}

	private void headerPanelUISetup() {
		final JLabel label = new JLabel("Control Panel");
		label.setBounds(0, 0, 514, 57);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		headerPanel.add(label);
	}

	private void mainPanelUISetup() {
		final JLabel title = new JLabel("Settings of General");
		title.setBounds(0, 0, 402, 39);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 16));
		title.setForeground(Color.YELLOW);
		mainPanel.add(title);

		final JComboBox colourDropdown = new JComboBox();
		colourDropdown.setModel(new DefaultComboBoxModel(
			new String[] { "Yellow", "Blue", "Red", "Cyan", "Green", "Magenta", "Orange", "Pink", "Rainbow" }));
		colourDropdown.setBounds(128, 100, 128, 23);
		mainPanel.add(colourDropdown);

		// dropdown to choose the colour
		colourDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (colourDropdown.getSelectedItem().equals("Rainbow")) {
					rainbow = true;
					Music.switchColours = true;
				} else {
					rainbow = false;
					Music.switchColours = false;
					String colourPicked = (String) colourDropdown.getSelectedItem();
					colourReturned = Music.colours.get(colourPicked);
					Wave.colour = colourReturned;
					Wave.colour1 = colourReturned;
					Radial.colour = colourReturned;
					Tunnel.colour = colourReturned;
				}
			}
		});

		final JButton hideBtn = new JButton("Hide All");
		hideBtn.setBounds(129, 50, 89, 23);
		mainPanel.add(hideBtn);

		// button to hide the displaying shape
		hideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showWave = false;
				Music.showTunnel = false;
				Music.showRadial = false;
			}
		});

		final JButton showBtn = new JButton("Show All");
		showBtn.setBounds(239, 50, 89, 23);
		mainPanel.add(showBtn);

		// button to show the displaying shape
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showWave = true;
				Music.showTunnel = true;
				Music.showRadial = true;
			}
		});

		final JButton pauseBtn = new JButton("Pause");
		pauseBtn.setBounds(129, 150, 89, 23);
		mainPanel.add(pauseBtn);

		// button to show the displaying shape
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.music.pause();
				musicPaused = true;
			}
		});

		final JButton playBtn = new JButton("Play");
		playBtn.setBounds(239, 150, 89, 23);
		mainPanel.add(playBtn);

		// button to show the displaying shape
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.music.play();
				musicPaused = false;
			}
		});

		final JLabel display = new JLabel("Display");
		display.setFont(new Font("Tahoma", Font.BOLD, 14));
		display.setForeground(Color.CYAN);
		display.setBounds(44, 50, 95, 19);
		mainPanel.add(display);

		final JLabel colour = new JLabel("Colour");
		colour.setForeground(Color.CYAN);
		colour.setFont(new Font("Tahoma", Font.BOLD, 14));
		colour.setBounds(44, 100, 95, 19);
		mainPanel.add(colour);

		final JLabel music = new JLabel("Music");
		music.setForeground(Color.CYAN);
		music.setFont(new Font("Tahoma", Font.BOLD, 14));
		music.setBounds(44, 150, 95, 19);
		mainPanel.add(music);

		final JLabel track = new JLabel("Track");
		track.setForeground(Color.CYAN);
		track.setFont(new Font("Tahoma", Font.BOLD, 14));
		track.setBounds(44, 200, 95, 19);
		mainPanel.add(track);

		musicPlayer = new JSlider();
		musicPlayer.setBounds(169, 200, 200, 26);
		musicPlayer.setValue(0);
		musicPlayer.setEnabled(false);
		mainPanel.add(musicPlayer);

		final JButton backwardBtn = new JButton("<");
		backwardBtn.setBounds(129, 200, 26, 26);
		backwardBtn.setBorder(null);
		mainPanel.add(backwardBtn);

		// button to show the displaying shape
		backwardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.music.skip(-10000);
			}
		});

		final JButton forwardBtn = new JButton(">");
		forwardBtn.setBounds(384, 200, 26, 26);
		forwardBtn.setBorder(null);
		mainPanel.add(forwardBtn);

		// button to show the displaying shape
		forwardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.music.skip(10000);
			}
		});
	}

	private void menuPanelUISetup() {
		mainBtn = new JButton("General");
		mainBtn.setBounds(10, 11, 89, 23);
		mainBtn.setFocusPainted(false);
		mainBtn.setBackground(new Color(30, 30, 30));
		mainBtn.setForeground(Color.WHITE);
		menuPanel.add(mainBtn);
		mainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(mainPanel, mainBtn);
			}
		});

		waveBtn = new JButton("Wave");
		waveBtn.setBounds(10, 57, 89, 23);
		waveBtn.setFocusPainted(false);
		waveBtn.setBorder(null);
		menuPanel.add(waveBtn);
		waveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(wavePanel, waveBtn);
			}
		});

		radialBtn = new JButton("Radial");
		radialBtn.setBounds(10, 110, 89, 23);
		radialBtn.setFocusPainted(false);
		menuPanel.add(radialBtn);
		radialBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(radialPanel, radialBtn);
			}
		});

		tunnelBtn = new JButton("Tunnel");
		tunnelBtn.setBounds(10, 165, 89, 23);
		tunnelBtn.setFocusPainted(false);
		menuPanel.add(tunnelBtn);
		tunnelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(tunnelPanel, tunnelBtn);
			}
		});
	}

	private void wavePanelUISetup() {
		final JLabel title = new JLabel("Settings of Wave");
		title.setBounds(0, 0, 402, 39);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 16));
		title.setForeground(Color.YELLOW);
		wavePanel.add(title);

		final JComboBox colourDropdown = new JComboBox();
		colourDropdown.setModel(new DefaultComboBoxModel(
			new String[] { "Yellow", "Blue", "Red", "Cyan", "Green", "Magenta", "Orange", "Pink" }));
		colourDropdown.setBounds(128, 100, 128, 23);
		wavePanel.add(colourDropdown);

		// dropdown to choose the colour
		colourDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rainbow = false;
				Music.switchColours = false;
				String colourPicked = (String) colourDropdown.getSelectedItem();
				int colourReturned = Music.colours.get(colourPicked);
				Wave.colour = colourReturned;
			}
		});

		final JComboBox colour1Dropdown = new JComboBox();
		colour1Dropdown.setModel(new DefaultComboBoxModel(
				new String[] { "Yellow", "Blue", "Red", "Cyan", "Green", "Magenta", "Orange", "Pink" }));
		colour1Dropdown.setBounds(270, 100, 128, 23);
		wavePanel.add(colour1Dropdown);

		// dropdown to choose the colour
		colour1Dropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rainbow = false;
				Music.switchColours = false;
				String colourPicked = (String) colour1Dropdown.getSelectedItem();
				int colourReturned = Music.colours.get(colourPicked);
				Wave.colour1 = colourReturned;
			}
		});

		final JButton hideBtn = new JButton("Hide");
		hideBtn.setBounds(129, 50, 89, 23);
		wavePanel.add(hideBtn);

		// button to hide the displaying shape
		hideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showWave = false;
			}
		});

		final JButton showBtn = new JButton("Show");
		showBtn.setBounds(239, 50, 89, 23);
		wavePanel.add(showBtn);

		// button to show the displaying shape
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showWave = true;
			}
		});

		final JLabel display = new JLabel("Display");
		display.setFont(new Font("Tahoma", Font.BOLD, 14));
		display.setForeground(Color.CYAN);
		display.setBounds(44, 50, 95, 19);
		wavePanel.add(display);

		final JLabel colour = new JLabel("Colour");
		colour.setForeground(Color.CYAN);
		colour.setFont(new Font("Tahoma", Font.BOLD, 14));
		colour.setBounds(44, 100, 95, 19);
		wavePanel.add(colour);
	}

	private void radialPanelUISetup() {
		final JLabel title = new JLabel("Settings of Radial");
		title.setBounds(0, 0, 402, 39);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 16));
		title.setForeground(Color.YELLOW);
		radialPanel.add(title);

		final JComboBox colourDropdown = new JComboBox();
		colourDropdown.setModel(new DefaultComboBoxModel(
			new String[] { "Yellow", "Blue", "Red", "Cyan", "Green", "Magenta", "Orange", "Pink" }));
		colourDropdown.setBounds(128, 100, 128, 23);
		radialPanel.add(colourDropdown);

		// dropdown to choose the colour
		colourDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rainbow = false;
				Music.switchColours = false;
				String colourPicked = (String) colourDropdown.getSelectedItem();
				int colourReturned = Music.colours.get(colourPicked);
				Radial.colour = colourReturned;
			}
		});

		final JButton hideBtn = new JButton("Hide");
		hideBtn.setBounds(129, 50, 89, 23);
		radialPanel.add(hideBtn);

		// button to hide the displaying shape
		hideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showRadial = false;
			}
		});

		final JButton showBtn = new JButton("Show");
		showBtn.setBounds(239, 50, 89, 23);
		radialPanel.add(showBtn);

		// button to show the displaying shape
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showRadial = true;
			}
		});

		final JLabel display = new JLabel("Display");
		display.setFont(new Font("Tahoma", Font.BOLD, 14));
		display.setForeground(Color.CYAN);
		display.setBounds(44, 50, 95, 19);
		radialPanel.add(display);

		final JLabel colour = new JLabel("Colour");
		colour.setForeground(Color.CYAN);
		colour.setFont(new Font("Tahoma", Font.BOLD, 14));
		colour.setBounds(44, 100, 95, 19);
		radialPanel.add(colour);

		JLabel speed = new JLabel("Speed");
		speed.setForeground(Color.CYAN);
		speed.setFont(new Font("Tahoma", Font.BOLD, 14));
		speed.setBounds(44, 150, 95, 19);
		radialPanel.add(speed);

		final JSlider speedControl = new JSlider(1, 10);
		speedControl.setValue(6);
		speedControl.setBounds(128, 150, 200, 26);
		radialPanel.add(speedControl);

		speedControl.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Radial.speed = ((float) speedControl.getValue() / 1000);
			}
		});
	}

	private void tunnelPanelUISetup() {
		final JLabel title = new JLabel("Settings of Tunnel");
		title.setBounds(0, 0, 402, 39);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 16));
		title.setForeground(Color.YELLOW);
		tunnelPanel.add(title);

		final JComboBox colourDropdown = new JComboBox();
		colourDropdown.setModel(new DefaultComboBoxModel(
			new String[] { "Yellow", "Blue", "Red", "Cyan", "Green", "Magenta", "Orange", "Pink" }));
		colourDropdown.setBounds(128, 100, 128, 23);
		tunnelPanel.add(colourDropdown);

		// dropdown to choose the colour
		colourDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rainbow = false;
				Music.switchColours = false;
				String colourPicked = (String) colourDropdown.getSelectedItem();
				int colourReturned = Music.colours.get(colourPicked);
				Tunnel.colour = colourReturned;
			}
		});

		final JButton hideBtn = new JButton("Hide");
		hideBtn.setBounds(129, 50, 89, 23);
		tunnelPanel.add(hideBtn);

		// button to hide the displaying shape
		hideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showTunnel = false;
			}
		});

		final JButton showBtn = new JButton("Show");
		showBtn.setBounds(239, 50, 89, 23);
		tunnelPanel.add(showBtn);

		// button to show the displaying shape
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music.showTunnel = true;
			}
		});

		final JLabel display = new JLabel("Display");
		display.setFont(new Font("Tahoma", Font.BOLD, 14));
		display.setForeground(Color.CYAN);
		display.setBounds(44, 50, 95, 19);
		tunnelPanel.add(display);

		final JLabel colour = new JLabel("Colour");
		colour.setForeground(Color.CYAN);
		colour.setFont(new Font("Tahoma", Font.BOLD, 14));
		colour.setBounds(44, 100, 95, 19);
		tunnelPanel.add(colour);
	}

	private void switchPanels(JPanel panel, JButton button) {
		for (JPanel p : panels) {
			if (p != panel) {
				p.setVisible(false);
			} else {
				p.setVisible(true);
			}
		}

		for (JButton btn : buttons) {
			if (btn != button) {
				btn.setBackground(new JButton().getBackground());
				btn.setForeground(new JButton().getForeground());
			} else {
				btn.setBackground(new Color(30, 30, 30));
				btn.setForeground(Color.WHITE);
			}
		}
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
	}
}
