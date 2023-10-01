import Bot.Bot
import Controler.Controller
import Json.JsonHandler
import YMoney.YMoneyHandler
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main(args: Array<String>) {
    val jsonHandler = JsonHandler()
    val yMoneyHandler = YMoneyHandler()
    val controller = Controller(yMoneyHandler, jsonHandler)
    val bot = Bot(controller)

    val botAPI = TelegramBotsApi(DefaultBotSession::class.java)
    botAPI.registerBot(bot)
}