package org.avalon.lark.common.cache;


public interface ICache {

    public Object get(Object key) throws Exception;

    public void set(Object key, Object value) throws Exception;
}
