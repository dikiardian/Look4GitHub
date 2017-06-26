package search;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Repo;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Diki Ardian W on 6/6/17.
 */

public class SearchEngine {
  final private String urlSearch = "https://api.github.com/search/users?";
  private String searchBy; //["login", "fullname", "email"]
  private boolean advanceSearch;
  private String sortBy; //["followers", "repositories"]
  private String order; //["desc", "asc"]
  private String followerMin;
  private String followerMax;
  private String repoMin;
  private String repoMax;
  private String location;
  private String language;
  private String created;
  private Map<String, User> result;
  private int resultCount;
  private int pageResult;

  public SearchEngine(String searchBy, boolean advanceSearch, String sortBy,
                      String order, String followerMin, String followerMax,
                      String repoMin, String repoMax, String location,
                      String language, String created, Map<String, User> result,
                      int resultCount, int pageResult) {
    this.searchBy = searchBy;
    this.advanceSearch = advanceSearch;
    this.sortBy = sortBy;
    this.order = order;
    this.followerMin = followerMin;
    this.followerMax = followerMax;
    this.repoMin = repoMin;
    this.repoMax = repoMax;
    this.location = location;
    this.language = language;
    this.created = created;
    this.result = result;
    this.resultCount = resultCount;
    this.pageResult = pageResult;
  }

  private String getDataRest(String urlSource) {
    String result = "";
    try {
      URL url = new URL(urlSource);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
      int responseCode = conn.getResponseCode();
      if (responseCode != 200) {
        throw new RuntimeException("HttpResponseCode: " + responseCode);
      } else {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()) {
          result = sb.append(result).append(sc.nextLine()).toString();
        }
        sc.close();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return result;
  }

  public void getUserRepos(String username) {
    String clientID = "82b5ba0bb36f5d8c6b17";
    String clientSecret = "347891e79e815871395d936adf803d68af56ef35";
    String url = "https://api.github.com/users/" + username + "/repos?" +
        "client_id=" + clientID +
        "&client_secret=" + clientSecret;
    List<Repo> repos = new ArrayList<>();
    try {
      String resultString = getDataRest(url);
      JSONParser parser = new JSONParser();
      JSONArray jsonArray = (JSONArray) parser.parse(resultString);
      for (Object obj : jsonArray) {
        JSONObject jasObj = (JSONObject) obj;
        String name = jasObj.get("name")!=null ? jasObj.get("name").toString() : "null";
        String description = jasObj.get("description")!=null ? jasObj.get("description").toString() : "null";
        String repoUrl = jasObj.get("url")!=null ? jasObj.get("url").toString() : "null";
        repos.add(new Repo(name, description, repoUrl));
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    result.get(username).setRepos(repos);
  }

  public void searchUsers(String keyword) {
    String clientID = "82b5ba0bb36f5d8c6b17";
    String clientSecret = "347891e79e815871395d936adf803d68af56ef35";
    int resultPerPage = 10;
    String url = urlSearch + "order=" + order +
        "&sort=" + sortBy +
        "&per_page=" + resultPerPage +
        "&page=" + pageResult +
        "&client_id=" + clientID +
        "&client_secret=" + clientSecret +
        "&q=" + keyword +
        "+in:" + searchBy +
        "+type:user";
    if (advanceSearch) {
      if (!followerMin.equals("") && !followerMax.equals("")) {
        url = url + "+followers:" + followerMin + ".." + followerMax;
      }
      if (!repoMin.equals("") && !repoMax.equals("")) {
        url = url + "+repo:" + repoMin + ".." + repoMax;
      }
      if (!location.equals("")) {
        url = url + "+location:" + location;
      }
      if (!language.equals("")) {
        url = url + "+language:" + language;
      }
      if (!created.equals("")) {
        url = url + "+created:" + created;
      }
    }
    try {
      String resultString = getDataRest(url);
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(resultString);
      JSONArray jsonArray = (JSONArray) jsonObject.get("items");
      if (pageResult==1) {
        resultCount = Integer.parseInt(jsonObject.get("total_count").toString());
        if (resultCount > 1000) {
          resultCount = 1000;
        }
      }
      int count = 1;
      while (((resultPerPage*(pageResult-1) + count <= resultCount) ||
          (count <= resultPerPage)) && resultCount!=0) {
        for (Object obj : jsonArray) {
          JSONObject jasObj = (JSONObject) obj;
          result.put(jasObj.get("login").toString(), new User(jasObj.get("login").toString(),
              jasObj.get("url").toString(), jasObj.get("avatar_url").toString()));
          count++;
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public String getUrlSearch() {
    return urlSearch;
  }

  public String getSearchBy() {
    return searchBy;
  }

  public void setSearchBy(String searchBy) {
    this.searchBy = searchBy;
  }

  public boolean isAdvanceSearch() {
    return advanceSearch;
  }

  public void setAdvanceSearch(boolean advanceSearch) {
    this.advanceSearch = advanceSearch;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getFollowerMin() {
    return followerMin;
  }

  public void setFollowerMin(String followerMin) {
    this.followerMin = followerMin;
  }

  public String getFollowerMax() {
    return followerMax;
  }

  public void setFollowerMax(String followerMax) {
    this.followerMax = followerMax;
  }

  public String getRepoMin() {
    return repoMin;
  }

  public void setRepoMin(String repoMin) {
    this.repoMin = repoMin;
  }

  public String getRepoMax() {
    return repoMax;
  }

  public void setRepoMax(String repoMax) {
    this.repoMax = repoMax;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public Map<String, User> getResult() {
    return result;
  }

  public void setResult(Map<String, User> result) {
    this.result = result;
  }

  public int getResultCount() {
    return resultCount;
  }

  public void setResultCount(int resultCount) {
    this.resultCount = resultCount;
  }

  public int getPageResult() {
    return pageResult;
  }

  public void setPageResult(int pageResult) {
    this.pageResult = pageResult;
  }

}
