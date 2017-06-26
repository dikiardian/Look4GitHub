package model;

import java.util.List;


public class User {
  private String username;
  private String namaPengguna;
  private String email;
  private List<Repo> repos;
  private int repoCount;
  private String userUrl;
  private String avatarUrl;

  public User(String username, String userUrl, String avatarUrl) {
    this.username = username;
    this.userUrl = userUrl;
    this.avatarUrl = avatarUrl;
  }

  public User(String username, String namaPengguna, String email,
              List<Repo> repos, int repoCount, String userUrl,
              String avatarUrl) {
    this.username = username;
    this.namaPengguna = namaPengguna;
    this.email = email;
    this.repos = repos;
    this.repoCount = repoCount;
    this.userUrl = userUrl;
    this.avatarUrl = avatarUrl;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNamaPengguna() {
    return namaPengguna;
  }

  public void setNamaPengguna(String namaPengguna) {
    this.namaPengguna = namaPengguna;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Repo> getRepos() {
    return repos;
  }

  public void setRepos(List<Repo> repos) {
    this.repos = repos;
  }

  public int getRepoCount() {
    return repoCount;
  }

  public void setRepoCount(int repoCount) {
    this.repoCount = repoCount;
  }

  public String getUserUrl() {
    return userUrl;
  }

  public void setUserUrl(String userUrl) {
    this.userUrl = userUrl;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}