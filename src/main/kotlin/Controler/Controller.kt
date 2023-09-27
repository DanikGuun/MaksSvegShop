package Controler

import Json.JsonHandler
import YMoney.YMoneyHandler

class Controller(val moneyHandler: YMoneyHandler, val jsonHandler: JsonHandler){
    val adminID = arrayOf("")
    fun processMessage(text: String, chatID: Long): String{
        return ""
    }
}