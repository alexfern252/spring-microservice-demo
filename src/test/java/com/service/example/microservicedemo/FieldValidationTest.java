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
     * Expected: {id.not-null}
     */
    @Test
    public void idIsNull() {
        final CompanyDetailsRequest payload = new CompanyDetailsRequest(null, "Test", "9999999999", "India");
        assertEquals("{id.not-null}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with empty id
     * Expected: {id.not-null}
     */
    @Test
    public void idIsEmpty() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("", "Test", "9999999999", "India");
        assertEquals("{id.not-null}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with 14 digit id
     * Expected: {id.size}
     */
    @Test
    public void idIsFourteenDigit() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("C0000000000123", "Test", "9999999999",  "India");
        assertEquals("{id.size}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with null mobile
     * Expected: {mobile.not-null}
     */
    @Test
    public void mobileIsNull() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("C12345", "Test", null,  "India");
        assertEquals("{mobile.not-null}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with 4 digit mobile
     * Expected: {mobile.size}
     */
    @Test
    public void mobileIsFourDigit() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("C12345", "Test", "9999",  "India");
        assertEquals("{mobile.size}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with 14 digit mobile
     * Expected: {mobile.size}
     */
    @Test
    public void mobileIsFourteenDigit() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("C12345", "Test", "99999999999999",  "India");
        assertEquals("{mobile.size}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with character mobile
     * Expected: {mobile.pattern}
     */
    @Test
    public void mobileIsNotPositiveDigits() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("C12345", "Test", "ABCD9999999",  "India");
        assertEquals("{mobile.pattern}", TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }

    /**
     * Test with proper id
     * Expected: status 200
     */
    @Test
    public void successPayload() {
        CompanyDetailsRequest payload = new CompanyDetailsRequest("9999999999", "Test", "9999999999", "India");
        assertNull(TestUtils.getFieldErrorMessageKey(payload, localValidatorFactory));
    }
}
