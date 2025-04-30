/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DIContainerExceptionTest {

    @Test
    public void testDIContainerExceptionString() {
        DIContainerException e = new DIContainerException("Test Message");
        assertEquals("Test Message", e.getMessage());
    }

    @Test
    public void testDIContainerExceptionThrowable() {
        Exception cause = new Exception("Test Message");
        DIContainerException e = new DIContainerException(cause);
        assertEquals("Test Message", e.getCause().getMessage());
    }

    @Test
    public void testDIContainerExceptionStringThrowable() {
        Exception cause = new Exception("Test Message1");
        DIContainerException e = new DIContainerException("Test Message2", cause);
        assertEquals("Test Message2", e.getMessage());
        assertEquals("Test Message1", e.getCause().getMessage());
    }
}
