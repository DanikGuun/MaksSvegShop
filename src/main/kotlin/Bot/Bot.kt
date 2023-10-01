package Bot

import Controler.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.File
import org.telegram.telegrambots.meta.api.objects.File as file
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.media.InputMedia
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.lang.Exception

class Bot(val controller: Controller): TelegramLongPollingBot(){
    override fun onUpdateReceived(update: Update) {
        try{
        val chatID = update.message.chatId.toString()
        val sendmulti = SendMediaGroup()
        sendmulti.chatId = chatID

        val photos = ArrayList<InputMedia>()

        val listPaths = listOf(
            "src/main/resources/images/1.jpeg",
            "src/main/resources/images/2.jpeg",
            "src/main/resources/images/3.jpeg",
            "src/main/resources/images/4.jpeg",
        )

        for(path in listPaths){
            val inputPhoto = InputMediaPhoto()
            inputPhoto.setMedia(java.io.File(path), path)
            inputPhoto.caption = path
            photos.add(inputPhoto)
        }

        sendmulti.medias = photos
        execute(sendmulti)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun sendMultiplePhotos(chatID: Long, photos: List<InputFile>) {
        val sendPhoto = SendPhoto()
        sendPhoto.chatId = chatID.toString()

        for (photo in photos) {
            sendPhoto.photo = photo
            try {
                execute(sendPhoto)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
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