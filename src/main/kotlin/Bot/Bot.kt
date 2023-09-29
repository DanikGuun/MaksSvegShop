package Bot

import Controler.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class Bot(val controller: Controller): TelegramLongPollingBot(){
    override fun onUpdateReceived(update: Update) {
        
    }

    fun sendMessage(chatID: String, text: String, buttons: ArrayList<List<InlineKeyboardButton>>? = null){
        val message = SendMessage()
        message.enableMarkdown(true)
        message.setChatId(chatID)
        message.text = text
        if(buttons != null){
            val inlineKeyboardMarkup = InlineKeyboardMarkup()
            inlineKeyboardMarkup.keyboard = buttons
            message.replyMarkup = inlineKeyboardMarkup
        }
        execute(message)
    }

    //список со строчками 0 - все строчки, 1 - все кнопки, 2 - текст и колбек кнопки
    fun createButtonsRowList(buttonsData: ArrayList<ArrayList<ArrayList<String>>>): ArrayList<List<InlineKeyboardButton>>{
        val buttonsArray = ArrayList<List<InlineKeyboardButton>>()
        for(row in buttonsData){
            buttonsArray.add(createRowOfButtons(row))
        }
        return buttonsArray
    }

    //делает строчку
    fun createRowOfButtons(buttonsData: ArrayList<ArrayList<String>>): List<InlineKeyboardButton>{
        val row = ArrayList<InlineKeyboardButton>()
        for(button in buttonsData){
            row.add(createInlineButton(button[0], button[1]))
        }
        return row.toList()
    }
    //кнопку делает епт
    fun createInlineButton(text: String, callbackData: String): InlineKeyboardButton{
        val button = InlineKeyboardButton()
        button.text = text
        button.callbackData = callbackData
        return button
    }


    override fun getBotUsername() = "svegshop_bot"
    override fun getBotToken() = "5929869980:AAE4GvBGulph377qbdpKc6xF75M069g-9ec"
}