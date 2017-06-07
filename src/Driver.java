import model.User;
import search.SearchEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Diki Ardian W on 6/6/17.
 */

public class Driver {
  public static void main(String[] args) {
    Map<String, User> resultSearch = new HashMap<>();
    SearchEngine searchEngine = new SearchEngine("https://api.github.com/search/users?",
        "login", false, "", "", "", "",
        "", "", "", "", "", resultSearch, 0,
        1);

    System.out.print("keyword: ");
    Scanner scanner = new Scanner(System.in);
    String keyword = scanner.nextLine();

    String next;
    boolean stop = false;
    do {
      searchEngine.searchUsers(keyword);
      for (Map.Entry<String, User> entry : resultSearch.entrySet()) {
        System.out.println(entry.getKey());
      }
      System.out.println("result : " + resultSearch.size() + "/" + searchEngine.getResultCount());
      if (resultSearch.size()<searchEngine.getResultCount()) {
        System.out.print("next? ");
        next = scanner.nextLine();
        if (next.equals("y")) {
          searchEngine.setPageResult(searchEngine.getPageResult() + 1);
        } else {
          stop = true;
        }
      }
    } while(resultSearch.size()<searchEngine.getResultCount() && !stop);
  }
}
