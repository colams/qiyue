package com.foxconn.sw.data.validate.validator;


import com.foxconn.sw.data.validate.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {
    private Class<? extends Enum<?>> enumClass;
    private boolean containsZero;

    @Override
    public void initialize(EnumValue enumValue) {
        this.enumClass = enumValue.enumClass();
        this.containsZero = enumValue.containsZero();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Enum[] enumConstants = enumClass.getEnumConstants();
        for (Enum item : enumConstants) {
            if (item.name().equalsIgnoreCase(value.toString())) {
                return true;
            }
            if (value instanceof Integer) {
                int intValue = (int) value;
                if (intValue < 0) {
                    return false;
                }

                if (intValue == 0 && containsZero) {
                    return true;
                }

                if (item.ordinal() + 1 == intValue) {
                    return true;
                }
            }
        }
        return false;
    }
}
