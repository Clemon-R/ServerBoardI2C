package fr.rtone.mqtt

import com.google.gson.Gson
import fr.rtone.mqtt.messages.status.MessageStatus

class MqttClientHandler {
	companion object{
		const val TAG = "ClientHandler"

		private fun parseBytesToString(bytes: ByteArray) : String{
			val result = StringBuilder()
			for (i in 0 until bytes.size - 1){
				result.append(bytes[i].toChar())
			}
			return result.toString()
		}
	}

	private var statusCallBack: ((data: MessageStatus) -> Unit)? = null

	fun parsePacket(topic: String, bytes: ByteArray) : Boolean {
		val msg = parseBytesToString(bytes)
		val params = topic.split("/")
		when (params[params.size - 1]){
			"data" -> {
				val packet = Gson().fromJson(msg, MessageStatus::class.java)
				statusCallBack?.invoke(packet)
			}
		}
		return true
	}

	fun setStatusCallBack(callback: (data: MessageStatus) -> Unit){
		statusCallBack = callback
	}
}