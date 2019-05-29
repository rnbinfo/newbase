package com.rnbbusiness.newbase.security.dao.api;

import java.util.List;

public interface ResourceDao {
    List<String> findRoleCodes(String url);
}
