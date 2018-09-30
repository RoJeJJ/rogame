package com.rojee.core.entity;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = -6398430384709961496L;

    @Id
    @Expose
    private long id;

    @Expose
    @Column(name = "account")
    private String account;

    @Expose
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    @Expose(serialize = false)
    private String password;

    @Transient
    private String gameToken;

    @Override
    public String toString() {
        return "User:{id:"+id+",account:"+account+",nickname:"+nickname+"}";
    }

    public String gson(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this);
    }

    public JsonObject gsonObject(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(this).getAsJsonObject();
    }
}
