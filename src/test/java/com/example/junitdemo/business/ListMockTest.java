package com.example.junitdemo.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mock = Mockito.mock(List.class);

    @Test
    public void size_basic() {
        Mockito.when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValues() {
        Mockito.when(mock.size()).thenReturn(5).thenReturn(10);
        Assertions.assertAll(
                () -> assertEquals(5, mock.size()),
                () -> assertEquals(10, mock.size())
        );
    }

    @Test
    public void returnWithParameters() {
        Mockito.when(mock.get(0)).thenReturn("Himani");
        assertEquals("Himani", mock.get(0));
        assertEquals(null, mock.get(1));
    }

    @Test
    @Disabled
    public void returnWithGenericParameters() {
        Mockito.when(mock.get(anyInt())).thenReturn("Himani");
        assertEquals("Himani", mock.get(0));
        assertEquals(null, mock.get(1));
    }

    @Test
    public void verificationBasics() {
        // SUT
        String value1 = mock.get(0);
        String value2 = mock.get(1);

        // Verify
        verify(mock).get(0);
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());

        // this method is never called on mock object
        verify(mock, never()).get(2);
    }

    @Test
    public void argumentCapturing() {
        //SUT
        mock.add("SomeString");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());
        assertEquals("SomeString", captor.getValue());
    }

    @Test
    public void multipleArgumentCapturing() {

        //SUT
        mock.add("SomeString1");
        mock.add("SomeString2");

        //Verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mock, times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();

        assertEquals("SomeString1", allValues.get(0));
        assertEquals("SomeString2", allValues.get(1));

    }

    @Test
    public void mocking() {
        ArrayList arrayListMock = mock(ArrayList.class);
        System.out.println(arrayListMock.get(0));//null
        System.out.println(arrayListMock.size());//0
        arrayListMock.add("Test");
        arrayListMock.add("Test2");
        System.out.println(arrayListMock.size());//0
        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size());//5
    }

    @Test
    public void spying() {
        ArrayList arrayListSpy = spy(ArrayList.class);
        arrayListSpy.add("Test0");
        System.out.println(arrayListSpy.get(0));//Test0
        System.out.println(arrayListSpy.size());//1
        arrayListSpy.add("Test");
        arrayListSpy.add("Test2");
        System.out.println(arrayListSpy.size());//3

        when(arrayListSpy.size()).thenReturn(5);
        System.out.println(arrayListSpy.size());//5

        arrayListSpy.add("Test4");
        System.out.println(arrayListSpy.size());//5

        verify(arrayListSpy).add("Test4");
    }
    /*
    * Difference between a Spy and a Mock

When Mockito creates a mock – it does so from the Class of a Type, not from an actual instance.
*  The mock simply creates a bare-bones shell instance of the Class,
* entirely instrumented to track interactions with it.
* On the other hand, the spy will wrap an existing instance.
*  It will still behave in the same way as the normal instance –
*  the only difference is that it will also be instrumented to track all the interactions with it.*/
}
