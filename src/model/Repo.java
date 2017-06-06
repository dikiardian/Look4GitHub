/**
 * Created by Diki Ardian W on 6/5/17.
 */

package model;


public class Repo {
  private String nama;
  private String deskripsi;
  private String url;

  public Repo(String nama, String deskripsi, String url) {
    this.nama = nama;
    this.deskripsi = deskripsi;
    this.url = url;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getDeskripsi() {
    return deskripsi;
  }

  public void setDeskripsi(String deskripsi) {
    this.deskripsi = deskripsi;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
