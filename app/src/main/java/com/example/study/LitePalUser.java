package com.example.study;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class LitePalUser extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    public String phone;
    public int password;
}
