package org.avalon.lark.test;

import org.avalon.lark.common.base.BasePo;
import org.avalon.lark.common.database.annotation.Id;

public class TestPo extends BasePo {
    @Override
    public String getTableName() {
        return "TEST";
    }
    @Override
    public boolean needAuditColumns() {
        return true;
    }
    private int age;
    @Id
    private String gender;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
