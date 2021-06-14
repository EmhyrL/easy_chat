package com.EmhyrL.easyChat.server.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`mobile`")
    private String mobile;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`avatar`")
    private String avatar;

    @Column(name = "`sex`")
    private Byte sex;

    @Column(name = "`status`")
    private Byte status;

    @Column(name = "`created`")
    private Date created;

    @Column(name = "`updated`")
    private Date updated;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String MOBILE = "mobile";

    public static final String DB_MOBILE = "mobile";

    public static final String PASSWORD = "password";

    public static final String DB_PASSWORD = "password";

    public static final String AVATAR = "avatar";

    public static final String DB_AVATAR = "avatar";

    public static final String SEX = "sex";

    public static final String DB_SEX = "sex";

    public static final String STATUS = "status";

    public static final String DB_STATUS = "status";

    public static final String CREATED = "created";

    public static final String DB_CREATED = "created";

    public static final String UPDATED = "updated";

    public static final String DB_UPDATED = "updated";
}