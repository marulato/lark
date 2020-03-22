package org.avalon.lark.common.session;

import org.avalon.lark.common.utility.ConfigUtils;
import org.avalon.lark.common.utility.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AppSession {

    private Map<String, Object> attributes;
    private String id;
    private String token;
    private long creationDatetime;
    private long lastAccessedTime;
    private int maxInactiveInterval;

    public AppSession() {
        attributes = new HashMap<>(16);
        creationDatetime = System.currentTimeMillis();
        lastAccessedTime = creationDatetime;
        String interval = ConfigUtils.getString("server.session.maxInactiveInterval");
        if (StringUtils.isBlank(interval)) {
            maxInactiveInterval = -1;
        } else {
            maxInactiveInterval = Integer.parseInt(interval);
        }
    }

    public void invalidate() {
        HttpSessionManager.invalidate(this);
    }

    public void setAttribute(String name, Object attr) {
        if (StringUtils.isNotBlank(name)) {
            attributes.put(name, attr);
        }
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public <T> T getAttribute(String name, Class<T> type) {
        return type.cast(attributes.get(name));
    }

    public Object removeAttribute(String name) {
        return attributes.remove(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(long creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }
}
