package display;

import model.User;
import search.SearchEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Look4GitHub {
  private JFrame frame;
  private JPanel toolbar;
  private JPanel main;
  private JPanel panel;
  private JPanel topPanel;
  private JPanel bottomPanel;
  private JLabel searchLabel;
  private JTextField searchBar;
  private JButton searchButton;
  private JScrollPane scrollPane;
  private JList resultList;
  private DefaultListModel resultModel;

  private SearchEngine searchEngine;

  public Look4GitHub() {
    Map<String, User> resultSearch = new HashMap<>();
    searchEngine = new SearchEngine("login", false,
        "", "", "", "", "", "",
        "", "", "", resultSearch, 0, 1);

    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setSize(900, 600);
    frame.setTitle("Look4GitHub");

    toolbar = new JPanel();
    toolbar.setPreferredSize(new Dimension(50,frame.getHeight()));
    toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));
    toolbar.setBackground(Color.GRAY);

    frame.add(toolbar, BorderLayout.WEST);

    JButton home = new JButton("1");
    home.setSize(50, 50);
    home.setMaximumSize(home.getSize());
    home.setBackground(Color.green);
    home.setBorderPainted(false);
    home.setFocusPainted(false);

    JButton setting = new JButton("2");
    setting.setSize(50, 50);
    setting.setMaximumSize(home.getSize());
    setting.setBackground(Color.GRAY);
    setting.setBorderPainted(false);
    setting.setFocusPainted(false);

    toolbar.add(home);
    toolbar.add(setting);

    main = new JPanel();
    main.setSize(frame.getSize());
    CardLayout layout = new CardLayout();
    main.setLayout(layout);

    frame.add(main, BorderLayout.CENTER);

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    topPanel = new JPanel();
    topPanel.setBackground(Color.GREEN);

    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.GREEN);

    searchLabel = new JLabel("Search :  ");

    searchBar = new JTextField(15);
    searchBar.setSize(100, 50);

    searchButton = new JButton("search");

    resultModel = new DefaultListModel();

    resultList = new JList(resultModel);
    resultList.setCellRenderer(new ListEntryCellRenderer());

//    resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    resultList.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    scrollPane = new JScrollPane();
    scrollPane.getViewport().add(resultList);

//    frame.add(panel);

    panel.add(topPanel);
    panel.add(bottomPanel);

    JPanel blue = new JPanel();
    blue.setBackground(Color.green);

    main.add("home", panel);
    main.add("blue", blue);

    topPanel.add(searchLabel);
    topPanel.add(searchBar);
    topPanel.add(searchButton);
    bottomPanel.add(scrollPane);

    home.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main, "home");
        home.setBackground(Color.green);
        setting.setBackground(Color.GRAY);
      }
    });

    setting.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main, "blue");
        setting.setBackground(Color.green);
        home.setBackground(Color.GRAY);
      }
    });

    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        resultModel.clear();
        resultSearch.clear();
        String keyword = searchBar.getText();
        if (!keyword.equals("")) {
          searchEngine.searchUsers(keyword);
          for (Map.Entry<String, User> entry : resultSearch.entrySet()) {
            try {
              URL url = new URL(entry.getValue().getAvatarUrl());
              BufferedImage image = ImageIO.read(url);
              Image resizedImg = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
              resultModel.addElement(new ListEntry(entry.getKey(), new ImageIcon(resizedImg)));
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    });

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  class ListEntry {
    private String value;
    private ImageIcon icon;

    public ListEntry(String value, ImageIcon icon) {
      this.value = value;
      this.icon = icon;
    }

    public String getValue() {
      return value;
    }

    public ImageIcon getIcon() {
      return icon;
    }

    public String toString() {
      return value;
    }
  }

  class ListEntryCellRenderer
      extends JLabel implements ListCellRenderer {
    private JLabel label;

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus) {
      ListEntry entry = (ListEntry) value;

      setText(value.toString());
      setIcon(entry.getIcon());

      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
      }
      else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }

      setEnabled(list.isEnabled());
      setFont(list.getFont());
      setOpaque(true);

      return this;
    }
  }

}
