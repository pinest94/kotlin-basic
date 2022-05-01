package chap2

class Stock(
    val id: Long,
    val name: String,
    var previousMarketPrice: Long = 0,
    var marketPrice: Long = 0,
    var highPrice: Long = 0,
    var lowPrice: Long = 0,
    val marketType: MarketType,
    var stockValue: Long = 0,
    var sellValue: Long = 0,
    var buyValue: Long = 0,
    var gapPrice: Long = 0,
    var gapRate: Double = 0.0
) {
    fun order(orderPrice: Long, orderType: OrderType) {
        previousMarketPrice = marketPrice
        marketPrice = orderPrice
        if(highPrice < orderPrice) highPrice = orderPrice
        if(lowPrice > orderPrice) lowPrice = orderPrice
        stockValue++
        calculateGapValue()
        when(orderType) {
            OrderType.BUY -> {
                buyValue++
                println("매수 주문이 완료되었습니다. $orderPrice KRW")
            }
            OrderType.SELL -> {
                sellValue++
                println("매도 주문이 완료되었습니다. $orderPrice KRW")
            }
        }

    }

    private fun calculateGapValue() {
        if(previousMarketPrice == 0L) {
            println("이전 데이터가 없습니다.")
        } else {
            if(marketPrice >= previousMarketPrice) {
                gapPrice = marketPrice - previousMarketPrice
                gapRate = (gapPrice.toDouble() / previousMarketPrice.toDouble())
                println("▲ $gapPrice  ${String.format("%.2f", gapRate)}%")
            } else {
                gapPrice = previousMarketPrice - marketPrice
                gapRate = (gapPrice.toDouble() / previousMarketPrice.toDouble())
                println("▼ $gapPrice  -${String.format("%.2f", gapRate)}%")
            }
        }
    }
}

enum class MarketType {
    NYSE, NASDAQ, KOSPI, KOSDAQ
}

enum class OrderType {
    BUY, SELL
}