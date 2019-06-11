package fr.rtone.mqtt.messages.status

import com.google.gson.annotations.SerializedName
import fr.rtone.mqtt.messages.MessageReceiver

data class MessageStatus(
	@SerializedName("hw_ver") var hw_ver: String,
	@SerializedName("fw_ver") var fw_ver: String
) : MessageReceiver()