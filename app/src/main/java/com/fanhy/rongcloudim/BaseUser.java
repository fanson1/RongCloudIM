package com.fanhy.rongcloudim;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class BaseUser {
    // 用户 Token，可以保存应用内，长度在 256 字节以内.用户 Token，可以保存应用内，长度在 256 字节以内。
    private String token;
    // 用户 Id，与输入的用户 Id 相同.用户 Id，与输入的用户 Id 相同。
    private String userId;
    // 用户头像地址
    private String portraitUri;
    // 用户名称
    private String name;

    public BaseUser() {
        // TODO Auto-generated constructor stub
    }

    public BaseUser(String token, String userId, String portraitUri, String name) {
        super();
        this.token = token;
        this.userId = userId;
        this.portraitUri = portraitUri;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseUser [token=" + token + ", userId=" + userId
                + ", portraitUri=" + portraitUri + ", name=" + name + "]";
    }
}
