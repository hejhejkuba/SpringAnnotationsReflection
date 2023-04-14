package firstdemoapp.firstapp.Names;


import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class NamesService {
    private Names names = new Names();
    public String PostName(String value)
    {
        Field name = null;
        try {
            name = Names.class.getDeclaredField(value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        MyTarget nameAnnotation = name.getAnnotation(MyTarget.class);
        String nameValue = nameAnnotation.value();
        return nameValue;
    }
}
