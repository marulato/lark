package org.avalon.lark.common.utility;

import org.avalon.lark.common.base.AppContext;
import org.avalon.lark.common.consts.SystemConsts;
import javax.servlet.http.HttpServletRequest;

public class AppUtils {

    public static AppContext getAppContext() {
        AppContext context = getAppContext(ServletUtils.getCurrentHttpServletRequest());
        if (context == null) {
            context = AppContext.threadContext.get();
        }
        return context;
    }

    public static AppContext getAppContext(HttpServletRequest request) {
        if (request != null) {
            return (AppContext) request.getSession().getAttribute(SystemConsts.APP_CONTEXT_NAME_ATTR);
        }
        return null;
    }

    public static void setAppContext(HttpServletRequest request, AppContext appCtx) {
        if (request != null && appCtx != null) {
            request.getSession().setAttribute(SystemConsts.APP_CONTEXT_NAME_ATTR, appCtx);
            setThreadContext(appCtx);
        }
    }

    public static void setThreadContext(AppContext appContext) {
        AppContext.threadContext.remove();
        AppContext.threadContext.set(appContext);
    }


}
