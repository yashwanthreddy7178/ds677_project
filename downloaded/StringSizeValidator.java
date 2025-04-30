package cn.com.zdht.pavilion.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Chenjazz
 * 2017-03-22
 */
public class StringSizeValidator implements ConstraintValidator<StringSize, String> {

    private StringSize stringSize;

    @Override
    public void initialize(StringSize constraintAnnotation) {
        stringSize = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //null
        if (value == null) {
            if (stringSize.allowNull()) {
                return true;
            } else {
                return false;
            }
        }

        //length
        List<Integer> integerList = new ArrayList<>();
        for (int i : stringSize.lengths()) {
            integerList.add(i);
        }
        return integerList.contains(value.length());
    }
}
