package com.bestsecret.groupifier.util;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
    public java.util.Date getCurrentTime() {
        return new java.util.Date();
    }

    public java.sql.Date getCurrentSQLDate() {
        return new java.sql.Date(getCurrentTime().getTime());
    }
}
