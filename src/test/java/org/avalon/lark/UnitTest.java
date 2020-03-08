package org.avalon.lark;

import org.avalon.lark.common.consts.SystemConsts;
import org.avalon.lark.common.docgen.TemplateMessageProvider;
import org.avalon.lark.common.utility.MappingUtils;
import org.avalon.lark.systemadmin.entity.User;
import org.junit.Test;


public class UnitTest {

    @Test
    public void Test2() throws Exception {
        System.out.println(MappingUtils.getMap(new User()));
    }

    @Test
    public void test1() {
        String serverPath = TemplateMessageProvider.class.getResource("").toString().replaceAll("%20", " ").substring(5);
        System.out.println(serverPath);
        System.out.println(SystemConsts.getClassPath());
    }


}
