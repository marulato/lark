package org.avalon.lark.common.consts;

public final class SystemConsts {

    private SystemConsts(){}

    public static final String APP_CONTEXT_NAME_ATTR    = "LarkWebContext";
    public static final String SYS_TEMP_DIR             = System.getProperty("java.io.tmpdir");
    public static final String SYS_FILE_SEPARATOR       = System.getProperty("file..separator");

    public static String getClassPath() {
        String currentPath = SystemConsts.class.getResource("").getPath().replaceAll("%20", " ");
        return currentPath.substring(1, currentPath.lastIndexOf("classes") + 8);
    }
}
