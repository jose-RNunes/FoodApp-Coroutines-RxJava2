package com.foodapp

import com.foodapp.presentation.ui.extensions.*
import org.junit.Assert.*
import org.junit.Test

class StringExtensionsTest {


    @Test
    fun `Test split extension`() {
        assertEquals(
            "",
            "".getFirstTextBeforeSpace()
        )
        assertEquals(
            "Caminhoneiro",
            "Caminhoneiro".getFirstTextBeforeSpace()
        )
        assertEquals(
            "Caminhoneiro",
            "Caminhoneiro Teste".getFirstTextBeforeSpace()
        )
        assertEquals(
            "Caminhoneiro",
            "Caminhoneiro Teste Teste2".getFirstTextBeforeSpace()
        )
        assertEquals(
            "Caminhoneiro",
            "Caminhoneiro Teste Teste Teste Teste".getFirstTextBeforeSpace()
        )
    }

    @Test
    fun `Test safeSubstring() extension`() {
        assertEquals("", "".safeSubstring(0, 2))

        assertEquals("", "txt".safeSubstring(0, 5))

        assertEquals("34", "34999855676".safeSubstring(0, 2))

        assertEquals("999855676", "34999855676".safeSubstring(2, 11))

        assertEquals("", "34999855676".safeSubstring(2, 20))

        assertEquals("", "34999855676".safeSubstring(-2, 20))
    }

    @Test
    fun `Test getNextTextAfterSpace() extension`() {
        assertEquals("Rodrigues Nunes Neto", "José Rodrigues Nunes Neto".getNextTextAfterSpace())

        assertEquals("", "José".getNextTextAfterSpace())
    }

    @Test
    fun `Test hasNextTextAfterSpace() extension`() {
        assertTrue("José Rodrigues".hasNextTextAfterSpace())

        assertFalse("José".hasNextTextAfterSpace())

        assertFalse("   José".hasNextTextAfterSpace())

        assertTrue("   José Rodrigues".hasNextTextAfterSpace())

        assertFalse("".hasNextTextAfterSpace())
    }

   @Test
   fun teste(){

       assertEquals("Jose Rodrigues", "Jose Rodrigues Nunes Neto".getFirstAndLastName())

       assertEquals("Jose", "Jose".getFirstAndLastName())

   }
}