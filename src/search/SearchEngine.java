package search;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Diki Ardian W on 6/6/17.
 */

public class SearchEngine {
  private String urlSearch;
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
  final private String clientID = "82b5ba0bb36f5d8c6b17";
  final private String clientSecret = "347891e79e815871395d936adf803d68af56ef35";

  public SearchEngine(String urlSearch, String searchBy, boolean advanceSearch,
                      String sortBy, String order, String followerMin,
                      String followerMax, String repoMin, String repoMax,
                      String location, String language, String created,
                      Map<String, User> result, int resultCount) {
    this.urlSearch = urlSearch;
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

  public void searchUsers(String keyword) {

    String finalUrl = urlSearch + keyword + "+in:" + searchBy + "type:user";
    if (advanceSearch) {
      if (followerMin!="" && followerMax!="") {
        finalUrl = finalUrl + "+followers:" + followerMin + ".." + followerMax;
      }
      if (repoMin!="" && repoMax!="") {
        finalUrl = finalUrl + "+repo:" + repoMin + ".." + repoMax;
      }
      if (!location.equals("")) {
        finalUrl = finalUrl + "+location:" + location;
      }
      if (!language.equals("")) {
        finalUrl = finalUrl + "+language:" + language;
      }
      if (!created.equals("")) {
        finalUrl = finalUrl + "+created:" + created;
      }
    }

    try {
      String result = getDataRest(finalUrl +
          "&per_page=100&client_id=" + clientID +
          "&client_secret=" + clientSecret);
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(result);
      JSONArray jsonArray = (JSONArray) jsonObject.get("items");
      int total = Integer.parseInt(jsonObject.get("total_count").toString());
      if (total > 1000) {
        total = 1000;
      }
      int count = 1;
      int page = 1;

      while (count <= total) {
        for (Object obj : jsonArray) {
          JSONObject jasObj = (JSONObject) obj;
          System.out.print(count + ". username: " + jasObj.get("login"));
          System.out.print(" ,  avatar: " + jasObj.get("avatar_url"));
          System.out.println(" ,  url: " + jasObj.get("url"));
          count++;
        }
        if (count < total) {
          page++;
          result = getDataRest("https://api.github.com/search/users?q=" + keyword + "in:login&per_page=100&page=" + page + "&client_id=82b5ba0bb36f5d8c6b17&client_secret=347891e79e815871395d936adf803d68af56ef35");
          jsonObject = (JSONObject) parser.parse(result);
          jsonArray = (JSONArray) jsonObject.get("items");
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public String getSearchBy() {
    return searchBy;
  }

  public void setSearchBy(String searchBy) {
    this.searchBy = searchBy;
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

  public String getClientID() {
    return clientID;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
