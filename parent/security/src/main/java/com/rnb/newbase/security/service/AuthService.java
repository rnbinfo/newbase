package com.rnb.newbase.security.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AuthService {
    List<Map<String,Object>> findAllResourceAndRole();
}
