package com.service.example.microservicedemo;

import com.service.example.microservicedemo.Model.CompanyDetailsRequest;
import com.service.example.microservicedemo.Util.TestUtils;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class FieldValidationTest {

    private LocalValidatorFactoryBean localValidatorFactory;

    @Before
    public void setUp() {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();

    }

    /**
     * Test with null id
     * Expected: id is required
     */
    @Test
    public void idIsNull() {
        final CompanyDetailsRequest payload = new CompanyDetailsRequest(null, "Test", "India");
        assertEquals("id is required", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with empty id
     * Expected: id should be between 2 to 10 characters
     */
    @Test
    public void idIsEmpty() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("", "Test", "India");
        assertEquals("id should be between 2 to 10 characters", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with 14 digit id
     * Expected: id should be between 2 to 10 characters
     */
    @Test
    public void idIsFourteenDigit() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("C0000000000123", "Test", "India");
        assertEquals("id should be between 2 to 10 characters", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with proper id
     * Expected: status 200
     */
    @Test
    public void successPayload() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("9999999999", "Test", "India");
        assertNull(TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }
}
