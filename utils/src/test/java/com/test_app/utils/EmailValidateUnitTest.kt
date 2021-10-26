package com.test_app.utils

import org.junit.Test
import org.junit.Assert.*


class EmailValidateUnitTest {
    @Test
    fun emailValidateTest_Is_True(){
        assertTrue(emailValidate("test@mail.ru"))
    }
    @Test
    fun emailValidateTest_Is_FalseDomain(){
         assertFalse(emailValidate("test@mail"))
    }
    @Test
    fun emailValidateTest_Is_Blank(){
         assertNull(emailValidate(""))
    }
}