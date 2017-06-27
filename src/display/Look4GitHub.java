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
import java.util.LinkedHashMap;
import java.util.Map;

public class Look4GitHub {
  private JFrame frame;
  private JPanel toolbar;
  private JPanel mainPanel;
  private JPanel searchPanel;
  private JPanel advanceSearchPanel;
  private JPanel topPanel;
  private JPanel midPanel;
  private JPanel bottomPanel;
  private JLabel searchLabel;
  private JTextField searchBar;
  private JComboBox<String> searchIn;
  private JButton searchButton;
  private JScrollPane scrollPane;
  private JList resultList;
  private DefaultListModel resultModel;
  private JButton nextButton;
  private JButton prevButton;
  private Map<String, User> resultSearch;

  private SearchEngine searchEngine;

  public Look4GitHub() {
    resultSearch = new LinkedHashMap<>();
    searchEngine = new SearchEngine("login", false,
        "", "", "", "", "", "",
        "", "", "", resultSearch, 0, 1);

    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setSize(900, 600);
    frame.setTitle("Look4GitHub");
    frame.setBackground(Color.decode("#2e302e"));

    toolbar = new JPanel();
    toolbar.setPreferredSize(new Dimension(50,frame.getHeight()));
    toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));
    toolbar.setBackground(Color.decode("#0d8450"));

    frame.add(toolbar, BorderLayout.WEST);

    JButton home = new JButton("1");
    home.setSize(50, 50);
    home.setMaximumSize(home.getSize());
    home.setBackground(Color.decode("#2e302e"));
    home.setBorderPainted(false);
    home.setFocusPainted(false);
    home.setForeground(Color.white);

    JButton advanceSearch = new JButton("2");
    advanceSearch.setSize(50, 50);
    advanceSearch.setMaximumSize(home.getSize());
    advanceSearch.setBackground(Color.decode("#0d8450"));
    advanceSearch.setBorderPainted(false);
    advanceSearch.setFocusPainted(false);
    advanceSearch.setForeground(Color.white);

    toolbar.add(home);
    toolbar.add(advanceSearch);

    mainPanel = new JPanel();
    mainPanel.setSize(frame.getSize());
    CardLayout layout = new CardLayout();
    mainPanel.setLayout(layout);
    mainPanel.setBackground(Color.decode("#0d8450"));

    frame.add(mainPanel, BorderLayout.CENTER);

    searchPanel = new JPanel();
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

    topPanel = new JPanel();
    topPanel.setBackground(Color.decode("#2e302e"));

    midPanel = new JPanel();
    midPanel.setBackground(Color.decode("#2e302e"));

    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.decode("#2e302e"));

    searchLabel = new JLabel("Keyword :  ");
    searchLabel.setForeground(Color.white);

    searchBar = new JTextField(15);
    searchBar.setSize(100, 50);
    searchBar.setBackground(Color.decode("#2e302e"));
    searchBar.setForeground(Color.white);
    searchBar.setBorder(BorderFactory.createLineBorder(Color.white));
    searchBar.setBorder(BorderFactory.createCompoundBorder(
        searchBar.getBorder(),
        BorderFactory.createEmptyBorder(3, 3, 3, 3)));
    searchBar.setCaretColor(Color.white);

    String[] searchInItem = {"username", "fullname", "email"};
    searchIn = new JComboBox<>(searchInItem);

    searchButton = new JButton("search");
    searchButton.setBackground(Color.white);
    searchButton.setBorderPainted(false);
    searchButton.setFocusPainted(false);

    resultModel = new DefaultListModel();

    resultList = new JList(resultModel);
    resultList.setCellRenderer(new ListEntryCellRenderer());

//    resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    resultList.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    scrollPane = new JScrollPane();
    scrollPane.getViewport().add(resultList);
    scrollPane.setPreferredSize(new Dimension(800, 400));
    scrollPane.getViewport().setBackground(Color.red);

    searchPanel.add(topPanel);
    searchPanel.add(midPanel);
    searchPanel.add(bottomPanel);

    advanceSearchPanel = new JPanel();
    advanceSearchPanel.setBackground(Color.decode("#2e302e"));

    mainPanel.add("home", searchPanel);
    mainPanel.add("advanceSearch", advanceSearchPanel);

    nextButton = new JButton("next >");
    nextButton.setVisible(false);
    prevButton = new JButton("< prev");
    prevButton.setVisible(false);

    topPanel.add(searchLabel);
    topPanel.add(searchBar);
    topPanel.add(searchIn);
    topPanel.add(searchButton);
    midPanel.add(scrollPane);
    bottomPanel.add(prevButton);
    bottomPanel.add(nextButton);

    home.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "home");
        home.setBackground(Color.decode("#2e302e"));
        advanceSearch.setBackground(Color.decode("#0d8450"));
      }
    });

    advanceSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "advanceSearch");
        advanceSearch.setBackground(Color.decode("#2e302e"));
        home.setBackground(Color.decode("#0d8450"));
      }
    });

    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        resultSearch.clear();
        searchEngine.setPageResult(1);
        resultAction();
        if (resultSearch.size()<searchEngine.getResultCount()) {
          nextButton.setVisible(true);
        }
      }
    });

    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        searchEngine.setPageResult(searchEngine.getPageResult()+1);
        resultAction();
        if (resultSearch.size()<searchEngine.getResultCount()) {
          nextButton.setVisible(true);
        } else {
          nextButton.setVisible(false);
        }
        prevButton.setVisible(true);
      }
    });

    prevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        searchEngine.setPageResult(searchEngine.getPageResult()-1);
        resultAction();
        if (searchEngine.getPageResult()==1) {
          prevButton.setVisible(false);
        } else {
          prevButton.setVisible(true);
        }
        nextButton.setVisible(true);
      }
    });

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private void resultAction() {
    resultModel.clear();
    String keyword = searchBar.getText();
    if (!keyword.equals("")) {
      searchEngine.searchUsers(keyword);
      int index = searchEngine.getResultPerPage()*(searchEngine.getPageResult()-1);
      User[] perPage = resultSearch.values().toArray(new User[0]);
      int maxCount = Integer.min(resultSearch.size(),searchEngine.getResultPerPage());
      for (int i=0; i<maxCount; i++) {
        try {
          URL url = new URL(perPage[index].getAvatarUrl());
          BufferedImage image = ImageIO.read(url);
          Image resizedImg = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
          resultModel.addElement(new ListEntry(perPage[index].getUsername(), new ImageIcon(resizedImg)));
        } catch (IOException e) {
          e.printStackTrace();
        }
        index++;
      }
    }
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
      setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
      return this;
    }
  }

}
