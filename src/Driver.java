import model.User;
import org.json.simple.parser.ParseException;
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
    SearchEngine searchEngine = new SearchEngine("https://api.github.com/search/users?q=",
        "login", false, "", "", "", "",
        "", "", "", "", "", resultSearch, 0);

    System.out.print("keyword: ");
    Scanner scanner = new Scanner(System.in);
    String keyword = scanner.nextLine();
    searchEngine.searchUsers(keyword);


  }
}
