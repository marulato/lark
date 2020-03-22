package org.avalon.lark.common.session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.avalon.lark.common.consts.SystemConsts;
import org.avalon.lark.common.exception.InvalidRequestException;
import org.avalon.lark.common.utility.RedisManager;
import org.avalon.lark.common.utility.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HttpSessionManager {

    private static CopyOnWriteArrayList<String> tokenVector = new CopyOnWriteArrayList<>();

    static {
        timeout();
    }

    public static void timeout() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
                new SessionExpirationTask(tokenVector), 1, 24, TimeUnit.HOURS);

    }

    public static AppSession getSession(HttpServletRequest request) {
        AppSession appSession = new AppSession();
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String tokenId = request.getHeader(SystemConsts.TOKEN_ID);
        if (StringUtils.isBlank(tokenId)) {
            tokenId = request.getParameter(SystemConsts.TOKEN_ID);
            if (StringUtils.isBlank(tokenId)) {
                tokenId = (String) request.getAttribute(SystemConsts.TOKEN_ID);
            }
        }
        if (StringUtils.isBlank(tokenId)) {
            throw new InvalidRequestException("Missing Token");
        }
        tokenVector.add(tokenId);
        RedisManager.setString(tokenId, gson.toJson(appSession));
        return appSession;
    }

    public static AppSession getSession(String token) {
        AppSession appSession = new AppSession();
        appSession.setToken(token);
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        tokenVector.add(token);
        RedisManager.setString(token, gson.toJson(appSession));
        return appSession;
    }

    public static void setToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            tokenVector.add(token);
        }
    }

    public static void invalidate(AppSession session) {
        if (session != null) {
            tokenVector.remove(session.getToken());
            RedisManager.remove(session.getToken());
        }
    }
}
