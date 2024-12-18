package service;

import bean.Product;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, Map<Product, Integer>> sessionData;

    public SessionManager() {
        this.sessionData = new HashMap<String, Map<Product, Integer>>();
    }
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    public void setSessionData(String key, Map<Product, Integer> data) {
        sessionData.put(key, data);
    }

    public Object getSessionData(String key) {
        return sessionData.get(key);
    }
}