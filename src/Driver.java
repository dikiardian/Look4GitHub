import display.Look4GitHub;

import com.jtattoo.plaf.hifi.*;

import javax.swing.*;

public class Driver {
  public static void main(String[] args) {
    new Look4GitHub();
//    try {
//      // select Look and Feel
//      UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//      // start application
//      new Look4GitHub();
//    }
//    catch (Exception ex) {
//      ex.printStackTrace();
//    }

//    Map<String, User> resultSearch = new HashMap<>();
//    SearchEngine searchEngine = new SearchEngine("login", false,
//        "", "", "", "", "", "",
//        "", "", "", resultSearch, 0, 1);
//    System.out.print("keyword: ");
//    Scanner scanner = new Scanner(System.in);
//    String keyword = scanner.nextLine();
//    String next;
//    boolean stop = false;
//    do {
//      searchEngine.searchUsers(keyword);
//      for (Map.Entry<String, User> entry : resultSearch.entrySet()) {
//        System.out.println(entry.getKey());
//      }
//      System.out.println("result : " + resultSearch.size() + "/" + searchEngine.getResultCount());
//      if (resultSearch.size()<searchEngine.getResultCount()) {
//        System.out.print("go to page: ");
//        next = scanner.nextLine();
//        if (next.equals("n")) {
//          stop = true;
//        } else {
//          searchEngine.setPageResult(Integer.parseInt(next));
//        }
//      }
//    } while(resultSearch.size()<searchEngine.getResultCount() && !stop);
//    if (searchEngine.getResultCount()!=0) {
//      System.out.print("pilih: ");
//      String pilih = scanner.nextLine();
//      searchEngine.getUserRepos(pilih);
//      System.out.println("getting.....\n");
//      for (Repo repo : resultSearch.get(pilih).getRepos()) {
//        System.out.println(repo.getNama());
//        System.out.println(repo.getDeskripsi());
//        System.out.println(repo.getUrl() + "\n");
//      }
//    }
    //TO DO: caching visited page
  }
}
