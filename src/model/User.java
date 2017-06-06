package model;

import java.util.List;

/**
 * Created by Diki Ardian W on 6/5/17.
 */

public class User {
  private String username;
  private String namaPengguna;
  private String email;
  private List<Repo> repos;
  private int repoCount;
  private int followerCount;

  public User(String username,
              String namaPengguna,
              String email,
              List<Repo> repos,
              int repoCount,
              int followerCount) {
    this.username = username;
    this.namaPengguna = namaPengguna;
    this.email = email;
    this.repos = repos;
    this.repoCount = repoCount;
    this.followerCount = followerCount;
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

  public int getFollowerCount() {
    return followerCount;
  }

  public void setFollowerCount(int followerCount) {
    this.followerCount = followerCount;
  }
}