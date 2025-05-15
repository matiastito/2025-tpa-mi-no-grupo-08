package ar.edu.utn.frba.dds.web.dto;

public class LoginResponse {
  private boolean error;
  private String message;
  private Data data;

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "LoginResponse{" +
        "error=" + error +
        ", message='" + message + '\'' +
        ", data=" + data +
        '}';
  }

  public static class Data {
    private String access_token;
    private String token_type;
    private User user;

    public String getAccess_token() {
      return access_token;
    }

    public void setAccess_token(String access_token) {
      this.access_token = access_token;
    }

    public String getToken_type() {
      return token_type;
    }

    public void setToken_type(String token_type) {
      this.token_type = token_type;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }

    @Override
    public String toString() {
      return "Data{" +
          "access_token='" + access_token + '\'' +
          ", token_type='" + token_type + '\'' +
          ", user=" + user +
          '}';
    }

    public static class User {
      private int id;
      private String name;
      private String email;
      private Object email_verified_at;
      private String created_at;
      private String updated_at;

      public int getId() {
        return id;
      }

      public void setId(int id) {
        this.id = id;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getEmail() {
        return email;
      }

      public void setEmail(String email) {
        this.email = email;
      }

      public Object getEmail_verified_at() {
        return email_verified_at;
      }

      public void setEmail_verified_at(Object email_verified_at) {
        this.email_verified_at = email_verified_at;
      }

      public String getCreated_at() {
        return created_at;
      }

      public void setCreated_at(String created_at) {
        this.created_at = created_at;
      }

      public String getUpdated_at() {
        return updated_at;
      }

      public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
      }

      @Override
      public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", email_verified_at=" + email_verified_at +
            ", created_at='" + created_at + '\'' +
            ", updated_at='" + updated_at + '\'' +
            '}';
      }
    }
  }
}
