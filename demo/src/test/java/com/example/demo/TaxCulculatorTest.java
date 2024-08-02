package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class TaxCulculatorTest {
//    @Mock
//    TaxResolver mock;

    @Test
    void testGetPriceWithText(){
        TaxResolver mock = Mockito.mock(TaxResolver.class); // второй способ создания мок
//        when(mock.getCurrentTax()).thenReturn(0.2);
        doReturn(0.2).when(mock).getCurrentTax();
        TaxCulculator taxCulculator = new TaxCulculator(mock);
        Assertions.assertEquals(120.0, taxCulculator.getPriceWithTax(100.0), 0.0001);

    }

}