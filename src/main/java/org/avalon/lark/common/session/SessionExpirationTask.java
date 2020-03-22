package org.avalon.lark.common.session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.avalon.lark.common.utility.DateUtils;
import org.avalon.lark.common.utility.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;

public class SessionExpirationTask implements Runnable {

    private CopyOnWriteArrayList<String> tokenVector;
    private static final Logger log = LoggerFactory.getLogger(SessionExpirationTask.class);

    public SessionExpirationTask(CopyOnWriteArrayList<String> vector) {
        this.tokenVector = vector;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        log.info("Session Validation Checking -- START");
        try {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            for (String key : tokenVector) {
                AppSession session = gson.fromJson(RedisManager.getString(key), AppSession.class);
                if (session != null && (System.currentTimeMillis()
                        - session.getLastAccessedTime()) > DateUtils.ONE_DAY_MILLIS) {
                    session.invalidate();
                    log.info("Session Token -> " + key + " Invalidated");
                    tokenVector.remove(key);
                }
            }
            long end = System.currentTimeMillis();
            log.info("Active Sessions Remaining -> " + tokenVector.size());
            log.info("Daemon task time cost: " + (end - start) + " ms");
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
