package com.demo.core.validation.validator.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.demo.core.validation.validator.AbstractValidator;
import com.demo.core.validation.validator.ValidateError;
import org.apache.commons.beanutils.BeanUtils;

public class NotNullValidator extends AbstractValidator {
    private static final String ERROR_MSG = "msg.error.validate.notnull";
    private List<String> fields;

    public NotNullValidator() {
    }

    public List<String> getFields() {
        return this.fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<ValidateError> validate(Object bean) {
        List<ValidateError> result = new ArrayList();
        Iterator var3 = this.fields.iterator();

        while(var3.hasNext()) {
            String field = (String)var3.next();

            try {
                String label = this.getLabel(bean, field);
                if (BeanUtils.getProperty(bean, field) == null) {
                    result.add(ValidateError.generateError(bean, field, "msg.error.validate.notnull", new Object[]{label}));
                }
            } catch (Exception var6) {
                var6.printStackTrace();
                result.add(ValidateError.generateError(bean, field, var6.getMessage()));
            }
        }

        return result;
    }
}
