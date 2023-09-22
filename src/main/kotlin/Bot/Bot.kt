package Bot

import Controler.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

class Bot(val controller: Controller): TelegramLongPollingBot(){
    override fun onUpdateReceived(update: Update) {
        val chatID = update.message.chatId
        sendMessage("aspdgkfh", chatID)
    }

    fun sendMessage(text: String, chatID: Long){
        val message = SendMessage()
        message.setChatId(chatID)
        message.text = text
        execute(message)
    }

    override fun getBotUsername() = "svegshop_bot"
    override fun getBotToken() = "5929869980:AAE4GvBGulph377qbdpKc6xF75M069g-9ec"
}