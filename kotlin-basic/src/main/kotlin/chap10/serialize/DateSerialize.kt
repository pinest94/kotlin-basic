package chap10.serialize

import java.text.SimpleDateFormat
import java.util.Date

class DateSerialize: ValueSerializer<Date> {
    private val dateFormat = SimpleDateFormat("dd-mm-yyyy")
    override fun toJsonValue(value: Date): Any? =
        dateFormat.format(value)

    override fun fromJsonValue(jsonValue: Any?): Date =
        dateFormat.parse(jsonValue as String)

}
