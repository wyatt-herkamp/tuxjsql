import dev.tuxjsql.basic.utils.BasicUtils;
import dev.tuxjsql.core.TuxJSQL;
import test.cool.EnumTest;

public class TestEnum {
    public static void main(String[] args) {
        String enumString = BasicUtils.enumToString(EnumTest.TEST);
        System.out.println(enumString);
        System.out.println(((EnumTest) BasicUtils.getAsEnum(enumString, TestEnum.class.getClassLoader())).name());
        System.out.println(TuxJSQL.getLogger().getClass().getName());
    }
}
