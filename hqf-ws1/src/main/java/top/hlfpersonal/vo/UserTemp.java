package top.hlfpersonal.vo;

import com.google.gson.Gson;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-04-09 23:49
 **/
public
class UserTemp {
    private String id;
    private String username;
    private String nickname;

    public UserTemp(String id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
