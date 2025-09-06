package ar.edu.utn.frba.dds.web.dto.apiDeDDS;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PagedResponseDTO {
  @JsonProperty("current_page")
  private int currentPage;
  private List<DesastreDTO> data;
  @JsonProperty("first_page_url")
  private String firstPageUrl;
  private int from;
  @JsonProperty("last_page")
  private int lastPage;
  @JsonProperty("last_page_url")
  private String lastPageUrl;
  @JsonProperty("next_page_url")
  private String nextPageUrl;
  private String path;
  @JsonProperty("per_page")
  private int perPage;
  @JsonProperty("prev_page_url")
  private String prevPageUrl;
  private int to;
  private int total;

  public PagedResponseDTO() {
  }

  // Getters y Setters
  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public List<DesastreDTO> getData() {
    return data;
  }

  public void setData(List<DesastreDTO> data) {
    this.data = data;
  }

  public String getFirstPageUrl() {
    return firstPageUrl;
  }

  public void setFirstPageUrl(String firstPageUrl) {
    this.firstPageUrl = firstPageUrl;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getLastPage() {
    return lastPage;
  }

  public void setLastPage(int lastPage) {
    this.lastPage = lastPage;
  }

  public String getLastPageUrl() {
    return lastPageUrl;
  }

  public void setLastPageUrl(String lastPageUrl) {
    this.lastPageUrl = lastPageUrl;
  }

  public String getNextPageUrl() {
    return nextPageUrl;
  }

  public void setNextPageUrl(String nextPageUrl) {
    this.nextPageUrl = nextPageUrl;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getPerPage() {
    return perPage;
  }

  public void setPerPage(int perPage) {
    this.perPage = perPage;
  }

  public String getPrevPageUrl() {
    return prevPageUrl;
  }

  public void setPrevPageUrl(String prevPageUrl) {
    this.prevPageUrl = prevPageUrl;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public boolean isLastPage() {
    return getLastPage() == getCurrentPage();
  }

  public int getNextPage() {
    return getCurrentPage() + 1;
  }
}
