package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Desktop;

public class Gui extends JFrame {

	private Control ctrl;
	private boolean darkmode, top, sett;

	private JPanel contentPane, sidebar, topStorys, settingsLabel;

	private ImagePanel top1, top2, top3, top4, top5, top6, top7, top8, top9;
	private JLabel topHeader, header1, header2, header3, header4, header5, header6, header7, header8, header9;

	private JButton close, settings, topStories;
	private JLabel footer;

	private JTextField apikey, lang;
	private JLabel apiL, langL, settHeader;
	private JButton sync, changeColor, apply;

	private String a404 = "https://upload.wikimedia.org/wikipedia/en/d/d1/Image_not_available.png";

	private News[] news;

	/**
	 * Launch the application.
	 *
	 * /** Create the frame.
	 */
	public Gui(Control ctrl) {
		this.ctrl = ctrl;
		darkmode = Boolean.valueOf(ctrl.getSettings()[1]);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setUndecorated(true);
		setTitle("News");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		sidebar = new JPanel();
		sidebar.setBackground(Color.decode("#4a345f"));
		sidebar.setLayout(null);
		sidebar.setBounds(0, 0, 100, 750);
		contentPane.add(sidebar);

		topStories = new JButton("TOP");
		topStories.setBounds(0, 0, 100, 100);
		topStories.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));
		topStories.setFocusable(false);
		topStories.setForeground(Color.BLACK);
		topStories.setOpaque(false);
		topStories.setContentAreaFilled(false);
		topStories.setBorderPainted(false);
		topStories.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!top)
					changePanel("topstories");
			}
		});
		sidebar.add(topStories);

		settings = new JButton("...");
		settings.setBounds(0, 500, 100, 100);
		settings.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));
		settings.setFocusable(false);
		settings.setOpaque(false);
		settings.setContentAreaFilled(false);
		settings.setBorderPainted(true);
		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!sett)
					changePanel("settings");
			}
		});
		sidebar.add(settings);

		close = new JButton("X");
		close.setBounds(0, 600, 100, 100);
		close.setBorder(null);
		close.setFocusable(false);
		close.setOpaque(false);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sidebar.add(close);

		footer = new JLabel("Made by Niki | powered by NewsAPI.org");
		footer.setForeground(Color.decode("#357272"));
		footer.setBackground(Color.WHITE);
		footer.setBounds(750, 650, 250, 50);
		footer.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					openWebpage(new URL("https://newsapi.org/"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(footer);

		topStorys = new JPanel();
		topStorys.setBackground(Color.WHITE);
		topStorys.setLayout(null);
		topStorys.setBounds(100, 0, 900, 650);
		topStorys.setVisible(false);
		contentPane.add(topStorys);

		settingsLabel = new JPanel();
		settingsLabel.setBackground(Color.WHITE);
		settingsLabel.setLayout(null);
		settingsLabel.setBounds(100, 0, 900, 650);
		settingsLabel.setVisible(true);
		contentPane.add(settingsLabel);

		FrameDragListener frameDragListener = new FrameDragListener(this);
		addMouseListener(frameDragListener);
		addMouseMotionListener(frameDragListener);

		if (!ctrl.getSettings()[2].equals("null") && ctrl.getSettings()[2] != null) {
			sync();
			topStorys.setVisible(true);
			settingsLabel.setVisible(false);
			addTopContent();
			initColor();
			changePanel("topstories");
		} else {
			settingsLabel.setVisible(true);
			topStorys.setVisible(false);
			addSettiContent();
			initColor();
			changePanel("settings");
		}
	}

	private void changePanel(String name) {

		topStorys.setVisible(false);
		topStorys.removeAll();
		top = false;
		topStories.setBorderPainted(false);
		settingsLabel.setVisible(false);
		settingsLabel.removeAll();
		sett = false;
		settings.setBorderPainted(false);

		switch (name) {
		case "topstories":
			topStorys.setVisible(true);
			topStories.setBorderPainted(true);

			top = true;
			addTopContent();
			break;
		case "settings":
			settingsLabel.setVisible(true);
			settings.setBorderPainted(true);

			sett = true;
			addSettiContent();
			break;
		}
	}

	private void initColor() {
		if (darkmode)
			changeDark();
		else
			changeWhite();
	}

	private void changeColor() {
		if (!darkmode) {
			changeDark();
			darkmode = true;
		} else {
			changeWhite();
			darkmode = false;
		}
	}

	private void changeWhite() {
		contentPane.setBackground(Color.WHITE);

		sidebar.setBackground(Color.decode("#4a345f"));

		topStorys.setBackground(Color.WHITE);
		settingsLabel.setBackground(Color.WHITE);

		topStories.setForeground(Color.BLACK);
		topStories.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));

		settings.setForeground(Color.BLACK);
		settings.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));

		close.setBackground(Color.WHITE);
		close.setForeground(Color.BLACK);

		footer.setForeground(Color.decode("#357272"));
		footer.setBackground(Color.WHITE);

		if (top) {
			topHeader.setForeground(Color.BLACK);
			if (this.news != null) {
				header1.setBackground(Color.decode("#4a345f"));
				header2.setBackground(Color.decode("#4a345f"));
				header3.setBackground(Color.decode("#4a345f"));
				header4.setBackground(Color.decode("#4a345f"));
				header5.setBackground(Color.decode("#4a345f"));
				header6.setBackground(Color.decode("#4a345f"));
				header7.setBackground(Color.decode("#4a345f"));
				header8.setBackground(Color.decode("#4a345f"));
				header9.setBackground(Color.decode("#4a345f"));
				header1.setForeground(Color.BLACK);
				header2.setForeground(Color.BLACK);
				header3.setForeground(Color.BLACK);
				header4.setForeground(Color.BLACK);
				header5.setForeground(Color.BLACK);
				header6.setForeground(Color.BLACK);
				header7.setForeground(Color.BLACK);
				header8.setForeground(Color.BLACK);
				header9.setForeground(Color.BLACK);
			}
		}

		if (sett) {
			sync.setForeground(Color.BLACK);
			changeColor.setForeground(Color.BLACK);
			changeColor.setText("DARK");
			apply.setForeground(Color.BLACK);
			apiL.setForeground(Color.BLACK);
			langL.setForeground(Color.BLACK);
			settHeader.setForeground(Color.BLACK);
		}
	}

	private void changeDark() {
		contentPane.setBackground(Color.decode("#222222"));

		sidebar.setBackground(Color.decode("#30223e"));

		topStorys.setBackground(Color.decode("#222222"));
		settingsLabel.setBackground(Color.decode("#222222"));

		topStories.setForeground(Color.WHITE);
		topStories.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

		settings.setForeground(Color.WHITE);
		settings.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

		close.setForeground(Color.WHITE);

		footer.setForeground(Color.decode("#59bfbf"));
		footer.setBackground(Color.decode("#222222"));

		if (top) {
			topHeader.setForeground(Color.WHITE);
			if (this.news != null) {
				header1.setBackground(Color.decode("#30223e"));
				header2.setBackground(Color.decode("#30223e"));
				header3.setBackground(Color.decode("#30223e"));
				header4.setBackground(Color.decode("#30223e"));
				header5.setBackground(Color.decode("#30223e"));
				header6.setBackground(Color.decode("#30223e"));
				header7.setBackground(Color.decode("#30223e"));
				header8.setBackground(Color.decode("#30223e"));
				header9.setBackground(Color.decode("#30223e"));
				header1.setForeground(Color.WHITE);
				header2.setForeground(Color.WHITE);
				header3.setForeground(Color.WHITE);
				header4.setForeground(Color.WHITE);
				header5.setForeground(Color.WHITE);
				header6.setForeground(Color.WHITE);
				header7.setForeground(Color.WHITE);
				header8.setForeground(Color.WHITE);
				header9.setForeground(Color.WHITE);
			}
		}

		if (sett) {
			sync.setForeground(Color.WHITE);
			changeColor.setForeground(Color.WHITE);
			changeColor.setText("WHITE");
			apply.setForeground(Color.WHITE);
			apiL.setForeground(Color.WHITE);
			langL.setForeground(Color.WHITE);
			settHeader.setForeground(Color.WHITE);
		}
	}

	public boolean openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean openWebpage(URL url) {
		try {
			return openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static class FrameDragListener extends MouseAdapter {

		private final JFrame frame;
		private Point mouseDownCompCoords = null;

		public FrameDragListener(JFrame frame) {
			this.frame = frame;
		}

		public void mouseReleased(MouseEvent e) {
			mouseDownCompCoords = null;
		}

		public void mousePressed(MouseEvent e) {
			mouseDownCompCoords = e.getPoint();
		}

		public void mouseDragged(MouseEvent e) {
			Point currCoords = e.getLocationOnScreen();
			frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
		}
	}

	private void addSettiContent() {

		settHeader = new JLabel("SETTINGS");
		settHeader.setForeground(Color.BLACK);
		settHeader.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		settHeader.setBounds(50, 30, 750, 50);
		settingsLabel.add(settHeader);

		sync = new JButton("SYNC");
		sync.setBounds(100, 500, 100, 100);
		sync.setForeground(Color.BLACK);
		sync.setFocusable(false);
		sync.setOpaque(false);
		sync.setContentAreaFilled(false);
		sync.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sync();
			}
		});
		settingsLabel.add(sync);

		String dm;
		if (this.darkmode)
			dm = "WHITE";
		else
			dm = "DARK";
		changeColor = new JButton(dm);
		changeColor.setBounds(400, 500, 100, 100);
		changeColor.setForeground(Color.BLACK);
		changeColor.setFocusable(false);
		changeColor.setOpaque(false);
		changeColor.setContentAreaFilled(false);
		changeColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeColor();
			}
		});
		settingsLabel.add(changeColor);

		apiL = new JLabel("API KEY");
		apiL.setForeground(Color.BLACK);
		apiL.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		apiL.setBounds(50, 200, 150, 50);
		settingsLabel.add(apiL);

		apikey = new JTextField();
		apikey.setForeground(Color.BLACK);
		apikey.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		apikey.setBounds(300, 200, 500, 50);
		settingsLabel.add(apikey);

		langL = new JLabel("LANGUAGE");
		langL.setForeground(Color.BLACK);
		langL.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		langL.setBounds(50, 300, 150, 50);
		settingsLabel.add(langL);

		lang = new JTextField();
		lang.setForeground(Color.BLACK);
		lang.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		lang.setBounds(300, 300, 500, 50);
		lang.setToolTipText("LEGAL: ae ar at au be bg br ca ch cn co cu cz de eg "
							+ "fr gb gr hk hu id ie il in it jp kr lt lv ma mx my "
							+ "ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr"
							+ "tw ua us ve za");
		settingsLabel.add(lang);

		apply = new JButton("»");
		apply.setBounds(700, 500, 100, 100);
		apply.setForeground(Color.BLACK);
		apply.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		apply.setFocusable(false);
		apply.setOpaque(false);
		apply.setContentAreaFilled(false);
		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isLangLeagal(lang.getText()) && !apikey.getText().isEmpty())
					ctrl.saveData(apikey.getText(), lang.getText(), darkmode);
				else if (isLangLeagal(lang.getText()) && apikey.getText().isEmpty())
					ctrl.saveData(ctrl.getSettings()[2], lang.getText(), darkmode);
				else if (!isLangLeagal(lang.getText()) && !apikey.getText().isEmpty())
					ctrl.saveData(apikey.getText(), ctrl.getSettings()[0], darkmode);
			}
		});
		settingsLabel.add(apply);

		initColor();
	}

	private void sync() {
		this.news = ctrl.callAPI("headlines", null);
		System.out.println("synced!");
	}

	private void addTopContent() {

		String upper;
		if (this.news == null)
			upper = "sync first".toUpperCase();
		else
			upper = ctrl.getSettings()[0].toUpperCase();
		topHeader = new JLabel("TOP HEADLINES - " + upper);
		topHeader.setForeground(Color.BLACK);
		topHeader.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		topHeader.setBounds(50, 30, 750, 50);
		topStorys.add(topHeader);

		if (this.news != null) {

			if (this.news.length >= 1) {
				if (news[0].getUrlToImg().equals(a404))
					top1 = new ImagePanel(news[0].getImage(), true);
				else
					top1 = new ImagePanel(news[0].getImage(), false);
				top1.setBackground(Color.BLACK);
				top1.setLayout(null);
				top1.setBounds(50, 100, 50, 50);
				topStorys.add(top1);
				header1 = new JLabel("   " + news[0].getTitle(), SwingConstants.LEFT);
				header1.setBackground(Color.decode("#4a345f"));
				header1.setOpaque(true);
				header1.setForeground(Color.BLACK);
				header1.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header1.setBounds(100, 100, 750, 50);
				topStorys.add(header1);
			}

			if (this.news.length >= 2) {
				if (news[1].getUrlToImg().equals(a404))
					top2 = new ImagePanel(news[1].getImage(), true);
				else
					top2 = new ImagePanel(news[1].getImage(), false);
				top2.setBackground(Color.BLACK);
				top2.setLayout(null);
				top2.setBounds(50, 160, 50, 50);
				topStorys.add(top2);
				header2 = new JLabel("   " + news[1].getTitle(), SwingConstants.LEFT);
				header2.setBackground(Color.decode("#4a345f"));
				header2.setOpaque(true);
				header2.setForeground(Color.BLACK);
				header2.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header2.setBounds(100, 160, 750, 50);
				topStorys.add(header2);
			}

			if (this.news.length >= 3) {
				if (news[2].getUrlToImg().equals(a404))
					top3 = new ImagePanel(news[2].getImage(), true);
				else
					top3 = new ImagePanel(news[2].getImage(), false);
				top3.setBackground(Color.BLACK);
				top3.setLayout(null);
				top3.setBounds(50, 220, 50, 50);
				topStorys.add(top3);
				header3 = new JLabel("   " + news[2].getTitle(), SwingConstants.LEFT);
				header3.setBackground(Color.decode("#4a345f"));
				header3.setOpaque(true);
				header3.setForeground(Color.BLACK);
				header3.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header3.setBounds(100, 220, 750, 50);
				topStorys.add(header3);
			}

			if (this.news.length >= 4) {
				if (news[3].getUrlToImg().equals(a404))
					top4 = new ImagePanel(news[3].getImage(), true);
				else
					top4 = new ImagePanel(news[3].getImage(), false);
				top4.setBackground(Color.BLACK);
				top4.setLayout(null);
				top4.setBounds(50, 280, 50, 50);
				topStorys.add(top4);
				header4 = new JLabel("   " + news[3].getTitle(), SwingConstants.LEFT);
				header4.setBackground(Color.decode("#4a345f"));
				header4.setOpaque(true);
				header4.setForeground(Color.BLACK);
				header4.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header4.setBounds(100, 280, 750, 50);
				topStorys.add(header4);
			}

			if (this.news.length >= 5) {
				if (news[4].getUrlToImg().equals(a404))
					top5 = new ImagePanel(news[4].getImage(), true);
				else
					top5 = new ImagePanel(news[4].getImage(), false);
				top5.setBackground(Color.BLACK);
				top5.setLayout(null);
				top5.setBounds(50, 340, 50, 50);
				topStorys.add(top5);
				header5 = new JLabel("   " + news[4].getTitle(), SwingConstants.LEFT);
				header5.setBackground(Color.decode("#4a345f"));
				header5.setOpaque(true);
				header5.setForeground(Color.BLACK);
				header5.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header5.setBounds(100, 340, 750, 50);
				topStorys.add(header5);
			}

			if (this.news.length >= 6) {
				if (news[5].getUrlToImg().equals(a404))
					top6 = new ImagePanel(news[5].getImage(), true);
				else
					top6 = new ImagePanel(news[5].getImage(), false);
				top6.setBackground(Color.BLACK);
				top6.setLayout(null);
				top6.setBounds(50, 400, 50, 50);
				topStorys.add(top6);
				header6 = new JLabel("   " + news[5].getTitle(), SwingConstants.LEFT);
				header6.setBackground(Color.decode("#4a345f"));
				header6.setOpaque(true);
				header6.setForeground(Color.BLACK);
				header6.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header6.setBounds(100, 400, 750, 50);
				topStorys.add(header6);
			}

			if (this.news.length >= 7) {
				if (news[6].getUrlToImg().equals(a404))
					top7 = new ImagePanel(news[6].getImage(), true);
				else
					top7 = new ImagePanel(news[6].getImage(), false);
				top7.setBackground(Color.BLACK);
				top7.setLayout(null);
				top7.setBounds(50, 460, 50, 50);
				topStorys.add(top7);
				header7 = new JLabel("   " + news[6].getTitle(), SwingConstants.LEFT);
				header7.setBackground(Color.decode("#4a345f"));
				header7.setOpaque(true);
				header7.setForeground(Color.BLACK);
				header7.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header7.setBounds(100, 460, 750, 50);
				topStorys.add(header7);
			}

			if (this.news.length >= 8) {
				if (news[7].getUrlToImg().equals(a404))
					top8 = new ImagePanel(news[7].getImage(), true);
				else
					top8 = new ImagePanel(news[7].getImage(), false);
				top8.setBackground(Color.BLACK);
				top8.setLayout(null);
				top8.setBounds(50, 520, 50, 50);
				topStorys.add(top8);
				header8 = new JLabel("   " + news[7].getTitle(), SwingConstants.LEFT);
				header8.setBackground(Color.decode("#4a345f"));
				header8.setOpaque(true);
				header8.setForeground(Color.BLACK);
				header8.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header8.setBounds(100, 520, 750, 50);
				topStorys.add(header8);
			}

			if (this.news.length >= 9) {
				if (news[8].getUrlToImg().equals(a404))
					top9 = new ImagePanel(news[8].getImage(), true);
				else
					top9 = new ImagePanel(news[8].getImage(), false);
				top9.setBackground(Color.BLACK);
				top9.setLayout(null);
				top9.setBounds(50, 580, 50, 50);
				topStorys.add(top9);
				header9 = new JLabel("   " + news[8].getTitle(), SwingConstants.LEFT);
				header9.setBackground(Color.decode("#4a345f"));
				header9.setOpaque(true);
				header9.setForeground(Color.BLACK);
				header9.setFont(new Font(Font.SERIF, Font.BOLD, 14));
				header9.setBounds(100, 580, 750, 50);
				topStorys.add(header9);
			}
		}
		initColor();
	}

	private boolean isLangLeagal(String lang) {

		switch (lang) {
		case "ae":
			return true;
		case "ar":
			return true;
		case "at":
			return true;
		case "au":
			return true;
		case "be":
			return true;
		case "bg":
			return true;
		case "br":
			return true;
		case "ca":
			return true;
		case "ch":
			return true;
		case "cn":
			return true;
		case "co":
			return true;
		case "cu":
			return true;
		case "cz":
			return true;
		case "de":
			return true;
		case "eg":
			return true;
		case "fr":
			return true;
		case "gb":
			return true;
		case "gr":
			return true;
		case "hk":
			return true;
		case "hu":
			return true;
		case "id":
			return true;
		case "ie":
			return true;
		case "il":
			return true;
		case "in":
			return true;
		case "it":
			return true;
		case "jp":
			return true;
		case "kr":
			return true;
		case "lt":
			return true;
		case "lv":
			return true;
		case "ma":
			return true;
		case "mx":
			return true;
		case "my":
			return true;
		case "ng":
			return true;
		case "nl":
			return true;
		case "no":
			return true;
		case "nz":
			return true;
		case "ph":
			return true;
		case "pl":
			return true;
		case "pt":
			return true;
		case "ro":
			return true;
		case "rs":
			return true;
		case "ru":
			return true;
		case "sa":
			return true;
		case "se":
			return true;
		case "sg":
			return true;
		case "si":
			return true;
		case "sk":
			return true;
		case "th":
			return true;
		case "tr":
			return true;
		case "ua":
			return true;
		case "us":
			return true;
		case "tw":
			return true;
		case "ve":
			return true;
		case "za":
			return true;
		default:
			return false;
		}

	}
}
