package chap2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StockTest {

    @Test
    fun stockCreationTest() {
        val stock = Stock(
            id = 1L,
            name = "TSLA",
            marketType = MarketType.NASDAQ
        )

        assertEquals("TSLA", stock.name)
        assertEquals(0, stock.marketPrice)
    }

    @Test
    fun orderTest() {
        val stock = Stock(
            id = 1L,
            name = "TSLA",
            marketType = MarketType.NASDAQ
        )

        stock.order(1_000_000, OrderType.BUY)
        stock.order(1_200_000, OrderType.BUY)
        stock.order(1_300_000, OrderType.BUY)
        stock.order(1_500_000, OrderType.BUY)
        stock.order(1_200_000, OrderType.SELL)
    }
}