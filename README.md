import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("rawtypes")
public class BaseSetterGetterTest {
    
    private final Class[] clazz = new Class[]{ 
        /* YOUR POJO CLASS HERE */
        UserResponse.class, ProductEntity.class, ProductDTO.class
    };

    @SuppressWarnings("unchecked")
    public void testSetterGetter(Class[] clazz) {

        for (Class aClass : clazz) {
            Object instance;
            try {
                instance = aClass.getDeclaredConstructor().newInstance();
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field f : declaredFields) {
                    try {
                        String fieldName = f.getName();
                        if (fieldName.equals("serialVersionUID")) {
                            continue;
                        }
                        String name = StringUtils.capitalize(fieldName);
                        String getter = "get" + name;
                        String setter = "set" + name;
                        Object value = null;
                        Class<?> fType = f.getType();
                        if (fType.isAssignableFrom(String.class)) {
                            value = "str";
                        } else if (fType.isAssignableFrom(Long.class) || fType.isAssignableFrom(long.class)) {
                            value = Long.valueOf("1");
                        } else if (fType.isAssignableFrom(Integer.class) || fType.isAssignableFrom(int.class)) {
                            value = Integer.valueOf("1");
                        } else if (fType.isAssignableFrom(Double.class) || fType.isAssignableFrom(double.class)) {
                            value = Double.valueOf("1.0");
                        } else if (fType.isAssignableFrom(Date.class)) {
                            value = new Date();
                        } else if (fType.isAssignableFrom(Timestamp.class)) {
                            value = new Timestamp(new Date().getTime());
                        } else if (fType.isAssignableFrom(Boolean.class)) {
                            value = Boolean.FALSE;
                        } else if (fType.isAssignableFrom(boolean.class)) {
                            value = Boolean.FALSE;
                            getter = "is" + name;
                        } // end the rest Type you expect, such as Collection
                        Method getterMethod = aClass.getMethod(getter);
                        Method setterMethod = aClass.getMethod(setter, getterMethod.getReturnType());
                        setterMethod.invoke(instance, value);
                        Object result = getterMethod.invoke(instance);
                        assertThat(result).as("in class %s and fields %s ", aClass.getName(), fieldName).isEqualTo(value);
                    } catch (IllegalArgumentException e) {
                        System.out.println("aClass = " + aClass);
                        e.printStackTrace();
                        throw e;
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                // toString test
                assertThat(instance.toString()).as("toString in class %s ", aClass.getName()).isNotBlank();

                // equals n hashCode test
                Set<Object> set = Set.of(instance);
                assertThat(set.contains(instance)).as("equals n hashCode in class %s ", aClass.getName()).isTrue();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
